package controllers;

import models.Secao;
import models.SubEvento;
import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.SecaoDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class SecaoControllerTest {
    private SecaoController secaoController;
    private SecaoDAO secaoDAO;
    private Evento mockEvento;
    private SubEvento mockSubEvento;

    @BeforeEach
    void setUp() {
        secaoDAO = mock(SecaoDAO.class);
        secaoController = new SecaoController();
        secaoController.secaoDAO = secaoDAO;

        mockEvento = mock(Evento.class);
        mockSubEvento = mock(SubEvento.class);

        when(mockEvento.getId()).thenReturn(1L);
        when(mockSubEvento.getId()).thenReturn(1L);
    }

    @Test
    void testAdicionarSecao_Sucesso() {
        Secao secao = new Secao(mockEvento, mockSubEvento, "Secao Teste", "Local C", "15:00");

        when(secaoDAO.insertSecao(secao)).thenReturn(true);

        boolean resultado = secaoController.adicionarSecao(secao);
        assertTrue(resultado);
        verify(secaoDAO).insertSecao(secao);
    }

    @Test
    void testAdicionarSecao_DadosInvalidos() {
        Secao secao = new Secao();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            secaoController.adicionarSecao(secao)
        );
        assertEquals("O nome da seção é obrigatório", exception.getMessage());
    }

    @Test
    void testAtualizarSecao_Sucesso() {
        Secao secaoExistente = new Secao(mockEvento, mockSubEvento, "Secao Teste", "Local C", "15:00");
        secaoExistente.setId(1L);

        Secao secaoAtualizada = new Secao(mockEvento, mockSubEvento, "Secao Atualizada", "Local D", "16:00");

        when(secaoDAO.selectSecao(1L)).thenReturn(secaoExistente);
        when(secaoDAO.updateSecao(any())).thenReturn(true);

        boolean resultado = secaoController.atualizarSecao(1L, secaoAtualizada);

        assertTrue(resultado);
        verify(secaoDAO).updateSecao(any());
    }

    @Test
    void testAtualizarSecao_NaoEncontrada() {
        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            secaoController.atualizarSecao(1L, new Secao());
        });

        assertEquals("Secao não encontrada", exception.getMessage());
    }

    @Test
    void testDeletarSecao_Sucesso() {
        Secao secao = new Secao(mockEvento, mockSubEvento, "Secao Teste", "Local C", "15:00");
        secao.setId(1L);

        when(secaoDAO.selectSecao(1L)).thenReturn(secao);
        when(secaoDAO.deleteSecao(1L)).thenReturn(true);

        boolean resultado = secaoController.deletarSecao(1L);

        assertTrue(resultado);
        verify(secaoDAO).deleteSecao(1L);
    }

    @Test
    void testDeletarSecao_NaoEncontrada() {
        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            secaoController.deletarSecao(1L);
        });

        assertEquals("Secao não encontrada", exception.getMessage());
    }

    @Test
    void testListarTodasSecoes() {
        List<Secao> secoes = Arrays.asList(
                new Secao(mockEvento, mockSubEvento, "Secao Teste 1", "Local A", "10:00"),
                new Secao(mockEvento, mockSubEvento, "Secao Teste 2", "Local B", "11:00")
        );

        when(secaoDAO.selectAllSecoes()).thenReturn(secoes);

        List<Secao> resultado = secaoController.listarTodasSecoes();

        assertEquals(2, resultado.size());
        verify(secaoDAO).selectAllSecoes();
    }

    @Test
    void testBuscarSecaoPorId_Sucesso() {
        Secao secao = new Secao(mockEvento, mockSubEvento, "Secao Teste", "Local C", "15:00");
        secao.setId(1L);

        when(secaoDAO.selectSecao(1L)).thenReturn(secao);

        Secao resultado = secaoController.buscarSecaoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(secaoDAO).selectSecao(1L);
    }

    @Test
    void testBuscarSecaoPorId_NaoEncontrada() {
        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            secaoController.buscarSecaoPorId(1L);
        });

        assertEquals("Secao não encontrada", exception.getMessage());
    }
}