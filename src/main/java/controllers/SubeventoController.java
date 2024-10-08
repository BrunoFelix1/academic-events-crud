package controllers;

import models.SubEvento;
import persistence.PersistenceSubEvento;
import java.util.List;
import interfaces.IControladorUI;

public class SubeventoController implements IControladorUI<SubEvento> {
    private PersistenceSubEvento subEventoP = new PersistenceSubEvento();

    public List<SubEvento> listar(){
        return subEventoP.getTodos();
    }

    public void cadastrar(SubEvento subEvento){
        List<SubEvento> lista = subEventoP.getTodos();
        subEvento.setId(lista.size() +1);
            subEventoP.add(subEvento);
    }

    public void atualizar(SubEvento subEvento){
        SubEvento subEventoAntigo = subEventoP.getPorId(subEvento.getId());
        subEventoP.update(subEventoAntigo, subEvento);
    }
    
    public boolean deletar(int id){
        SubEvento subEvento = subEventoP.getPorId(id);
        if( subEvento !=null ){
            subEventoP.delete(subEvento);
            return true;
        }
        else {
            System.out.println("SubEvento não encontrado");
            return false;
        }
    }
}
