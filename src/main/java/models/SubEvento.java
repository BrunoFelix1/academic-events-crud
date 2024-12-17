package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class SubEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @NotNull
    @Size(max = 100)
    private String titulo;

    @NotNull
    @Size(max = 100)
    private String local;

    @NotNull
    private LocalDateTime horario;

    @Size(max = 500)
    private String descricao;

    private String nome;
    private String data;

    //Construtor
    public SubEvento(Long id, Evento evento, String titulo, String local, LocalDateTime horario, String descricao, String nome, String data) {
        this.id = id;
        this.evento = evento;
        this.titulo = titulo;
        this.local = local;
        this.horario = horario;
        this.descricao = descricao;
        this.nome = nome;
        this.data = data;
    }

    public SubEvento() {}

    // Novo construtor sem o campo 'id'
    public SubEvento(Evento evento, String nome, String local, LocalDateTime horario, String descricao) {
        this.evento = evento;
        this.nome = nome;
        this.local = local;
        this.horario = horario;
        this.descricao = descricao;
    }

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
