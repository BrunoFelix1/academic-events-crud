package controllers;

import repositories.SubEventoDAO;
import models.SubEvento;

import java.util.List;

public class SubEventoController {

    private SubEventoDAO subEventoDAO = new SubEventoDAO();

    // Adicionar SubEvento
    public boolean adicionarSubEvento(SubEvento subEvento) {
        return subEventoDAO.insertSubEvento(subEvento);
    }

    // Atualizar SubEvento
    public boolean atualizarSubEvento(Long id, SubEvento subEventoAtualizado) {
        SubEvento subEvento = subEventoDAO.selectSubEvento(id);
        if (subEvento == null) {
            throw new RuntimeException("SubEvento não encontrado");
        }

        subEvento.setNome(subEventoAtualizado.getNome());
        subEvento.setDescricao(subEventoAtualizado.getDescricao());
        subEvento.setData(subEventoAtualizado.getData());

        return subEventoDAO.updateSubEvento(subEvento);
    }

    // Deletar SubEvento
    public boolean deletarSubEvento(Long id) {
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
}


