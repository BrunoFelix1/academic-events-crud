package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.SubEvento;

public interface SubEventoDAO extends JpaRepository<SubEvento, Long> {
}

