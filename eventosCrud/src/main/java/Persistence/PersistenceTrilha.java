package Persistence;

import java.util.ArrayList;
import Models.Trilha;


public class PersistenceTrilha {
    //Instanciando manipulador e adicionando o path da tabela de Trilhas
    private String pathTrilha = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Trilhas.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathTrilha);

    //Retorna um objeto Trilha em formato de linha String
    private String TrilhaToCSV(Trilha trilha){
        String linha = trilha.getId() + "," + trilha.getIdSecao()+ "," + trilha.getNome();
        return linha;
    }

    //Retorna uma lista de todos as Trilhas no momento
    public ArrayList<Trilha> getTodasTrilhas() {
        Trilha trilhaDaVez = new Trilha();
        String linha;
        ArrayList<Trilha> trilhas = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
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
    public void addTrilha(Trilha trilha) {
        String linha = TrilhaToCSV(trilha);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void deleteTrilha (Trilha trilha) {
        ArrayList<Trilha> trilhas = new ArrayList<>();
        trilhas = getTodasTrilhas();
        for (int i = 0; i < trilhas.size(); i++){
            if (trilha.getId() == trilhas.get(i).getId()){
                trilhas.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,secao_id,nome");
        for (Trilha u : trilhas){
            manipulador.escreverNoArquivo(TrilhaToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateTrilha (Trilha trilhaAntiga, Trilha trilhaNova) {
        ArrayList<Trilha> trilhas = new ArrayList<>();
        trilhas = getTodasTrilhas();
        for (int i = 0; i < trilhas.size(); i++){
            if (trilhaAntiga.getId() == trilhas.get(i).getId()) {
                trilhas.get(i).setIdSecao(trilhaNova.getIdSecao());
                trilhas.get(i).setNome(trilhaNova.getNome());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,secao_id,nome");
        for (Trilha u : trilhas) {
            manipulador.escreverNoArquivo(TrilhaToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

        public Trilha getTrilhaPorId(int id) {
        ArrayList<Trilha> trilhas = getTodasTrilhas();
        for (Trilha t : trilhas) {
            if (id == t.getId()) {
                return t;
            }
        }
        return null; // Caso não encontre o ID da trilha
    }


   
}
