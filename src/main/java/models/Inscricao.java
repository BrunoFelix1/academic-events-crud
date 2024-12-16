package models;

import javax.persistence.*;

@Entity
public class Inscricao {
    @EmbeddedId
    private InscricaoId id;

    @ManyToOne
    @MapsId("usuarioId")
    private Usuario usuario;

    @ManyToOne
    @MapsId("eventoId")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "subevento_id", nullable = true)
    private SubEvento subEvento;

    @ManyToOne
    @JoinColumn(name = "secao_id", nullable = true)
    private Secao secao;

    @ManyToOne
    @JoinColumn(name = "trilha_id", nullable = true)
    private Trilha trilha;

    //Construtor
    public Inscricao(Usuario usuario, Evento evento, SubEvento subEvento, Secao secao, Trilha trilha) {
        this.usuario = usuario;
        this.evento = evento;
        this.subEvento = subEvento;
        this.secao = secao;
        this.trilha = trilha;
    }

    public Inscricao() {}

    //Acessores
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

    public SubEvento getSubEvento() {
        return subEvento;
    }

    public void setSubEvento(SubEvento subEvento) {
        this.subEvento = subEvento;
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
}
