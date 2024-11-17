package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.EventoController;
import controllers.IController;
import models.Evento;

import static org.junit.jupiter.api.Assertions.*;

public class ControllersTest {

    private IController<Evento> eventoController;

    @BeforeEach
    void setUp() {
        eventoController = new EventoController();
    }

    @Test
    void addEventoTest() {
        Evento evento = new Evento(99999999, "Evento Teste", "Local A", "2024-12-01 10:00", "Descrição teste");
        int numeroEventos = eventoController.listar().size();
        Evento eventoAdicionado = eventoController.cadastrar(evento);

        
        assertEquals(numeroEventos+1, eventoController.listar().size(), "Deveria haver 1 evento na lista.");
        assertEquals(evento, eventoAdicionado);
    }
}
