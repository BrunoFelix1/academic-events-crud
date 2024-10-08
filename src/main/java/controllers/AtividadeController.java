package controllers;

import models.Atividade;
import persistence.PersistenceAtividade;
import interfaces.iControladorUI;
import interfaces.iPersistenciaControlador;

import java.util.List;

public class AtividadeController implements iControladorUI<Atividade>{
    private iPersistenciaControlador<Atividade> atividadeP = new PersistenceAtividade();

    public List<Atividade> listar(){
        return  atividadeP.getTodos();
    }

    public void cadastrar(Atividade atividade){
        List<Atividade> lista = atividadeP.getTodos();
        atividade.setId(lista.size() +1);
            atividadeP.add(atividade);
    }

    public void atualizar(Atividade atividade){
        Atividade atividadeAntiga = atividadeP.getPorId(atividade.getId());
        atividadeP.update(atividadeAntiga, atividade);
    }

    public boolean deletar(int id){
        Atividade atividade = atividadeP.getPorId(id);
        if( atividade !=null ){
            atividadeP.delete(atividade);
            return true;
        }
        else {
            System.out.println("Atividade não encontrada");
            return false;
        }
    }
}