package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repositories.SubEventoDAO;
import models.SubEvento;
import models.Evento;

import java.util.List;
import java.util.Arrays;

public class SubEventoDAOTest {

    private SubEventoDAO subEventoDAOMock;
    private SubEvento subEvento;
    private Evento eventoMock;

    @BeforeEach
    public void setUp() {
        subEventoDAOMock = mock(SubEventoDAO.class);
        eventoMock = mock(Evento.class); // Evento simulado para uso nos testes
        subEvento = new SubEvento(eventoMock, "SubEvento Teste", "Local Teste", "10:00", "Descrição do subevento teste");
    }

    @Test
    public void testInsertSubEvento() {
        when(subEventoDAOMock.insertSubEvento(subEvento)).thenReturn(true);

        boolean result = subEventoDAOMock.insertSubEvento(subEvento);

        assertTrue(result);
        verify(subEventoDAOMock, times(1)).insertSubEvento(subEvento);
    }

    @Test
    public void testSelectSubEvento() {
        when(subEventoDAOMock.selectSubEvento(1L)).thenReturn(subEvento);

        SubEvento result = subEventoDAOMock.selectSubEvento(1L);

        assertNotNull(result);
        assertEquals("SubEvento Teste", result.getNome());
        verify(subEventoDAOMock, times(1)).selectSubEvento(1L);
    }

    @Test
    public void testSelectAllSubEventos() {
        List<SubEvento> subEventos = Arrays.asList(
                new SubEvento(eventoMock, "SubEvento 1", "Local 1", "09:00", "Descrição 1"),
                new SubEvento(eventoMock, "SubEvento 2", "Local 2", "14:00", "Descrição 2")
        );

        when(subEventoDAOMock.selectAllSubEventos()).thenReturn(subEventos);

        List<SubEvento> result = subEventoDAOMock.selectAllSubEventos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(subEventoDAOMock, times(1)).selectAllSubEventos();
    }

    @Test
    public void testDeleteSubEvento() {
        when(subEventoDAOMock.deleteSubEvento(1L)).thenReturn(true);

        boolean result = subEventoDAOMock.deleteSubEvento(1L);

        assertTrue(result);
        verify(subEventoDAOMock, times(1)).deleteSubEvento(1L);
    }

    @Test
    public void testUpdateSubEvento() {
        when(subEventoDAOMock.updateSubEvento(subEvento)).thenReturn(true);

        boolean result = subEventoDAOMock.updateSubEvento(subEvento);

        assertTrue(result);
        verify(subEventoDAOMock, times(1)).updateSubEvento(subEvento);
    }
}
