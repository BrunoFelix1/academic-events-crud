package models;

public class SubEvento extends SameEventSubEvent {
    private int id;
    private int idEvento;
   
    //Construtor
    public SubEvento(int id, int idEvento, String titulo, String local, String horario, String descricao) {
        this.id = id;
        this.idEvento = idEvento;
        this.setTitulo(titulo);
        this.setLocal(local);
        this.setHorario(horario);
        this.setDescricao(descricao);
    }
    //Acessores
    public SubEvento() {}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
}
