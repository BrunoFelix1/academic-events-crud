package Models;

public class Inscricao {
    private int idUsuario;
    private int idEvento;
    private int idSubEvento;
    private int idSecao;
    private int idTrilha;

    //Construtor
    public Inscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.idSubEvento = idSubEvento;
        this.idSecao = idSecao;
        this.idTrilha = idTrilha;
    }

    public Inscricao() {}
    //Acessores
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    public int getIdSubEvento() {
        return idSubEvento;
    }
    public void setIdSubEvento(int idSubEvento) {
        this.idSubEvento = idSubEvento;
    }
    public int getIdSecao() {
        return idSecao;
    }
    public void setIdSecao(int idSecao) {
        this.idSecao = idSecao;
    }
    public int getIdTrilha() {
        return idTrilha;
    }
    public void setIdTrilha(int idTrilha) {
        this.idTrilha = idTrilha;
    }


}
