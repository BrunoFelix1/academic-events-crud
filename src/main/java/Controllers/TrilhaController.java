package Controllers;

import java.util.List;
import Interfaces.iControladorUI;
import Interfaces.iPersistenciaControlador;
import Models.Trilha;
import Persistence.PersistenceTrilha;

public class TrilhaController implements iControladorUI<Trilha> {

    private iPersistenciaControlador<Trilha> trilhaP = new PersistenceTrilha();

    public List<Trilha> listar(){
        List<Trilha> listaTrilhas =  trilhaP.getTodos();
        return listaTrilhas;
    }

    public void cadastrar(Trilha trilha){
        List<Trilha> lista = trilhaP.getTodos();
        trilha.setId(lista.size()+1);
        Trilha t = trilhaP.getPorId(trilha.getId());
        //if (t != null ){
            trilhaP.add(trilha);
        //}
    }

    public void atualizar(Trilha trilha){
        Trilha trilhaAntiga = trilhaP.getPorId(trilha.getId());
        trilhaP.update(trilhaAntiga, trilha);
    }
    public boolean deletar(int id){
        Trilha trilha = trilhaP.getPorId(id);
        if( trilha !=null ){
            trilhaP.delete(trilha);
            return true;
        }
        else {
            System.out.println("Trilha não encontrada");
            return false;
        }
    }
}

