package Models;
import Enum.TipoDeAtividade;
public class Atividade {


    private TipoDeAtividade tipoSubmissao;
    private Usuario autor;
    private String resumo;
    private Trilha trilha;

    public Atividade(TipoDeAtividade tipoSubmissao, Usuario autor, String resumo, Trilha trilha) {
        this.tipoSubmissao = tipoSubmissao;
        this.autor = autor;
        this.resumo = resumo;
        this.trilha = trilha;
    }

    // Getters e Setters

    public TipoDeAtividade getTipoSubmissao() {
        return tipoSubmissao;
    }

    public void setTipoSubmissao(TipoDeAtividade tipoSubmissao) {
        this.tipoSubmissao = tipoSubmissao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Trilha getTrilha() {
        return trilha;
    }

    public void setTrilha(Trilha trilha) {
        this.trilha = trilha;
    }
}
