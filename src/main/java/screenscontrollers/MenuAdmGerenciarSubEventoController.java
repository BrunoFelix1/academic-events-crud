package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuADM_GerenciarSubEventoController extends BaseSubEventoController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarSubEvento;

    @FXML
    private Button botaoListarSubEventos;

    @FXML
    private Button botaoAtualizarSubEvento;

    @FXML
    private Button botaoDeletarSubEvento;

    @FXML
    void voltarParaMenuADM() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        voltarParaMenuADM(stage);
    }

    @FXML
    void irParaTelaAdicionarSubEvento() {
        Stage stage = (Stage) botaoAdicionarSubEvento.getScene().getWindow();
        irParaTelaAdicionarSubEvento(stage);
    }

    @FXML
    void irParaTelaListarSubEventos() {
        Stage stage = (Stage) botaoListarSubEventos.getScene().getWindow();
        irParaTelaListarSubEventos(stage);
    }

    @FXML
    void irParaTelaAtualizarSubEvento() {
        Stage stage = (Stage) botaoAtualizarSubEvento.getScene().getWindow();
        irParaTelaAtualizarSubEventoTela1(stage);
    }

    @FXML
    void irParaTelaDeletarSubEvento() {
        Stage stage = (Stage) botaoDeletarSubEvento.getScene().getWindow();
        irParaTelaDeletarSubEvento(stage);
    }
}
