package persistence;

import java.util.ArrayList;
import interfaces.IPersistenciaControlador;
import models.SubEvento;



public class PersistenceSubEvento implements IPersistenciaControlador<SubEvento> {
    //Instanciando manipulador e adicionando o path da tabela de SubEventos
    private String pathSubEvento = "src\\main\\resources\\database\\SubEventos.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathSubEvento);

    //Retorna um objeto SubEvento em formato de linha String
    private String SubEventoToCSV(SubEvento subEvento){
        String linha = subEvento.getId() + "," + subEvento.getIdEvento()+ "," + subEvento.getTitulo() + "," +
        subEvento.getLocal()+ "," + subEvento.getHorario() + "," + subEvento.getDescricao();
        return linha;
    }

    //Retorna uma lista de todos os SubEventos no momento
    public ArrayList<SubEvento> getTodos() {
        String linha;
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            SubEvento subEventoDaVez = new SubEvento();
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
    public void add(SubEvento subEvento) {
        String linha = SubEventoToCSV(subEvento);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
        manipulador.fecharArquivoEscrita();
    }

    public void delete (SubEvento subEvento) {
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos = getTodos();
        for (int i = 0; i < subEventos.size(); i++){
            if (subEvento.getId() == subEventos.get(i).getId()){
                subEventos.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (SubEvento u : subEventos){
            manipulador.escreverNoArquivo(SubEventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void update (SubEvento subEventoAntigo, SubEvento subEventoNovo) {
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos = getTodos();
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
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        for (SubEvento u : subEventos) {
            manipulador.escreverNoArquivo(SubEventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public SubEvento getPorId(int id) {
        ArrayList<SubEvento> subEventos = getTodos();
        for (SubEvento s : subEventos) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null; // Caso não encontre o ID do SubEvento
    }   
    
    //Usar apenas na persistência da inscrição
    public SubEvento getPorIdInscricao(int id, int id2, int id3, int id4, int id5){
        SubEvento SubEvento = new SubEvento();
        return SubEvento;
    }
   
}
