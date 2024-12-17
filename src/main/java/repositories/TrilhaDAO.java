package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Trilha;

@Repository
public interface TrilhaDAO extends JpaRepository<Trilha, Long> {
}


