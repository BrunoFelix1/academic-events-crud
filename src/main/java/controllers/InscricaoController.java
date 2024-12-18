package controllers;

import repositories.InscricaoDAO;
import models.Inscricao;

import java.util.List;

public class InscricaoController {

    private InscricaoDAO inscricaoDAO = new InscricaoDAO();

    // Adicionar Inscricao
    public boolean adicionarInscricao(Inscricao inscricao) {
        return inscricaoDAO.insertInscricao(inscricao);
    }

    // Atualizar Inscricao
    public boolean atualizarInscricao(Long id, Inscricao inscricaoAtualizada) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscricao não encontrada");
        }

        inscricao.setUsuario(inscricaoAtualizada.getUsuario());
        inscricao.setEvento(inscricaoAtualizada.getEvento());
        inscricao.setSubEvento(inscricaoAtualizada.getSubEvento());
        inscricao.setSecao(inscricaoAtualizada.getSecao());
        inscricao.setTrilha(inscricaoAtualizada.getTrilha());

        return inscricaoDAO.updateInscricao(inscricao);
    }

    // Deletar Inscricao
    public boolean deletarInscricao(Long id) {
        return inscricaoDAO.deleteInscricao(id);
    }

    // Listar todas as Inscricoes
    public List<Inscricao> listarTodasInscricoes() {
        return inscricaoDAO.selectAllInscricoes();
    }

    // Buscar Inscricao por ID
    public Inscricao buscarInscricaoPorId(Long id) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscricao não encontrada");
        }
        return inscricao;
    }

    // Cancelar Inscricao
    public void cancelarInscricao(Long id) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não encontrada");
        }
        inscricaoDAO.deleteInscricao(id);
    }

    // Adicionar método para listar Inscricoes por usuarioId
    public List<Inscricao> listarInscricoesPorUsuario(Long usuarioId) {
        return inscricaoDAO.findByUsuarioId(usuarioId);
    }
}
