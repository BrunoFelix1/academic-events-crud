package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.SubEvento;
import persistence.PersistenceSubEvento;

public class MenuAdmAdicionarSubEventoController extends MenuAdmGerenciarSubEventoController {

    @FXML
    private Button botaoSalvarSubEvento;

    @FXML
    private TextField nomeSubEventoField;

    @FXML
    private TextField localSubEventoField;

    @FXML
    private TextField descricaoSubEventoField;

    @FXML
    private TextField horarioSubEventoField;

    @FXML
    private TextField eventoAssociadoField;

    private PersistenceSubEvento persistence = new PersistenceSubEvento();

    @FXML
    void salvarSubEvento() {
        // Implementar lógica para salvar o subevento
        String nome = nomeSubEventoField.getText();
        String local = localSubEventoField.getText();
        String descricao = descricaoSubEventoField.getText();
        String horario = horarioSubEventoField.getText();
        int eventoId = Integer.parseInt(eventoAssociadoField.getText());
        int subeventoId = persistence.getTodos().size() + 1;

        SubEvento novoSubEvento = new SubEvento(subeventoId, eventoId, nome, local, horario, descricao);
        persistence.add(novoSubEvento);


        nomeSubEventoField.clear();
        localSubEventoField.clear();
        descricaoSubEventoField.clear();
        horarioSubEventoField.clear();
        eventoAssociadoField.clear();
    }
}
