package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.TrilhaController;
import models.Trilha;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class TrilhaControllerTest {

    private static IController<Trilha> trilhaController;
    private static List<Trilha> estadoInicial;

    @BeforeEach
    void setUp() {
        trilhaController = new TrilhaController();
        // Salva o estado inicial completo do "banco" (arquivo CSV).
        estadoInicial = new ArrayList<>(trilhaController.listar());
    }

    @AfterEach
    void tearDown() {
        // Restaura o estado inicial do "banco" (arquivo CSV).
        List<Trilha> estadoAtual = new ArrayList<>(trilhaController.listar());

        // Remove trilhas adicionadas durante os testes.
        for (Trilha trilha : estadoAtual) {
            if (!estadoInicial.contains(trilha)) {
                trilhaController.deletar(trilha);
            }
        }

        // Adiciona trilhas que foram removidas durante os testes.
        for (Trilha trilha : estadoInicial) {
            if (!estadoAtual.contains(trilha)) {
                trilhaController.cadastrar(trilha);
            }
        }
    }

    @Test
    void addTrilhaTest() {
        Trilha trilha = new Trilha(999, 1, "Trilha Teste");
        int numeroTrilhas = trilhaController.listar().size();
        Trilha trilhaAdicionada = trilhaController.cadastrar(trilha);

        assertEquals(numeroTrilhas + 1, trilhaController.listar().size(), "Deveria haver 1 trilha a mais na lista.");
        assertEquals(trilha, trilhaAdicionada, "A trilha adicionada deveria ser igual à trilha cadastrada.");
    }

    @Test
    void listTrilhasTest() {
        Trilha trilha1 = new Trilha(999, 1, "Trilha 1");
        Trilha trilha2 = new Trilha(1000, 1, "Trilha 2");

        trilhaController.cadastrar(trilha1);
        trilhaController.cadastrar(trilha2);

        List<Trilha> trilhas = trilhaController.listar();
        assertEquals(estadoInicial.size() + 2, trilhas.size(), "Deveria listar todas as trilhas iniciais mais as duas novas.");
    }

    @Test
    void updateTrilhaTest() {
        Trilha trilhaAntiga = new Trilha(999, 1, "Trilha Antiga");
        Trilha trilhaNova = new Trilha(999, 1, "Trilha Atualizada");

        trilhaController.cadastrar(trilhaAntiga);
        Trilha trilhaAtualizada = trilhaController.atualizar(trilhaAntiga, trilhaNova);

        List<Trilha> trilhas = trilhaController.listar();
        assertEquals(estadoInicial.size() + 1, trilhas.size(), "Deveria haver 1 trilha na lista além das trilhas iniciais.");
        assertEquals(trilhaNova, trilhaAtualizada, "A trilha atualizada deveria ser igual à trilha nova.");
    }

    @Test
    void deleteTrilhaTest() {
        Trilha trilha = new Trilha(999, 1, "Trilha Deletável");

        trilhaController.cadastrar(trilha);
        boolean deletada = trilhaController.deletar(trilha);

        assertTrue(deletada, "Deveria retornar verdadeiro ao deletar a trilha.");
        assertEquals(estadoInicial.size(), trilhaController.listar().size(), "O banco deveria ter voltado ao estado inicial.");
    }
}
