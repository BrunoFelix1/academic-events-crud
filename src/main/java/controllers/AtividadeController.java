package controllers;

import repositories.AtividadeDAO;
import models.Atividade;

import java.util.List;

public class AtividadeController {

    private AtividadeDAO atividadeDAO = new AtividadeDAO();

    // Adicionar Atividade
    public boolean adicionarAtividade(Atividade atividade) {
        if (atividade.getTipoDeAtividade() == null || atividade.getTipoDeAtividade().isEmpty()) {
            throw new RuntimeException("Tipo de atividade é obrigatório");
        }
        if (atividade.getAutor() == null || atividade.getAutor().isEmpty()) {
            throw new RuntimeException("Autor é obrigatório");
        }
        if (atividade.getTrilha() == null || atividade.getTrilha().getId() == null) {
            throw new RuntimeException("Trilha inválida");
        }
        return atividadeDAO.insertAtividade(atividade);
    }

    // Atualizar Atividade
    public boolean atualizarAtividade(Long id, Atividade atividadeAtualizada) {
        Atividade atividade = atividadeDAO.selectAtividade(id);
        if (atividade == null) {
            throw new RuntimeException("Atividade não encontrada");
        }
        if (atividadeAtualizada.getTipoDeAtividade() == null || atividadeAtualizada.getTipoDeAtividade().isEmpty()) {
            throw new RuntimeException("Tipo de atividade é obrigatório");
        }
        if (atividadeAtualizada.getAutor() == null || atividadeAtualizada.getAutor().isEmpty()) {
            throw new RuntimeException("Autor é obrigatório");
        }
        if (atividadeAtualizada.getTrilha() == null || atividadeAtualizada.getTrilha().getId() == null) {
            throw new RuntimeException("Trilha inválida");
        }
        atividade.setTipoDeAtividade(atividadeAtualizada.getTipoDeAtividade());
        atividade.setResumo(atividadeAtualizada.getResumo());
        atividade.setAutor(atividadeAtualizada.getAutor());
        atividade.setTrilha(atividadeAtualizada.getTrilha());
        return atividadeDAO.updateAtividade(atividade);
    }

    // Deletar Atividade
    public boolean deletarAtividade(Long id) {
        Atividade atividade = atividadeDAO.selectAtividade(id);
        if (atividade == null) {
            throw new RuntimeException("Atividade não encontrada");
        }
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


