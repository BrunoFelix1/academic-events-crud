package controllers;

import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.EventoDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoControllerTest {
    private EventoController eventoController;
    private EventoDAO eventoDAO;

    @BeforeEach
    void setUp() {
        eventoDAO = mock(EventoDAO.class);
        eventoController = new EventoController();
        eventoController.eventoDAO = eventoDAO;
    }

    @Test
    void adicionarEvento_comDadosValidos() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste");

        when(eventoDAO.insertEvento(evento)).thenReturn(true);

        boolean resultado = eventoController.adicionarEvento(evento);
        assertTrue(resultado);
        verify(eventoDAO, times(1)).insertEvento(evento);
    }

    @Test
    void adicionarEvento_comDadosInvalidos_lancaExcecao() {
        Evento evento = new Evento();

        Exception exception = assertThrows(RuntimeException.class, () ->
            eventoController.adicionarEvento(evento)
        );
        assertEquals("Título do evento é obrigatório", exception.getMessage());
    }
}