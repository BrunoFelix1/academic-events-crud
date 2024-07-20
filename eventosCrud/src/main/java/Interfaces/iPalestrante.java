package Interfaces;

import Models.Atividade;
import Models.Usuario;

import java.util.List;

public interface iPalestrante {
    void SubmeterAtividade(Usuario usuario, Atividade atividade);
    boolean ApagarAtividade(int IdAtividade);
    void AtualizarAtividade(Atividade atividade);
    List<Atividade> ListarMinhasAtividades(Usuario usuario);
}
