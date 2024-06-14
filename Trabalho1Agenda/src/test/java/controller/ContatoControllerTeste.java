package controller;

import model.Contato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ContatoService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContatoControllerTeste {
    private ContatoController contatoController;
    private ContatoService contatoService;

    @BeforeEach
    public void setUp() {
        contatoController = new ContatoController();
        contatoService = new ContatoService();
    }

    @Test
    public void testAddContato() {
        contatoController.addContato("John Doe", "123456789", "john@example.com", LocalDate.of(1990, 1, 1));
        List<Contato> contatos = contatoController.listarContato();

        assertEquals(1, contatos.size());
        assertEquals("John Doe", contatos.get(0).getNome());
    }

    @Test
    public void testRemoverContato() {
        contatoController.addContato("John Doe", "123456789", "john@example.com", LocalDate.of(1990, 1, 1));
        List<Contato> contatos = contatoController.listarContato();
        int id = contatos.get(0).getId(); // Obtendo o ID gerado automaticamente

        contatoController.removerContato(id);
        contatos = contatoController.listarContato();

        assertEquals(0, contatos.size());
    }

    @Test
    public void testAtualizarContato() {
        contatoController.addContato("John Doe", "123456789", "john@example.com", LocalDate.of(1990, 1, 1));
        List<Contato> contatos = contatoController.listarContato();
        int id = contatos.get(0).getId(); // Obtendo o ID gerado automaticamente

        contatoController.atualizarContato(id, "Jane Doe", "987654321", "jane@example.com", LocalDate.of(1991, 2, 2));
        Contato atualizarContato = contatoController.listarContato().get(0);

        assertEquals("Jane Doe", atualizarContato.getNome());
        assertEquals("987654321", atualizarContato.getTelefone());
        assertEquals("jane@example.com", atualizarContato.getEmail());
        assertEquals(LocalDate.of(1991, 2, 2), atualizarContato.getDataNascimento());
    }

    @Test
    public void testBuscarContato() {
        contatoController.addContato("Alice", "123456789", "alice@example.com", LocalDate.of(1967, 3, 24));
        contatoController.addContato("Bob", "987654321", "bob@example.com", LocalDate.of(1999, 10, 31));
        List<Contato> resultado = contatoController.buscarContatoPorLetra("Al");

        assertEquals(1, resultado.size());
        assertEquals("Alice", resultado.get(0).getNome());
    }

    @Test
    public void testListarAniversarios() {
        Contato c1 = new Contato("Alice", "123456789", "alice@example.com", LocalDate.of(1990, 1, 1));
        Contato c2 = new Contato("Bob", "987654321", "bob@example.com", LocalDate.of(1991, 1, 2));
        contatoService.adicionarContato(c1);
        contatoService.adicionarContato(c2);

        List<Contato> resultado = contatoService.listarAniversarios(1);
        assertEquals(2, resultado.size());
        assertEquals("Alice", resultado.get(0).getNome());
        assertEquals("Bob", resultado.get(1).getNome());
    }
}
