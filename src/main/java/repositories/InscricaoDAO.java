package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Inscricao;
import models.InscricaoId;

public interface InscricaoDAO extends JpaRepository<Inscricao, InscricaoId> {
}


