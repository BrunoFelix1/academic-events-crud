package Persistence;

import java.util.ArrayList;
import Models.Evento;


public class PersistenceEvento {
    //Instanciando manipulador e adicionando o path da tabela de eventos
    private String pathEvento = "C:\\Users\\PC TESTE\\Desktop\\Docs para P2\\Eventos.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathEvento);

    //Retorna um objeto Evento em formato de linha String
    private String eventoToCSV(Evento evento){
        String linha = evento.getId() + "," + evento.getTitulo()+ "," + evento.getHorario() + "," +
        evento.getLocal()+ "," + evento.getDescricao();
        return linha;
    }

    //Retorna uma lista de todos os eventos no momento
    public ArrayList<Evento> getTodosEventos() {
        Evento eventoDaVez = new Evento();
        String linha;
        ArrayList<Evento> eventos = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            eventoDaVez.setId(Integer.parseInt(dados[0]));
            eventoDaVez.setTitulo(dados[1]);
            eventoDaVez.setLocal(dados[2]);
            eventoDaVez.setHorario(dados[3]);
            eventoDaVez.setDescricao(dados[4]);
            eventos.add(eventoDaVez);
        }
        manipulador.fecharArquivoParaLeitura();
        return eventos;
    }

    //Adiciona um Evento na tabela
    public void addEvento(Evento evento) {
        String linha = eventoToCSV(evento);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
    }

    public void deleteEvento (Evento evento) {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos = getTodosEventos();
        for (int i = 0; i < eventos.size(); i++){
            if (evento.getId() == eventos.get(i).getId()){
                eventos.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,titulo,local,horario,descricao");
        for (Evento u : eventos){
            manipulador.escreverNoArquivo(eventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void updateEvento (Evento eventoAntigo, Evento eventoNovo) {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos = getTodosEventos();
        for (int i = 0; i < eventos.size(); i++){
            if (eventoAntigo.getId() == eventos.get(i).getId()) {
                eventos.get(i).setTitulo(eventoNovo.getTitulo());
                eventos.get(i).setLocal(eventoNovo.getLocal());
                eventos.get(i).setHorario(eventoNovo.getHorario());
                eventos.get(i).setDescricao(eventoNovo.getDescricao());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivo("id,titulo,local,horario,descricao");
        for (Evento u : eventos) {
            manipulador.escreverNoArquivo(eventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public Evento getEventoPorId(int id) {
        ArrayList<Evento> eventos = getTodosEventos();
        for (Evento e : eventos) {
            if (id == e.getId()) {
                return e;
            }
        }
        return null; // Caso não encontre o ID do Evento
    }
   
}
