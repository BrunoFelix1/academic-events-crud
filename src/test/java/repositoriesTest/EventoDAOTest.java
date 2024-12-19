package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import models.Evento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.EventoDAO;

class EventoDAOTest {

    private EntityManagerFactory emf;
    private EntityManager entityManager;
    private EventoDAO eventoDAO;
    private Evento evento;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("your-persistence-unit");
        entityManager = emf.createEntityManager();
        eventoDAO = new EventoDAO();

        evento = new Evento("Título Teste", "Local Teste", "12:00", "Descrição Teste");

        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Evento").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }

    @Test
    void testInsertEvento() {
        Evento novoEvento = new Evento("Novo Título", "Novo Local", "15:00", "Nova Descrição");

        boolean result = eventoDAO.insertEvento(novoEvento);

        assertTrue(result);

        Evento eventoPersistido = entityManager.find(Evento.class, novoEvento.getId());
        assertNotNull(eventoPersistido);
        assertEquals("Novo Título", eventoPersistido.getTitulo());
    }

    @Test
    void testSelectEvento() {
        Evento eventoSelecionado = eventoDAO.selectEvento(evento.getId());

        assertNotNull(eventoSelecionado);
        assertEquals(evento.getTitulo(), eventoSelecionado.getTitulo());
    }

    @Test
    void testSelectAllEventos() {
        List<Evento> eventos = eventoDAO.selectAllEventos();

        assertNotNull(eventos);
        assertFalse(eventos.isEmpty());
        assertEquals(1, eventos.size());
        assertEquals(evento.getTitulo(), eventos.get(0).getTitulo());
    }


    @Test
    void testDeleteEvento() {
        boolean result = eventoDAO.deleteEvento(evento.getId());

        assertTrue(result);
        entityManager.clear();
        Evento eventoDeletado = entityManager.find(Evento.class, evento.getId());
        assertNull(eventoDeletado);
    }

    @Test
    void testUpdateEvento() {
        evento.setTitulo("Título Atualizado");

        boolean result = eventoDAO.updateEvento(evento);

        assertTrue(result);

        Evento eventoAtualizado = entityManager.find(Evento.class, evento.getId());
        assertNotNull(eventoAtualizado);
        assertEquals("Título Atualizado", eventoAtualizado.getTitulo());
    }
}
