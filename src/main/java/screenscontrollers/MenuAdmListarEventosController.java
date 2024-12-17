package screenscontrollers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.Evento;

import facade.Facade;

public class MenuAdmListarEventosController extends MenuAdmGerEventController {

    @FXML
    private TextArea textAreaEventos;

    private Facade facade = new Facade();

    @FXML
    public void initialize() {
        try {
            // Utilizar a Facade para obter os eventos
            List<Evento> eventos = facade.listarEventos();
            StringBuilder sb = new StringBuilder();

            // Iterar sobre os eventos e adicionar ao StringBuilder
            for (Evento evento : eventos) {
                sb.append("ID: ").append(evento.getId()).append("\n")
                  .append("Nome: ").append(evento.getTitulo()).append("\n")
                  .append("Local: ").append(evento.getLocal()).append("\n")
                  .append("Horário: ").append(evento.getHorario()).append("\n")
                  .append("Descrição: ").append(evento.getDescricao()).append("\n")
                  .append("----------------------------\n");
            }

            textAreaEventos.setText(sb.toString());
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }
}
