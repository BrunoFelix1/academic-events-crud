package controllers;

import repositories.SecaoDAO;
import models.Secao;

import java.util.List;

public class SecaoController {

    private SecaoDAO secaoDAO = new SecaoDAO();

    // Adicionar Secao
    public boolean adicionarSecao(Secao secao) {
        if (secao.getNome() == null || secao.getNome().isEmpty()) {
            throw new RuntimeException("O nome da seção é obrigatório");
        }
        if (secao.getEvento() == null || secao.getEvento().getId() == null) {
            throw new RuntimeException("Evento inválido");
        }
        if (secao.getSubEvento() == null || secao.getSubEvento().getId() == null) {
            throw new RuntimeException("SubEvento inválido");
        }
        return secaoDAO.insertSecao(secao);
    }

    // Atualizar Secao
    public boolean atualizarSecao(Long id, Secao secaoAtualizada) {
        Secao secao = secaoDAO.selectSecao(id);
        if (secao == null) {
            throw new RuntimeException("Secao não encontrada");
        }
        if (secaoAtualizada.getNome() == null || secaoAtualizada.getNome().isEmpty()) {
            throw new RuntimeException("O nome da seção é obrigatório");
        }
        if (secaoAtualizada.getEvento() == null || secaoAtualizada.getEvento().getId() == null) {
            throw new RuntimeException("Evento inválido");
        }
        if (secaoAtualizada.getSubEvento() == null || secaoAtualizada.getSubEvento().getId() == null) {
            throw new RuntimeException("SubEvento inválido");
        }

        secao.setNome(secaoAtualizada.getNome());
        secao.setLocal(secaoAtualizada.getLocal());
        secao.setHorario(secaoAtualizada.getHorario());
        secao.setEvento(secaoAtualizada.getEvento());
        secao.setSubEvento(secaoAtualizada.getSubEvento());

        return secaoDAO.updateSecao(secao);
    }

    // Deletar Secao
    public boolean deletarSecao(Long id) {
        Secao secao = secaoDAO.selectSecao(id);
        if (secao == null) {
            throw new RuntimeException("Secao não encontrada");
        }
        return secaoDAO.deleteSecao(id);
    }

    // Listar todas as Secoes
    public List<Secao> listarTodasSecoes() {
        return secaoDAO.selectAllSecoes();
    }

    // Buscar Secao por ID
    public Secao buscarSecaoPorId(Long id) {
        Secao secao = secaoDAO.selectSecao(id);
        if (secao == null) {
            throw new RuntimeException("Secao não encontrada");
        }
        return secao;
    }
}