package service;

import model.Contato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContatoService {
    private final List<Contato> contatos = new ArrayList<>();
    private int nextId = 1; // Para gerar IDs automaticamente

    public void adicionarContato(Contato contato) {
        contato.setId(nextId++);
        contatos.add(contato);
    }

    public List<Contato> listaContatos() {
        return contatos.stream()
                .sorted((c1, c2) -> c1.getNome().compareToIgnoreCase(c2.getNome()))
                .collect(Collectors.toList());
    }

    public void removerContato(int id) {
        contatos.removeIf(contato -> contato.getId() == id);
    }

    public void atualizarContato(int id, String nome, String telefone, String email, LocalDate dataNascimento) {
        for (Contato contato : contatos) {
            if (contato.getId() == id) {
                contato.setNome(nome);
                contato.setTelefone(telefone);
                contato.setEmail(email);
                contato.setDataNascimento(dataNascimento);
                break;
            }
        }
    }

    public List<Contato> buscarContato(String prefix) {
        return contatos.stream()
                .filter(contato -> contato.getNome().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Contato> listarAniversarios(int mes) {
        return contatos.stream()
                .filter(contato -> contato.getDataNascimento().getMonthValue() == mes)
                .sorted((c1, c2) -> c1.getDataNascimento().compareTo(c2.getDataNascimento()))
                .collect(Collectors.toList());
    }
}
