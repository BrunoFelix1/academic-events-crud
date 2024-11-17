package controllers;

import models.Evento;
import persistence.PersistenceEvento;
import java.util.List;
import interfaces.IPersistenciaControlador;

public class EventoController implements IController<Evento> {
    private IPersistenciaControlador<Evento> eventoP = new PersistenceEvento();

    public List<Evento> listar(){
        return eventoP.getTodos();
    }

    public Evento cadastrar(Evento evento){
        List<Evento> lista = eventoP.getTodos();
        evento.setId(lista.size()+1);
            eventoP.add(evento);
            return evento;
    }

    public Evento atualizar(Evento eventoAntigo, Evento eventoNovo){
        eventoP.update(eventoAntigo, eventoNovo);
        return eventoNovo;
    }

    public boolean deletar(Evento evento){
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
