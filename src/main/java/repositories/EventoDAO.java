package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Evento;

public interface EventoDAO extends JpaRepository<Evento, Long> {
    Evento findByTitulo(String titulo);
}