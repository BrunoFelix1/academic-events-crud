package controllers;

import java.util.List;

public interface IController<E> {

    public abstract List<E> listar();

    public abstract E cadastrar(E entidade);

    public abstract boolean deletar(E entidade);

    public abstract E atualizar(E entidadeAntiga, E entidadeNova);
}
