package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.Evento;

public class EventoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public void insertEvento(Evento evento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        em.close();
    }

    public Evento selectEvento(long id) {
        EntityManager em = emf.createEntityManager();
        Evento evento = em.find(Evento.class, id);
        em.close();
        return evento;
    }

    public List<Evento> selectAllEventos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Evento> query = em.createQuery("SELECT e FROM Evento e", Evento.class);
        List<Evento> eventos = query.getResultList();
        em.close();
        return eventos;
    }

    public boolean deleteEvento(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Evento evento = em.find(Evento.class, id);
        if (evento != null) {
            em.remove(evento);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateEvento(Evento evento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Evento existingEvento = em.find(Evento.class, evento.getId());
        if (existingEvento != null) {
            existingEvento.setTitulo(evento.getTitulo());
            existingEvento.setLocal(evento.getLocal());
            existingEvento.setHorario(evento.getHorario());
            existingEvento.setDescricao(evento.getDescricao());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}