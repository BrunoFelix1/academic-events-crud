package controllers;

import repositories.UsuarioDAO;
import models.Usuario;
import exception.UsuarioNaoEncontradoException;

import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Adicionar Usuario
    public void adicionarUsuario(Usuario usuario) {
        usuarioDAO.insertUser(usuario);
    }

    // Atualizar Usuario
    public boolean atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioDAO.selectUser(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }

        // Atualizar campos necessários
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setCpf(usuarioAtualizado.getCpf());
        usuario.setIdade(usuarioAtualizado.getIdade());
        usuario.setInstituicao(usuarioAtualizado.getInstituicao());
        usuario.setTipoDeUsuario(usuarioAtualizado.getTipoDeUsuario());
        usuario.setLogin(usuarioAtualizado.getLogin());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return usuarioDAO.updateUser(usuario);
    }

    // Deletar Usuario
    public boolean deletarUsuario(Long id) {
        return usuarioDAO.deleteUser(id);
    }

    // Listar todos os Usuarios
    public List<Usuario> listarTodosUsuarios() {
        return usuarioDAO.selectAllUsers();
    }

    // Buscar Usuario por ID
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioDAO.selectUser(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }
        return usuario;
    }

    // Autenticar Usuario
    public Usuario autenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException {
        List<Usuario> usuarios = usuarioDAO.selectAllUsers();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário ou senha inválidos.");
    }
}
