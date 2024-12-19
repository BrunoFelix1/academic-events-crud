package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import controllers.SubEventoController;
import models.Evento;
import models.SubEvento;
import repositories.SubEventoDAO;

import java.util.Arrays;
import java.util.List;

class SubEventoControllerTest {

    @Mock
    private SubEventoDAO subEventoDAO;

    @InjectMocks
    private SubEventoController subEventoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAtualizarSubEventoNaoEncontrado() {
        Evento evento = new Evento();
        SubEvento subEventoAtualizado = new SubEvento(evento, "Mini-Workshop Avançado", "Sala 2", "10:30", "Tema avançado");

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.atualizarSubEvento(1L, subEventoAtualizado);
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
        verify(subEventoDAO, never()).updateSubEvento(any());
    }



    @Test
    void testListarTodosSubEventos() {
        Evento evento = new Evento();
        SubEvento subEvento1 = new SubEvento(evento, "Mini-Workshop", "Sala 1", "09:00", "Introdução ao tema");
        SubEvento subEvento2 = new SubEvento(evento, "Mini-Workshop Avançado", "Sala 2", "10:30", "Tema avançado");

        List<SubEvento> subEventosEsperados = Arrays.asList(subEvento1, subEvento2);
        when(subEventoDAO.selectAllSubEventos()).thenReturn(subEventosEsperados);

        List<SubEvento> subEventos = subEventoController.listarTodosSubEventos();

        assertNotNull(subEventos);
        assertEquals(2, subEventos.size());
        assertEquals("Mini-Workshop", subEventos.get(0).getNome());
        assertEquals("Mini-Workshop Avançado", subEventos.get(1).getNome());
        verify(subEventoDAO, times(1)).selectAllSubEventos();
    }

    @Test
    void testBuscarSubEventoPorId() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento(evento, "Mini-Workshop", "Sala 1", "09:00", "Introdução ao tema");
        subEvento.setId(1L);

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(subEvento);

        SubEvento subEventoBuscado = subEventoController.buscarSubEventoPorId(1L);

        assertNotNull(subEventoBuscado);
        assertEquals("Mini-Workshop", subEventoBuscado.getNome());
        assertEquals("Sala 1", subEventoBuscado.getLocal());
        verify(subEventoDAO, times(1)).selectSubEvento(1L);
    }

    @Test
    void testBuscarSubEventoPorIdNaoEncontrado() {
        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.buscarSubEventoPorId(1L);
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
        verify(subEventoDAO, times(1)).selectSubEvento(1L);
    }
}