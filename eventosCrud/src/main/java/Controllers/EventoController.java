package Controllers;

import Models.Evento;
import Persistence.PersistenceEvento;

import java.util.List;

public class EventoController {
    private PersistenceEvento eventoP = new PersistenceEvento();

    public List<Evento> ListarEventos(){
        List<Evento> listaEvento =  eventoP.getTodos();
        return listaEvento;
    }

    public void CadastrarEvento(Evento evento){
        Evento e = eventoP.getPorId(evento.getId());
        if (e != null ){
            eventoP.add(evento);
        }
    }

    public void AtualizarEvento(Evento evento){
        Evento eventoAntigo = eventoP.getPorId(evento.getId());
        eventoP.update(eventoAntigo, evento);
    }
    public boolean DeletarEvento(int id){
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
