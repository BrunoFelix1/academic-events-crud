package screenscontrollers;

import controllers.EventoController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;
import models.Evento;
import models.Secao;
import persistence.PersistenceEvento;

public class MenuPalestranteSubmeterAtividadeController implements IControladorTelas {
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private TextField tpSub;

    private TextField resumo;

    private TextField idTrilha;


    private PersistenceEvento persistence = new PersistenceEvento();

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
        String tiposub = tpSub.getText();
        String resum = resumo.getText();
        String trilha = idTrilha.getText();
        int idEvento = persistence.getTodos().size()+1;


        trocarTela("/screens/Menu_Palestrante_Sucesso.fxml", btnVoltar);
    }
}