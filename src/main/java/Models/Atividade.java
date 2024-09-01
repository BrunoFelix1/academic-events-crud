package Models;
import Enum.TipoDeAtividade;
public class Atividade {

    private int id;
    private TipoDeAtividade tipoSubmissao;
    private String autor;
    private String resumo;
    private int idTrilha;
    
    
    public Atividade(int id, TipoDeAtividade tipoSubmissao, String autor, String resumo, int idTrilha) {
        this.id = id;
        this.tipoSubmissao = tipoSubmissao;
        this.autor = autor;
        this.resumo = resumo;
        this.idTrilha = idTrilha;
    }
    
    public Atividade(){}
    
    // Getters e Setters

    public int getIdTrilha() {
        return idTrilha;
    }

    public void setIdTrilha(int idTrilha) {
        this.idTrilha = idTrilha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public TipoDeAtividade getTipoSubmissao() {
        return tipoSubmissao;
    }
    
    public void setTipoSubmissao(TipoDeAtividade tipoSubmissao) {
        this.tipoSubmissao = tipoSubmissao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

}
