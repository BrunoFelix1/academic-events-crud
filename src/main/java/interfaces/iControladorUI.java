package interfaces;

import java.util.List;

public interface iControladorUI<T> {
    List<T> listar();
    void cadastrar(T objeto);
    void atualizar(T objeto);
    boolean deletar(int id);
}