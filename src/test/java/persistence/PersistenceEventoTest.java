package persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import Models.Evento;

public class PersistenceEventoTest {

    private PersistenceEvento persistenceEvento;
    private Evento Evento1;
    private Evento Evento2;

    @BeforeEach
    public void setUp() {
        persistenceEvento = new PersistenceEvento();
        Evento1 = new Evento();
        Evento1.setId(-1);
        Evento1.setTitulo("Congresso Romano");
        Evento1.setHorario("13:20");
        Evento1.setLocal("ITA");
        Evento1.setDescricao("Congresso aeronáutico");

        Evento2 = new Evento();
        Evento2.setId(-2);
        Evento2.setTitulo("Feira");
        Evento2.setHorario("Horario 2");
        Evento2.setLocal("Local 2");
        Evento2.setDescricao("Descrição 2");
    }

    @Test
    public void testAddEvento() {
        boolean teste = false;
        persistenceEvento.add(Evento1);
        ArrayList<Evento> Eventos = persistenceEvento.getTodos();
        for (Evento Evento : Eventos){
            if (Evento.getId() == Evento1.getId()){
                teste = true;
            }
        }
        assertTrue(teste, "O Evento deveria estar presente na lista.");
    }

    @Test
    public void testDeleteEvento() {
        persistenceEvento.add(Evento1);
        persistenceEvento.delete(Evento1);
        ArrayList<Evento> Eventos = persistenceEvento.getTodos();
        assertFalse(Eventos.contains(Evento1), "O Evento deveria ter sido removida.");
    }

    @Test
    public void testUpdateEvento() {
        persistenceEvento.add(Evento1);
        persistenceEvento.update(Evento1, Evento2);
        Evento EventoAtualizada = persistenceEvento.getTodos().getLast();
        assertEquals("Feira", EventoAtualizada.getTitulo(), "O tipo de submissão deveria ter sido atualizado.");
        assertEquals("Local 2", EventoAtualizada.getLocal(), "O autor deveria ter sido atualizado.");
    }

    @Test
    public void testGetPorId() {
        persistenceEvento.add(Evento1);
        Evento EventoBuscada = persistenceEvento.getPorId(-1);
        assertNotNull(EventoBuscada, "O Evento com ID 1 deveria existir.");
        assertEquals(Evento1.getId(), EventoBuscada.getId(), "Os IDs deveriam ser iguais.");
    }

    @Test
    public void testGetTodos() {
        persistenceEvento.add(Evento1);
        persistenceEvento.add(Evento2);
        ArrayList<Evento> Eventos = persistenceEvento.getTodos();
        //Lembrar que a quantidade ali embaixo depende da quantidade de pessoas atualmente cadastradas
        assertEquals(4, Eventos.size(), "Deveriam existir 4 Eventos na lista.");
    }

    @AfterEach
    public void tearDown(){
        persistenceEvento.delete(Evento1);
        persistenceEvento.delete(Evento2);
    }
}
