package service;

import model.Contato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContatoServiceTeste {
    private ContatoService contatoService;

    @BeforeEach
    public void setUp() {
        contatoService = new ContatoService();
    }

    @Test
    public void testAdicionarContato() {
        Contato c1 = new Contato("John Doe", "123456789", "john@example.com", LocalDate.of(1990, 1, 1));
        contatoService.adicionarContato(c1);
        List<Contato> contatos = contatoService.listaContatos();

        assertEquals(1, contatos.size());
        assertEquals("John Doe", contatos.get(0).getNome());
    }

    @Test
    public void testRemoverContato() {
        Contato c1 = new Contato("John Doe", "123456789", "john@example.com", LocalDate.of(1990, 1, 1));
        contatoService.adicionarContato(c1);
        List<Contato> contatos = contatoService.listaContatos();
        int id = contatos.get(0).getId(); // Obtendo o ID gerado automaticamente

        contatoService.removerContato(id);
        contatos = contatoService.listaContatos();

        assertEquals(0, contatos.size());
    }

    @Test
    public void testAtualizarContato() {
        Contato c1 = new Contato("John Doe", "123456789", "john@example.com", LocalDate.of(1990, 1, 1));
        contatoService.adicionarContato(c1);
        List<Contato> contatos = contatoService.listaContatos();
        int id = contatos.get(0).getId(); // Obtendo o ID gerado automaticamente

        contatoService.atualizarContato(id, "Jane Doe", "987654321", "jane@example.com", LocalDate.of(1991, 2, 2));
        Contato atualizarContato = contatoService.listaContatos().get(0);

        assertEquals("Jane Doe", atualizarContato.getNome());
        assertEquals("987654321", atualizarContato.getTelefone());
        assertEquals("jane@example.com", atualizarContato.getEmail());
        assertEquals(LocalDate.of(1991, 2, 2), atualizarContato.getDataNascimento());
    }

    @Test
    public void testBuscarContato() {
        Contato c1 = new Contato("Alice", "123456789", "alice@example.com", LocalDate.of(1967, 3, 24));
        Contato c2 = new Contato("Bob", "987654321", "bob@example.com", LocalDate.of(1999, 10, 31));
        contatoService.adicionarContato(c1);
        contatoService.adicionarContato(c2);

        List<Contato> resultado = contatoService.buscarContato("Al");

        assertEquals(1, resultado.size());
        assertEquals("Alice", resultado.get(0).getNome());
    }

    @Test
    public void testListarAniversarios() {
        Contato c1 = new Contato("Alice", "123456789", "alice@example.com", LocalDate.of(1990, 1, 1));
        Contato c2 = new Contato("Bob", "987654321", "bob@example.com", LocalDate.of(1991, 1, 2));
        contatoService.adicionarContato(c1);
        contatoService.adicionarContato(c2);

        List<Contato> resultado = contatoService.listarAniversarios(1); // Correção: passando o valor real do mês
        assertEquals(2, resultado.size());
        assertEquals("Alice", resultado.get(0).getNome());
        assertEquals("Bob", resultado.get(1).getNome());
    }
}
