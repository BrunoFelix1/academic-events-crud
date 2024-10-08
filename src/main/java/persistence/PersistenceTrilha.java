package persistence;

import java.util.ArrayList;
import interfaces.IPersistenciaControlador;
import models.Trilha;


public class PersistenceTrilha implements IPersistenciaControlador<Trilha> {
    //Instanciando manipulador e adicionando o path da tabela de Trilhas
    private String pathTrilha = "C:\\Users\\PC TESTE\\Desktop\\P2P3\\Trilhas.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathTrilha);

    //Retorna um objeto Trilha em formato de linha String
    private String TrilhaToCSV(Trilha trilha){
        String linha = trilha.getId() + "," + trilha.getIdSecao()+ "," + trilha.getNome();
        return linha;
    }

    //Retorna uma lista de todos as Trilhas no momento
    public ArrayList<Trilha> getTodos() {

        String linha;
        ArrayList<Trilha> trilhas = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            Trilha trilhaDaVez = new Trilha();
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            trilhaDaVez.setId(Integer.parseInt(dados[0]));
            trilhaDaVez.setIdSecao(Integer.parseInt(dados[1]));
            trilhaDaVez.setNome(dados[2]);
            trilhas.add(trilhaDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return trilhas;
    }

    //Adiciona uma Trilha na tabela
    public void add(Trilha trilha) {
        String linha = TrilhaToCSV(trilha);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
        manipulador.fecharArquivoEscrita();
    }

    public void delete (Trilha trilha) {
        ArrayList<Trilha> trilhas = new ArrayList<>();
        trilhas = getTodos();
        for (int i = 0; i < trilhas.size(); i++){
            if (trilha.getId() == trilhas.get(i).getId()){
                trilhas.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,secao_id,nome");
        for (Trilha u : trilhas){
            manipulador.escreverNoArquivo(TrilhaToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void update (Trilha trilhaAntiga, Trilha trilhaNova) {
        ArrayList<Trilha> trilhas = new ArrayList<>();
        trilhas = getTodos();
        for (int i = 0; i < trilhas.size(); i++){
            if (trilhaAntiga.getId() == trilhas.get(i).getId()) {
                trilhas.get(i).setIdSecao(trilhaNova.getIdSecao());
                trilhas.get(i).setNome(trilhaNova.getNome());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,secao_id,nome");
        for (Trilha u : trilhas) {
            manipulador.escreverNoArquivo(TrilhaToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public Trilha getPorNome(String nome) {
        ArrayList<Trilha> trilhas = getTodos();
        for (Trilha t : trilhas) {
            if (nome.equals(t.getNome())) {
                return t;
            }
        }
        return null;
    }

    public Trilha getPorId(int id) {
    ArrayList<Trilha> trilhas = getTodos();
    for (Trilha t : trilhas) {
        if (id == t.getId()) {
            return t;
        }
    }
        return null; // Caso não encontre o ID da trilha
    }

    //Usar apenas na persistência da inscrição
    public Trilha getPorIdInscricao(int id, int id2, int id3, int id4, int id5){
        Trilha Trilha = new Trilha();
        return Trilha;
    }



}
