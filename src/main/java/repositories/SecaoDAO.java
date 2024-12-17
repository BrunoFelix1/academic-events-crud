package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Secao;

@Repository
public interface SecaoDAO extends JpaRepository<Secao, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}
