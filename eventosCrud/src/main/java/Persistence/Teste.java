package Persistence;
import java.util.*;


import Models.Usuario;
public class Teste {
    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        CarregadorDeArquivos carregadorDeArquivos = new CarregadorDeArquivos();
        usuarios = carregadorDeArquivos.carregarDadosUsuarios("C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Usuários.txt");
        System.out.println("O primeiro usuario foi:" + usuarios.get(0).getId());
        System.out.println("O primeiro usuario foi:" + usuarios.get(0).getNome());
        System.out.println("O primeiro usuario foi:" + usuarios.get(0).getCPF());
        System.out.println("O primeiro usuario foi:" + usuarios.get(0).getIdade());
        System.out.println("O primeiro usuario foi:" + usuarios.get(0).getInstituicao());
        System.out.println("O primeiro usuario foi:" + usuarios.get(0).getTipoDeUsuario());
    }
}
