package controllers;

import repositories.TrilhaDAO;
import models.Trilha;

import java.util.List;

public class TrilhaController {

    private TrilhaDAO trilhaDAO = new TrilhaDAO();

    // Adicionar Trilha
    public void adicionarTrilha(Trilha trilha) {
        trilhaDAO.insertTrilha(trilha);
    }

    // Atualizar Trilha
    public boolean atualizarTrilha(Long id, Trilha trilhaAtualizada) {
        Trilha trilha = trilhaDAO.selectTrilha(id);
        if (trilha == null) {
            throw new RuntimeException("Trilha não encontrada");
        }

        trilha.setNome(trilhaAtualizada.getNome());
        trilha.setSecao(trilhaAtualizada.getSecao());

        return trilhaDAO.updateTrilha(trilha);
    }

    // Deletar Trilha
    public boolean deletarTrilha(Long id) {
        return trilhaDAO.deleteTrilha(id);
    }

    // Listar todas as Trilhas
    public List<Trilha> listarTodasTrilhas() {
        return trilhaDAO.selectAllTrilhas();
    }

    // Buscar Trilha por ID
    public Trilha buscarTrilhaPorId(Long id) {
        Trilha trilha = trilhaDAO.selectTrilha(id);
        if (trilha == null) {
            throw new RuntimeException("Trilha não encontrada");
        }
        return trilha;
    }
}


