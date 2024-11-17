package controllers;

import java.util.List;
import interfaces.IPersistenciaControlador;
import models.Trilha;
import persistence.PersistenceTrilha;

public class TrilhaController implements IController<Trilha> {

    private IPersistenciaControlador<Trilha> trilhaP = new PersistenceTrilha();

    public List<Trilha> listar(){
        return trilhaP.getTodos();
    }

    public Trilha cadastrar(Trilha trilha){
        List<Trilha> lista = trilhaP.getTodos();
        trilha.setId(lista.size()+1);
            trilhaP.add(trilha);
            return trilha;
    }

    public Trilha atualizar(Trilha trilhaAntiga, Trilha trilhaNova){
        trilhaP.update(trilhaAntiga, trilhaNova);
        return trilhaNova;
    }

    public Trilha atualizar(Trilha trilha){
        Trilha trilhaAntiga = trilhaP.getPorId(trilha.getId());
        trilhaP.update(trilhaAntiga, trilha);
        return trilha;
    }

    public boolean deletar(int idTrilha){
        Trilha trilha = trilhaP.getPorId(idTrilha);
        if( trilha !=null ){
            trilhaP.delete(trilha);
            return true;
        }
        else {
            System.out.println("Trilha não encontrada");
            return false;
        }
    }

    @Override
    public boolean deletar(Trilha entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
}

