package interfaces;


import java.util.ArrayList;

public interface IPersistenciaControlador<E>{
     ArrayList<E> getTodos();
     void add(E entidade);
     void delete(E entidade);
     void update(E entidadeAntiga, E entidadeNova);
     E getPorId(int id); //Usar para todos os tipos menos Inscrição
     E getPorIdInscricao(int idUsuario,int idEvento,int idSubEvento, int idSecao, int idTrilha); //Usar apenas para o tipo Inscrição

}
