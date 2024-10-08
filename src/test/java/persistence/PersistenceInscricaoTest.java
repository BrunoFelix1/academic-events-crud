package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Models.Inscricao;

public class PersistenceInscricaoTest {

    private PersistenceInscricao persistenceInscricao;
    private Inscricao Inscricao1;
    private Inscricao Inscricao2;

    @BeforeEach
    public void setUp() {
        persistenceInscricao = new PersistenceInscricao();
        Inscricao1 = new Inscricao();
        Inscricao1.setIdUsuario(-1);
        Inscricao1.setIdEvento(-1);
        Inscricao1.setIdSubEvento(-1);
        Inscricao1.setIdSecao(-1);
        Inscricao1.setIdTrilha(-1);

        Inscricao2 = new Inscricao();
        Inscricao2.setIdUsuario(-2);
        Inscricao2.setIdEvento(-2);
        Inscricao2.setIdSubEvento(-2);
        Inscricao2.setIdSecao(-2);
        Inscricao2.setIdTrilha(-2);
    }

    @Test
    public void testAddInscricao() {
        boolean teste = false;
        persistenceInscricao.add(Inscricao1);
        ArrayList<Inscricao> Inscricaos = persistenceInscricao.getTodos();
        for (Inscricao Inscricao : Inscricaos){
            if (Inscricao.getIdUsuario() == Inscricao1.getIdUsuario() &&
                    Inscricao.getIdEvento() == Inscricao1.getIdEvento() &&
                    Inscricao.getIdSecao() == Inscricao1.getIdSecao() &&
                    Inscricao.getIdSubEvento() == Inscricao1.getIdSubEvento() &&
                    Inscricao.getIdTrilha() == Inscricao1.getIdTrilha() &&
                    Inscricao.getIdUsuario() == Inscricao1.getIdUsuario()){
                teste = true;
            }
        }
        assertTrue(teste, "O Inscricao deveria estar presente na lista.");
    }

    @Test
    public void testDeleteInscricao() {
        persistenceInscricao.add(Inscricao1);
        persistenceInscricao.delete(Inscricao1);
        ArrayList<Inscricao> Inscricaos = persistenceInscricao.getTodos();
        assertFalse(Inscricaos.contains(Inscricao1), "O Inscricao deveria ter sido removida.");
    }

    @Test
    public void testUpdateInscricao() {
        persistenceInscricao.add(Inscricao1);
        persistenceInscricao.update(Inscricao1, Inscricao2);
        Inscricao atualizada = persistenceInscricao.getTodos().getLast();
        assertEquals(-2, atualizada.getIdEvento(), "O tipo de evento deveria ter sido atualizado.");
        assertEquals(-2, atualizada.getIdSecao(), "O seção deveria ter sido atualizado.");
    }

    @Test
    public void testGetTodos() {
        persistenceInscricao.add(Inscricao1);
        persistenceInscricao.add(Inscricao2);
        ArrayList<Inscricao> Inscricaos = persistenceInscricao.getTodos();
        //Lembrar que a quantidade ali embaixo depende da quantidade de pessoas atualmente cadastradas
        assertEquals(6, Inscricaos.size(), "Deveriam existir 4 Inscricaos na lista.");
    }

    @AfterEach
    public void tearDown(){
        persistenceInscricao.delete(Inscricao1);
        persistenceInscricao.delete(Inscricao2);
    }

}
