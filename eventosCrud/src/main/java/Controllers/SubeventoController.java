package Controllers;

import Models.Secao;
import Models.SubEvento;
import Models.Usuario;
import Persistence.PersistenceSubEvento;

import java.util.List;

public class SubeventoController {
    private PersistenceSubEvento subEventoP = new PersistenceSubEvento();

    public List<SubEvento> ListarSubEvento(){
        List<SubEvento> listasubEvento =  subEventoP.getTodos();
        return listasubEvento;
    }
    public void CadastrarSubEvento(SubEvento subEvento){
        List<SubEvento> lista = subEventoP.getTodos();
        subEvento.setId(lista.size() +1);
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
