package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_DeletarTrilhaController extends BaseTrilhaController {

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
    private Button botaoDeletar;

    @FXML
    private TextField idTrilhaField;

    @FXML
    void voltarParaGerenciarTrilha() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarTrilha(stage);
    }

    @FXML
    void irParaTelaAdicionarTrilha() {
        Stage stage = (Stage) botaoAdicionarTrilha.getScene().getWindow();
        irParaTelaAdicionarTrilha(stage);
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
    void deletarTrilha() {
        // Implementar lógica para deletar a trilha
        String idTrilha = idTrilhaField.getText();
        int idTrilhaInt = Integer.parseInt(idTrilha);
        // Código para deletar a trilha do sistema
        TrilhaController trilhaController= new TrilhaController();
        trilhaController.deletar(idTrilhaInt);
        // Exibir mensagem de sucesso ou limpar o campo
        idTrilhaField.clear();
    }
}
