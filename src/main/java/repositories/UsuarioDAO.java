package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}


