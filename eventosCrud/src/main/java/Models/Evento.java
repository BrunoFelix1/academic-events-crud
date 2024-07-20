package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento {
    private int id;
    private String titulo;
    private String local;
    private Date horario;
    private String descricao;
    private List<Secao> secoes;
    private List<SubEvento> subEventos;

    public Evento(int id, String titulo, String local, Date horario, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.local = local;
        this.horario = horario;
        this.descricao = descricao;
        this.secoes = new ArrayList<>();
        this.subEventos = new ArrayList<>();
    }

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

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Secao> getSecoes() {
        return secoes;
    }

    public void adicionarSecao(Secao secao) {
        this.secoes.add(secao);
    }

    public List<SubEvento> getSubEventos() {
        return subEventos;
    }

    public void adicionarSubEvento(SubEvento subEvento) {
        this.subEventos.add(subEvento);
    }
}
