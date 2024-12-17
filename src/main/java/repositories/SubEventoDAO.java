package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.SubEvento;

@Repository
public interface SubEventoDAO extends JpaRepository<SubEvento, Long> {
}

