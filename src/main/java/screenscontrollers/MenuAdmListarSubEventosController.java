package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.SubEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.SubEventoService;

import java.util.List;

@Controller
public class MenuAdmListarSubEventosController extends MenuAdmGerenciarSubEventoController {

    @FXML
    private TextArea textAreaSubEventos;

    @Autowired
    private SubEventoService subEventoService;

    @FXML
    public void initialize() {
        try {
            // Utilizar o SubEventoService para obter os subeventos
            List<SubEvento> subEventos = subEventoService.listarTodosSubEventos();
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
