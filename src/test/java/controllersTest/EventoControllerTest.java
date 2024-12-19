package controllersTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import controllers.EventoController;
import models.Evento;
import repositories.EventoDAO;

import java.util.Arrays;
import java.util.List;

class EventoControllerTest {

    @Mock
    private EventoDAO eventoDAO;

    @InjectMocks
    private EventoController eventoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testAdicionarEvento() {
        Evento novoEvento = new Evento("Workshop de Tecnologia", "Auditório 1", "10:00", "Evento de tecnologia");
        when(eventoDAO.insertEvento(novoEvento)).thenReturn(true);

        boolean resultado = eventoController.adicionarEvento(novoEvento);

        assertTrue(resultado, "O evento deveria ser adicionado com sucesso.");
        verify(eventoDAO, times(1)).insertEvento(novoEvento);
    }

    @Test
    void testAdicionarEventoFalha() {
        Evento novoEvento = new Evento("Workshop de Tecnologia", "Auditório 1", "10:00", "Evento de tecnologia");
        when(eventoDAO.insertEvento(novoEvento)).thenReturn(false);

        boolean resultado = eventoController.adicionarEvento(novoEvento);

        assertFalse(resultado, "O evento não deveria ser adicionado.");
        verify(eventoDAO, times(1)).insertEvento(novoEvento);
    }

    @Test
    void testAtualizarEvento() {
        Evento eventoExistente = new Evento("Workshop de Tecnologia", "Auditório 1", "10:00", "Evento de tecnologia");
        eventoExistente.setId(1L);
        Evento eventoAtualizado = new Evento("Palestra de C++", "Auditório 2", "14:00", "Evento sobre C++");

        when(eventoDAO.selectEvento(1L)).thenReturn(eventoExistente);
        when(eventoDAO.updateEvento(eventoExistente)).thenReturn(true);

        boolean resultado = eventoController.atualizarEvento(1L, eventoAtualizado);

        assertTrue(resultado, "O evento deveria ser atualizado com sucesso.");
        assertEquals("Palestra de C++", eventoExistente.getTitulo());
        assertEquals("Auditório 2", eventoExistente.getLocal());
        assertEquals("14:00", eventoExistente.getHorario());
        assertEquals("Evento sobre C++", eventoExistente.getDescricao());
        verify(eventoDAO, times(1)).updateEvento(eventoExistente);
    }

    @Test
    void testAtualizarEventoNaoEncontrado() {
        Evento eventoAtualizado = new Evento("Palestra de C++", "Auditório 4", "18:00", "Evento sobre C++");

        when(eventoDAO.selectEvento(1L)).thenReturn(null); // Simula que o evento não existe

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventoController.atualizarEvento(1L, eventoAtualizado);
        });

        assertEquals("Evento não encontrado", exception.getMessage());
        verify(eventoDAO, never()).updateEvento(any()); // Garante que o update não foi chamado
    }

    @Test
    void testDeletarEvento() {
        when(eventoDAO.deleteEvento(1L)).thenReturn(true);

        boolean resultado = eventoController.deletarEvento(1L);

        assertTrue(resultado, "O evento deveria ser deletado com sucesso.");
        verify(eventoDAO, times(1)).deleteEvento(1L);
    }

    @Test
    void testDeletarEventoNaoEncontrado() {
        when(eventoDAO.deleteEvento(1L)).thenReturn(false);

        boolean resultado = eventoController.deletarEvento(1L);

        assertFalse(resultado, "O evento não deveria ser deletado.");
        verify(eventoDAO, times(1)).deleteEvento(1L);
    }

    @Test
    void testListarTodosEventos() {
        Evento evento1 = new Evento("Workshop de Tecnologia", "Auditório 1", "10:00", "Evento de tecnologia");
        Evento evento2 = new Evento("Palestra de C++", "Auditório 2", "14:00", "Evento sobre C++");

        List<Evento> eventosEsperados = Arrays.asList(evento1, evento2);
        when(eventoDAO.selectAllEventos()).thenReturn(eventosEsperados);

        List<Evento> eventos = eventoController.listarTodosEventos();

        assertNotNull(eventos);
        assertEquals(2, eventos.size());
        assertEquals("Workshop de Tecnologia", eventos.get(0).getTitulo());
        assertEquals("Palestra de C++", eventos.get(1).getTitulo());
        verify(eventoDAO, times(1)).selectAllEventos();
    }

    @Test
    void testBuscarEventoPorId() {
        Evento evento = new Evento("Workshop de Tecnologia", "Auditório 1", "10:00", "Evento de tecnologia");
        evento.setId(1L);

        when(eventoDAO.selectEvento(1L)).thenReturn(evento);

        Evento eventoBuscado = eventoController.buscarEventoPorId(1L);

        assertNotNull(eventoBuscado);
        assertEquals("Workshop de Tecnologia", eventoBuscado.getTitulo());
        assertEquals("Auditório 1", eventoBuscado.getLocal());
        verify(eventoDAO, times(1)).selectEvento(1L);
    }

    @Test
    void testBuscarEventoPorIdNaoEncontrado() {
        when(eventoDAO.selectEvento(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventoController.buscarEventoPorId(1L);
        });

        assertEquals("Evento não encontrado", exception.getMessage());
        verify(eventoDAO, times(1)).selectEvento(1L);
    }
}
