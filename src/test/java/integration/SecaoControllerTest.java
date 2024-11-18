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
        // Salva o estado inicial completo do "banco" (arquivo CSV).
        estadoInicial = new ArrayList<>(secaoController.listar());
    }

    @AfterEach
    void tearDown() {
        // Restaura o estado inicial do "banco" (arquivo CSV).
        List<Secao> estadoAtual = new ArrayList<>(secaoController.listar());

        // Remove seções adicionadas durante os testes.
        for (Secao secao : estadoAtual) {
            if (!estadoInicial.contains(secao)) {
                secaoController.deletar(secao);
            }
        }

        // Adiciona seções que foram removidas durante os testes.
        for (Secao secao : estadoInicial) {
            if (!estadoAtual.contains(secao)) {
                secaoController.cadastrar(secao);
            }
        }
    }

    @Test
    void addSecaoTest() {
        Secao secao = new Secao(999, 1, 1, "Local A", "2024-12-01 10:00", "Descrição teste");
        int numeroSecoes = secaoController.listar().size();
        Secao secaoAdicionada = secaoController.cadastrar(secao);

        assertEquals(numeroSecoes + 1, secaoController.listar().size(), "Deveria haver 1 seção a mais na lista.");
        assertEquals(secao, secaoAdicionada, "A seção adicionada deveria ser igual à seção cadastrada.");
    }

    @Test
    void listSecoesTest() {
        Secao secao1 = new Secao(999, 1, 1, "Local A", "2024-12-02 11:00", "Descrição seção 1");
        Secao secao2 = new Secao(1000, 1, 1, "Local B", "2024-12-03 12:00", "Descrição seção 2");

        secaoController.cadastrar(secao1);
        secaoController.cadastrar(secao2);

        List<Secao> secoes = secaoController.listar();
        assertEquals(estadoInicial.size() + 2, secoes.size(), "Deveria listar todas as seções iniciais mais as duas novas.");
    }

    @Test
    void updateSecaoTest() {
        Secao secaoAntiga = new Secao(999, 1, 1, "Local C", "2024-12-04 13:00", "Descrição original");
        Secao secaoNova = new Secao(999, 1, 1, "Local D", "2024-12-05 14:00", "Descrição atualizada");

        secaoController.cadastrar(secaoAntiga);
        Secao secaoAtualizada = secaoController.atualizar(secaoAntiga, secaoNova);

        List<Secao> secoes = secaoController.listar();
        assertEquals(estadoInicial.size() + 1, secoes.size(), "Deveria haver 1 seção na lista além das seções iniciais.");
        assertEquals(secaoNova, secaoAtualizada, "A seção atualizada deveria ser igual à seção nova.");
    }

    @Test
    void deleteSecaoTest() {
        Secao secao = new Secao(999, 1, 1, "Local E", "2024-12-06 15:00", "Descrição deletável");

        secaoController.cadastrar(secao);
        boolean deletada = secaoController.deletar(secao);

        assertTrue(deletada, "Deveria retornar verdadeiro ao deletar a seção.");
        assertEquals(estadoInicial.size(), secaoController.listar().size(), "O banco deveria ter voltado ao estado inicial.");
    }
}
