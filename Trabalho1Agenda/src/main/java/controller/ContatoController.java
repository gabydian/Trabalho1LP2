package controller;

import model.Contato;
import service.ContatoService;

import java.time.LocalDate;
import java.util.List;

public class ContatoController {
    private final ContatoService contatoService;

    public ContatoController() {
        this.contatoService = new ContatoService();
    }

    public void addContato(String nome, String telefone, String email, LocalDate dataNascimento) {
        Contato contato = new Contato(nome, telefone, email, dataNascimento);
        contatoService.adicionarContato(contato);
    }

    public List<Contato> listarContato() {
        return contatoService.listaContatos();
    }

    public void removerContato(int id) {
        contatoService.removerContato(id);
    }

    public void atualizarContato(int id, String nome, String telefone, String email, LocalDate dataNascimento) {
        contatoService.atualizarContato(id, nome, telefone, email, dataNascimento);
    }

    public List<Contato> buscarContato(String prefix) {
        return contatoService.buscarContato(prefix);
    }

    public List<Contato> listarAniversarios(int mes) {
        return contatoService.listarAniversarios(mes);
    }
}
