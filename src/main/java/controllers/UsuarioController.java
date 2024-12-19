package controllers;

import repositories.UsuarioDAO;
import models.Usuario;
import exception.UsuarioNaoEncontradoException;

import java.util.List;

public class UsuarioController {

    public UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Adicionar Usuario
    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }
        if (!usuario.ValidarCPF()) {
            throw new RuntimeException("CPF inválido");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }
        if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
            throw new RuntimeException("Login é obrigatório");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new RuntimeException("Senha é obrigatória");
        }
        return usuarioDAO.insertUser(usuario);
    }

    // Atualizar Usuario
    public boolean atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioDAO.selectUser(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }
        if (usuarioAtualizado.getCpf() == null || usuarioAtualizado.getCpf().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }
        if (!usuarioAtualizado.ValidarCPF()) {
            throw new RuntimeException("CPF inválido");
        }
        if (usuarioAtualizado.getNome() == null || usuarioAtualizado.getNome().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }
        if (usuarioAtualizado.getLogin() == null || usuarioAtualizado.getLogin().isEmpty()) {
            throw new RuntimeException("Login é obrigatório");
        }
        if (usuarioAtualizado.getSenha() == null || usuarioAtualizado.getSenha().isEmpty()) {
            throw new RuntimeException("Senha é obrigatória");
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
        Usuario usuario = usuarioDAO.selectUser(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado");
        }
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
