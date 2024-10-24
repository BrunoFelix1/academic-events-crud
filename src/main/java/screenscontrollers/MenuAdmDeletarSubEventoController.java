package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_DeletarSubEventoController extends BaseSubEventoController {

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
    private Button botaoDeletar;

    @FXML
    private TextField nomeSubEventoField;

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
    void deletarSubEvento() {
        // Implementar lógica para deletar o subevento
        String nomeSubEvento = nomeSubEventoField.getText();

        // Código para deletar o subevento do sistema

        // Exibir mensagem de sucesso ou limpar o campo
        nomeSubEventoField.clear();
    }
}
