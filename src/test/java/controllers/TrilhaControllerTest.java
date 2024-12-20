/* package controllers;

import models.Trilha;
import models.Secao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TrilhaDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrilhaControllerTest {
    private TrilhaController trilhaController;
    private TrilhaDAO trilhaDAO;
    private Secao mockSecao;

    @BeforeEach
    void setUp() {
        trilhaDAO = mock(TrilhaDAO.class);
        trilhaController = new TrilhaController();
        trilhaController.trilhaDAO = trilhaDAO;

        mockSecao = mock(Secao.class);
        when(mockSecao.getId()).thenReturn(1L);
    }

    @Test
    void testAdicionarTrilha_Sucesso() {
        Trilha trilha = new Trilha(mockSecao, "Trilha Teste");

        when(trilhaDAO.insertTrilha(trilha)).thenReturn(true);

        boolean resultado = trilhaController.adicionarTrilha(trilha);
        assertTrue(resultado);
        verify(trilhaDAO).insertTrilha(trilha);
    }

    @Test
    void testAdicionarTrilha_DadosInvalidos() {
        Trilha trilha = new Trilha();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            trilhaController.adicionarTrilha(trilha)
        );
        assertEquals("O nome da trilha é obrigatório", exception.getMessage());
    }

    @Test
    void testAtualizarTrilha_Sucesso() {
        Trilha trilhaExistente = new Trilha(mockSecao, "Trilha Teste");
        trilhaExistente.setId(1L);

        Trilha trilhaAtualizada = new Trilha(mockSecao, "Trilha Atualizada");

        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilhaExistente);
        when(trilhaDAO.updateTrilha(any())).thenReturn(true);

        boolean resultado = trilhaController.atualizarTrilha(1L, trilhaAtualizada);

        assertTrue(resultado);
        verify(trilhaDAO).updateTrilha(any());
    }

    @Test
    void testAtualizarTrilha_NaoEncontrada() {
        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.atualizarTrilha(1L, new Trilha());
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
    }

    @Test
    void testDeletarTrilha_Sucesso() {
        Trilha trilha = new Trilha(mockSecao, "Trilha Teste");
        trilha.setId(1L);

        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilha);
        when(trilhaDAO.deleteTrilha(1L)).thenReturn(true);

        boolean resultado = trilhaController.deletarTrilha(1L);

        assertTrue(resultado);
        verify(trilhaDAO).deleteTrilha(1L);
    }

    @Test
    void testDeletarTrilha_NaoEncontrada() {
        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.deletarTrilha(1L);
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
    }

    @Test
    void testListarTodasTrilhas() {
        List<Trilha> trilhas = Arrays.asList(
                new Trilha(mockSecao, "Trilha Teste 1"),
                new Trilha(mockSecao, "Trilha Teste 2")
        );

        when(trilhaDAO.selectAllTrilhas()).thenReturn(trilhas);

        List<Trilha> resultado = trilhaController.listarTodasTrilhas();

        assertEquals(2, resultado.size());
        verify(trilhaDAO).selectAllTrilhas();
    }

    @Test
    void testBuscarTrilhaPorId_Sucesso() {
        Trilha trilha = new Trilha(mockSecao, "Trilha Teste");
        trilha.setId(1L);

        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilha);

        Trilha resultado = trilhaController.buscarTrilhaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(trilhaDAO).selectTrilha(1L);
    }

    @Test
    void testBuscarTrilhaPorId_NaoEncontrada() {
        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.buscarTrilhaPorId(1L);
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
    }

    @Test
    void testBuscarTrilhaPorNome_Sucesso() {
        Trilha trilha = new Trilha(mockSecao, "Trilha Teste");

        when(trilhaDAO.selectAllTrilhas()).thenReturn(Arrays.asList(trilha));

        Trilha resultado = trilhaController.buscarTrilhaPorNome("Trilha Teste");

        assertNotNull(resultado);
        assertEquals("Trilha Teste", resultado.getNome());
        verify(trilhaDAO).selectAllTrilhas();
    }

    @Test
    void testBuscarTrilhaPorNome_NaoEncontrada() {
        when(trilhaDAO.selectAllTrilhas()).thenReturn(Arrays.asList());

        Trilha resultado = trilhaController.buscarTrilhaPorNome("Trilha Inexistente");

        assertNull(resultado);
        verify(trilhaDAO).selectAllTrilhas();
    }
} */