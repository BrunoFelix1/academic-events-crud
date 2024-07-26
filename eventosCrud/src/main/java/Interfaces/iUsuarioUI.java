package Interfaces;

import Models.Inscricao;
import Models.Usuario;
import Exception.UsuarioNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public interface iUsuarioUI {
    Usuario AutenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException;
    void cadastrar(Usuario usuario);
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
