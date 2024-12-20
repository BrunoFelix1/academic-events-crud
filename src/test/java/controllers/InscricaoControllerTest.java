package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.InscricaoDAO;

import java.util.Arrays;
import java.util.List;

class InscricaoControllerTest {

    private InscricaoDAO inscricaoDAO;
    private InscricaoController controller;
    private Usuario mockUsuario;
    private Evento mockEvento;
    private SubEvento mockSubEvento;
    private Secao mockSecao;
    private Trilha mockTrilha;

    @BeforeEach
    void setUp() {
        inscricaoDAO = mock(InscricaoDAO.class);
        controller = new InscricaoController();
        controller.inscricaoDAO = inscricaoDAO; // Tornar o DAO acessível para testes

        mockUsuario = mock(Usuario.class);
        mockEvento = mock(Evento.class);
        mockSubEvento = mock(SubEvento.class);
        mockSecao = mock(Secao.class);
        mockTrilha = mock(Trilha.class);

        when(mockUsuario.getId()).thenReturn(1L);
        when(mockEvento.getId()).thenReturn(1L);
        when(mockSubEvento.getId()).thenReturn(1L);
        when(mockSecao.getId()).thenReturn(1L);
        when(mockTrilha.getId()).thenReturn(1L);
    }

    @Test
    void testAdicionarInscricao_Sucesso() {
        Inscricao inscricao = new Inscricao(mockUsuario, mockEvento, null, null, null);

        when(inscricaoDAO.inscricaoJaExiste(1L, 1L, null, null, null)).thenReturn(false);
        when(inscricaoDAO.insertInscricao(inscricao)).thenReturn(true);

        boolean resultado = controller.adicionarInscricao(inscricao);

        assertTrue(resultado);
        verify(inscricaoDAO).insertInscricao(inscricao);
    }

    @Test
    void testAdicionarInscricao_InscricaoJaExiste() {
        Inscricao inscricao = new Inscricao(mockUsuario, mockEvento, null, null, null);

        when(inscricaoDAO.inscricaoJaExiste(1L, 1L, null, null, null)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.adicionarInscricao(inscricao);
        });

        assertEquals("Inscrição já existente", exception.getMessage());
    }

    @Test
    void testAtualizarInscricao_Sucesso() {
        Inscricao inscricaoExistente = new Inscricao(mockUsuario, mockEvento, null, null, null);
        inscricaoExistente.setId(1L);

        Inscricao inscricaoAtualizada = new Inscricao(mockUsuario, mockEvento, null, null, null);

        when(inscricaoDAO.selectInscricao(1L)).thenReturn(inscricaoExistente);
        when(inscricaoDAO.updateInscricao(any())).thenReturn(true);

        boolean resultado = controller.atualizarInscricao(1L, inscricaoAtualizada);

        assertTrue(resultado);
        verify(inscricaoDAO).updateInscricao(any());
    }

    @Test
    void testAtualizarInscricao_NaoEncontrada() {
        when(inscricaoDAO.selectInscricao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.atualizarInscricao(1L, new Inscricao());
        });

        assertEquals("Inscrição não encontrada", exception.getMessage());
    }

    @Test
    void testDeletarInscricao_Sucesso() {
        Inscricao inscricao = new Inscricao(mockUsuario, mockEvento, null, null, null);
        inscricao.setId(1L);

        when(inscricaoDAO.selectInscricao(1L)).thenReturn(inscricao);
        when(inscricaoDAO.deleteInscricao(1L)).thenReturn(true);

        boolean resultado = controller.deletarInscricao(1L);

        assertTrue(resultado);
        verify(inscricaoDAO).deleteInscricao(1L);
    }

    @Test
    void testDeletarInscricao_NaoEncontrada() {
        when(inscricaoDAO.selectInscricao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.deletarInscricao(1L);
        });

        assertEquals("Inscrição não encontrada", exception.getMessage());
    }

    @Test
    void testListarTodasInscricoes() {
        List<Inscricao> inscricoes = Arrays.asList(
                new Inscricao(mockUsuario, mockEvento, null, null, null),
                new Inscricao(mockUsuario, mockEvento, null, null, null)
        );

        when(inscricaoDAO.selectAllInscricoes()).thenReturn(inscricoes);

        List<Inscricao> resultado = controller.listarTodasInscricoes();

        assertEquals(2, resultado.size());
        verify(inscricaoDAO).selectAllInscricoes();
    }

    @Test
    void testBuscarInscricaoPorId_Sucesso() {
        Inscricao inscricao = new Inscricao(mockUsuario, mockEvento, null, null, null);
        inscricao.setId(1L);

        when(inscricaoDAO.selectInscricao(1L)).thenReturn(inscricao);

        Inscricao resultado = controller.buscarInscricaoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(inscricaoDAO).selectInscricao(1L);
    }

    @Test
    void testBuscarInscricaoPorId_NaoEncontrada() {
        when(inscricaoDAO.selectInscricao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.buscarInscricaoPorId(1L);
        });

        assertEquals("Inscrição não encontrada", exception.getMessage());
    }

    @Test
    void testCancelarInscricao_Sucesso() {
        Inscricao inscricao = new Inscricao(mockUsuario, mockEvento, null, null, null);
        inscricao.setId(1L);

        when(inscricaoDAO.selectInscricao(1L)).thenReturn(inscricao);

        controller.cancelarInscricao(1L, 1L);

        verify(inscricaoDAO).deleteInscricao(1L);
    }

    @Test
    void testCancelarInscricao_PermissaoNegada() {
        Usuario outroUsuario = mock(Usuario.class);
        when(outroUsuario.getId()).thenReturn(2L);

        Inscricao inscricao = new Inscricao(outroUsuario, mockEvento, null, null, null);
        inscricao.setId(1L);

        when(inscricaoDAO.selectInscricao(1L)).thenReturn(inscricao);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.cancelarInscricao(1L, 1L);
        });

        assertEquals("Você não tem permissão para cancelar esta inscrição", exception.getMessage());
    }

    @Test
    void testListarInscricoesPorUsuario() {
        List<Inscricao> inscricoes = Arrays.asList(
                new Inscricao(mockUsuario, mockEvento, null, null, null),
                new Inscricao(mockUsuario, mockEvento, null, null, null)
        );

        when(inscricaoDAO.findByUsuarioId(1L)).thenReturn(inscricoes);

        List<Inscricao> resultado = controller.listarInscricoesPorUsuario(1L);

        assertEquals(2, resultado.size());
        verify(inscricaoDAO).findByUsuarioId(1L);
    }
}