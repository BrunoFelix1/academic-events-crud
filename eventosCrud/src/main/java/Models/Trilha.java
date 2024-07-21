package Models;

public class Trilha {
    private int id;
    private int idSecao;
    private String nome;
    
    //Construtor
    public Trilha(int id, int idSecao, String nome) {
        this.id = id;
        this.idSecao = idSecao;
        this.nome = nome;
    }
    
    //Acessores
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSecao() {
        return idSecao;
    }

    public void setIdSecao(int idSecao) {
        this.idSecao = idSecao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}

