package Controllers;

import Models.Trilha;
import Models.Usuario;
import Persistence.PersistenceInscricao;
import Persistence.PersistenceTrilha;
import Persistence.PersistenceUsuario;
import java.util.List;
import Exception.UsuarioNaoEncontradoException;
public class UsuarioController  {

    private PersistenceUsuario usuarioP = new PersistenceUsuario();

    public Usuario AutenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException{
        List<Usuario> usuarioList = usuarioP.getTodos();
        for (Usuario user : usuarioList){
            if ( user.getLogin() == login && user.getSenha()== senha){
                return user;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado ou credenciais inválidas");

    }


    public void cadastrarUsuario(Usuario usuario) {
        boolean cpfCorreto = usuario.ValidarCPF();
        if (cpfCorreto) {
            Object o = usuarioP.getPorId(usuario.getId());
            if (o.getClass() != Usuario.class) {
                usuarioP.add(usuario);
            } else {
                System.out.println("Usuário já cadastrado.");
            }
        } else {
            System.out.println("CPF inválido.");
        }
    }

    public void AtualizarUsuario(Usuario usuarioNovo) {
        Usuario usuarioAntigo = usuarioP.getPorId(usuarioNovo.getId());
        usuarioP.update(usuarioAntigo, usuarioNovo);
    }

    public boolean DeletarUsuario(int id) {
        Usuario usuario = usuarioP.getPorId(id);
        if ( usuario!= null) {
            usuarioP.delete(usuario);
            return true;
        }
        else { return false; }
    }

    public boolean EmitirCertificado(Usuario usuario, String nomeTrilha){
        PersistenceTrilha trilhaP = new PersistenceTrilha();
        List<Trilha> listaTrilha = trilhaP.getTodos();
        for ( Trilha trilha : listaTrilha ){
            if ( trilha.getNome() == nomeTrilha ){
                // chamar método pra ver se ele tá inscrito na trilha
                PersistenceInscricao inscricaoP = new PersistenceInscricao();
                boolean resultado = inscricaoP.estaInscritoEmTrilha(usuario.getId(), trilha.getId());
                return resultado;
            }
        }
        return false;
    }

    public void InscricaoEvento(){

    }

    public void InscricaoTrilha(){

    }
    
}
