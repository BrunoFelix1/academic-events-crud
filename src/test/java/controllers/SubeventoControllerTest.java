package controllers;

import models.SubEvento;
import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repositories.SubEventoDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubeventoControllerTest {

    @InjectMocks
    private SubEventoController subEventoController;

    @Mock
    private SubEventoDAO subEventoDAO;

    @Mock
    private Evento evento;

    private SubEvento subEvento;

    @BeforeEach
    public void setUp() {
        evento = mock(Evento.class);
        subEvento = new SubEvento();
        subEvento.setId(1L);
        subEvento.setNome("SubEvento Teste");
        subEvento.setLocal("Local Teste");
        subEvento.setHorario("10:00");
        subEvento.setDescricao("Descrição do SubEvento");
        subEvento.setEvento(evento);
    }

    @Test
    public void testAdicionarSubEvento_Sucesso() {
        when(subEventoDAO.insertSubEvento(subEvento)).thenReturn(true);

        boolean result = subEventoController.adicionarSubEvento(subEvento);

        assertTrue(result);
        verify(subEventoDAO, times(1)).insertSubEvento(subEvento);
    }

    @Test
    public void testAdicionarSubEvento_Falha() {
        when(subEventoDAO.insertSubEvento(subEvento)).thenReturn(false);

        boolean result = subEventoController.adicionarSubEvento(subEvento);

        assertFalse(result);
        verify(subEventoDAO, times(1)).insertSubEvento(subEvento);
    }

    @Test
    public void testAtualizarSubEvento_Sucesso() {
        SubEvento subEventoAtualizado = new SubEvento();
        subEventoAtualizado.setNome("SubEvento Atualizado");
        subEventoAtualizado.setLocal("Novo Local");
        subEventoAtualizado.setHorario("14:00");
        subEventoAtualizado.setDescricao("Nova Descrição");
        subEventoAtualizado.setEvento(evento);

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(subEvento);
        when(subEventoDAO.updateSubEvento(subEvento)).thenReturn(true);

        boolean result = subEventoController.atualizarSubEvento(1L, subEventoAtualizado);

        assertTrue(result);
        verify(subEventoDAO, times(1)).selectSubEvento(1L);
        verify(subEventoDAO, times(1)).updateSubEvento(subEvento);
    }

    @Test
    public void testAtualizarSubEvento_SubEventoNaoEncontrado() {
        SubEvento subEventoAtualizado = new SubEvento();
        subEventoAtualizado.setNome("SubEvento Atualizado");

        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.atualizarSubEvento(1L, subEventoAtualizado);
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
        verify(subEventoDAO, times(1)).selectSubEvento(1L);
        verify(subEventoDAO, times(0)).updateSubEvento(any());
    }

    @Test
    public void testDeletarSubEvento_Sucesso() {
        when(subEventoDAO.deleteSubEvento(1L)).thenReturn(true);

        boolean result = subEventoController.deletarSubEvento(1L);

        assertTrue(result);
        verify(subEventoDAO, times(1)).deleteSubEvento(1L);
    }

    @Test
    public void testDeletarSubEvento_Falha() {
        when(subEventoDAO.deleteSubEvento(1L)).thenReturn(false);

        boolean result = subEventoController.deletarSubEvento(1L);

        assertFalse(result);
        verify(subEventoDAO, times(1)).deleteSubEvento(1L);
    }

    @Test
    public void testListarTodosSubEventos() {
        SubEvento subEvento2 = new SubEvento();
        subEvento2.setId(2L);
        subEvento2.setNome("SubEvento Teste 2");
        subEvento2.setEvento(evento);  // Atribuindo o Evento mockado

        List<SubEvento> subEventos = Arrays.asList(subEvento, subEvento2);

        when(subEventoDAO.selectAllSubEventos()).thenReturn(subEventos);

        List<SubEvento> result = subEventoController.listarTodosSubEventos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(subEventoDAO, times(1)).selectAllSubEventos();
    }

    @Test
    public void testBuscarSubEventoPorId_Sucesso() {
        when(subEventoDAO.selectSubEvento(1L)).thenReturn(subEvento);

        SubEvento result = subEventoController.buscarSubEventoPorId(1L);

        assertNotNull(result);
        assertEquals(subEvento.getId(), result.getId());
        verify(subEventoDAO, times(1)).selectSubEvento(1L);
    }

    @Test
    public void testBuscarSubEventoPorId_SubEventoNaoEncontrado() {
        when(subEventoDAO.selectSubEvento(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            subEventoController.buscarSubEventoPorId(1L);
        });

        assertEquals("SubEvento não encontrado", exception.getMessage());
        verify(subEventoDAO, times(1)).selectSubEvento(1L);
    }
}
