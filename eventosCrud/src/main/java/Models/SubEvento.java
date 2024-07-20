package Models;

import java.util.ArrayList;
import java.util.List;

public class SubEvento {
    private Evento evento;
    private List<Secao> secoes;

    public SubEvento(Evento evento) {
        this.evento = evento;
        this.secoes = new ArrayList<>();
    }

    // Getters e Setters

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Secao> getSecoes() {
        return secoes;
    }

    public void adicionarSecao(Secao secao) {
        this.secoes.add(secao);
    }
}
