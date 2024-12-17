package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import models.Inscricao;
import models.InscricaoId;

@Repository
public interface InscricaoDAO extends JpaRepository<Inscricao, InscricaoId> {
    Optional<Inscricao> findByUsuarioIdAndEventoIdAndSubEventoIdAndSecaoIdAndTrilhaId(Long usuarioId, Long eventoId, Long subEventoId, Long secaoId, Long trilhaId);

    // Adicionar método para buscar Inscricoes por usuarioId
    List<Inscricao> findByUsuarioId(Long usuarioId);
}


