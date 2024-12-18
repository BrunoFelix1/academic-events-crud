package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Secao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "sub_evento_id", nullable = false)
    private SubEvento subEvento;

    @NotNull
    @Size(max = 100)
    private String nome;

    @Size(max = 255)
    private String descricao;

    private String local;
    private String horario;

    // Construtor
    public Secao(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Secao() {}

    // Novo construtor sem o campo 'id'
    public Secao(Evento evento, SubEvento subEvento, String nome, String local, String horario) {
        this.evento = evento;
        this.subEvento = subEvento;
        this.nome = nome;
        this.local = local;
        this.horario = horario;
    }

    // Getters e Setters
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
