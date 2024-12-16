package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Secao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "subevento_id", nullable = false)
    private SubEvento subEvento;

    private String titulo;
    private String nome;
    private String local;
    private String horario;
    private String descricao;

    //Construtor
    public Secao(Long id, Evento evento, SubEvento subEvento, String local, String horario, String nome, String descricao) {
        this.id = id;
        this.evento = evento;
        this.subEvento = subEvento;
        this.local = local;
        this.horario = horario;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Secao(Long id, Evento evento, SubEvento subEvento, String local, String horario, String nome) {
        this.id = id;
        this.evento = evento;
        this.subEvento = subEvento;
        this.local = local;
        this.horario = horario;
        this.nome = nome;
    }

    public Secao(){}

    //Acessores
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo() {
        return this.titulo;
    }
}
