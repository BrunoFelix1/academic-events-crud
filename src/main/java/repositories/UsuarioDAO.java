package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}


