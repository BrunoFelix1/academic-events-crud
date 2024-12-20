package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Atividade;
import models.Trilha;

import java.util.List;
import java.util.Arrays;

public class AtividadeDAOTest {

    private AtividadeDAO atividadeDAOMock;
    private Atividade atividade;
    private Trilha trilhaMock;

    @BeforeEach
    public void setUp() {
        atividadeDAOMock = mock(AtividadeDAO.class);

        // Mock do objeto Trilha
        trilhaMock = mock(Trilha.class);

        // Instância de Atividade com o construtor adequado
        atividade = new Atividade(null, "Palestra", "João Silva", "Resumo da atividade", trilhaMock);
    }

    @Test
    public void testInsertAtividade() {
        when(atividadeDAOMock.insertAtividade(atividade)).thenReturn(true);

        boolean result = atividadeDAOMock.insertAtividade(atividade);

        assertTrue(result);
        verify(atividadeDAOMock, times(1)).insertAtividade(atividade);
    }

    @Test
    public void testSelectAtividade() {
        when(atividadeDAOMock.selectAtividade(1L)).thenReturn(atividade);

        Atividade result = atividadeDAOMock.selectAtividade(1L);

        assertNotNull(result);
        assertEquals("Palestra", result.getTipoDeAtividade());
        assertEquals("João Silva", result.getAutor());
        verify(atividadeDAOMock, times(1)).selectAtividade(1L);
    }

    @Test
    public void testSelectAllAtividades() {
        List<Atividade> atividades = Arrays.asList(
                new Atividade(null, "Palestra", "João Silva", "Resumo 1", trilhaMock),
                new Atividade(null, "Workshop", "Maria Souza", "Resumo 2", trilhaMock)
        );

        when(atividadeDAOMock.selectAllAtividades()).thenReturn(atividades);

        List<Atividade> result = atividadeDAOMock.selectAllAtividades();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Workshop", result.get(1).getTipoDeAtividade());
        verify(atividadeDAOMock, times(1)).selectAllAtividades();
    }

    @Test
    public void testDeleteAtividade() {
        when(atividadeDAOMock.deleteAtividade(1L)).thenReturn(true);

        boolean result = atividadeDAOMock.deleteAtividade(1L);

        assertTrue(result);
        verify(atividadeDAOMock, times(1)).deleteAtividade(1L);
    }

    @Test
    public void testUpdateAtividade() {
        when(atividadeDAOMock.updateAtividade(atividade)).thenReturn(true);

        boolean result = atividadeDAOMock.updateAtividade(atividade);

        assertTrue(result);
        verify(atividadeDAOMock, times(1)).updateAtividade(atividade);
    }
}

