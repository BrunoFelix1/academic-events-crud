package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repositories.EventoDAO;
import models.Evento;

import java.util.List;
import java.util.Arrays;

public class EventoDAOTest {

    private EventoDAO eventoDAOMock;
    private Evento evento;

    @BeforeEach
    public void setUp() {
        eventoDAOMock = mock(EventoDAO.class);
        evento = new Evento("Evento Teste", "Local Teste", "10:00", "Descrição do evento teste");
    }

    @Test
    public void testInsertEvento() {
        when(eventoDAOMock.insertEvento(evento)).thenReturn(true);

        boolean result = eventoDAOMock.insertEvento(evento);

        assertTrue(result);
        verify(eventoDAOMock, times(1)).insertEvento(evento);
    }

    @Test
    public void testSelectEvento() {
        when(eventoDAOMock.selectEvento(1L)).thenReturn(evento);

        Evento result = eventoDAOMock.selectEvento(1L);

        assertNotNull(result);
        assertEquals("Evento Teste", result.getTitulo());
        verify(eventoDAOMock, times(1)).selectEvento(1L);
    }

    @Test
    public void testSelectAllEventos() {
        List<Evento> eventos = Arrays.asList(
                new Evento("Evento 1", "Local 1", "09:00", "Descrição 1"),
                new Evento("Evento 2", "Local 2", "14:00", "Descrição 2")
        );

        when(eventoDAOMock.selectAllEventos()).thenReturn(eventos);

        List<Evento> result = eventoDAOMock.selectAllEventos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventoDAOMock, times(1)).selectAllEventos();
    }

    @Test
    public void testDeleteEvento() {
        when(eventoDAOMock.deleteEvento(1L)).thenReturn(true);

        boolean result = eventoDAOMock.deleteEvento(1L);

        assertTrue(result);
        verify(eventoDAOMock, times(1)).deleteEvento(1L);
    }

    @Test
    public void testUpdateEvento() {
        when(eventoDAOMock.updateEvento(evento)).thenReturn(true);

        boolean result = eventoDAOMock.updateEvento(evento);

        assertTrue(result);
        verify(eventoDAOMock, times(1)).updateEvento(evento);
    }
}
