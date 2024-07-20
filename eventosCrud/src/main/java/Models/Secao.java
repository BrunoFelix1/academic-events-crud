package Models;

public class Secao {
    private int id;
    private String nome;

    public Secao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Secao() {}

    // Getters e Setters

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
