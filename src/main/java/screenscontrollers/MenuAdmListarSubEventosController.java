package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MenuAdmListarSubEventosController extends BaseSubEventoController {

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
    private TextArea textAreaSubEventos;

    @FXML
    public void initialize() {
        // Carregar os subeventos e exibi-los no textAreaSubEventos
        // Exemplo:
        // textAreaSubEventos.setText("SubEvento 1\nSubEvento 2\nSubEvento 3");
    }

    @FXML
    void voltarParaGerenciarSubEvento() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarSubEvento(stage);
    }

    @FXML
    void irParaTelaAdicionarSubEvento() {
        Stage stage = (Stage) botaoAdicionarSubEvento.getScene().getWindow();
        irParaTelaAdicionarSubEvento(stage);
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
