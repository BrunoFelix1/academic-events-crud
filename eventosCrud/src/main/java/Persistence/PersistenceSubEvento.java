package Persistence;

import java.util.ArrayList;
import Models.SubEvento;


public class PersistenceSubEvento {
    //Instanciando manipulador e adicionando o path da tabela de SubEventos
    private String pathSubEvento = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\SubEventos.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathSubEvento);

    //Retorna um objeto SubEvento em formato de linha String
    private String SubEventoToCSV(SubEvento subEvento){
        String linha = subEvento.getId() + "," + subEvento.getIdEvento()+ "," + subEvento.getTitulo() + "," +
        subEvento.getLocal()+ "," + subEvento.getHorario() + "," + subEvento.getDescricao();
        return linha;
    }

    //Retorna uma lista de todos os SubEventos no momento
    public ArrayList<SubEvento> getTodosSubEventos() {
        SubEvento subEventoDaVez = new SubEvento();
        String linha;
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            subEventoDaVez.setId(Integer.parseInt(dados[0]));
            subEventoDaVez.setIdEvento(Integer.parseInt(dados[1]));
            subEventoDaVez.setTitulo(dados[2]);
            subEventoDaVez.setLocal(dados[3]);
            subEventoDaVez.setHorario(dados[4]);
            subEventoDaVez.setDescricao(dados[5]);
            subEventos.add(subEventoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return subEventos;
    }

    //Adiciona um SubEvento na tabela
    public void addSubEvento(SubEvento subEvento) {
        String linha = SubEventoToCSV(subEvento);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void deleteSubEvento (SubEvento subEvento) {
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos = getTodosSubEventos();
        for (int i = 0; i < subEventos.size(); i++){
            if (subEvento.getId() == subEventos.get(i).getId()){
                subEventos.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (SubEvento u : subEventos){
            manipulador.escreverNoArquivo(SubEventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateSubEvento (SubEvento subEventoAntigo, SubEvento subEventoNovo) {
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos = getTodosSubEventos();
        for (int i = 0; i < subEventos.size(); i++){
            if (subEventoAntigo.getId() == subEventos.get(i).getId()) {
                subEventos.get(i).setIdEvento(subEventoNovo.getIdEvento());
                subEventos.get(i).setTitulo(subEventoNovo.getTitulo());
                subEventos.get(i).setLocal(subEventoNovo.getLocal());
                subEventos.get(i).setHorario(subEventoNovo.getHorario());
                subEventos.get(i).setDescricao(subEventoNovo.getDescricao());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (SubEvento u : subEventos) {
            manipulador.escreverNoArquivo(SubEventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }


   
}
