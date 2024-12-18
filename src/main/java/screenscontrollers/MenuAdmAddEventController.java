package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;

import facade.Facade;

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

    private Facade facade = new Facade();

    @FXML
    void salvarEvento() {
        String nomeEvento = nomeEventoField.getText();
        String localEvento = localEventoField.getText();
        String horarioEventoStr = horarioEventoField.getText();
        String descricaoEvento = descricaoEventoField.getText();

        String horarioEvento = horarioEventoStr;

        Evento novoEvento = new Evento(nomeEvento, localEvento, horarioEvento, descricaoEvento);
        facade.adicionarEvento(novoEvento);

        nomeEventoField.clear();
        localEventoField.clear();
        horarioEventoField.clear();
        descricaoEventoField.clear();
    }
}
