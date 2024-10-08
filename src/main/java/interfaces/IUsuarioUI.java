package interfaces;

import models.Inscricao;
import models.Usuario;
import exception.UsuarioNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public interface IUsuarioUI {
    Usuario AutenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException;
    boolean cadastrar(Usuario usuario);
    List<Usuario> listar();
    void atualizar(Usuario usuarioNovo);
    boolean deletar(int id);

    boolean EmitirCertificado(Usuario usuario, String nomeTrilha);

    void InscricaoEvento(Usuario usuario, String nomeDoEvento);
    void InscricaoTrilha(Usuario usuario, String nomeDaTrilha);
    void CancelarInscriçãoEvento(Usuario usuario, String nomeEvento);
    void CancelarInscriçãoTrilha(Usuario usuario, String nomeTrilha);

    ArrayList<Inscricao> listaInscricoes(int idUsuario);
}
