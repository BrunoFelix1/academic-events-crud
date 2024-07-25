package Controllers;

import Models.Atividade;
import Models.Usuario;
import Persistence.PersistenceAtividade;
import Interfaces.iControladorUI;

import java.util.List;

public class AtividadeController implements iControladorUI<Atividade>{
    private PersistenceAtividade atividadeP = new PersistenceAtividade();

    public List<Atividade> ListarAtividades(){
        List<Atividade> listaAtividade =  atividadeP.getTodos();
        return listaAtividade;
    }

    public void CadastrarAtividade(Atividade atividade){
        List<Atividade> lista = atividadeP.getTodos();
        atividade.setId(lista.size() +1);
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

    @Override
    public List<Atividade> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public void cadastrar(Atividade objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public void atualizar(Atividade objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public boolean deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

}