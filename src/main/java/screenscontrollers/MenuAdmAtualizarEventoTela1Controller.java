package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuAdmAtualizarEventoTela1Controller extends MenuAdmGerEventController {
    

    @FXML
    private Button botaoProximo;

    @FXML
    private TextField nomeEventoField;

    @FXML
    void irParaTelaAtualizarEvento2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event_AtualizarEvento_tela2.fxml"));
            Parent root = loader.load();

            // Passar o nome do evento para a próxima tela (opcional)
            MenuAdmAtualizarEventoTela2Controller controller = loader.getController();
            controller.setNomeEvento(nomeEventoField.getText());

            Stage stage = (Stage) botaoProximo.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
