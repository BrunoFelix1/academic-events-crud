package models;

import jakarta.persistence.*;


@Entity
public class Trilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "secao_id", nullable = false)
    private Secao secao;

    private String nome;

    //Construtor
    public Trilha(Long id, Secao secao, String nome) {
        this.id = id;
        this.secao = secao;
        this.nome = nome;
    }

    public Trilha() {}

    // Novo construtor sem o campo 'id'
    public Trilha(Secao secao, String nome) {
        this.secao = secao;
        this.nome = nome;
    }

    //Acessores
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

