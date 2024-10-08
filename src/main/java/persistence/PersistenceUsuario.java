package persistence;

import java.util.ArrayList;
import interfaces.iPersistenciaControlador;
import models.Usuario;


public class PersistenceUsuario implements iPersistenciaControlador<Usuario> {
    //Instanciando manipulador e adicionando o path da tabela de usuários
    private String pathUsuario = "C:\\Users\\PC TESTE\\Desktop\\P2P3\\Usuarios.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathUsuario);

    //Retorna um objeto Usuario em formato de linha String
    private String usuarioToCSV(Usuario usuario){
        String linha = usuario.getId() + "," + usuario.getCPF()+ "," + usuario.getNome() + "," +
        usuario.getIdade()+ "," + usuario.getInstituicao()+ "," +usuario.getTipoDeUsuario() + "," +usuario.getLogin()+ ","+usuario.getSenha();
        return linha;
    }

    //Retorna uma lista de todos os usuários no momento
    public ArrayList<Usuario> getTodos() {
        String linha;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null) {
            // Desconsiderando cabeçalho
            if (linha.contains("id")) {
                continue;
            }
            String[] dados = linha.split(",");

            // Verifica se a linha possui o número correto de campos
            if (dados.length >= 7) {
                try {
                    Usuario usuarioDaVez = new Usuario(); // Cria uma nova instância para cada linha
                    usuarioDaVez.setId(Integer.parseInt(dados[0].trim()));
                    usuarioDaVez.setCPF(dados[1].trim());
                    usuarioDaVez.setNome(dados[2].trim());
                    usuarioDaVez.setIdade(Integer.parseInt(dados[3].trim()));
                    usuarioDaVez.setInstituicao(dados[4].trim());
                    usuarioDaVez.setTipoDeUsuario(dados[5].trim());
                    usuarioDaVez.setLogin(dados[6].trim());
                    usuarioDaVez.setSenha(dados[7].trim());
                    usuarios.add(usuarioDaVez);
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter número: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao converter tipo de usuário: " + e.getMessage());
                }
            } else {
                // System.out.println("Linha de dados inválida. Esperado pelo menos 8 elementos, mas recebeu " + dados.length);
            }
        }
        manipulador.fecharArquivoParaLeitura();
        return usuarios;
    }
    //Adiciona um Usuário na tabela
    public void add(Usuario usuario) {
        String linha = usuarioToCSV(usuario);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
        manipulador.fecharArquivoEscrita();
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
        manipulador.abrirArquivoParaEscrita(1);
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
        manipulador.abrirArquivoParaEscrita(1);
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
