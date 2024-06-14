package view;

import controller.ContatoController;
import model.Contato;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ContatoView {
    private static Scanner teclado = new Scanner(System.in);
    private static ContatoController contatoController = new ContatoController();

    public static void main(String[] args) {
        boolean rodar = true;

        while (rodar) {
            exibirMenu();
            try {
                int opcao = Integer.parseInt(teclado.nextLine());

                switch (opcao) {
                    case 1 -> adicionarContato();
                    case 2 -> listarContato();
                    case 3 -> removerContato();
                    case 4 -> atualizarContato();
                    case 5 -> buscarContatos();
                    case 6 -> listarAniversarios();
                    case 7 -> rodar = false;
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Agenda - Menu");
        System.out.println("1. Adicionar Contato");
        System.out.println("2. Listar Contatos");
        System.out.println("3. Remover Contato");
        System.out.println("4. Alterar Contato");
        System.out.println("5. Pesquisar Contatos por Letra");
        System.out.println("6. Listar Aniversariantes do Mês");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarContato() {
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Telefone: ");
        String telefone = teclado.nextLine();
        System.out.print("E-mail: ");
        String email = teclado.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(teclado.nextLine());

        contatoController.addContato(nome, telefone, email, dataNascimento);
        System.out.println("Contato adicionado com sucesso!");
    }

    private static void listarContato() {
        List<Contato> contatos = contatoController.listarContato();
        contatos.forEach(contato -> {
            System.out.println("ID: " + contato.getId());
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Telefone: " + contato.getTelefone());
            System.out.println("E-mail: " + contato.getEmail());
            System.out.println("Data de Nascimento: " + contato.getDataNascimento());
            System.out.println("-----------------------------");
        });
    }

    private static void removerContato() {
        System.out.print("ID do contato a remover: ");
        int id = Integer.parseInt(teclado.nextLine());
        contatoController.removerContato(id);
        System.out.println("Contato removido com sucesso!");
    }

    private static void atualizarContato() {
        System.out.print("Id do contato a alterar: ");
        int id = Integer.parseInt(teclado.nextLine());
        System.out.print("Novo Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = teclado.nextLine();
        System.out.print("Novo E-mail: ");
        String email = teclado.nextLine();
        System.out.print("Nova Data de Nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(teclado.nextLine());

        contatoController.atualizarContato(id, nome, telefone, email, dataNascimento);
        System.out.println("Contato atualizado com sucesso!");
    }

    private static void buscarContatos() {
        System.out.print("Letra para pesquisa: ");
        String letra = teclado.nextLine();
        List<Contato> contatos = contatoController.buscarContatoPorLetra(letra);
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado contendo a letra: " + letra);
        } else {
            contatos.forEach(contato -> {
                System.out.println("ID: " + contato.getId());
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Telefone: " + contato.getTelefone());
                System.out.println("E-mail: " + contato.getEmail());
                System.out.println("-----------------------------");
            });
        }
    }

    private static void listarAniversarios() {
        System.out.print("Mês (1-12): ");
        int mes = Integer.parseInt(teclado.nextLine());
        List<Contato> contatos = contatoController.listarAniversarios(mes);
        contatos.forEach(contato -> {
            System.out.println("ID: " + contato.getId());
            System.out.println("Nome: " + contato.getNome());
            System.out.println("Data de Nascimento: " + contato.getDataNascimento());
            System.out.println("-----------------------------");
        });
    }
}
