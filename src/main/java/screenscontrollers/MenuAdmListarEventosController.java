package screenscontrollers;

import java.util.ArrayList;

import controllers.EventoController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.Evento;

public class MenuAdmListarEventosController extends MenuAdmGerEventController {
    @FXML
    private TextArea textAreaEventos;

    @FXML
    public void initialize() {
        // Carregar os eventos e exibi-los no textAreaEventos
        // Exemplo:
        // textAreaEventos.setText("Evento 1\nEvento 2\nEvento 3");
        EventoController eventoc = new EventoController();
        ArrayList<Evento> eventos = (ArrayList<Evento>) eventoc.listar();
        StringBuilder sb = new StringBuilder();

        // Itera sobre os subeventos e adiciona ao StringBuilder
        for (Evento evento : eventos) {
            sb.append("ID: ").append(evento.getId()).append("\n")
                    .append("Nome: ").append(evento.getTitulo()).append("\n")
                    .append("Local: ").append(evento.getLocal()).append("\n")
                    .append("Horário: ").append(evento.getHorario()).append("\n")
                    .append("Descrição: ").append(evento.getDescricao()).append("\n")
                    .append("----------------------------\n");
        }


        textAreaEventos.setText(sb.toString());
    }
}
