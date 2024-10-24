package screenscontrollers;

import controllers.UsuarioController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;

import java.util.List;

public class MenuPalestranteController implements IControladorTelas {

    @FXML
    private Button btnSair;
    @FXML
    private Button btnListarAtividades;
    @FXML
    private Button btnAtualizarAtividade;
    @FXML
    private Button btnSubmeterAtividade;
    @FXML
    private Button btnApagarAtividade;

    @FXML
    private void onSair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        mostrarTela("/screens/tela_inicial.fxml", stage);
    }
    @FXML
    private void onListarAtividade() {
        Stage stage = (Stage) btnListarAtividades.getScene().getWindow();
        mostrarTela("/screens/Menu_Palestrante_ListarAtividades.fxml", stage);
    }
    @FXML
    private void onApagarAtividade() {
        Stage stage = (Stage) btnApagarAtividade.getScene().getWindow();
        mostrarTela("/screens/Menu_Palestrante_ApagarAtividade.fxml", stage);
    }
    @FXML
    private void onSubmeterAtividade() {
        Stage stage = (Stage) btnSubmeterAtividade.getScene().getWindow();
        mostrarTela("/screens/Menu_Palestrante_SubmeterAtividade.fxml", stage);
    }
    @FXML
    private void onAtualizarAtividade() {
        Stage stage = (Stage) btnAtualizarAtividade.getScene().getWindow();
        mostrarTela("/screens/Menu_Palestrante_AtualizarAtividade.fxml", stage);
    }

}
