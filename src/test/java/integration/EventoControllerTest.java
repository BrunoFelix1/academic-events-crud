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
    // Salva o estado inicial completo do "banco" (arquivo CSV).
    estadoInicial = new ArrayList<>(eventoController.listar());
}

@AfterEach
void tearDown() {
    // Restaura o estado inicial do "banco" (arquivo CSV).
    List<Evento> estadoAtual = new ArrayList<>(eventoController.listar());
    
    // Remove eventos que foram adicionados durante os testes.
    for (Evento evento : estadoAtual) {
        if (!estadoInicial.contains(evento)) {
            eventoController.deletar(evento);
        }
    }
    
    // Adiciona eventos que foram removidos durante os testes.
    for (Evento evento : estadoInicial) {
        if (!estadoAtual.contains(evento)) {
            eventoController.cadastrar(evento);
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


        int numeroEventos = eventoController.listar().size();
        Evento eventoAdicionado = eventoController.cadastrar(evento);

        assertEquals(numeroEventos + 1, eventoController.listar().size(), "Deveria haver 1 evento a mais na lista.");
        assertEquals(evento, eventoAdicionado, "O evento adicionado deveria ser igual ao evento cadastrado.");
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
        assertEquals(estadoInicial.size() + 2, eventos.size(), "Deveria listar todos os eventos iniciais mais os dois novos.");
    }

    @Test
    void updateEventoTest() {
        Evento eventoAntigo = new Evento();
        eventoAntigo.setTitulo("Evento Teste1");
        eventoAntigo.setLocal("Local A");
        eventoAntigo.setHorario("13:00");
        eventoAntigo.setDescricao("Descrição Teste1");

        Evento eventoNovo = new Evento();
        eventoNovo.setTitulo("Evento Teste2");
        eventoNovo.setLocal("Local B");
        eventoNovo.setHorario("13:00");
        eventoNovo.setDescricao("Descrição Teste2");

        eventoController.cadastrar(eventoAntigo);
        Evento eventoAtualizado = eventoController.atualizar(eventoAntigo, eventoNovo);

        List<Evento> eventos = eventoController.listar();
        assertEquals(estadoInicial.size() + 1, eventos.size(), "Deveria haver 1 evento na lista além dos eventos iniciais.");
        assertEquals(eventoNovo, eventoAtualizado, "O evento atualizado deveria ser igual ao evento novo.");
    }

    @Test
    void deleteEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste1");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste1");

        eventoController.cadastrar(evento);
        eventoController.deletar(evento);

        assertEquals(estadoInicial.size(), eventoController.listar().size(), "O banco deveria ter voltado ao estado inicial.");
    }
}
