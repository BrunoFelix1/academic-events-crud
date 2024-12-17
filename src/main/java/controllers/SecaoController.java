package controllers;

import repositories.SecaoDAO;
import models.Secao;

import java.util.List;

public class SecaoController {

    private SecaoDAO secaoDAO = new SecaoDAO();

    // Adicionar Secao
    public boolean adicionarSecao(Secao secao) {
        return secaoDAO.insertSecao(secao);
    }

    // Atualizar Secao
    public boolean atualizarSecao(Long id, Secao secaoAtualizada) {
        Secao secao = secaoDAO.selectSecao(id);
        if (secao == null) {
            throw new RuntimeException("Secao não encontrada");
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