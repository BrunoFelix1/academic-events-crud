package controllers;

import java.util.List;
import interfaces.IControladorUI;
import interfaces.IPersistenciaControlador;
import models.Trilha;
import persistence.PersistenceTrilha;

public class TrilhaController implements IControladorUI<Trilha> {

    private IPersistenciaControlador<Trilha> trilhaP = new PersistenceTrilha();

    public List<Trilha> listar(){
        return trilhaP.getTodos();
    }

    public void cadastrar(Trilha trilha){
        List<Trilha> lista = trilhaP.getTodos();
        trilha.setId(lista.size()+1);
            trilhaP.add(trilha);
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

