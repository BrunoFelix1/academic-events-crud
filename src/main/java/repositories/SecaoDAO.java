package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Secao;

public interface SecaoDAO extends JpaRepository<Secao, Long> {
}
