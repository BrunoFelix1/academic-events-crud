package Controllers;

import Models.Atividade;
import Persistence.PersistenceAtividade;

import java.util.List;

public class AtividadeController {
    private PersistenceAtividade atividadeP = new PersistenceAtividade();

    public List<Atividade> ListarAtividades(){
        List<Atividade> listaAtividade =  atividadeP.getTodos();
        return listaAtividade;
    }

    public void CadastrarAtividade(Atividade atividade){
        Atividade e = atividadeP.getPorId(atividade.getId());
        if (e != null ){
            atividadeP.add(atividade);
        }
    }

    public void AtualizarAtividade(Atividade atividade){
        Atividade atividadeAntiga = atividadeP.getPorId(atividade.getId());
        atividadeP.update(atividadeAntiga, atividade);
    }

    public boolean DeletarAtividade(int id){
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