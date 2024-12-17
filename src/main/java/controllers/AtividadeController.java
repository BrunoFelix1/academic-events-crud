package controllers;

import repositories.AtividadeDAO;
import models.Atividade;

import java.util.List;

public class AtividadeController {

    private AtividadeDAO atividadeDAO = new AtividadeDAO();

    // Adicionar Atividade
    public boolean adicionarAtividade(Atividade atividade) {
        return atividadeDAO.insertAtividade(atividade);
    }

    // Atualizar Atividade
    public boolean atualizarAtividade(Long id, Atividade atividadeAtualizada) {
        Atividade atividade = atividadeDAO.selectAtividade(id);
        if (atividade == null) {
            throw new RuntimeException("Atividade não encontrada");
        }

        atividade.setTipoDeAtividade(atividadeAtualizada.getTipoDeAtividade());
        atividade.setResumo(atividadeAtualizada.getResumo());

        return atividadeDAO.updateAtividade(atividade);
    }

    // Deletar Atividade
    public boolean deletarAtividade(Long id) {
        return atividadeDAO.deleteAtividade(id);
    }

    // Listar todas as Atividades
    public List<Atividade> listarTodasAtividades() {
        return atividadeDAO.selectAllAtividades();
    }

    // Buscar Atividade por ID
    public Atividade buscarAtividadePorId(Long id) {
        Atividade atividade = atividadeDAO.selectAtividade(id);
        if (atividade == null) {
            throw new RuntimeException("Atividade não encontrada");
        }
        return atividade;
    }
}


