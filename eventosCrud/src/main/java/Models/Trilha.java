package Models;

public class Trilha {
    private Secao secao;
    private int id;
    private String nome;

    public Trilha(Secao secao, int id, String nome) {
        this.secao = secao;
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

