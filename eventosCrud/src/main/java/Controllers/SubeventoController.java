package Controllers;

import Models.Secao;
import Models.SubEvento;
import Persistence.PersistenceSubEvento;

import java.util.List;

public class SubeventoController {
    private PersistenceSubEvento subEventoP = new PersistenceSubEvento();

    public List<SubEvento> ListarSubEvento(){
        List<SubEvento> listasubEvento =  subEventoP.getTodos();
        return listasubEvento;
    }
    public void CadastrarSubEvento(SubEvento subEvento){
        SubEvento s = subEventoP.getPorId(subEvento.getId());
        if (s != null ){
            subEventoP.add(subEvento);
        }
    }
    public void AtualizarSubEvento(SubEvento subEvento){
        SubEvento subEventoAntigo = subEventoP.getPorId(subEvento.getId());
        subEventoP.update(subEventoAntigo, subEvento);
    }
    public boolean DeletarSubEvento(int id){
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
