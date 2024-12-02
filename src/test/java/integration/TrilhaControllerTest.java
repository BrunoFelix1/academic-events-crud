package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import controllers.IController;
import controllers.TrilhaController;
import models.Trilha;

public class TrilhaControllerTest extends BaseControllerTest<Trilha> {

    @Override
    protected IController<Trilha> createController() {
        return new TrilhaController();
    }

    @Test
    void addTrilhaTest() {
        Trilha trilha = new Trilha();
        trilha.setNome("Trilha Teste");
        trilha.setIdSecao(1); 
        Trilha trilhaAdicionada = controller.cadastrar(trilha);
        assertEquals(trilha, trilhaAdicionada);
    }

    @Test
    void listTrilhasTest() {
        Trilha trilha1 = new Trilha();
        trilha1.setNome("Trilha Teste1");
        trilha1.setIdSecao(1); 

        Trilha trilha2 = new Trilha();
        trilha2.setNome("Trilha Teste2");
        trilha2.setIdSecao(1);

        controller.cadastrar(trilha1);
        controller.cadastrar(trilha2);

        List<Trilha> trilhas = controller.listar();
        assertNotNull(trilhas);
        assertTrue(trilhas.size() >= 2);
    }

    @Test
    void updateTrilhaTest() {
        Trilha trilhaAntiga = new Trilha();
        trilhaAntiga.setNome("Trilha Teste1");
        trilhaAntiga.setIdSecao(1); 

        Trilha trilhaAntigaComId = controller.cadastrar(trilhaAntiga);

        Trilha trilhaNova = new Trilha();
        trilhaNova.setNome("Trilha Teste2");
        trilhaNova.setIdSecao(1);

        Trilha trilhaAtualizada = controller.atualizar(trilhaAntigaComId, trilhaNova);

        assertEquals(trilhaNova, trilhaAtualizada);
    }

    @Test
    void deleteTrilhaTest() {
        Trilha trilha = new Trilha();
        trilha.setNome("Trilha Teste1");
        trilha.setIdSecao(1); 

        Trilha trilhaComId = controller.cadastrar(trilha);

        boolean resultado = controller.deletar(trilhaComId);
        assertTrue(resultado);

        List<Trilha> trilhas = controller.listar();
        assertFalse(trilhas.contains(trilhaComId));
    }
}