package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Trilha;
import models.Secao;

import java.util.List;
import java.util.Arrays;

public class TrilhaDAOTest {

    private TrilhaDAO trilhaDAOMock;
    private Trilha trilha;
    private Secao secaoMock;

    @BeforeEach
    public void setUp() {
        trilhaDAOMock = mock(TrilhaDAO.class);

        // Mock do objeto Secao
        secaoMock = mock(Secao.class);

        // Instância de Trilha com o construtor adequado
        trilha = new Trilha(secaoMock, "Trilha Teste");
    }

    @Test
    public void testInsertTrilha() {
        when(trilhaDAOMock.insertTrilha(trilha)).thenReturn(true);

        boolean result = trilhaDAOMock.insertTrilha(trilha);

        assertTrue(result);
        verify(trilhaDAOMock, times(1)).insertTrilha(trilha);
    }

    @Test
    public void testSelectTrilha() {
        when(trilhaDAOMock.selectTrilha(1L)).thenReturn(trilha);

        Trilha result = trilhaDAOMock.selectTrilha(1L);

        assertNotNull(result);
        assertEquals("Trilha Teste", result.getNome());
        verify(trilhaDAOMock, times(1)).selectTrilha(1L);
    }

    @Test
    public void testSelectAllTrilhas() {
        List<Trilha> trilhas = Arrays.asList(
                new Trilha(secaoMock, "Trilha 1"),
                new Trilha(secaoMock, "Trilha 2")
        );

        when(trilhaDAOMock.selectAllTrilhas()).thenReturn(trilhas);

        List<Trilha> result = trilhaDAOMock.selectAllTrilhas();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Trilha 1", result.get(0).getNome());
        verify(trilhaDAOMock, times(1)).selectAllTrilhas();
    }

    @Test
    public void testDeleteTrilha() {
        when(trilhaDAOMock.deleteTrilha(1L)).thenReturn(true);

        boolean result = trilhaDAOMock.deleteTrilha(1L);

        assertTrue(result);
        verify(trilhaDAOMock, times(1)).deleteTrilha(1L);
    }

    @Test
    public void testUpdateTrilha() {
        when(trilhaDAOMock.updateTrilha(trilha)).thenReturn(true);

        boolean result = trilhaDAOMock.updateTrilha(trilha);

        assertTrue(result);
        verify(trilhaDAOMock, times(1)).updateTrilha(trilha);
    }
}
