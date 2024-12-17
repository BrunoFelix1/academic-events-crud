package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.SubEvento;

public class SubEventoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public void insertSubEvento(SubEvento subEvento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(subEvento);
        em.getTransaction().commit();
        em.close();
    }

    public SubEvento selectSubEvento(long id) {
        EntityManager em = emf.createEntityManager();
        SubEvento subEvento = em.find(SubEvento.class, id);
        em.close();
        return subEvento;
    }

    public List<SubEvento> selectAllSubEventos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<SubEvento> query = em.createQuery("SELECT s FROM SubEvento s", SubEvento.class);
        List<SubEvento> subEventos = query.getResultList();
        em.close();
        return subEventos;
    }

    public boolean deleteSubEvento(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SubEvento subEvento = em.find(SubEvento.class, id);
        if (subEvento != null) {
            em.remove(subEvento);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateSubEvento(SubEvento subEvento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        SubEvento existingSubEvento = em.find(SubEvento.class, subEvento.getId());
        if (existingSubEvento != null) {
            existingSubEvento.setTitulo(subEvento.getTitulo());
            existingSubEvento.setLocal(subEvento.getLocal());
            existingSubEvento.setHorario(subEvento.getHorario());
            existingSubEvento.setDescricao(subEvento.getDescricao());
            existingSubEvento.setNome(subEvento.getNome());
            existingSubEvento.setData(subEvento.getData());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}

