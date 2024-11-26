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

    private static IController<SubEvento> subEventoController;
    private static List<SubEvento> estadoInicial;

    @BeforeEach
    void setUp() {
        subEventoController = new SubeventoController();
        estadoInicial = new ArrayList<>(subEventoController.listar());
    }
    
    @AfterEach
    void tearDown() {
        // Obtém o estado atual do banco de dados após o teste.
        List<SubEvento> estadoAtual = new ArrayList<>(subEventoController.listar());
    
        // Identifica e remove subeventos que foram adicionados durante os testes.
        for (SubEvento subEvento : estadoAtual) {
            if (!estadoInicial.contains(subEvento)) {
                subEventoController.deletar(subEvento);
            }
        }
    }

    @Test
    void addSubEventoTest() {
        SubEvento subEvento = new SubEvento();
        subEvento.setTitulo("SubEvento Teste");
        subEvento.setLocal("Local A");
        subEvento.setHorario("15:00");
        subEvento.setDescricao("Descrição Teste");
        subEvento.setIdEvento(1); // Supondo que exista um evento com id 1

        SubEvento subEventoAdicionado = subEventoController.cadastrar(subEvento);
        assertEquals(subEvento, subEventoAdicionado);
    }

    @Test
    void listSubEventosTest() {
        SubEvento subEvento1 = new SubEvento();
        subEvento1.setTitulo("SubEvento Teste1");
        subEvento1.setLocal("Local A");
        subEvento1.setHorario("15:00");
        subEvento1.setDescricao("Descrição Teste1");
        subEvento1.setIdEvento(1); // Supondo que exista um evento com id 1

        SubEvento subEvento2 = new SubEvento();
        subEvento2.setTitulo("SubEvento Teste2");
        subEvento2.setLocal("Local B");
        subEvento2.setHorario("15:00");
        subEvento2.setDescricao("Descrição Teste2");
        subEvento2.setIdEvento(1);

        subEventoController.cadastrar(subEvento1);
        subEventoController.cadastrar(subEvento2);

        List<SubEvento> subEventos = subEventoController.listar();
        assertNotNull(subEventos);
        assertTrue(subEventos.size() >= 2);
    }

    @Test
    void updateSubEventoTest() {
        SubEvento subEventoAntigo = new SubEvento();
        subEventoAntigo.setTitulo("SubEvento Teste1");
        subEventoAntigo.setLocal("Local A");
        subEventoAntigo.setHorario("15:00");
        subEventoAntigo.setDescricao("Descrição Teste1");
        subEventoAntigo.setIdEvento(1); // Supondo que exista um evento com id 1

        SubEvento subEventoAntigoComId = subEventoController.cadastrar(subEventoAntigo);

        SubEvento subEventoNovo = new SubEvento();
        subEventoNovo.setTitulo("SubEvento Teste2");
        subEventoNovo.setLocal("Local B");
        subEventoNovo.setHorario("15:00");
        subEventoNovo.setDescricao("Descrição Teste2");
        subEventoNovo.setIdEvento(1);

        SubEvento subEventoAtualizado = subEventoController.atualizar(subEventoAntigoComId, subEventoNovo);

        assertEquals(subEventoNovo, subEventoAtualizado);
    }

    @Test
    void deleteSubEventoTest() {
        boolean resultado;
        SubEvento subEvento = new SubEvento();
        subEvento.setTitulo("SubEvento Teste1");
        subEvento.setLocal("Local A");
        subEvento.setHorario("15:00");
        subEvento.setDescricao("Descrição Teste1");
        subEvento.setIdEvento(1); // Supondo que exista um evento com id 1

        SubEvento subEventoAdicionado = subEventoController.cadastrar(subEvento);
        resultado = subEventoController.deletar(subEventoAdicionado);

        assertEquals(true, resultado);
    }
}
