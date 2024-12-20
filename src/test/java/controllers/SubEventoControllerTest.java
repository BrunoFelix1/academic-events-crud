package controllers;

import models.SubEvento;
import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.SubEventoDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubEventoControllerTest {
    private SubEventoController subEventoController;
    private SubEventoDAO subEventoDAO;
    private Evento mockEvento;

    @BeforeEach
    void setUp() {
        subEventoDAO = mock(SubEventoDAO.class);
        subEventoController = new SubEventoController();
        subEventoController.subEventoDAO = subEventoDAO;

        mockEvento = mock(Evento.class);
        when(mockEvento.getId()).thenReturn(1L);
    }

    @Test
    void testAdicionarSubEvento_Sucesso() {
        SubEvento subEvento = new SubEvento(mockEvento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");

        when(subEventoDAO.insertSubEvento(subEvento)).thenReturn(true);

        boolean resultado = subEventoController.adicionarSubEvento(subEvento);
        assertTrue(resultado);
        verify(subEventoDAO).insertSubEvento(subEvento);
    }

    @Test
    void testAdicionarSubEvento_DadosInvalidos() {
        SubEvento subEvento = new SubEvento();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            subEventoController.adicionarSubEvento(subEvento)
        );
        assertEquals("O nome do subevento é obrigatório", exception.getMessage());
    }

    @Test
    void testAtualizarSubEvento_Sucesso() {
        SubEvento subEventoExistente = new SubEvento(mockEvento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        subEventoExistente.setId(1L);

        SubEvento subEventoAtualizado = new SubEvento(mockEvento, "SubEvento Atualizado", "Local C", "15:00", "Descrição Atualizada");

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(subEventoExistente);
        when(subEventoDAO.updateSubEvento(any())).thenReturn(true);

        boolean resultado = subEventoController.atualizarSubEvento(1L, subEventoAtualizado);

        assertTrue(resultado);
        verify(subEventoDAO).updateSubEvento(any());
    }

    @Test
    void testAtualizarSubEvento_NaoEncontrado() {
        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.atualizarSubEvento(1L, new SubEvento());
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
    }

    @Test
    void testDeletarSubEvento_Sucesso() {
        SubEvento subEvento = new SubEvento(mockEvento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        subEvento.setId(1L);

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(subEvento);
        when(subEventoDAO.deleteSubEvento(1L)).thenReturn(true);

        boolean resultado = subEventoController.deletarSubEvento(1L);

        assertTrue(resultado);
        verify(subEventoDAO).deleteSubEvento(1L);
    }

    @Test
    void testDeletarSubEvento_NaoEncontrado() {
        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.deletarSubEvento(1L);
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
    }

    @Test
    void testListarTodosSubEventos() {
        List<SubEvento> subEventos = Arrays.asList(
                new SubEvento(mockEvento, "SubEvento Teste 1", "Local A", "10:00", "Descrição 1"),
                new SubEvento(mockEvento, "SubEvento Teste 2", "Local B", "11:00", "Descrição 2")
        );

        when(subEventoDAO.selectAllSubEventos()).thenReturn(subEventos);

        List<SubEvento> resultado = subEventoController.listarTodosSubEventos();

        assertEquals(2, resultado.size());
        verify(subEventoDAO).selectAllSubEventos();
    }

    @Test
    void testBuscarSubEventoPorId_Sucesso() {
        SubEvento subEvento = new SubEvento(mockEvento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        subEvento.setId(1L);

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(subEvento);

        SubEvento resultado = subEventoController.buscarSubEventoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(subEventoDAO).selectSubEvento(1L);
    }

    @Test
    void testBuscarSubEventoPorId_NaoEncontrado() {
        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.buscarSubEventoPorId(1L);
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
    }

    @Test
    void testBuscarSubEventoPorNome_Sucesso() {
        SubEvento subEvento = new SubEvento(mockEvento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");

        when(subEventoDAO.selectAllSubEventos()).thenReturn(Arrays.asList(subEvento));

        SubEvento resultado = subEventoController.buscarSubEventoPorNome("SubEvento Teste");

        assertNotNull(resultado);
        assertEquals("SubEvento Teste", resultado.getNome());
        verify(subEventoDAO).selectAllSubEventos();
    }

    @Test
    void testBuscarSubEventoPorNome_NaoEncontrado() {
        when(subEventoDAO.selectAllSubEventos()).thenReturn(Arrays.asList());

        SubEvento resultado = subEventoController.buscarSubEventoPorNome("SubEvento Inexistente");

        assertNull(resultado);
        verify(subEventoDAO).selectAllSubEventos();
    }
}