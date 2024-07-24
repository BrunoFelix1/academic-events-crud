package Persistence;

import java.util.ArrayList;
import Enum.TipoDeUsuario;
import Interfaces.iPersistenciaControlador;
import Models.Usuario;


public class PersistenceUsuario implements iPersistenciaControlador<Usuario> {
    //Instanciando manipulador e adicionando o path da tabela de usuários
    private String pathUsuario = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Usuarios.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathUsuario);

    //Retorna um objeto Usuario em formato de linha String
    private String usuarioToCSV(Usuario usuario){
        String linha = usuario.getId() + "," + usuario.getCPF()+ "," + usuario.getNome() + "," +
        usuario.getIdade()+ "," + usuario.getInstituicao()+ "," +usuario.getTipoDeUsuario();
        return linha;
    }

    //Retorna uma lista de todos os usuários no momento
    public ArrayList<Usuario> getTodos() {
        Usuario usuarioDaVez = new Usuario();
        String linha;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            usuarioDaVez.setId(Integer.parseInt(dados[0]));
            usuarioDaVez.setCPF(dados[1]);
            usuarioDaVez.setNome(dados[2]);
            usuarioDaVez.setIdade(Integer.parseInt(dados[3]));
            usuarioDaVez.setInstituicao(dados[4]);
            usuarioDaVez.setTipoDeUsuario(TipoDeUsuario.valueOf(dados[5]));
            usuarios.add(usuarioDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return usuarios;
    }

    //Adiciona um Usuário na tabela
    public void add(Usuario usuario) {
        String linha = usuarioToCSV(usuario);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void delete (Usuario usuario) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = getTodos();
        for (int i = 0; i < usuarios.size(); i++){
            if (usuario.getId() == usuarios.get(i).getId()){
                usuarios.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,cpf,nome,idade,instituicao,tipoDeUsuario");
        for (Usuario u : usuarios){
            manipulador.escreverNoArquivo(usuarioToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void update (Usuario usuarioAntigo, Usuario usuarioNovo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = getTodos();
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarioAntigo.getId() == usuarios.get(i).getId()) {
                usuarios.get(i).setCPF(usuarioNovo.getCPF());
                usuarios.get(i).setNome(usuarioNovo.getNome());
                usuarios.get(i).setIdade(usuarioNovo.getIdade());
                usuarios.get(i).setInstituicao(usuarioNovo.getInstituicao());
                usuarios.get(i).setTipoDeUsuario(usuarioNovo.getTipoDeUsuario());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,cpf,nome,idade,instituicao,tipoDeUsuario");
        for (Usuario u : usuarios) {
            manipulador.escreverNoArquivo(usuarioToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public Usuario getPorId(int id) {
        ArrayList<Usuario> usuarios = getTodos();
        for (Usuario u : usuarios) {
            if (id == u.getId()) {
                return u;
            }
        }
        return null; // Caso não encontre o Usuário
    }
    
    //Usar apenas na persistência da inscrição
    public Usuario getPorIdInscricao(int id, int id2, int id3, int id4, int id5){
        Usuario usuario = new Usuario();
        return usuario;
    }
   
}
