package screenscontrollers;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Controller
public class EmitirCertificadoDeTrilhaController extends MenuUsuarioController {

    @FXML
    private Button botaoEmitirCertificadoDeTrilha;

    @FXML
    private TextField nomeTrilha;

    @FXML
    private void emitirCertificado() {
        String trilha = nomeTrilha.getText();
        exibirMensagem("Certificado da Trilha '" + trilha + "' está sendo emitido e enviado para você...");
    }

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Emissão de Certificado");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
