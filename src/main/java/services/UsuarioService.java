package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UsuarioDAO;
import models.Usuario;
import exception.UsuarioNaoEncontradoException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioRepository;

    // Adicionar Usuario
    public Usuario adicionarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Atualizar Usuario
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        // Atualizar campos necessários
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setCpf(usuarioAtualizado.getCpf());
        usuario.setIdade(usuarioAtualizado.getIdade());
        usuario.setInstituicao(usuarioAtualizado.getInstituicao());
        usuario.setTipoDeUsuario(usuarioAtualizado.getTipoDeUsuario());
        usuario.setLogin(usuarioAtualizado.getLogin());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return usuarioRepository.save(usuario);
    }

    // Deletar Usuario
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario não encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    // Listar todos os Usuarios
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar Usuario por ID
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    // Autenticar Usuario
    public Usuario autenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new UsuarioNaoEncontradoException("Usuário ou senha inválidos.");
        }
        return usuario;
    }
}
