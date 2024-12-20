package controllers;

import repositories.SubEventoDAO;
import models.SubEvento;

import java.util.List;

public class SubEventoController {

    protected SubEventoDAO subEventoDAO = new SubEventoDAO();

    // Adicionar SubEvento
    public boolean adicionarSubEvento(SubEvento subEvento) {
        if (subEvento.getNome() == null || subEvento.getNome().isEmpty()) {
            throw new RuntimeException("O nome do subevento é obrigatório");
        }
        if (subEvento.getEvento() == null || subEvento.getEvento().getId() == null) {
            throw new RuntimeException("Evento inválido");
        }
        if (subEvento.getLocal() == null || subEvento.getLocal().isEmpty()) {
            throw new RuntimeException("O local do subevento é obrigatório");
        }
        if (subEvento.getHorario() == null || subEvento.getHorario().isEmpty()) {
            throw new RuntimeException("O horário do subevento é obrigatório");
        }
        return subEventoDAO.insertSubEvento(subEvento);
    }

    // Atualizar SubEvento
    public boolean atualizarSubEvento(Long id, SubEvento subEventoAtualizado) {
        SubEvento subEvento = subEventoDAO.selectSubEvento(id);
        if (subEvento == null) {
            throw new RuntimeException("SubEvento não encontrado");
        }
        if (subEventoAtualizado.getNome() == null || subEventoAtualizado.getNome().isEmpty()) {
            throw new RuntimeException("O nome do subevento é obrigatório");
        }
        if (subEventoAtualizado.getEvento() == null || subEventoAtualizado.getEvento().getId() == null) {
            throw new RuntimeException("Evento inválido");
        }
        if (subEventoAtualizado.getLocal() == null || subEventoAtualizado.getLocal().isEmpty()) {
            throw new RuntimeException("O local do subevento é obrigatório");
        }
        if (subEventoAtualizado.getHorario() == null || subEventoAtualizado.getHorario().isEmpty()) {
            throw new RuntimeException("O horário do subevento é obrigatório");
        }

        subEvento.setNome(subEventoAtualizado.getNome());
        subEvento.setDescricao(subEventoAtualizado.getDescricao());
        subEvento.setData(subEventoAtualizado.getData());
        subEvento.setLocal(subEventoAtualizado.getLocal());
        subEvento.setHorario(subEventoAtualizado.getHorario());
        subEvento.setEvento(subEventoAtualizado.getEvento());

        return subEventoDAO.updateSubEvento(subEvento);
    }

    // Deletar SubEvento
    public boolean deletarSubEvento(Long id) {
        SubEvento subEvento = subEventoDAO.selectSubEvento(id);
        if (subEvento == null) {
            throw new RuntimeException("SubEvento não encontrado");
        }
        return subEventoDAO.deleteSubEvento(id);
    }

    // Listar todos os SubEventos
    public List<SubEvento> listarTodosSubEventos() {
        return subEventoDAO.selectAllSubEventos();
    }

    // Buscar SubEvento por ID
    public SubEvento buscarSubEventoPorId(Long id) {
        SubEvento subEvento = subEventoDAO.selectSubEvento(id);
        if (subEvento == null) {
            throw new RuntimeException("SubEvento não encontrado");
        }
        return subEvento;
    }

    // Buscar SubEvento por Nome
    public SubEvento buscarSubEventoPorNome(String nome) {
        List<SubEvento> subEventos = subEventoDAO.selectAllSubEventos();
        for (SubEvento subEvento : subEventos) {
            if (subEvento.getNome().equalsIgnoreCase(nome)) {
                return subEvento;
            }
        }
        return null;
    }
}


