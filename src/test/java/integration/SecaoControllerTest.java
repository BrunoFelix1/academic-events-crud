package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.SecaoController;
import models.Secao;

public class SecaoControllerTest extends BaseControllerTest<Secao> {

    @Override
    protected IController<Secao> createController() {
        return new SecaoController();
    }

    @Test
    void listSecoesTest() {
        Secao secao1 = new Secao();
        secao1.setTitulo("Secao Teste1");
        secao1.setLocal("Local A");
        secao1.setHorario("15:00");
        secao1.setDescricao("Descrição Teste1");
        secao1.setId_evento(1);;

        Secao secao2 = new Secao();
        secao2.setTitulo("Secao Teste2");
        secao2.setLocal("Local B");
        secao2.setHorario("15:00");
        secao2.setDescricao("Descrição Teste2");
        secao2.setId_evento(2);;

        controller.cadastrar(secao1);
        controller.cadastrar(secao2);

        List<Secao> secoes = controller.listar();
        assertNotNull(secoes);
        assertTrue(secoes.size() >= 2);
    }

    @Test
    void updateSecaoTest() {
        Secao secaoAntiga = new Secao();
        secaoAntiga.setTitulo("Secao Teste1");
        secaoAntiga.setLocal("Local A");
        secaoAntiga.setHorario("15:00");
        secaoAntiga.setDescricao("Descrição Teste1");
        secaoAntiga.setId_evento(1);; // Supondo que exista um evento com id 1

        Secao secaoAntigaComId = controller.cadastrar(secaoAntiga);

        Secao secaoNova = new Secao();
        secaoNova.setTitulo("Secao Teste2");
        secaoNova.setLocal("Local B");
        secaoNova.setHorario("15:00");
        secaoNova.setDescricao("Descrição Teste2");
        secaoNova.setId_evento(1);;

        Secao secaoAtualizada = controller.atualizar(secaoAntigaComId, secaoNova);

        assertEquals(secaoNova, secaoAtualizada);
    }

    @Test
    void deleteSecaoTest() {
        Secao secao = new Secao();
        secao.setTitulo("Secao Teste1");
        secao.setLocal("Local A");
        secao.setHorario("15:00");
        secao.setDescricao("Descrição Teste1");
        secao.setId_evento(1);;

        Secao secaoComId = controller.cadastrar(secao);

        boolean resultado = controller.deletar(secaoComId);
        assertTrue(resultado);

        List<Secao> secoes = controller.listar();
        assertFalse(secoes.contains(secaoComId));
    }

}