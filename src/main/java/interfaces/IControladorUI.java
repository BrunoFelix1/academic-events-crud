package interfaces;

import java.util.List;

public interface IControladorUI<T> {
    List<T> listar();
    void cadastrar(T objeto);
    void atualizar(T objeto);
    boolean deletar(int id);
}