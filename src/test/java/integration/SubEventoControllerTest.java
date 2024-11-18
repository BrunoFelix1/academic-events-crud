package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.SubeventoController;
import models.SubEvento;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SubEventoControllerTest {

    private static IController<SubEvento> SubEventoController;
    private static List<SubEvento> estadoInicial;

    @BeforeEach
    void setUp() {
        SubEventoController = new SubeventoController();
        // Salva o estado inicial completo do "banco" (arquivo CSV).
        estadoInicial = new ArrayList<>(SubEventoController.listar());
    }

    @AfterEach
    void tearDown() {
        // Restaura o estado inicial do "banco" (arquivo CSV).
        List<SubEvento> estadoAtual = new ArrayList<>(SubEventoController.listar());

        // Remove SubEventos adicionados durante os testes.
        for (SubEvento SubEvento : estadoAtual) {
            if (!estadoInicial.contains(SubEvento)) {
                SubEventoController.deletar(SubEvento);
            }
        }

        // Adiciona SubEventos que foram removidos durante os testes.
        for (SubEvento SubEvento : estadoInicial) {
            if (!estadoAtual.contains(SubEvento)) {
                SubEventoController.cadastrar(SubEvento);
            }
        }
    }

    @Test
    void addSubEventoTest() {
        SubEvento SubEvento = new SubEvento(999, 1, "Título teste", "Local A", "2024-12-01 10:00", "Descrição teste");
        int numeroSubEventos = SubEventoController.listar().size();
        SubEvento SubEventoAdicionado = SubEventoController.cadastrar(SubEvento);

        assertEquals(numeroSubEventos + 1, SubEventoController.listar().size(), "Deveria haver 1 SubEvento a mais na lista.");
        assertEquals(SubEvento, SubEventoAdicionado, "O SubEvento adicionado deveria ser igual ao SubEvento cadastrado.");
    }

    @Test
    void listSubEventosTest() {
        SubEvento SubEvento1 = new SubEvento(999, 1, "Título 1", "Local A", "2024-12-02 11:00", "Descrição SubEvento 1");
        SubEvento SubEvento2 = new SubEvento(1000, 1, "Título 2", "Local B", "2024-12-03 12:00", "Descrição SubEvento 2");

        SubEventoController.cadastrar(SubEvento1);
        SubEventoController.cadastrar(SubEvento2);

        List<SubEvento> SubEventos = SubEventoController.listar();
        assertEquals(estadoInicial.size() + 2, SubEventos.size(), "Deveria listar todos os SubEventos iniciais mais os dois novos.");
    }

    @Test
    void updateSubEventoTest() {
        SubEvento SubEventoAntigo = new SubEvento(999, 1, "Título antigo", "Local C", "2024-12-04 13:00", "Descrição original");
        SubEvento SubEventoNovo = new SubEvento(999, 1, "Título novo", "Local D", "2024-12-05 14:00", "Descrição atualizada");

        SubEventoController.cadastrar(SubEventoAntigo);
        SubEvento SubEventoAtualizado = SubEventoController.atualizar(SubEventoAntigo, SubEventoNovo);

        List<SubEvento> SubEventos = SubEventoController.listar();
        assertEquals(estadoInicial.size() + 1, SubEventos.size(), "Deveria haver 1 SubEvento na lista além dos SubEventos iniciais.");
        assertEquals(SubEventoNovo, SubEventoAtualizado, "O SubEvento atualizado deveria ser igual ao SubEvento novo.");
    }

    @Test
    void deleteSubEventoTest() {
        SubEvento SubEvento = new SubEvento(999, 1, "Título deletável", "Local E", "2024-12-06 15:00", "Descrição deletável");

        SubEventoController.cadastrar(SubEvento);
        boolean deletado = SubEventoController.deletar(SubEvento);

        assertTrue(deletado, "Deveria retornar verdadeiro ao deletar o SubEvento.");
        assertEquals(estadoInicial.size(), SubEventoController.listar().size(), "O banco deveria ter voltado ao estado inicial.");
    }
}
