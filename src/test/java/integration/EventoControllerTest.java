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
        Evento evento = new Evento(999, "Evento Teste", "Local A", "2024-12-01 10:00", "Descrição teste");
        int numeroEventos = eventoController.listar().size();
        Evento eventoAdicionado = eventoController.cadastrar(evento);

        assertEquals(numeroEventos + 1, eventoController.listar().size(), "Deveria haver 1 evento a mais na lista.");
        assertEquals(evento, eventoAdicionado, "O evento adicionado deveria ser igual ao evento cadastrado.");
    }

    @Test
    void listEventosTest() {
        Evento evento1 = new Evento(999, "Evento 1", "Local A", "2024-12-02 11:00", "Descrição evento 1");
        Evento evento2 = new Evento(1000, "Evento 2", "Local B", "2024-12-03 12:00", "Descrição evento 2");

        eventoController.cadastrar(evento1);
        eventoController.cadastrar(evento2);

        List<Evento> eventos = eventoController.listar();
        assertEquals(estadoInicial.size() + 2, eventos.size(), "Deveria listar todos os eventos iniciais mais os dois novos.");
    }

    @Test
    void updateEventoTest() {
        Evento eventoAntigo = new Evento(999, "Evento Original", "Local C", "2024-12-04 13:00", "Descrição original");
        Evento eventoNovo = new Evento(999, "Evento Atualizado", "Local D", "2024-12-05 14:00", "Descrição atualizada");

        eventoController.cadastrar(eventoAntigo);
        Evento eventoAtualizado = eventoController.atualizar(eventoAntigo, eventoNovo);

        List<Evento> eventos = eventoController.listar();
        assertEquals(estadoInicial.size() + 1, eventos.size(), "Deveria haver 1 evento na lista além dos eventos iniciais.");
        assertEquals(eventoNovo, eventoAtualizado, "O evento atualizado deveria ser igual ao evento novo.");
    }

    @Test
    void deleteEventoTest() {
        Evento evento = new Evento(999, "Evento Deletável", "Local E", "2024-12-06 15:00", "Descrição deletável");

        eventoController.cadastrar(evento);
        eventoController.deletar(evento);

        assertEquals(estadoInicial.size(), eventoController.listar().size(), "O banco deveria ter voltado ao estado inicial.");
    }
}
