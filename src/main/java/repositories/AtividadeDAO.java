package repositories;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import models.Atividade;

public class AtividadeDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public boolean insertAtividade(Atividade atividade) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(atividade);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Atividade selectAtividade(long id) {
        EntityManager em = emf.createEntityManager();
        Atividade atividade = em.find(Atividade.class, id);
        em.close();
        return atividade;
    }

    public List<Atividade> selectAllAtividades() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Atividade> query = em.createQuery("SELECT a FROM Atividade a", Atividade.class);
        List<Atividade> atividades = query.getResultList();
        em.close();
        return atividades;
    }

    public boolean deleteAtividade(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Atividade atividade = em.find(Atividade.class, id);
        if (atividade != null) {
            em.remove(atividade);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateAtividade(Atividade atividade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Atividade existingAtividade = em.find(Atividade.class, atividade.getId());
        if (existingAtividade != null) {
            existingAtividade.setTipoDeAtividade(atividade.getTipoDeAtividade());
            existingAtividade.setAutor(atividade.getAutor());
            existingAtividade.setResumo(atividade.getResumo());
            existingAtividade.setTrilha(atividade.getTrilha());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}


