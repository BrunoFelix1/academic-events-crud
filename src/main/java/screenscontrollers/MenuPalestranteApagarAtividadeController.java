package screenscontrollers;

import controllers.AtividadeController;
import controllers.EventoController;
import controllers.SecaoController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;

public class MenuPalestranteApagarAtividadeController implements IControladorTelas {
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private TextField idTXT;

    private void trocarTela(String caminho, Button botao) {
        Stage stage = (Stage) botao.getScene().getWindow();
        mostrarTela(caminho, stage);
    }

    @FXML
    private void onVoltar() {
        trocarTela("/screens/Menu_Palestrante.fxml", btnVoltar);
    }

    @FXML
    private void onConfirmar() {
        String idSessao = idTXT.getText();

        // Código para deletar a sessão do sistema
        AtividadeController atividadeController = new AtividadeController();
        int id = Integer.parseInt(idSessao);
        atividadeController.deletar(id);

        trocarTela("/screens/Menu_Palestrante_Sucesso.fxml", btnVoltar);
    }
}
