package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.SubEvento;
import Models.Trilha;
import Models.Usuario;
import Persistence.PersistenceTrilha;

public class TrilhaController {

    private PersistenceTrilha trilhaP = new PersistenceTrilha();

    public List<Trilha> ListarTrilhas(){

        List<Trilha> listaTrilhas =  trilhaP.getTodos();
        return listaTrilhas;
    }
    public void CadastrarTrilha(Trilha trilha){
        List<Trilha> lista = trilhaP.getTodos();
        trilha.setId(lista.size()+1);
        Trilha t = trilhaP.getPorId(trilha.getId());
        if (t != null ){
            trilhaP.add(trilha);
        }
    }

    public void AtualizarTrilha(Trilha trilha){
        Trilha trilhaAntiga = trilhaP.getPorId(trilha.getId());
        trilhaP.update(trilhaAntiga, trilha);
    }
    public boolean DeletarTrilha(int id){
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

