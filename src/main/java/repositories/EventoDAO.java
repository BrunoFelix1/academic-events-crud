package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Evento;

@Repository
public interface EventoDAO extends JpaRepository<Evento, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}