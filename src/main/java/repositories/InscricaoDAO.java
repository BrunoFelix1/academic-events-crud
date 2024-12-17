package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.Inscricao;
import models.InscricaoId;

public class InscricaoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public void insertInscricao(Inscricao inscricao) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(inscricao);
        em.getTransaction().commit();
        em.close();
    }

    public Inscricao selectInscricao(InscricaoId id) {
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

    public boolean deleteInscricao(InscricaoId id) {
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


