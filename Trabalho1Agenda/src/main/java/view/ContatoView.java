package view;

import controller.ContatoController;
import model.Contato;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ContatoView {
    private static final Scanner teclado = new Scanner(System.in);
    private static final ContatoController contatoController = new ContatoController();

    public static void main(String[] args) {
        boolean rodar = true;

        while (rodar) {
            System.out.println("Agenda - Menu");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Listar Contatos");
            System.out.println("3. Remover Contato");
            System.out.println("4. Alterar Contato");
            System.out.println("5. Pesquisar Contatos por Prefixo");
            System.out.println("6. Listar Aniversariantes do Mês");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = teclado.nextInt();
            teclado.nextLine();  // Consume newline

            switch (opcao) {
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    listarContato();
                    break;
                case 3:
                    removerContato();
                    break;
                case 4:
                    atualizarContato();
                    break;
                case 5:
                    buscarContato();
                    break;
                case 6:
                    listarAniversarios();
                    break;
                case 7:
                    rodar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarContato() {
        System.out.println("Nome: ");
        String nome = teclado.nextLine();
        System.out.println("Telefone: ");
        String telefone = teclado.nextLine();
        System.out.println("E-mail: ");
        String email = teclado.nextLine();
        System.out.println("Data de Nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(teclado.nextLine());

        contatoController.addContato(nome, telefone, email, dataNascimento);
        System.out.println("Contato adicionado com sucesso!");
    }

    private static void listarContato() {
        List<Contato> contatos = contatoController.listarContato();
        contatos.forEach(contato -> System.out.println(contato));
    }

    private static void removerContato() {
        System.out.println("ID do contato a remover: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        contatoController.removerContato(id);
        System.out.println("Contato removido com sucesso!!!");
    }

    private static void atualizarContato() {
        System.out.println("Id do contato a alterar: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Novo Nome: ");
        String nome = teclado.nextLine();
        System.out.println("Novo Telefone: ");
        String telefone = teclado.nextLine();
        System.out.println("Novo E-mail: ");
        String email = teclado.nextLine();
        System.out.println("Nova Data de Nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(teclado.nextLine());

        contatoController.atualizarContato(id, nome, telefone, email, dataNascimento);
        System.out.println("Contato atualizado com sucesso!");
    }

    private static void buscarContato() {
        System.out.println("Prefixo para pesquisa: ");
        String prefix = teclado.nextLine();

        List<Contato> contatos = contatoController.buscarContato(prefix);
        contatos.forEach(contato -> System.out.println(contato));
    }

    private static void listarAniversarios() {
        System.out.println("Mês (1-12): ");
        int mes = teclado.nextInt();
        teclado.nextLine();

        List<Contato> contatos = contatoController.listarAniversarios(mes);
        contatos.forEach(contato -> System.out.println(contato));
    }
}