package screenscontrollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnSair;

    @FXML
    private void onLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/tela_login.fxml"));
            Pane newScene = loader.load();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCadastrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/tela_cadastro.fxml"));
            Pane newScene = loader.load();
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSair() {
        Platform.exit();
    }
}
