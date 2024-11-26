package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.SecaoController;
import models.Secao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SecaoControllerTest {

    private static IController<Secao> secaoController;
    private static List<Secao> estadoInicial;

    @BeforeEach
    void setUp() {
        secaoController = new SecaoController();
        estadoInicial = new ArrayList<>(secaoController.listar());
    }
    
    @AfterEach
    void tearDown() {
        // Obtém o estado atual do banco de dados após o teste.
        List<Secao> estadoAtual = new ArrayList<>(secaoController.listar());
    
        // Identifica e remove seções que foram adicionadas durante os testes.
        for (Secao secao : estadoAtual) {
            if (!estadoInicial.contains(secao)) {
                secaoController.deletar(secao);
            }
        }
    }

    @Test
    void addSecaoTest() {
        Secao secao = new Secao();
        secao.setNome("Secao Teste");
        secao.setLocal("Local A");
        secao.setHorario("14:00");
        secao.setDescricao("Descrição Teste");
        secao.setId_evento(1); // Supondo que exista um evento com id 1

        Secao secaoAdicionada = secaoController.cadastrar(secao);
        assertEquals(secao, secaoAdicionada);
    }

    @Test
    void listSecaoTest() {
        Secao secao1 = new Secao();
        secao1.setNome("Secao Teste1");
        secao1.setLocal("Local A");
        secao1.setHorario("14:00");
        secao1.setDescricao("Descrição Teste1");
        secao1.setId_evento(1); // Supondo que exista um evento com id 1

        Secao secao2 = new Secao();
        secao2.setNome("Secao Teste2");
        secao2.setLocal("Local B");
        secao2.setHorario("14:00");
        secao2.setDescricao("Descrição Teste2");
        secao2.setId_evento(1);

        secaoController.cadastrar(secao1);
        secaoController.cadastrar(secao2);

        List<Secao> secoes = secaoController.listar();
        assertNotNull(secoes);
        assertTrue(secoes.size() >= 2);
    }

    @Test
    void updateSecaoTest() {
        Secao secaoAntiga = new Secao();
        secaoAntiga.setNome("Secao Teste1");
        secaoAntiga.setLocal("Local A");
        secaoAntiga.setHorario("14:00");
        secaoAntiga.setDescricao("Descrição Teste1");
        secaoAntiga.setId_evento(1); // Supondo que exista um evento com id 1

        Secao secaoAntigaComId = secaoController.cadastrar(secaoAntiga);

        Secao secaoNova = new Secao();
        secaoNova.setNome("Secao Teste2");
        secaoNova.setLocal("Local B");
        secaoNova.setHorario("14:00");
        secaoNova.setDescricao("Descrição Teste2");
        secaoNova.setId_evento(1);

        Secao secaoAtualizada = secaoController.atualizar(secaoAntigaComId, secaoNova);

        assertEquals(secaoNova, secaoAtualizada);
    }

    @Test
    void deleteSecaoTest() {
        boolean resultado;
        Secao secao = new Secao();
        secao.setNome("Secao Teste1");
        secao.setLocal("Local A");
        secao.setHorario("14:00");
        secao.setDescricao("Descrição Teste1");
        secao.setId_evento(1); // Supondo que exista um evento com id 1

        Secao secaoAdicionada = secaoController.cadastrar(secao);
        resultado = secaoController.deletar(secaoAdicionada);

        assertEquals(true, resultado);
    }
}
