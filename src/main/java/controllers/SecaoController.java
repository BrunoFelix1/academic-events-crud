package controllers;

import models.Secao;
import persistence.PersistenceSecao;
import java.util.List;
import interfaces.iControladorUI;
import interfaces.iPersistenciaControlador;

public class SecaoController implements iControladorUI<Secao> {
    private iPersistenciaControlador<Secao> secaoP = new PersistenceSecao();

    public List<Secao> listar(){
        List<Secao> listaSecao =  secaoP.getTodos();
        return listaSecao;
    }

    public void cadastrar(Secao Secao){
        List<Secao> lista = secaoP.getTodos();
        Secao.setId(lista.size()+1);
        Secao e = secaoP.getPorId(Secao.getId());
        //if (e != null ){
            secaoP.add(Secao);
        //}
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
