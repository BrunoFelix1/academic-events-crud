package Persistence;
import java.util.*;
import Enum.TipoDeUsuario;
import Models.Usuario;

public class CarregadorDeArquivos {
    ManipuladorArquivos manipulador = new ManipuladorArquivos(null);

    public ArrayList<Usuario> carregarDadosUsuarios(String path){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String linha;
        Usuario usuarioDaVez = new Usuario();
        manipulador.setPath(path);
        manipulador.abrirArquivoParaLeitura();

        while ((linha = manipulador.lerLinhaArquivo()) != null) {
            if (linha.contains("id")){
                continue;
            }
            String dados[] = linha.split(";");
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
}
