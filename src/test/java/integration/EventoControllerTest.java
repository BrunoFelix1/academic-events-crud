package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.EventoController;
import controllers.IController;
import models.Evento;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class EventoControllerTest {

    private static IController<Evento> eventoController;
    private static List<Evento> estadoInicial;

    @BeforeEach
    void setUp() {
        eventoController = new EventoController();
        estadoInicial = new ArrayList<>(eventoController.listar());
    }
    
    @AfterEach
    void tearDown() {
        // Obtém o estado atual do banco de dados após o teste.
        List<Evento> estadoAtual = new ArrayList<>(eventoController.listar());
    
        // Identifica e remove eventos que foram adicionados durante os testes.
        for (Evento evento : estadoAtual) {
            if (!estadoInicial.contains(evento)) {
                eventoController.deletar(evento);
            }
        }
    }

    @Test
    void addEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste");

        Evento eventoAdicionado = eventoController.cadastrar(evento);
        assertEquals(evento, eventoAdicionado);
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

        eventoController.cadastrar(evento1);
        eventoController.cadastrar(evento2);

        List<Evento> eventos = eventoController.listar();
        assertNotNull(eventos);
    }

    @Test
    void updateEventoTest() {
        Evento eventoAntigo = new Evento();
        eventoAntigo.setTitulo("Evento Teste1");
        eventoAntigo.setLocal("Local A");
        eventoAntigo.setHorario("13:00");
        eventoAntigo.setDescricao("Descrição Teste1");

        Evento eventoAntigoComId = eventoController.cadastrar(eventoAntigo);

        Evento eventoNovo = new Evento();
        eventoNovo.setTitulo("Evento Teste2");
        eventoNovo.setLocal("Local B");
        eventoNovo.setHorario("13:00");
        eventoNovo.setDescricao("Descrição Teste2");

        Evento eventoAtualizado = eventoController.atualizar(eventoAntigoComId, eventoNovo);

        assertEquals(eventoNovo, eventoAtualizado);
    }

    @Test
    void deleteEventoTest() {
        boolean resultado;
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste1");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste1");

        Evento eventoAdicionado = eventoController.cadastrar(evento);
        resultado = eventoController.deletar(eventoAdicionado);

        assertEquals(true, resultado);
    }
}
