package controllers;

import models.Evento;
import persistence.PersistenceEvento;
import java.util.List;
import interfaces.iControladorUI;
import interfaces.iPersistenciaControlador;

public class EventoController implements iControladorUI<Evento> {
    private iPersistenciaControlador<Evento> eventoP = new PersistenceEvento();

    public List<Evento> listar(){
        return eventoP.getTodos();
    }

    public void cadastrar(Evento evento){
        List<Evento> lista = eventoP.getTodos();
        evento.setId(lista.size()+1);
            eventoP.add(evento);
    }

    public void atualizar(Evento evento){
        Evento eventoAntigo = eventoP.getPorId(evento.getId());
        eventoP.update(eventoAntigo, evento);
    }

    public boolean deletar(int id){
        Evento evento = eventoP.getPorId(id);
        if( evento !=null ){
            eventoP.delete(evento);
            return true;
        }
        else {
            System.out.println("Evento não encontrado");
            return false;
        }
    }
}
