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

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onConfirmar() {
        try {
            String idSessao = idTXT.getText();
            AtividadeController atividadeController = new AtividadeController();
            int id = Integer.parseInt(idSessao);
            boolean sucesso = atividadeController.deletar(id);
            if (sucesso) {
                exibirAlertaSucesso("Atividade deletada com sucesso.");
            } else {
                exibirAlerta("Erro ao excluir atividade.");
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Erro: O ID informado não é válido.");
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlerta("Erro ao excluir atividade.");
        }
    }
}
