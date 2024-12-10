package controllers;

import java.util.List;

import models.SubEvento;
import persistence.PersistenceSubEvento;

public class SubeventoController {

    private PersistenceSubEvento subEventoP = new PersistenceSubEvento();

    public SubEvento cadastrar(SubEvento subEvento) {
        List<SubEvento> lista = subEventoP.getTodos();
        subEvento.setId(lista.size() + 1);
        subEventoP.add(subEvento);
        return subEvento;
    }

    public SubEvento atualizar(SubEvento subEventoAntigo, SubEvento subEventoNovo) {
        if (subEventoAntigo != null && subEventoNovo != null) {
            subEventoP.update(subEventoAntigo, subEventoNovo);
        }
        return subEventoNovo;
    }

    public boolean deletar(int idSubEvento) {
        SubEvento subEvento = subEventoP.getPorId(idSubEvento);
        if (subEvento != null) {
            subEventoP.delete(subEvento);
            return true;
        } else {
            System.out.println("SubEvento não encontrado para o ID: " + idSubEvento);
            return false;
        }
    }

    public boolean deletar(SubEvento subEvento) {
        if (subEvento != null) {
            subEventoP.delete(subEvento);
            return true;
        } else {
            System.out.println("Tentativa de deletar um SubEvento nulo");
            return false;
        }
    }

    public SubEvento buscarPorId(int id) {
        return subEventoP.getPorId(id);
    }

    public List<SubEvento> listar() {
        return subEventoP.getTodos();
    }
}
