package Persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import Models.Atividade;

public class PersistenceAtividadeTest {

    private PersistenceAtividade persistenceAtividade;
    private Atividade atividade1;
    private Atividade atividade2;

    @BeforeEach
    public void setUp() {
        persistenceAtividade = new PersistenceAtividade();
        atividade1 = new Atividade();
        atividade1.setId(-1);
        atividade1.setTipoSubmissao("Artigo");
        atividade1.setAutor("Autor 1");
        atividade1.setResumo("Resumo 1");
        atividade1.setIdTrilha(101);

        atividade2 = new Atividade();
        atividade2.setId(-2);
        atividade2.setTipoSubmissao("Palestra");
        atividade2.setAutor("Autor 2");
        atividade2.setResumo("Resumo 2");
        atividade2.setIdTrilha(102);
    }

    @Test
    public void testAddAtividade() {
        boolean teste = false;
        persistenceAtividade.add(atividade1);
        ArrayList<Atividade> atividades = persistenceAtividade.getTodos();
        for (Atividade atividade : atividades){
            if (atividade.getId() == atividade1.getId()){
                teste = true;
            }
        }
        assertTrue(teste, "A atividade deveria estar presente na lista.");
    }

    @Test
    public void testDeleteAtividade() {
        persistenceAtividade.add(atividade1);
        persistenceAtividade.delete(atividade1);
        ArrayList<Atividade> atividades = persistenceAtividade.getTodos();
        assertFalse(atividades.contains(atividade1), "A atividade deveria ter sido removida.");
    }

    @Test
    public void testUpdateAtividade() {
        persistenceAtividade.add(atividade1);
        persistenceAtividade.update(atividade1, atividade2);
        Atividade atividadeAtualizada = persistenceAtividade.getTodos().getLast();
        assertEquals("Palestra", atividadeAtualizada.getTipoSubmissao(), "O tipo de submissão deveria ter sido atualizado.");
        assertEquals("Autor 2", atividadeAtualizada.getAutor(), "O autor deveria ter sido atualizado.");
    }

    @Test
    public void testGetPorId() {
        persistenceAtividade.add(atividade1);
        Atividade atividadeBuscada = persistenceAtividade.getPorId(-1);
        assertNotNull(atividadeBuscada, "A atividade com ID 1 deveria existir.");
        assertEquals(atividade1.getId(), atividadeBuscada.getId(), "Os IDs deveriam ser iguais.");
    }

    @Test
    public void testGetTodos() {
        persistenceAtividade.add(atividade1);
        persistenceAtividade.add(atividade2);
        ArrayList<Atividade> atividades = persistenceAtividade.getTodos();
        //Lembrar que a quantidade ali embaixo depende da quantidade de pessoas atualmente cadastradas
        assertEquals(4, atividades.size(), "Deveriam existir 4 atividades na lista.");
    }

    @AfterEach
    public void tearDown(){
        persistenceAtividade.delete(atividade1);
        persistenceAtividade.delete(atividade2);
    }
}
