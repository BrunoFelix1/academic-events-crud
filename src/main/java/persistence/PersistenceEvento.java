package persistence;

import java.util.ArrayList;
import interfaces.IPersistenciaControlador;
import models.Evento;


public class PersistenceEvento implements IPersistenciaControlador<Evento> {
    //Instanciando manipulador e adicionando o path da tabela de eventos
    private String pathEvento = "src\\main\\resources\\database\\Eventos.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathEvento);

    public void setManipulador(ManipuladorArquivos manipulador) {
        this.manipulador = manipulador;
    }
    
    //Retorna um objeto Evento em formato de linha String
    public String eventoToCSV(Evento evento){
        String linha = evento.getId() + "," + evento.getTitulo()+ "," + evento.getLocal() + "," +
        evento.getHorario()+ "," + evento.getDescricao();
        return linha;
    }

    //Retorna uma lista de todos os eventos no momento
    public ArrayList<Evento> getTodos() {
        String linha;
        ArrayList<Evento> eventos = new ArrayList<>();
        manipulador.abrirArquivoParaLeitura();
        while ((linha = manipulador.lerLinhaArquivo()) != null){
            //Desconsiderando cabeçalho
            if (linha.contains("id")){
                continue;
            }
            String dados [] = linha.split(",");
            Evento eventoDaVez = new Evento(); // Cria um novo objeto Evento a cada iteração
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
    public void add(Evento evento) {
        String linha = eventoToCSV(evento);
        manipulador.abrirArquivoParaEscrita();
        manipulador.escreverNoArquivoPorUltimo(linha);
        manipulador.fecharArquivoEscrita();
    }

    public void delete (Evento evento) {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos = getTodos();
        for (int i = 0; i < eventos.size(); i++){
            if (evento.getId() == eventos.get(i).getId()){
                eventos.remove(i);
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,titulo,local,horario,descricao");
        for (Evento u : eventos){
            manipulador.escreverNoArquivo(eventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public void update (Evento eventoAntigo, Evento eventoNovo) {
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos = getTodos();
        for (int i = 0; i < eventos.size(); i++){
            if (eventoAntigo.getId() == eventos.get(i).getId()) {
                eventos.get(i).setTitulo(eventoNovo.getTitulo());
                eventos.get(i).setLocal(eventoNovo.getLocal());
                eventos.get(i).setHorario(eventoNovo.getHorario());
                eventos.get(i).setDescricao(eventoNovo.getDescricao());
                break;
            }
        }
        manipulador.abrirArquivoParaEscrita(1);
        manipulador.escreverNoArquivo("id,titulo,local,horario,descricao");
        for (Evento u : eventos) {
            manipulador.escreverNoArquivo(eventoToCSV(u));
        }
        manipulador.fecharArquivoEscrita();
    }

    public Evento getPorNome(String nome) {
        ArrayList<Evento> eventos = getTodos();
        for (Evento e : eventos) {
            if (nome.equals(e.getTitulo())) {
                return e;
            }
        }
        return null; // Caso não encontre o ID do Evento
    }

    public Evento getPorId(int id) {
        ArrayList<Evento> eventos = getTodos();
        for (Evento e : eventos) {
            if (id == e.getId()) {
                return e;
            }
        }
        return null; // Caso não encontre o ID do Evento
    }

    //Usar apenas na persistência da inscrição
    public Evento getPorIdInscricao(int id, int id2, int id3, int id4, int id5){
        Evento Evento = new Evento();
        return Evento;
    }




   
}
