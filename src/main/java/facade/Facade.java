package facade;

import controllers.*;
import exception.UsuarioNaoEncontradoException;
import models.*;

import java.util.List;

public class Facade {

    private AtividadeController atividadeController;
    private SecaoController secaoController;
    private UsuarioController usuarioController;
    private TrilhaController trilhaController;
    private SubEventoController subEventoController;
    private InscricaoController inscricaoController;
    private EventoController eventoController;

    public Facade() {
        this.atividadeController = new AtividadeController();
        this.secaoController = new SecaoController();
        this.usuarioController = new UsuarioController();
        this.trilhaController = new TrilhaController();
        this.subEventoController = new SubEventoController();
        this.inscricaoController = new InscricaoController();
        this.eventoController = new EventoController();
    }

    // Métodos para Atividade
    public boolean adicionarAtividade(Atividade atividade) {
        return atividadeController.adicionarAtividade(atividade);
    }

    public boolean atualizarAtividade(Long id, Atividade atividadeAtualizada) {
        return atividadeController.atualizarAtividade(id, atividadeAtualizada);
    }

    public Atividade buscarAtividade(Long id) {
        return atividadeController.buscarAtividadePorId(id);
    }

    public List<Atividade> listarAtividades() {
        return atividadeController.listarTodasAtividades();
    }

    public boolean deletarAtividade(Long id) {
        return atividadeController.deletarAtividade(id);
    }

    // Métodos para Secao
    public boolean adicionarSecao(Secao secao) {
        return secaoController.adicionarSecao(secao);
    }

    public boolean atualizarSecao(Long id, Secao secaoAtualizada) {
        return secaoController.atualizarSecao(id, secaoAtualizada);
    }

    public Secao buscarSecao(Long id) {
        return secaoController.buscarSecaoPorId(id);
    }

    public List<Secao> listarSecoes() {
        return secaoController.listarTodasSecoes();
    }

    public boolean deletarSecao(Long id) {
        return secaoController.deletarSecao(id);
    }

    // Métodos para Usuario
    public boolean adicionarUsuario(Usuario usuario) {
        return usuarioController.adicionarUsuario(usuario);
    }

    public boolean atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        return usuarioController.atualizarUsuario(id, usuarioAtualizado);
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioController.buscarUsuarioPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioController.listarTodosUsuarios();
    }

    public boolean deletarUsuario(Long id) {
        return usuarioController.deletarUsuario(id);
    }

    public Usuario autenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException {
        return usuarioController.autenticarUsuario(login, senha);
    }

    // Métodos para Trilha
    public boolean adicionarTrilha(Trilha trilha) {
        return trilhaController.adicionarTrilha(trilha);
    }

    public boolean atualizarTrilha(Long id, Trilha trilhaAtualizada) {
        return trilhaController.atualizarTrilha(id, trilhaAtualizada);
    }

    public Trilha buscarTrilha(Long id) {
        return trilhaController.buscarTrilhaPorId(id);
    }

    public List<Trilha> listarTrilhas() {
        return trilhaController.listarTodasTrilhas();
    }

    public boolean deletarTrilha(Long id) {
        return trilhaController.deletarTrilha(id);
    }

    // Métodos para SubEvento
    public boolean adicionarSubEvento(SubEvento subEvento) {
        return subEventoController.adicionarSubEvento(subEvento);
    }

    public boolean atualizarSubEvento(Long id, SubEvento subEventoAtualizado) {
        return subEventoController.atualizarSubEvento(id, subEventoAtualizado);
    }

    public SubEvento buscarSubEvento(Long id) {
        return subEventoController.buscarSubEventoPorId(id);
    }

    public List<SubEvento> listarSubEventos() {
        return subEventoController.listarTodosSubEventos();
    }

    public boolean deletarSubEvento(Long id) {
        return subEventoController.deletarSubEvento(id);
    }

    // Métodos para Inscricao
    public boolean adicionarInscricao(Inscricao inscricao) {
        return inscricaoController.adicionarInscricao(inscricao);
    }

    public boolean atualizarInscricao(Long id, Inscricao inscricaoAtualizada) {
        return inscricaoController.atualizarInscricao(id, inscricaoAtualizada);
    }

    public Inscricao buscarInscricao(Long id) {
        return inscricaoController.buscarInscricaoPorId(id);
    }

    public List<Inscricao> listarInscricoes() {
        return inscricaoController.listarTodasInscricoes();
    }

    public boolean deletarInscricao(Long id) {
        return inscricaoController.deletarInscricao(id);
    }

    public void cancelarInscricao(Long id) {
        inscricaoController.cancelarInscricao(id);
    }

    public List<Inscricao> listarInscricoesPorUsuario(Long usuarioId) {
        return inscricaoController.listarInscricoesPorUsuario(usuarioId);
    }

    // Métodos para Evento
    public boolean adicionarEvento(Evento evento) {
        return eventoController.adicionarEvento(evento);
    }

    public boolean atualizarEvento(Long id, Evento eventoAtualizado) {
        return eventoController.atualizarEvento(id, eventoAtualizado);
    }

    public Evento buscarEvento(Long id) {
        return eventoController.buscarEventoPorId(id);
    }

    public List<Evento> listarEventos() {
        return eventoController.listarTodosEventos();
    }

    public boolean deletarEvento(Long id) {
        return eventoController.deletarEvento(id);
    }
}