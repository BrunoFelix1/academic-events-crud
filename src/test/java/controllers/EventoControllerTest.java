package controllers;

import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.EventoDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoControllerTest {
    private EventoController eventoController;
    private EventoDAO eventoDAO;

    @BeforeEach
    void setUp() {
        eventoDAO = mock(EventoDAO.class);
        eventoController = new EventoController();
        eventoController.eventoDAO = eventoDAO;
    }

    @Test
    void testAdicionarEvento_Sucesso() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");

        when(eventoDAO.insertEvento(evento)).thenReturn(true);

        boolean resultado = eventoController.adicionarEvento(evento);
        assertTrue(resultado);
        verify(eventoDAO).insertEvento(evento);
    }

    @Test
    void testAdicionarEvento_DadosInvalidos() {
        Evento evento = new Evento();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            eventoController.adicionarEvento(evento)
        );
        assertEquals("Título do evento é obrigatório", exception.getMessage());
    }

    @Test
    void testAtualizarEvento_Sucesso() {
        Evento eventoExistente = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        eventoExistente.setId(1L);

        Evento eventoAtualizado = new Evento("Evento Atualizado", "Local B", "14:00", "Descrição Atualizada");

        when(eventoDAO.selectEvento(1L)).thenReturn(eventoExistente);
        when(eventoDAO.updateEvento(any())).thenReturn(true);

        boolean resultado = eventoController.atualizarEvento(1L, eventoAtualizado);

        assertTrue(resultado);
        verify(eventoDAO).updateEvento(any());
    }

    @Test
    void testAtualizarEvento_NaoEncontrado() {
        when(eventoDAO.selectEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoController.atualizarEvento(1L, new Evento());
        });

        assertEquals("Evento não encontrado", exception.getMessage());
    }

    @Test
    void testDeletarEvento_Sucesso() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        evento.setId(1L);

        when(eventoDAO.selectEvento(1L)).thenReturn(evento);
        when(eventoDAO.deleteEvento(1L)).thenReturn(true);

        boolean resultado = eventoController.deletarEvento(1L);

        assertTrue(resultado);
        verify(eventoDAO).deleteEvento(1L);
    }

    @Test
    void testDeletarEvento_NaoEncontrado() {
        when(eventoDAO.selectEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoController.deletarEvento(1L);
        });

        assertEquals("Evento não encontrado", exception.getMessage());
    }

    @Test
    void testListarTodosEventos() {
        List<Evento> eventos = Arrays.asList(
                new Evento("Evento Teste 1", "Local A", "10:00", "Descrição 1"),
                new Evento("Evento Teste 2", "Local B", "11:00", "Descrição 2")
        );

        when(eventoDAO.selectAllEventos()).thenReturn(eventos);

        List<Evento> resultado = eventoController.listarTodosEventos();

        assertEquals(2, resultado.size());
        verify(eventoDAO).selectAllEventos();
    }

    @Test
    void testBuscarEventoPorId_Sucesso() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        evento.setId(1L);

        when(eventoDAO.selectEvento(1L)).thenReturn(evento);

        Evento resultado = eventoController.buscarEventoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(eventoDAO).selectEvento(1L);
    }

    @Test
    void testBuscarEventoPorId_NaoEncontrado() {
        when(eventoDAO.selectEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoController.buscarEventoPorId(1L);
        });

        assertEquals("Evento não encontrado", exception.getMessage());
    }

    @Test
    void testBuscarEventoPorNome_Sucesso() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");

        when(eventoDAO.selectAllEventos()).thenReturn(Arrays.asList(evento));

        Evento resultado = eventoController.buscarEventoPorNome("Evento Teste");

        assertNotNull(resultado);
        assertEquals("Evento Teste", resultado.getTitulo());
        verify(eventoDAO).selectAllEventos();
    }

    @Test
    void testBuscarEventoPorNome_NaoEncontrado() {
        when(eventoDAO.selectAllEventos()).thenReturn(Arrays.asList());

        Evento resultado = eventoController.buscarEventoPorNome("Evento Inexistente");

        assertNull(resultado);
        verify(eventoDAO).selectAllEventos();
    }
}