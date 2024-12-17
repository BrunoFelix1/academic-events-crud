package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaInicialController implements IControladorTelas {

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCadastrar;

    @FXML
    private void onLogin() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        mostrarTela("/screens/tela_login.fxml", stage);
    }

    @FXML
    private void onCadastrar() {
        Stage stage = (Stage) btnCadastrar.getScene().getWindow();
        mostrarTela("/screens/tela_cadastro.fxml", stage);
    }

    @FXML
    private void onSair() {
        Platform.exit();
    }
}
