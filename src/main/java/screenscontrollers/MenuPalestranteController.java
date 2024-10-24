package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

    private void trocarTela(String caminho, Button botao) {
        Stage stage = (Stage) botao.getScene().getWindow();
        mostrarTela(caminho, stage);
    }

    @FXML
    private void onSair() {
        trocarTela("/screens/tela_inicial.fxml", btnSair);
    }

    @FXML
    private void onListarAtividade() {
        trocarTela("/screens/Menu_Palestrante_ListarAtividades.fxml", btnListarAtividades);
    }

    @FXML
    private void onApagarAtividade() {
        trocarTela("/screens/Menu_Palestrante_ApagarAtividade.fxml", btnApagarAtividade);
    }

    @FXML
    private void onSubmeterAtividade() {
        trocarTela("/screens/Menu_Palestrante_SubmeterAtividade.fxml", btnSubmeterAtividade);
    }

    @FXML
    private void onAtualizarAtividade() {
        trocarTela("/screens/Menu_Palestrante_AtualizarAtividade.fxml", btnAtualizarAtividade);
    }
}
