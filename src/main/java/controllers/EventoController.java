package controllers;

import repositories.EventoDAO;
import models.Evento;

import java.util.List;

public class EventoController {

    private EventoDAO eventoDAO = new EventoDAO();

    // Adicionar Evento
    public void adicionarEvento(Evento evento) {
        eventoDAO.insertEvento(evento);
    }

    // Atualizar Evento
    public boolean atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento evento = eventoDAO.selectEvento(id);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado");
        }

        evento.setTitulo(eventoAtualizado.getTitulo());
        evento.setLocal(eventoAtualizado.getLocal());
        evento.setHorario(eventoAtualizado.getHorario());
        evento.setDescricao(eventoAtualizado.getDescricao());

        return eventoDAO.updateEvento(evento);
    }

    // Deletar Evento
    public boolean deletarEvento(Long id) {
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
