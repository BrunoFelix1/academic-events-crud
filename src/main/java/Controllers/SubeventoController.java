package Controllers;

import Models.SubEvento;
import Persistence.PersistenceSubEvento;
import java.util.List;
import Interfaces.iControladorUI;

public class SubeventoController implements iControladorUI<SubEvento> {
    private PersistenceSubEvento subEventoP = new PersistenceSubEvento();

    public List<SubEvento> listar(){
        List<SubEvento> listasubEvento =  subEventoP.getTodos();
        return listasubEvento;
    }

    public void cadastrar(SubEvento subEvento){
        List<SubEvento> lista = subEventoP.getTodos();
        subEvento.setId(lista.size() +1);
        SubEvento s = subEventoP.getPorId(subEvento.getId());
        //if (s != null ){
            subEventoP.add(subEvento);
        //}
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
