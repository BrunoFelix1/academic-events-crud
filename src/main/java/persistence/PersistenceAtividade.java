package persistence;

import java.util.ArrayList;
import interfaces.iPersistenciaControlador;
import models.Atividade;


public class PersistenceAtividade implements iPersistenciaControlador<Atividade>{
    //Instanciando manipulador e adicionando o path da tabela de atividades
    private String pathAtividade = "C:\\Users\\PC TESTE\\Desktop\\P2P3\\Atividades.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathAtividade);

    //Retorna um objeto atividade em formato de linha String
    private String atividadeToCSV(Atividade atividade){
        String linha = atividade.getId() + "," + atividade.getTipoSubmissao()+ "," + atividade.getAutor() + "," +
        atividade.getResumo()+ "," + atividade.getIdTrilha();
        return linha;
    }

    //Retorna uma lista de todos os atividades no momento
    public ArrayList<Atividade> getTodos() {
        String linha;
        ArrayList<Atividade> atividades = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            Atividade atividadeDaVez = new Atividade();
            atividadeDaVez.setId(Integer.parseInt(dados[0]));
            atividadeDaVez.setTipoSubmissao(dados[1]);
            atividadeDaVez.setAutor(dados[2]);
            atividadeDaVez.setResumo(dados[3]);
            atividadeDaVez.setIdTrilha(Integer.parseInt(dados[4]));
            atividades.add(atividadeDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return atividades;
    }

    //Adiciona um atividade na tabela
    public void add(Atividade atividade) {
        String linha = atividadeToCSV(atividade);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
        manipulador.fecharArquivoEscrita();
    }

    public void delete (Atividade atividade) {
        ArrayList<Atividade> atividades = new ArrayList<>();
        atividades = getTodos();
        for (int i = 0; i < atividades.size(); i++){
            if (atividade.getId() == atividades.get(i).getId()){
                atividades.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,tipoDeAtividade,autor,resumo,id_trilha");
        for (Atividade u : atividades){
            manipulador.escreverNoArquivo(atividadeToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void update (Atividade atividadeAntiga, Atividade atividadeNova) {
        ArrayList<Atividade> atividades = new ArrayList<>();
        atividades = getTodos();
        for (int i = 0; i < atividades.size(); i++){
            if (atividadeAntiga.getId() == atividades.get(i).getId()) {
                atividades.get(i).setTipoSubmissao(atividadeNova.getTipoSubmissao());
                atividades.get(i).setAutor(atividadeNova.getAutor());
                atividades.get(i).setResumo(atividadeNova.getResumo());
                atividades.get(i).setIdTrilha(atividadeNova.getIdTrilha());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,tipoDeAtividade,autor,resumo,id_trilha");
        for (Atividade u : atividades) {
            manipulador.escreverNoArquivo(atividadeToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public Atividade getPorId(int id) {
        ArrayList<Atividade> atividades = getTodos();
        for (Atividade a : atividades) {
            if (id == a.getId()) {
                return a;
            }
        }
        return null; // Caso não encontre o ID do usuário
    }

    //Usar apenas na persistência da inscrição
    public Atividade getPorIdInscricao(int id, int id2, int id3, int id4, int id5){
        Atividade Atividade = new Atividade();
        return Atividade;
    }
   
}
