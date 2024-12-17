package repositories;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import models.Trilha;

public class TrilhaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public void insertTrilha(Trilha trilha) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(trilha);
        em.getTransaction().commit();
        em.close();
    }

    public Trilha selectTrilha(long id) {
        EntityManager em = emf.createEntityManager();
        Trilha trilha = em.find(Trilha.class, id);
        em.close();
        return trilha;
    }

    public List<Trilha> selectAllTrilhas() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Trilha> query = em.createQuery("SELECT t FROM Trilha t", Trilha.class);
        List<Trilha> trilhas = query.getResultList();
        em.close();
        return trilhas;
    }

    public boolean deleteTrilha(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Trilha trilha = em.find(Trilha.class, id);
        if (trilha != null) {
            em.remove(trilha);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateTrilha(Trilha trilha) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Trilha existingTrilha = em.find(Trilha.class, trilha.getId());
        if (existingTrilha != null) {
            existingTrilha.setNome(trilha.getNome());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}


