package screenscontrollers;

import controllers.AtividadeController;
import controllers.EventoController;
import controllers.TrilhaController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;
import models.Evento;
import models.Trilha;

public class MenuPalestranteAtualizarAtividadeController implements IControladorTelas {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button Confirmar;

    @FXML
    private TextField idAtividade;

    @FXML
    private TextField tipoSubmissao;

    @FXML
    private TextField atvResumo;

    @FXML
    private TextField idTrilha;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onConfirmar() {
        // Implementar lógica para salvar as alterações do evento
       // String idAtividade = idAt.getText();
       // String tpSub = tipoSub.getText();
       // String res = resumo.getText();
       // String idTri = idTrilha.getText();

    }
}