package controllers;

import repositories.TrilhaDAO;
import models.Trilha;

import java.util.List;

public class TrilhaController {

    private TrilhaDAO trilhaDAO = new TrilhaDAO();

    // Adicionar Trilha
    public boolean adicionarTrilha(Trilha trilha) {
        if (trilha.getNome() == null || trilha.getNome().isEmpty()) {
            throw new RuntimeException("O nome da trilha é obrigatório");
        }
        if (trilha.getSecao() == null || trilha.getSecao().getId() == null) {
            throw new RuntimeException("Seção inválida");
        }
        return trilhaDAO.insertTrilha(trilha);
    }

    // Atualizar Trilha
    public boolean atualizarTrilha(Long id, Trilha trilhaAtualizada) {
        Trilha trilha = trilhaDAO.selectTrilha(id);
        if (trilha == null) {
            throw new RuntimeException("Trilha não encontrada");
        }
        if (trilhaAtualizada.getNome() == null || trilhaAtualizada.getNome().isEmpty()) {
            throw new RuntimeException("O nome da trilha é obrigatório");
        }
        if (trilhaAtualizada.getSecao() == null || trilhaAtualizada.getSecao().getId() == null) {
            throw new RuntimeException("Seção inválida");
        }

        trilha.setNome(trilhaAtualizada.getNome());
        trilha.setSecao(trilhaAtualizada.getSecao());

        return trilhaDAO.updateTrilha(trilha);
    }

    // Deletar Trilha
    public boolean deletarTrilha(Long id) {
        Trilha trilha = trilhaDAO.selectTrilha(id);
        if (trilha == null) {
            throw new RuntimeException("Trilha não encontrada");
        }
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

    // Buscar Trilha por Nome
    public Trilha buscarTrilhaPorNome(String nome) {
        List<Trilha> trilhas = trilhaDAO.selectAllTrilhas();
        for (Trilha trilha : trilhas) {
            if (trilha.getNome().equalsIgnoreCase(nome)) {
                return trilha;
            }
        }
        return null;
    }
}


