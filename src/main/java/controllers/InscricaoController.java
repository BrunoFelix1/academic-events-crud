package controllers;

import repositories.InscricaoDAO;
import models.Inscricao;

import java.util.List;

public class InscricaoController {

    protected InscricaoDAO inscricaoDAO = new InscricaoDAO();

    // Adicionar Inscrição
    public boolean adicionarInscricao(Inscricao inscricao) {
        if (inscricao.getUsuario() == null || inscricao.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário inválido");
        }
        if (inscricao.getEvento() == null || inscricao.getEvento().getId() == null) {
            throw new RuntimeException("Evento inválido");
        }
        if (inscricao.getSubEvento() != null && inscricao.getSubEvento().getId() == null) {
            throw new RuntimeException("SubEvento inválido");
        }
        if (inscricao.getSecao() != null && inscricao.getSecao().getId() == null) {
            throw new RuntimeException("Secao inválida");
        }
        if (inscricao.getTrilha() != null && inscricao.getTrilha().getId() == null) {
            throw new RuntimeException("Trilha inválida");
        }
        // Verificar se o usuário já está inscrito no evento, subevento, seção e trilha
        if (inscricaoJaExiste(inscricao)) {
            throw new RuntimeException("Inscrição já existente");
        }
        return inscricaoDAO.insertInscricao(inscricao);
    }

    // Novo método para verificar se a inscrição já existe
    private boolean inscricaoJaExiste(Inscricao inscricao) {
        return inscricaoDAO.inscricaoJaExiste(inscricao.getUsuario().getId(), inscricao.getEvento().getId(), 
                                              inscricao.getSubEvento() != null ? inscricao.getSubEvento().getId() : null,
                                              inscricao.getSecao() != null ? inscricao.getSecao().getId() : null,
                                              inscricao.getTrilha() != null ? inscricao.getTrilha().getId() : null);
    }

    // Atualizar Inscrição
    public boolean atualizarInscricao(Long id, Inscricao inscricaoAtualizada) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não encontrada");
        }
        if (inscricaoAtualizada.getUsuario() == null || inscricaoAtualizada.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário inválido");
        }
        if (inscricaoAtualizada.getEvento() == null || inscricaoAtualizada.getEvento().getId() == null) {
            throw new RuntimeException("Evento inválido");
        }

        inscricao.setUsuario(inscricaoAtualizada.getUsuario());
        inscricao.setEvento(inscricaoAtualizada.getEvento());
        inscricao.setSubEvento(inscricaoAtualizada.getSubEvento());
        inscricao.setSecao(inscricaoAtualizada.getSecao());
        inscricao.setTrilha(inscricaoAtualizada.getTrilha());

        return inscricaoDAO.updateInscricao(inscricao);
    }

    // Deletar Inscrição
    public boolean deletarInscricao(Long id) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não encontrada");
        }
        return inscricaoDAO.deleteInscricao(id);
    }

    // Listar todas as Inscrições
    public List<Inscricao> listarTodasInscricoes() {
        return inscricaoDAO.selectAllInscricoes();
    }

    // Buscar Inscrição por ID
    public Inscricao buscarInscricaoPorId(Long id) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não encontrada");
        }
        return inscricao;
    }

    // Cancelar Inscrição
    public void cancelarInscricao(Long id, Long usuarioId) {
        Inscricao inscricao = inscricaoDAO.selectInscricao(id);
        if (inscricao == null) {
            throw new RuntimeException("Inscrição não encontrada");
        }
        // Verificar se a inscrição pertence ao usuário
        if (!inscricao.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Você não tem permissão para cancelar esta inscrição");
        }
        inscricaoDAO.deleteInscricao(id);
    }

    // Adicionar método para listar Inscrições por usuarioId
    public List<Inscricao> listarInscricoesPorUsuario(Long usuarioId) {
        return inscricaoDAO.findByUsuarioId(usuarioId);
    }
}
