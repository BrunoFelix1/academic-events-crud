package screenscontrollers;


import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPalestranteApagarAtividadeErroController implements IControladorTelas {


    @FXML
    private Button btnVoltar;

    private void trocarTela(String caminho, Button botao) {
        Stage stage = (Stage) botao.getScene().getWindow();
        mostrarTela(caminho, stage);
    }

    @FXML
    private void onVoltar() {
        trocarTela("/screens/Menu_Palestrante.fxml", btnVoltar);
    }
}
