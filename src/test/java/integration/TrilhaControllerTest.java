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
        estadoInicial = new ArrayList<>(trilhaController.listar());
    }
    
    @AfterEach
    void tearDown() {
        // Obtém o estado atual do banco de dados após o teste.
        List<Trilha> estadoAtual = new ArrayList<>(trilhaController.listar());
    
        // Identifica e remove trilhas que foram adicionadas durante os testes.
        for (Trilha trilha : estadoAtual) {
            if (!estadoInicial.contains(trilha)) {
                trilhaController.deletar(trilha);
            }
        }
    }

    @Test
    void addTrilhaTest() {
        Trilha trilha = new Trilha();
        trilha.setNome("Trilha Teste");
        trilha.setIdSecao(1); // Supondo que exista uma seção com id 1

        Trilha trilhaAdicionada = trilhaController.cadastrar(trilha);
        assertEquals(trilha, trilhaAdicionada);
    }

    @Test
    void listTrilhasTest() {
        Trilha trilha1 = new Trilha();
        trilha1.setNome("Trilha Teste1");
        trilha1.setIdSecao(1); // Supondo que exista uma seção com id 1

        Trilha trilha2 = new Trilha();
        trilha2.setNome("Trilha Teste2");
        trilha2.setIdSecao(1);

        trilhaController.cadastrar(trilha1);
        trilhaController.cadastrar(trilha2);

        List<Trilha> trilhas = trilhaController.listar();
        assertNotNull(trilhas);
        assertTrue(trilhas.size() >= 2);
    }

    @Test
    void updateTrilhaTest() {
        Trilha trilhaAntiga = new Trilha();
        trilhaAntiga.setNome("Trilha Teste1");
        trilhaAntiga.setIdSecao(1); // Supondo que exista uma seção com id 1

        Trilha trilhaAntigaComId = trilhaController.cadastrar(trilhaAntiga);

        Trilha trilhaNova = new Trilha();
        trilhaNova.setNome("Trilha Teste2");
        trilhaNova.setIdSecao(1);

        Trilha trilhaAtualizada = trilhaController.atualizar(trilhaAntigaComId, trilhaNova);

        assertEquals(trilhaNova, trilhaAtualizada);
    }

    @Test
    void deleteTrilhaTest() {
        boolean resultado;
        Trilha trilha = new Trilha();
        trilha.setNome("Trilha Teste1");
        trilha.setIdSecao(1); // Supondo que exista uma seção com id 1

        Trilha trilhaAdicionada = trilhaController.cadastrar(trilha);
        resultado = trilhaController.deletar(trilhaAdicionada);

        assertEquals(true, resultado);
    }
}
