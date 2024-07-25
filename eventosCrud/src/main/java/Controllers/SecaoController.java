package Controllers;

import Models.Evento;
import Models.Secao;
import Persistence.PersistenceSecao;

import java.util.List;

public class SecaoController {
    private PersistenceSecao secaoP = new PersistenceSecao();

    public List<Secao> ListarSecao(){
        List<Secao> listaSecao =  secaoP.getTodos();
        return listaSecao;
    }
    public void CadastrarSecao(Secao Secao){
        Secao e = secaoP.getPorId(Secao.getId());
        if (e != null ){
            secaoP.add(Secao);
        }
    }
    public void AtualizarSecao(Secao secao){
        Secao secaoAntiga = secaoP.getPorId(secao.getId());
        secaoP.update(secaoAntiga, secao);
    }
    public boolean DeletarSecao(int id){
        Secao secao = secaoP.getPorId(id);
        if( secao !=null ){
            secaoP.delete(secao);
            return true;
        }
        else {
            System.out.println("Sessão não encontrada.");
            return false;
        }
    }
}
