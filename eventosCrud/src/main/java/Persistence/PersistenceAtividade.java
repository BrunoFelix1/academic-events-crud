package Persistence;

import java.util.ArrayList;

import Enum.TipoDeAtividade;
import Models.Atividade;


public class PersistenceAtividade {
    //Instanciando manipulador e adicionando o path da tabela de atividades
    private String pathAtividade = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Atividades.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathAtividade);

    //Retorna um objeto atividade em formato de linha String
    private String atividadeToCSV(Atividade atividade){
        String linha = atividade.getId() + "," + atividade.getTipoSubmissao()+ "," + atividade.getAutor() + "," +
        atividade.getResumo()+ "," + atividade.getIdTrilha();
        return linha;
    }

    //Retorna uma lista de todos os atividades no momento
    public ArrayList<Atividade> getTodosatividades() {
        Atividade atividadeDaVez = new Atividade();
        String linha;
        ArrayList<Atividade> atividades = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            atividadeDaVez.setId(Integer.parseInt(dados[0]));
            atividadeDaVez.setTipoSubmissao(TipoDeAtividade.valueOf(dados[1]));
            atividadeDaVez.setAutor(dados[2]);
            atividadeDaVez.setResumo(dados[3]);
            atividadeDaVez.setIdTrilha(Integer.parseInt(dados[4]));
            atividades.add(atividadeDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return atividades;
    }

    //Adiciona um atividade na tabela
    public void addatividade(Atividade atividade) {
        String linha = atividadeToCSV(atividade);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void deleteatividade (Atividade atividade) {
        ArrayList<Atividade> atividades = new ArrayList<>();
        atividades = getTodosatividades();
        for (int i = 0; i < atividades.size(); i++){
            if (atividade.getId() == atividades.get(i).getId()){
                atividades.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,tipoDeAtividade,autor,resumo,id_trilha");
        for (Atividade u : atividades){
            manipulador.escreverNoArquivo(atividadeToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateatividade (Atividade atividadeAntiga, Atividade atividadeNova) {
        ArrayList<Atividade> atividades = new ArrayList<>();
        atividades = getTodosatividades();
        for (int i = 0; i < atividades.size(); i++){
            if (atividadeAntiga.getId() == atividades.get(i).getId()) {
                atividades.get(i).setTipoSubmissao(atividadeNova.getTipoSubmissao());
                atividades.get(i).setAutor(atividadeNova.getAutor());
                atividades.get(i).setResumo(atividadeNova.getResumo());
                atividades.get(i).setIdTrilha(atividadeNova.getIdTrilha());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,tipoDeAtividade,autor,resumo,id_trilha");
        for (Atividade u : atividades) {
            manipulador.escreverNoArquivo(atividadeToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }


   
}
