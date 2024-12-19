package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import models.Evento;
import models.Secao;
import models.SubEvento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.SecaoDAO;

class SecaoDAOTest {

    private EntityManagerFactory emf;
    private EntityManager entityManager;
    private SecaoDAO secaoDAO;
    private Evento evento;
    private SubEvento subEvento;
    private Secao secao;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("your-persistence-unit");
        entityManager = emf.createEntityManager();
        secaoDAO = new SecaoDAO();

        evento = new Evento("Evento Teste", "Local Evento", "10:00", "Descrição Evento");
        subEvento = new SubEvento(evento, "SubEvento Teste", "Local SubEvento", "12:00", "Descrição SubEvento");
        secao = new Secao(evento, subEvento, "Secao Teste", "Local Secao", "14:00");

        // Persistindo evento e subevento para criar relação com a seção
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.persist(subEvento);
        entityManager.persist(secao);
        entityManager.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Secao").executeUpdate();
        entityManager.createQuery("DELETE FROM SubEvento").executeUpdate();
        entityManager.createQuery("DELETE FROM Evento").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }

    @Test
    void testInsertSecao() {
        Secao novaSecao = new Secao(evento, subEvento, "Nova Secao", "Local Nova Secao", "16:00");

        boolean result = secaoDAO.insertSecao(novaSecao);

        assertTrue(result);

        Secao secaoPersistida = entityManager.find(Secao.class, novaSecao.getId());
        assertNotNull(secaoPersistida);
        assertEquals("Nova Secao", secaoPersistida.getNome());
    }

    @Test
    void testSelectSecao() {
        Secao secaoSelecionada = secaoDAO.selectSecao(secao.getId());

        assertNotNull(secaoSelecionada);
        assertEquals(secao.getNome(), secaoSelecionada.getNome());
    }

    @Test
    void testSelectAllSecoes() {
        List<Secao> secoes = secaoDAO.selectAllSecoes();

        assertNotNull(secoes);
        assertFalse(secoes.isEmpty());
        assertEquals(1, secoes.size());
        assertEquals(secao.getNome(), secoes.get(0).getNome());
    }

    @Test
    void testDeleteSecao() {
        boolean result = secaoDAO.deleteSecao(secao.getId());

        assertTrue(result);

        entityManager.clear(); // Garante que o estado do EntityManager está sincronizado
        Secao secaoDeletada = entityManager.find(Secao.class, secao.getId());
        assertNull(secaoDeletada);
    }

    @Test
    void testUpdateSecao() {
        secao.setNome("Secao Atualizada");
        secao.setDescricao("Descrição Atualizada");

        boolean result = secaoDAO.updateSecao(secao);

        assertTrue(result);

        Secao secaoAtualizada = entityManager.find(Secao.class, secao.getId());
        assertNotNull(secaoAtualizada);
        assertEquals("Secao Atualizada", secaoAtualizada.getNome());
        assertEquals("Descrição Atualizada", secaoAtualizada.getDescricao());
    }
}
