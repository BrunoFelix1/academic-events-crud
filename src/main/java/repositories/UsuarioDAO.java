package repositories;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import models.Usuario;

public class UsuarioDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit");

    public boolean insertUser(Usuario user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public Usuario selectUser(long id) {
        EntityManager em = emf.createEntityManager();
        Usuario user = em.find(Usuario.class, id);
        em.close();
        return user;
    }

    public List<Usuario> selectAllUsers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> users = query.getResultList();
        em.close();
        return users;
    }

    public boolean deleteUser(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario user = em.find(Usuario.class, id);
        if (user != null) {
            em.remove(user);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }

    public boolean updateUser(Usuario user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario existingUser = em.find(Usuario.class, user.getId());
        if (existingUser != null) {
            existingUser.setLogin(user.getLogin());
            existingUser.setSenha(user.getSenha());
            em.getTransaction().commit();
            em.close();
            return true;
        }
        em.getTransaction().rollback();
        em.close();
        return false;
    }
}


