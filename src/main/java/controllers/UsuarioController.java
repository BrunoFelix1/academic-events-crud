package controllers;

import interfaces.iUsuarioUI;
import models.Evento;
import models.Inscricao;
import models.Trilha;
import models.Usuario;
import persistence.PersistenceEvento;
import persistence.PersistenceInscricao;
import persistence.PersistenceTrilha;
import persistence.PersistenceUsuario;

import java.util.ArrayList;
import java.util.List;
import exception.UsuarioNaoEncontradoException;
import interfaces.iPersistenciaControlador;


public class UsuarioController implements iUsuarioUI {

    private iPersistenciaControlador<Usuario> usuarioP = new PersistenceUsuario();

    public Usuario AutenticarUsuario(String login, String senha) throws UsuarioNaoEncontradoException{
        List<Usuario> usuarioList = usuarioP.getTodos();
        for (Usuario user : usuarioList){
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)){
                return user;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado ou credenciais inválidas, por favor tente novamente.");
    }

    public List<Usuario> listar(){
        List<Usuario> listaUsuario =  usuarioP.getTodos();
        return listaUsuario;
    }

    public boolean cadastrar(Usuario usuario) {
        List<Usuario> lista = usuarioP.getTodos();
        usuario.setId(lista.size()+1);
        boolean cpfCorreto = usuario.ValidarCPF();
        if (cpfCorreto) {
            Object o = usuarioP.getPorId(usuario.getId());
            if (o == null) {
                usuarioP.add(usuario);
                return true;
            } else {
                System.out.println("Usuário já cadastrado.");
                return false;
            }
        } else {
            System.out.println("CPF inválido.");
            return false;
        }
    }

    public void atualizar(Usuario usuarioNovo) {
        Usuario usuarioAntigo = usuarioP.getPorId(usuarioNovo.getId());
        usuarioP.update(usuarioAntigo, usuarioNovo);
    }

    public boolean deletar(int id) {
        Usuario usuario = usuarioP.getPorId(id);
        if ( usuario!= null) {
            usuarioP.delete(usuario);
            return true;
        }
        else { return false; }
    }

    public boolean EmitirCertificado(Usuario usuario, String nomeTrilha){
        PersistenceTrilha trilhaP = new PersistenceTrilha();
        Trilha trilha = trilhaP.getPorNome(nomeTrilha);
        // chamar método pra ver se ele tá inscrito na trilha
        PersistenceInscricao inscricaoP = new PersistenceInscricao();
        boolean resultado = inscricaoP.estaInscritoEmTrilha(usuario.getId(), trilha.getId());
        if ( resultado ){
            System.out.println("Este certificado é validado pelo MEC em todo o território brasileiro\nUsuário: " + usuario.getNome() + ", está inscrito na trilha: " + trilha.getNome());

        }
        return resultado;
    }

    public void InscricaoEvento(Usuario usuario, String nomeDoEvento){
        PersistenceEvento eventoP = new PersistenceEvento();
        Evento evento = eventoP.getPorNome(nomeDoEvento);
        //if( evento != null ){
            Inscricao novaInscricao = new Inscricao();
            novaInscricao.setIdUsuario(usuario.getId());
            novaInscricao.setIdEvento(evento.getId());

            // Adiciona a inscrição
            PersistenceInscricao inscricaoP = new PersistenceInscricao();
            inscricaoP.add(novaInscricao);
            System.out.println("Inscrição realizada.");
       // }
       // else {
        //    System.out.println("Evento não encontrado");
       // }
    }

    public void InscricaoTrilha(Usuario usuario, String nomeDaTrilha){
        PersistenceTrilha trilhaP = new PersistenceTrilha();
        Trilha trilha = trilhaP.getPorNome(nomeDaTrilha);

        Inscricao novaInscricao = new Inscricao();
        novaInscricao.setIdUsuario(usuario.getId());
        novaInscricao.setIdTrilha(trilha.getId());

        // Adiciona a inscrição
        PersistenceInscricao inscricaoP = new PersistenceInscricao();
        inscricaoP.add(novaInscricao);

    }

    public void CancelarInscriçãoEvento(Usuario usuario, String nomeEvento){
        PersistenceEvento eventoP = new PersistenceEvento();
        Evento evento = eventoP.getPorNome(nomeEvento);
        PersistenceInscricao inscricaoP = new PersistenceInscricao();
        Inscricao inscricao = new Inscricao();
        inscricao = inscricaoP.getPorIdInscricaoEvento(usuario.getId(),evento.getId());
        inscricaoP.delete(inscricao);
    }

    public void CancelarInscriçãoTrilha(Usuario usuario, String nomeTrilha){
        PersistenceTrilha trilhaP = new PersistenceTrilha();
        Trilha trilha = trilhaP.getPorNome(nomeTrilha);

        PersistenceInscricao inscricaoP = new PersistenceInscricao();
        Inscricao inscricao = new Inscricao();
        inscricao = inscricaoP.getPorIdInscricaoTrilha(usuario.getId(),trilha.getId());
        inscricaoP.delete(inscricao);
    }


    public ArrayList<Inscricao> listaInscricoes(int idUsuario) {
        PersistenceInscricao inscricaoP = new PersistenceInscricao();
        List<Inscricao> lista = inscricaoP.getTodos();
        ArrayList<Inscricao> listaPorUsuario = new ArrayList<Inscricao>();
        for ( Inscricao i : lista ){
            if ( i.getIdUsuario() == idUsuario ){
                listaPorUsuario.add(i);
            }
        }
        return listaPorUsuario;
    }

}