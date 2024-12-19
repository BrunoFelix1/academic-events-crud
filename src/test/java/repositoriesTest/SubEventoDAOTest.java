package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import models.Evento;
import models.SubEvento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.SubEventoDAO;

class SubEventoDAOTest {

    private EntityManagerFactory emf;
    private EntityManager entityManager;
    private SubEventoDAO subEventoDAO;
    private SubEvento subEvento;
    private Evento evento;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("your-persistence-unit");
        entityManager = emf.createEntityManager();
        subEventoDAO = new SubEventoDAO();

        // Criar um evento para associar ao subevento
        evento = new Evento("Evento Teste", "Local Evento", "14:00", "Descrição do Evento");
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();

        // Criar um subevento
        subEvento = new SubEvento(evento, "SubEvento Teste", "Local SubEvento", "16:00", "Descrição do SubEvento");
        entityManager.getTransaction().begin();
        entityManager.persist(subEvento);
        entityManager.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM SubEvento").executeUpdate();
        entityManager.createQuery("DELETE FROM Evento").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }

    @Test
    void testInsertSubEvento() {
        SubEvento novoSubEvento = new SubEvento(evento, "Novo SubEvento", "Novo Local", "18:00", "Nova Descrição");

        boolean result = subEventoDAO.insertSubEvento(novoSubEvento);

        assertTrue(result);

        SubEvento subEventoPersistido = entityManager.find(SubEvento.class, novoSubEvento.getId());
        assertNotNull(subEventoPersistido);
        assertEquals("Novo SubEvento", subEventoPersistido.getNome());
    }

    @Test
    void testSelectSubEvento() {
        SubEvento subEventoSelecionado = subEventoDAO.selectSubEvento(subEvento.getId());

        assertNotNull(subEventoSelecionado);
        assertEquals(subEvento.getNome(), subEventoSelecionado.getNome());
    }

    @Test
    void testSelectAllSubEventos() {
        List<SubEvento> subEventos = subEventoDAO.selectAllSubEventos();

        assertNotNull(subEventos);
        assertFalse(subEventos.isEmpty());
        assertEquals(1, subEventos.size());
        assertEquals(subEvento.getNome(), subEventos.get(0).getNome());
    }

    @Test
    void testDeleteSubEvento() {
        boolean result = subEventoDAO.deleteSubEvento(subEvento.getId());

        assertTrue(result);

        // Sincroniza o estado do banco antes de tentar encontrar o objeto
        entityManager.clear();
        SubEvento subEventoDeletado = entityManager.find(SubEvento.class, subEvento.getId());
        assertNull(subEventoDeletado);
    }


    @Test
    void testUpdateSubEvento() {
        subEvento.setNome("SubEvento Atualizado");

        boolean result = subEventoDAO.updateSubEvento(subEvento);

        assertTrue(result);

        SubEvento subEventoAtualizado = entityManager.find(SubEvento.class, subEvento.getId());
        assertNotNull(subEventoAtualizado);
        assertEquals("SubEvento Atualizado", subEventoAtualizado.getNome());
    }
}
