package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_AdicionarTrilhaController extends BaseTrilhaController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarTrilha;

    @FXML
    private Button botaoListarTrilhas;

    @FXML
    private Button botaoAtualizarTrilha;

    @FXML
    private Button botaoDeletarTrilha;

    @FXML
    private Button botaoSalvarTrilha;

    @FXML
    private TextField nomeTrilhaField;

    @FXML
    private TextField localTrilhaField;

    @FXML
    private TextField horarioTrilhaField;

    @FXML
    private TextField sessaoRelacionadaField;

    @FXML
    void voltarParaGerenciarTrilha() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarTrilha(stage);
    }

    @FXML
    void irParaTelaListarTrilhas() {
        Stage stage = (Stage) botaoListarTrilhas.getScene().getWindow();
        irParaTelaListarTrilhas(stage);
    }

    @FXML
    void irParaTelaAtualizarTrilha() {
        Stage stage = (Stage) botaoAtualizarTrilha.getScene().getWindow();
        irParaTelaAtualizarTrilha(stage);
    }

    @FXML
    void irParaTelaDeletarTrilha() {
        Stage stage = (Stage) botaoDeletarTrilha.getScene().getWindow();
        irParaTelaDeletarTrilha(stage);
    }

    @FXML
    void salvarTrilha() {
        // Implementar lógica para salvar a nova trilha
        String nomeTrilha = nomeTrilhaField.getText();
        String localTrilha = localTrilhaField.getText();
        String horarioTrilha = horarioTrilhaField.getText();
        String sessaoRelacionada = sessaoRelacionadaField.getText();

        // Código para salvar a trilha no sistema

        // Exibir mensagem de sucesso ou limpar os campos
        nomeTrilhaField.clear();
        localTrilhaField.clear();
        horarioTrilhaField.clear();
        sessaoRelacionadaField.clear();
    }
}
