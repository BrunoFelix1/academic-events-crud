package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;

import java.time.LocalDateTime;

import controllers.EventoController;

public class MenuAdmAddEventController extends MenuAdmGerEventController {
    @FXML
    private Button botaoSalvarEvento;

    @FXML
    private TextField nomeEventoField;

    @FXML
    private TextField localEventoField;

    @FXML
    private TextField horarioEventoField;

    @FXML
    private TextField descricaoEventoField;

    private EventoController eventoController = new EventoController();

    @FXML
    void salvarEvento() {
        String nomeEvento = nomeEventoField.getText();
        String localEvento = localEventoField.getText();
        String horarioEventoStr = horarioEventoField.getText();
        String descricaoEvento = descricaoEventoField.getText();

        LocalDateTime horarioEvento = LocalDateTime.parse(horarioEventoStr);

        Evento novoEvento = new Evento(nomeEvento, localEvento, horarioEvento, descricaoEvento);
        eventoController.adicionarEvento(novoEvento);

        nomeEventoField.clear();
        localEventoField.clear();
        horarioEventoField.clear();
        descricaoEventoField.clear();
    }
}
