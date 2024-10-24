package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Evento;
import persistence.PersistenceEvento;

public class MenuAdmAddEventController extends MenuAdmGerEventController {
    @FXML 
    private Button botaoVoltar;

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
    void voltarParaGerenciarEvento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
