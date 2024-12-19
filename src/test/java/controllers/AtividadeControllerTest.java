package controllers;

import models.Atividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.AtividadeDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtividadeControllerTest {

    @Mock
    private AtividadeDAO atividadeDAO;

    @InjectMocks
    private AtividadeController atividadeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAtualizarAtividadeNaoEncontrada() {
        Long id = 1L;
        when(atividadeDAO.selectAtividade(id)).thenReturn(null);

        Atividade atividadeAtualizada = new Atividade();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            atividadeController.atualizarAtividade(id, atividadeAtualizada);
        });

        assertEquals("Atividade não encontrada", exception.getMessage());
        verify(atividadeDAO, times(1)).selectAtividade(id);
        verify(atividadeDAO, never()).updateAtividade(any());
    }


    @Test
    void testListarTodasAtividades() {
        when(atividadeDAO.selectAllAtividades()).thenReturn(List.of(new Atividade(), new Atividade()));

        List<Atividade> atividades = atividadeController.listarTodasAtividades();

        assertNotNull(atividades);
        assertEquals(2, atividades.size());
        verify(atividadeDAO, times(1)).selectAllAtividades();
    }

    @Test
    void testBuscarAtividadePorId() {
        Long id = 1L;
        Atividade atividade = new Atividade();
        when(atividadeDAO.selectAtividade(id)).thenReturn(atividade);

        Atividade resultado = atividadeController.buscarAtividadePorId(id);

        assertNotNull(resultado);
        assertEquals(atividade, resultado);
        verify(atividadeDAO, times(1)).selectAtividade(id);
    }

    @Test
    void testBuscarAtividadePorIdNaoEncontrada() {
        Long id = 1L;
        when(atividadeDAO.selectAtividade(id)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            atividadeController.buscarAtividadePorId(id);
        });

        assertEquals("Atividade não encontrada", exception.getMessage());
        verify(atividadeDAO, times(1)).selectAtividade(id);
    }
}
