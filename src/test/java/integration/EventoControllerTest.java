package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.EventoController;
import models.Evento;

public class EventoControllerTest extends BaseControllerTest<Evento> {

    @Override
    protected IController<Evento> createController() {
        return new EventoController();
    }

    @Test
    void listEventosTest() {
        Evento evento1 = new Evento();
        evento1.setTitulo("Evento Teste1");
        evento1.setLocal("Local A");
        evento1.setHorario("13:00");
        evento1.setDescricao("Descrição Teste1");

        Evento evento2 = new Evento();
        evento2.setTitulo("Evento Teste2");
        evento2.setLocal("Local B");
        evento2.setHorario("13:00");
        evento2.setDescricao("Descrição Teste2");

        controller.cadastrar(evento1);
        controller.cadastrar(evento2);

        List<Evento> eventos = controller.listar();
        assertNotNull(eventos);
        assertTrue(eventos.size() >= 2);
    }

    @Test
    void updateEventoTest() {
        Evento eventoAntigo = new Evento();
        eventoAntigo.setTitulo("Evento Teste1");
        eventoAntigo.setLocal("Local A");
        eventoAntigo.setHorario("13:00");
        eventoAntigo.setDescricao("Descrição Teste1");

        Evento eventoAntigoComId = controller.cadastrar(eventoAntigo);

        Evento eventoNovo = new Evento();
        eventoNovo.setTitulo("Evento Teste2");
        eventoNovo.setLocal("Local B");
        eventoNovo.setHorario("13:00");
        eventoNovo.setDescricao("Descrição Teste2");


        Evento eventoAtualizado = controller.atualizar(eventoAntigoComId, eventoNovo);

        assertEquals(eventoNovo, eventoAtualizado);
    }

    @Test
    void deleteEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste1");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste1");

        Evento eventoComId = controller.cadastrar(evento);

        boolean resultado = controller.deletar(eventoComId);
        assertTrue(resultado);

        List<Evento> eventos = controller.listar();
        assertFalse(eventos.contains(eventoComId));
    }

    @Test
    void addEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste");

        Evento eventoAdicionado = controller.cadastrar(evento);
        assertEquals(evento, eventoAdicionado);
    }
}