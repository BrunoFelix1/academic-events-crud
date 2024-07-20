package Interfaces;

import Models.Evento;
import Models.Secao;
import Models.SubEvento;
import Models.Trilha;

import java.util.List;

public interface iAdministrador {
    // CRUD Eventos
    boolean AdicionarEvento(Evento evento); // retorne falso se o evento estiver cadastrado
    List<Evento> ListarEventos();
    void AtualizarEvento(Evento evento);
    boolean DeletarEvento(int idEvento);  //   retorne falso se não existir

    // CRUD SubEventos
    boolean AdicionarSubEvento(SubEvento subevento);
    List<SubEvento> ListarSubEventos();
    void AtualizarSubEvento(SubEvento subevento);
    boolean DeletarSubEvento(int idSubEvento);

    // CRUD Secao
    boolean AdicionarSecao(Secao secao);
    List<Secao> ListarSecoes();
    void AtualizarSecao(Secao secao);
    boolean DeletarSecao(int idSecao);

    // CRUD Trilha
    boolean AdicionarTrilha(Trilha trilha);
    List<Trilha> ListarTrilhas();
    void AtualizarTrilha(Trilha trilha);
    boolean DeletarTrilha(int idTrilha);
}
