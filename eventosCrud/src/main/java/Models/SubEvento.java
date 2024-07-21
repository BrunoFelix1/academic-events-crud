package Models;




public class SubEvento {
    private int id;
    private int idEvento;
    private String titulo;
    private String local;
    private String horario;
    private String descricao;

    //Construtor
    public SubEvento(int id, int idEvento, String titulo, String local, String horario, String descricao) {
        this.id = id;
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.local = local;
        this.horario = horario;
        this.descricao = descricao;
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
