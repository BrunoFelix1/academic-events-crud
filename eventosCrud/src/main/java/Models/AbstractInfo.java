package Models;

public abstract class AbstractInfo {
    protected Usuario usuario;
    protected Evento evento;
    protected SubEvento subevento;
    protected Secao secao;
    protected Trilha trilha;
    protected boolean status;

    public AbstractInfo(Usuario usuario, Evento evento, SubEvento subevento, Secao secao, Trilha trilha, boolean status) {
        this.usuario = usuario;
        this.evento = evento;
        this.subevento = subevento;
        this.secao = secao;
        this.trilha = trilha;
        this.status = status;
    }

    public AbstractInfo () {}

    // Getters e Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public SubEvento getSubevento() {
        return subevento;
    }

    public void setSubevento(SubEvento subevento) {
        this.subevento = subevento;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Trilha getTrilha() {
        return trilha;
    }

    public void setTrilha(Trilha trilha) {
        this.trilha = trilha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
