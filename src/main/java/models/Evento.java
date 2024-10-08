package models;



public class Evento {
    private int id;
    private String titulo;
    private String local;
    private String horario;
    private String descricao;


    public Evento(int id, String titulo, String local, String horario, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.local = local;
        this.horario = horario;
        this.descricao = descricao;
    }

    public Evento() {}

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
