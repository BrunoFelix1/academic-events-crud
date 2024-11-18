package controllers;

import models.SubEvento;
import models.SubEvento;
import persistence.PersistenceSubEvento;
import java.util.List;

public class SubeventoController implements IController<SubEvento> {
    private PersistenceSubEvento subEventoP = new PersistenceSubEvento();

    public List<SubEvento> listar(){
        return subEventoP.getTodos();
    }

    public SubEvento cadastrar(SubEvento subEvento){
        List<SubEvento> lista = subEventoP.getTodos();
        subEvento.setId(lista.size() +1);
            subEventoP.add(subEvento);
            return subEvento;
    }

    public SubEvento atualizar(SubEvento subEventoAntigo, SubEvento subEventoNovo){
        subEventoP.update(subEventoAntigo, subEventoNovo);
        return subEventoNovo;
    }
    
    public boolean deletar(int idSubEvento){
        SubEvento subEvento = subEventoP.getPorId(idSubEvento);
        if( subEvento !=null ){
            subEventoP.delete(subEvento);
            return true;
        }
        else {
            System.out.println("SubEvento não encontrado");
            return false;
        }
    }

    @Override
    public boolean deletar(SubEvento SubEvento) {
        if( SubEvento !=null ){
            subEventoP.delete(SubEvento);
            return true;
        }
        else {
            System.out.println("Subevento não encontrada");
            return false;
        }
    }

}
