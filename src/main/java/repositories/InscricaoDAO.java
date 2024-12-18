package repositories;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import models.Inscricao;

public class InscricaoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public boolean insertInscricao(Inscricao inscricao) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(inscricao);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Inscricao selectInscricao(Long id) {
        EntityManager em = emf.createEntityManager();
        Inscricao inscricao = em.find(Inscricao.class, id);
        em.close();
        return inscricao;
    }

    public List<Inscricao> selectAllInscricoes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Inscricao> query = em.createQuery("SELECT i FROM Inscricao i", Inscricao.class);
        List<Inscricao> inscricoes = query.getResultList();
        em.close();
        return inscricoes;
    }

    public boolean deleteInscricao(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Inscricao inscricao = em.find(Inscricao.class, id);
        if (inscricao != null) {
            em.remove(inscricao);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateInscricao(Inscricao inscricao) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Inscricao existingInscricao = em.find(Inscricao.class, inscricao.getId());
        if (existingInscricao != null) {
            existingInscricao.setUsuario(inscricao.getUsuario());
            existingInscricao.setEvento(inscricao.getEvento());
            existingInscricao.setSubEvento(inscricao.getSubEvento());
            existingInscricao.setSecao(inscricao.getSecao());
            existingInscricao.setTrilha(inscricao.getTrilha());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public List<Inscricao> findByUsuarioId(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Inscricao> query = em.createQuery("SELECT i FROM Inscricao i WHERE i.usuario.id = :usuarioId", Inscricao.class);
        query.setParameter("usuarioId", usuarioId);
        List<Inscricao> inscricoes = query.getResultList();
        em.close();
        return inscricoes;
    }
}


