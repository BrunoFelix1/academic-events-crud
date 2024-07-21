package Models;
import java.util.Date;

public class Secao {
    private int id;
    private int id_evento;
    private int id_subEvento;
    private String local;
    private Date horario;
    
    //Construtor
    public Secao(int id, int id_evento, int id_subEvento, String local, Date horario) {
        this.id = id;
        this.id_evento = id_evento;
        this.id_subEvento = id_subEvento;
        this.local = local;
        this.horario = horario;
    }
    
    //Acessores
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_evento() {
        return id_evento;
    }
    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }
    public int getId_subEvento() {
        return id_subEvento;
    }
    public void setId_subEvento(int id_subEvento) {
        this.id_subEvento = id_subEvento;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public Date getHorario() {
        return horario;
    }
    public void setHorario(Date horario) {
        this.horario = horario;
    }


   
}
