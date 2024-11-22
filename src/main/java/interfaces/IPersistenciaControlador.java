package interfaces;


import java.util.ArrayList;

public interface IPersistenciaControlador<E>{
     ArrayList<E> getTodos();
     E add(E entidade);
     boolean delete(E entidade);
     E update(E entidadeAntiga, E entidadeNova);
     E getPorId(int id); //Usar para todos os tipos menos Inscrição
     E getPorIdInscricao(int idUsuario,int idEvento,int idSubEvento, int idSecao, int idTrilha); //Usar apenas para o tipo Inscrição

}
