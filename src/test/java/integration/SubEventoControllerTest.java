package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.SubeventoController;
import models.SubEvento;

public class SubEventoControllerTest extends BaseControllerTest<SubEvento> {

    @Override
    protected IController<SubEvento> createController() {
        return new SubeventoController();
    }

    @Test
    void listSubEventosTest() {
        SubEvento subEvento1 = new SubEvento();
        subEvento1.setTitulo("SubEvento Teste1");
        subEvento1.setLocal("Local A");
        subEvento1.setHorario("15:00");
        subEvento1.setDescricao("Descrição Teste1");
        subEvento1.setIdEvento(1);

        SubEvento subEvento2 = new SubEvento();
        subEvento2.setTitulo("SubEvento Teste2");
        subEvento2.setLocal("Local B");
        subEvento2.setHorario("15:00");
        subEvento2.setDescricao("Descrição Teste2");
        subEvento2.setIdEvento(1);

        controller.cadastrar(subEvento1);
        controller.cadastrar(subEvento2);

        List<SubEvento> subEventos = controller.listar();
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

        SubEvento subEventoAntigoComId = controller.cadastrar(subEventoAntigo);

        SubEvento subEventoNovo = new SubEvento();
        subEventoNovo.setTitulo("SubEvento Teste2");
        subEventoNovo.setLocal("Local B");
        subEventoNovo.setHorario("15:00");
        subEventoNovo.setDescricao("Descrição Teste2");
        subEventoNovo.setIdEvento(1);

        SubEvento subEventoAtualizado = controller.atualizar(subEventoAntigoComId, subEventoNovo);

        assertEquals(subEventoNovo, subEventoAtualizado);
    }

    @Test
    void deleteSubEventoTest() {
        SubEvento subEvento = new SubEvento();
        subEvento.setTitulo("SubEvento Teste1");
        subEvento.setLocal("Local A");
        subEvento.setHorario("15:00");
        subEvento.setDescricao("Descrição Teste1");
        subEvento.setIdEvento(1);

        SubEvento subEventoComId = controller.cadastrar(subEvento);

        boolean resultado = controller.deletar(subEventoComId);
        assertTrue(resultado);

        List<SubEvento> subEventos = controller.listar();
        assertFalse(subEventos.contains(subEventoComId));
    }

}