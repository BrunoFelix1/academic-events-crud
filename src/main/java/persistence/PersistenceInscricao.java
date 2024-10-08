package persistence;

import java.util.ArrayList;
import interfaces.iPersistenciaControlador;
import models.Inscricao;



public class PersistenceInscricao implements iPersistenciaControlador<Inscricao> {
    //Instanciando manipulador e adicionando o path da tabela de Inscrições
    private String pathInscricao = "C:\\Users\\PC TESTE\\Desktop\\P2P3\\Inscrições.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathInscricao);

    //Retorna um objeto Inscricao em formato de linha String
    private String inscricaoToCSV(Inscricao inscricao){
        String linha = inscricao.getIdUsuario() + "," + inscricao.getIdEvento()+ "," + inscricao.getIdSubEvento() + "," +
        inscricao.getIdSecao()+ "," + inscricao.getIdTrilha();
        return linha;
    }

    //Retorna uma lista de todas as Inscricoes no momento
    public ArrayList<Inscricao> getTodos() {
        String linha;
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            Inscricao inscricaoDaVez = new Inscricao();
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            inscricaoDaVez.setIdUsuario(Integer.parseInt(dados[0]));
            inscricaoDaVez.setIdEvento(Integer.parseInt(dados[1]));
            inscricaoDaVez.setIdSubEvento(Integer.parseInt(dados[2]));
            inscricaoDaVez.setIdSecao(Integer.parseInt(dados[3]));
            inscricaoDaVez.setIdTrilha(Integer.parseInt(dados[4]));
            inscricoes.add(inscricaoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return inscricoes;
    }

    //Adiciona uma Inscricao na tabela
    public void add(Inscricao inscricao) {
        String linha = inscricaoToCSV(inscricao);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
        manipulador.fecharArquivoEscrita();
    }

    public void delete (Inscricao inscricao) {
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        inscricoes = getTodos();
        for (int i = 0; i < inscricoes.size(); i++){
            if (inscricao.getIdEvento() == inscricoes.get(i).getIdEvento() &&
            inscricao.getIdUsuario() == inscricoes.get(i).getIdUsuario() &&
            inscricao.getIdSubEvento() == inscricoes.get(i).getIdSubEvento() &&
            inscricao.getIdSecao() == inscricoes.get(i).getIdSecao() &&
            inscricao.getIdTrilha() == inscricoes.get(i).getIdTrilha()){
                inscricoes.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("usuario_id,evento_id,subevento_id,secao_id,trilha_id");
        for (Inscricao u : inscricoes){
            manipulador.escreverNoArquivo(inscricaoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void update (Inscricao inscricaoAntiga, Inscricao inscricaoNova) {
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        inscricoes = getTodos();
        for (int i = 0; i < inscricoes.size(); i++){
            if (inscricaoAntiga.getIdEvento() == inscricoes.get(i).getIdEvento() &&
            inscricaoAntiga.getIdUsuario() == inscricoes.get(i).getIdUsuario() &&
            inscricaoAntiga.getIdSubEvento() == inscricoes.get(i).getIdSubEvento() &&
            inscricaoAntiga.getIdSecao() == inscricoes.get(i).getIdSecao() &&
            inscricaoAntiga.getIdTrilha() == inscricoes.get(i).getIdTrilha()){
                inscricoes.get(i).setIdEvento(inscricaoNova.getIdEvento());
                inscricoes.get(i).setIdUsuario(inscricaoNova.getIdUsuario());
                inscricoes.get(i).setIdSubEvento(inscricaoNova.getIdSubEvento());
                inscricoes.get(i).setIdSecao(inscricaoNova.getIdSecao());
                inscricoes.get(i).setIdTrilha(inscricaoNova.getIdTrilha());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("usuario_id,evento_id,subevento_id,secao_id,trilha_id");
        for (Inscricao u : inscricoes) {
            manipulador.escreverNoArquivo(inscricaoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public Inscricao getPorId(int id) {
        Inscricao inscricao = new Inscricao();
        return inscricao;
    }

        //Usar apenas na persistência da inscrição
    public Inscricao getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha){
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao i : inscricoes) {
            if (idUsuario == i.getIdUsuario() &&
                idEvento == i.getIdEvento() &&
                idSubEvento == i.getIdSecao() &&
                idSecao == i.getIdSecao() &&
                idTrilha == i.getIdTrilha()) {
                return i;
            }
        }
        return null; // Caso não encontre o ID da Inscrição
    }

    public Inscricao getPorIdInscricaoEvento(int idUsuario, int idEvento){
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao i : inscricoes) {
            if (idUsuario == i.getIdUsuario() &&
                    idEvento == i.getIdEvento() ) {
                return i;
            }
        }
        return null; // Caso não encontre o ID da Inscrição
    }
    public Inscricao getPorIdInscricaoTrilha(int idUsuario, int idTrilha){
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao i : inscricoes) {
            if (idUsuario == i.getIdUsuario() &&
                    idTrilha == i.getIdTrilha() ) {
                return i;
            }
        }
        return null; // Caso não encontre o ID da Inscrição
    }


    // Método para verificar se o usuário está inscrito em uma trilha
    public boolean estaInscritoEmTrilha(int idUsuario, int idTrilha) {
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao inscricao : inscricoes) {
            if (inscricao.getIdUsuario() == idUsuario && inscricao.getIdTrilha() == idTrilha) {
                return true;
            }
        }
        return false;
    }
}
