package services;

import org.springframework.beans.factory.annotation.Autowired;
import repositories.EventoDAO;
import org.springframework.stereotype.Service;

import models.Evento;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoDAO eventoRepository;

    // Adicionar Evento
    public Evento adicionarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Atualizar Evento
    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        evento.setTitulo(eventoAtualizado.getTitulo());
        evento.setLocal(eventoAtualizado.getLocal());
        evento.setHorario(eventoAtualizado.getHorario());
        evento.setDescricao(eventoAtualizado.getDescricao());

        return eventoRepository.save(evento);
    }

    // Deletar Evento
    public void deletarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        eventoRepository.deleteById(id);
    }

    // Listar todos os Eventos
    public List<Evento> listarTodosEventos() {
        return eventoRepository.findAll();
    }

    // Buscar Evento por ID
    public Evento buscarEventoPorId(Long id) {
        return eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    // Outros métodos relacionados a Evento podem ser adicionados aqui
}
