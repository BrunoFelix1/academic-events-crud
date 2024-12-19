package controllers;

import repositories.EventoDAO;
import models.Evento;

import java.util.List;

public class EventoController {

    private EventoDAO eventoDAO = new EventoDAO();

    // Adicionar Evento
    public boolean adicionarEvento(Evento evento) {
        if (evento.getTitulo() == null || evento.getTitulo().isEmpty()) {
            throw new RuntimeException("Título do evento é obrigatório");
        }
        if (evento.getLocal() == null || evento.getLocal().isEmpty()) {
            throw new RuntimeException("Local do evento é obrigatório");
        }
        if (evento.getHorario() == null || evento.getHorario().isEmpty()) {
            throw new RuntimeException("Horário do evento é obrigatório");
        }
        return eventoDAO.insertEvento(evento);
    }

    // Atualizar Evento
    public boolean atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento evento = eventoDAO.selectEvento(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado");
        }
        if (eventoAtualizado.getTitulo() == null || eventoAtualizado.getTitulo().isEmpty()) {
            throw new RuntimeException("Título do evento é obrigatório");
        }
        if (eventoAtualizado.getLocal() == null || eventoAtualizado.getLocal().isEmpty()) {
            throw new RuntimeException("Local do evento é obrigatório");
        }
        if (eventoAtualizado.getHorario() == null || eventoAtualizado.getHorario().isEmpty()) {
            throw new RuntimeException("Horário do evento é obrigatório");
        }

        evento.setTitulo(eventoAtualizado.getTitulo());
        evento.setLocal(eventoAtualizado.getLocal());
        evento.setHorario(eventoAtualizado.getHorario());
        evento.setDescricao(eventoAtualizado.getDescricao());

        return eventoDAO.updateEvento(evento);
    }

    // Deletar Evento
    public boolean deletarEvento(Long id) {
        Evento evento = eventoDAO.selectEvento(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado");
        }
        return eventoDAO.deleteEvento(id);
    }

    // Listar todos os Eventos
    public List<Evento> listarTodosEventos() {
        return eventoDAO.selectAllEventos();
    }

    // Buscar Evento por ID
    public Evento buscarEventoPorId(Long id) {
        Evento evento = eventoDAO.selectEvento(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado");
        }
        return evento;
    }

    // Outros métodos relacionados a Evento podem ser adicionados aqui
}
