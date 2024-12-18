package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import models.Evento;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeEventTest extends BaseIntegrationTest {

    @Test
    void addEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste");

        assertEquals(true, facade.adicionarEvento(evento));
        eventosCriados.add(evento.getId()); // Rastrear o ID do evento criado
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

        facade.adicionarEvento(evento1);
        facade.adicionarEvento(evento2);

        // Rastrear os IDs dos eventos criados
        eventosCriados.add(evento1.getId());
        eventosCriados.add(evento2.getId());

        List<Evento> eventos = facade.listarEventos();
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

        facade.adicionarEvento(eventoAntigo);
        Long eventoAntigoId = eventoAntigo.getId();
        eventosCriados.add(eventoAntigoId); // Rastrear o ID do evento criado

        Evento eventoNovo = new Evento();
        eventoNovo.setTitulo("Evento Teste2");
        eventoNovo.setLocal("Local B");
        eventoNovo.setHorario("14:00");
        eventoNovo.setDescricao("Descrição Teste2");

        assertTrue(facade.atualizarEvento(eventoAntigoId, eventoNovo));
    }

    @Test
    void deleteEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste1");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste1");

        facade.adicionarEvento(evento);
        Long eventoId = evento.getId();
        eventosCriados.add(eventoId); // Rastrear o ID do evento criado

        boolean resultado = facade.deletarEvento(eventoId);
        assertTrue(resultado);

        // Remover o ID rastreado, pois já foi deletado
        eventosCriados.remove(eventoId);
    }
}
