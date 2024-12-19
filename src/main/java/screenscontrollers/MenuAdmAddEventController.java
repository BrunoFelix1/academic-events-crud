package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;

import facade.Facade;
import interfaces.IControladorTelas;

public class MenuAdmAddEventController extends MenuAdmGerEventController implements IControladorTelas {
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
        try {
            String nomeEvento = nomeEventoField.getText();
            String localEvento = localEventoField.getText();
            String horarioEventoStr = horarioEventoField.getText();
            String descricaoEvento = descricaoEventoField.getText();

            String horarioEvento = horarioEventoStr;

            Evento novoEvento = new Evento(nomeEvento, localEvento, horarioEvento, descricaoEvento);

            // Validar e adicionar o evento usando a Facade
            if (facade.adicionarEvento(novoEvento)) {
                exibirAlertaSucesso("Evento adicionado com sucesso!");
                nomeEventoField.clear();
                localEventoField.clear();
                horarioEventoField.clear();
                descricaoEventoField.clear();
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
