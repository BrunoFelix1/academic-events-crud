package models;



public class Evento extends SameEventSubEvent {
    private int id;

    public Evento(int id, String titulo, String local, String horario, String descricao) {
        this.id = id;
        this.setTitulo(titulo);
        this.setLocal(local);
        this.setHorario(horario);
        this.setDescricao(descricao);
    }

    public Evento() {}

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
