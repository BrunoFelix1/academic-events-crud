package controllers;

import models.Secao;
import persistence.PersistenceSecao;
import java.util.List;
import interfaces.IPersistenciaControlador;

public class SecaoController implements IController<Secao> {
    private IPersistenciaControlador<Secao> secaoP = new PersistenceSecao();

    public List<Secao> listar(){
        return secaoP.getTodos();
    }

    public Secao cadastrar(Secao secao){
        List<Secao> lista = secaoP.getTodos();
        secao.setId(lista.size()+1);
        secaoP.add(secao);
        return secao;
    }

    public Secao atualizar(Secao secaoAntiga, Secao secaoNova){
        secaoP.update(secaoAntiga, secaoNova);
        return secaoNova;
    }
    
    public boolean deletar(int secaoId){
        Secao secao = secaoP.getPorId(secaoId);
        if( secao != null ){
            secaoP.delete(secao);
            return true;
        }
        else {
            System.out.println("Sessão não encontrada.");
            return false;
        }
    }

    @Override
    public boolean deletar(Secao secao) {
        if( secao !=null ){
            secaoP.delete(secao);
            return true;
        }
        else {
            System.out.println("Seção não encontrada");
            return false;
        }
    }
}
