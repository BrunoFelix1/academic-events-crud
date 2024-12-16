package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Atividade;

public interface AtividadeDAO extends JpaRepository<Atividade, Long> {
}


