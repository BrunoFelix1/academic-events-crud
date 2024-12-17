package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.SubEvento;

import controllers.SubEventoController;

import java.util.List;

public class MenuAdmListarSubEventosController extends MenuAdmGerenciarSubEventoController {

    @FXML
    private TextArea textAreaSubEventos;

    private SubEventoController subEventoController = new SubEventoController();

    @FXML
    public void initialize() {
        try {
            // Utilizar o SubEventoController para obter os subeventos
            List<SubEvento> subEventos = subEventoController.listarTodosSubEventos();
            StringBuilder sb = new StringBuilder();

            // Iterar sobre os subeventos e adicionar ao StringBuilder
            for (SubEvento subEvento : subEventos) {
                sb.append("ID: ").append(subEvento.getId()).append("\n")
                  .append("Nome: ").append(subEvento.getTitulo()).append("\n")
                  .append("Local: ").append(subEvento.getLocal()).append("\n")
                  .append("Horário: ").append(subEvento.getHorario()).append("\n")
                  .append("Descrição: ").append(subEvento.getDescricao()).append("\n")
                  .append("----------------------------\n");
            }

            textAreaSubEventos.setText(sb.toString());
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }
}
