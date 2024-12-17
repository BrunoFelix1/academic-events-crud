package repositories;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import models.Secao;

public class SecaoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public boolean insertSecao(Secao secao) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(secao);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Secao selectSecao(long id) {
        EntityManager em = emf.createEntityManager();
        Secao secao = em.find(Secao.class, id);
        em.close();
        return secao;
    }

    public List<Secao> selectAllSecoes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Secao> query = em.createQuery("SELECT s FROM Secao s", Secao.class);
        List<Secao> secoes = query.getResultList();
        em.close();
        return secoes;
    }

    public boolean deleteSecao(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Secao secao = em.find(Secao.class, id);
        if (secao != null) {
            em.remove(secao);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateSecao(Secao secao) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Secao existingSecao = em.find(Secao.class, secao.getId());
        if (existingSecao != null) {
            existingSecao.setNome(secao.getNome());
            existingSecao.setDescricao(secao.getDescricao());
            existingSecao.setLocal(secao.getLocal());
            existingSecao.setHorario(secao.getHorario());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}
