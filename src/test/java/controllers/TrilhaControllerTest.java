package controllers;

import controllers.TrilhaController;
import models.Trilha;
import models.Secao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repositories.TrilhaDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrilhaControllerTest {

    @InjectMocks
    private TrilhaController trilhaController;

    @Mock
    private TrilhaDAO trilhaDAO;

    @Mock
    private Secao secao;

    private Trilha trilha;

    @BeforeEach
    public void setUp() {
        trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Trilha Teste");
        trilha.setSecao(secao);
    }

    @Test
    public void testAdicionarTrilha_Sucesso() {
        when(trilhaDAO.insertTrilha(trilha)).thenReturn(true);

        boolean result = trilhaController.adicionarTrilha(trilha);

        assertTrue(result);
        verify(trilhaDAO, times(1)).insertTrilha(trilha);
    }

    @Test
    public void testAdicionarTrilha_Falha() {
        when(trilhaDAO.insertTrilha(trilha)).thenReturn(false);

        boolean result = trilhaController.adicionarTrilha(trilha);

        assertFalse(result);
        verify(trilhaDAO, times(1)).insertTrilha(trilha);
    }

    @Test
    public void testAtualizarTrilha_Sucesso() {
        Trilha trilhaAtualizada = new Trilha();
        trilhaAtualizada.setNome("Trilha Atualizada");
        trilhaAtualizada.setSecao(secao);  // Atribuindo a Secao mockada

        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilha);
        when(trilhaDAO.updateTrilha(trilha)).thenReturn(true);

        boolean result = trilhaController.atualizarTrilha(1L, trilhaAtualizada);

        assertTrue(result);
        verify(trilhaDAO, times(1)).selectTrilha(1L);
        verify(trilhaDAO, times(1)).updateTrilha(trilha);
    }

    @Test
    public void testAtualizarTrilha_TrilhaNaoEncontrada() {
        Trilha trilhaAtualizada = new Trilha();
        trilhaAtualizada.setNome("Trilha Atualizada");

        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.atualizarTrilha(1L, trilhaAtualizada);
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
        verify(trilhaDAO, times(1)).selectTrilha(1L);
        verify(trilhaDAO, times(0)).updateTrilha(any());
    }

    @Test
    public void testDeletarTrilha_Sucesso() {
        when(trilhaDAO.deleteTrilha(1L)).thenReturn(true);

        boolean result = trilhaController.deletarTrilha(1L);

        assertTrue(result);
        verify(trilhaDAO, times(1)).deleteTrilha(1L);
    }

    @Test
    public void testDeletarTrilha_Falha() {
        when(trilhaDAO.deleteTrilha(1L)).thenReturn(false);

        boolean result = trilhaController.deletarTrilha(1L);

        assertFalse(result);
        verify(trilhaDAO, times(1)).deleteTrilha(1L);
    }

    @Test
    public void testListarTodasTrilhas() {
        Trilha trilha2 = new Trilha();
        trilha2.setId(2L);
        trilha2.setNome("Trilha Teste 2");
        trilha2.setSecao(secao);

        List<Trilha> trilhas = Arrays.asList(trilha, trilha2);

        when(trilhaDAO.selectAllTrilhas()).thenReturn(trilhas);

        List<Trilha> result = trilhaController.listarTodasTrilhas();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(trilhaDAO, times(1)).selectAllTrilhas();
    }

    @Test
    public void testBuscarTrilhaPorId_Sucesso() {
        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilha);

        Trilha result = trilhaController.buscarTrilhaPorId(1L);

        assertNotNull(result);
        assertEquals(trilha.getId(), result.getId());
        verify(trilhaDAO, times(1)).selectTrilha(1L);
    }

    @Test
    public void testBuscarTrilhaPorId_TrilhaNaoEncontrada() {
        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.buscarTrilhaPorId(1L);
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
        verify(trilhaDAO, times(1)).selectTrilha(1L);
    }
}
