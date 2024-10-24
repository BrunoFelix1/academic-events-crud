package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        // Implementar lógica para salvar o evento
        String nomeEvento = nomeEventoField.getText();
        String localEvento = localEventoField.getText();
        String horarioEvento = horarioEventoField.getText();
        String descricaoEvento = descricaoEventoField.getText();

        // Código para salvar o evento no sistema

        // Após salvar, você pode mostrar uma mensagem de sucesso ou limpar os campos
        nomeEventoField.clear();
        localEventoField.clear();
        horarioEventoField.clear();
        descricaoEventoField.clear();
    }
}
