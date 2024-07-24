package Interfaces;


import java.util.ArrayList;

public interface iPersistenciaControlador<E>{
     ArrayList<E> getTodos();
     void add(E entidade);
     void delete(E entidade);
     void update(E entidadeAntiga, E entidadeNova);
     E getPorId(int id);

}
