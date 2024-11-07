package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Evento;
import persistence.PersistenceEvento;

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

    private PersistenceEvento persistence = new PersistenceEvento();

    @FXML
    void salvarEvento() {
        String nomeEvento = nomeEventoField.getText();
        String localEvento = localEventoField.getText();
        String horarioEvento = horarioEventoField.getText();
        String descricaoEvento = descricaoEventoField.getText();
        int idEvento = persistence.getTodos().size()+1;



        Evento novoEvento = new Evento(idEvento, nomeEvento, localEvento, horarioEvento, descricaoEvento);
        persistence.add(novoEvento);


        nomeEventoField.clear();
        localEventoField.clear();
        horarioEventoField.clear();
        descricaoEventoField.clear();
    }
}
