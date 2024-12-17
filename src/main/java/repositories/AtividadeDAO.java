package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Atividade;

@Repository
public interface AtividadeDAO extends JpaRepository<Atividade, Long> {
}


