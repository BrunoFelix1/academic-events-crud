
package controllers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import models.Atividade;
import models.Trilha;
import repositories.AtividadeDAO;

public class AtividadeControllerTest {

    @Mock
    private AtividadeDAO atividadeDAO;

    private AtividadeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new AtividadeController(atividadeDAO);
    }

    @Test
    void testAdicionarAtividadeValida() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade atividade = new Atividade(null, "Palestra", "Autor", "Resumo", trilha);
        when(atividadeDAO.insertAtividade(atividade)).thenReturn(true);
        assertTrue(controller.adicionarAtividade(atividade));
        verify(atividadeDAO, times(1)).insertAtividade(atividade);
    }

    @Test
    void testAdicionarAtividadeSemTipo() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade atividade = new Atividade(null, "", "Autor", "Resumo", trilha);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.adicionarAtividade(atividade));
        assertEquals("Tipo de atividade é obrigatório", ex.getMessage());
        verify(atividadeDAO, never()).insertAtividade(any());
    }

    @Test
    void testAdicionarAtividadeSemAutor() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade atividade = new Atividade(null, "Palestra", "", "Resumo", trilha);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.adicionarAtividade(atividade));
        assertEquals("Autor é obrigatório", ex.getMessage());
        verify(atividadeDAO, never()).insertAtividade(any());
    }

    @Test
    void testAdicionarAtividadeSemTrilhaValida() {
        Atividade atividade = new Atividade(null, "Palestra", "Autor", "Resumo", null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.adicionarAtividade(atividade));
        assertEquals("Trilha inválida", ex.getMessage());
        verify(atividadeDAO, never()).insertAtividade(any());
    }

    @Test
    void testAtualizarAtividadeValida() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade existente = new Atividade(1L, "Tipo", "Autor", "Resumo", trilha);
        Atividade atualizada = new Atividade(1L, "TipoNovo", "AutorNovo", "ResumoNovo", trilha);
        when(atividadeDAO.selectAtividade(1L)).thenReturn(existente);
        when(atividadeDAO.updateAtividade(existente)).thenReturn(true);
        assertTrue(controller.atualizarAtividade(1L, atualizada));
        verify(atividadeDAO, times(1)).updateAtividade(existente);
    }

    @Test
    void testAtualizarAtividadeNaoEncontrada() {
        when(atividadeDAO.selectAtividade(1L)).thenReturn(null);
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade atualizada = new Atividade(1L, "Tipo", "Autor", "Resumo", trilha);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.atualizarAtividade(1L, atualizada));
        assertEquals("Atividade não encontrada", ex.getMessage());
    }

    @Test
    void testAtualizarAtividadeSemTipo() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade existente = new Atividade(1L, "Tipo", "Autor", "Resumo", trilha);
        Atividade atualizada = new Atividade(1L, "", "AutorNovo", "ResumoNovo", trilha);
        when(atividadeDAO.selectAtividade(1L)).thenReturn(existente);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.atualizarAtividade(1L, atualizada));
        assertEquals("Tipo de atividade é obrigatório", ex.getMessage());
    }

    @Test
    void testAtualizarAtividadeSemAutor() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade existente = new Atividade(1L, "Tipo", "Autor", "Resumo", trilha);
        Atividade atualizada = new Atividade(1L, "TipoNovo", "", "ResumoNovo", trilha);
        when(atividadeDAO.selectAtividade(1L)).thenReturn(existente);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.atualizarAtividade(1L, atualizada));
        assertEquals("Autor é obrigatório", ex.getMessage());
    }

    @Test
    void testAtualizarAtividadeSemTrilhaValida() {
        Trilha trilha = new Trilha();
        trilha.setId(null);
        Atividade existente = new Atividade(1L, "Tipo", "Autor", "Resumo", trilha);
        Atividade atualizada = new Atividade(1L, "TipoNovo", "AutorNovo", "ResumoNovo", trilha);
        when(atividadeDAO.selectAtividade(1L)).thenReturn(existente);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.atualizarAtividade(1L, atualizada));
        assertEquals("Trilha inválida", ex.getMessage());
    }

    @Test
    void testDeletarAtividadeValida() {
        Trilha trilha = new Trilha();
        trilha.setId(1L);
        Atividade existente = new Atividade(1L, "Tipo", "Autor", "Resumo", trilha);
        when(atividadeDAO.selectAtividade(1L)).thenReturn(existente);
        when(atividadeDAO.deleteAtividade(1L)).thenReturn(true);
        assertTrue(controller.deletarAtividade(1L));
        verify(atividadeDAO, times(1)).deleteAtividade(1L);
    }

    @Test
    void testDeletarAtividadeNaoEncontrada() {
        when(atividadeDAO.selectAtividade(1L)).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.deletarAtividade(1L));
        assertEquals("Atividade não encontrada", ex.getMessage());
    }

    @Test
    void testListarTodasAtividades() {
        List<Atividade> lista = Arrays.asList(new Atividade(), new Atividade());
        when(atividadeDAO.selectAllAtividades()).thenReturn(lista);
        assertEquals(lista, controller.listarTodasAtividades());
        verify(atividadeDAO, times(1)).selectAllAtividades();
    }

    @Test
    void testBuscarAtividadePorIdValida() {
        Atividade existente = new Atividade();
        when(atividadeDAO.selectAtividade(1L)).thenReturn(existente);
        assertEquals(existente, controller.buscarAtividadePorId(1L));
        verify(atividadeDAO, times(1)).selectAtividade(1L);
    }

    @Test
    void testBuscarAtividadePorIdNaoEncontrada() {
        when(atividadeDAO.selectAtividade(1L)).thenReturn(null);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.buscarAtividadePorId(1L));
        assertEquals("Atividade não encontrada", ex.getMessage());
    }
}
