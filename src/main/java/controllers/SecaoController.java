package controllers;

import models.Secao;
import persistence.PersistenceSecao;
import java.util.List;
import interfaces.IControladorUI;
import interfaces.IPersistenciaControlador;

public class SecaoController implements IControladorUI<Secao> {
    private IPersistenciaControlador<Secao> secaoP = new PersistenceSecao();

    public List<Secao> listar(){
        return secaoP.getTodos();
    }

    public void cadastrar(Secao secao){
        List<Secao> lista = secaoP.getTodos();
        secao.setId(lista.size()+1);
        secaoP.add(secao);
    }

    public void atualizar(Secao secao){
        Secao secaoAntiga = secaoP.getPorId(secao.getId());
        secaoP.update(secaoAntiga, secao);
    }
    
    public boolean deletar(int id){
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
