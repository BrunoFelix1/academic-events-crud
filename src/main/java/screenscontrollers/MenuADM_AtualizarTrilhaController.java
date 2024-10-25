package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Trilha;

public class MenuADM_AtualizarTrilhaController extends BaseTrilhaController {

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
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField idTrilhaField;

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
    void irParaTelaDeletarTrilha() {
        Stage stage = (Stage) botaoDeletarTrilha.getScene().getWindow();
        irParaTelaDeletarTrilha(stage);
    }

    @FXML
    void salvarAlteracoes() {
        // Implementar lógica para salvar as alterações da trilha
        String idTrilha = idTrilhaField.getText();
        String nomeTrilha = nomeTrilhaField.getText();
        String localTrilha = localTrilhaField.getText();
        String horarioTrilha = horarioTrilhaField.getText();
        String sessaoRelacionada = sessaoRelacionadaField.getText();
        int sessaoRelacionadaInt = Integer.parseInt(sessaoRelacionada);
        int idTrilhaInt = Integer.parseInt(idTrilha);
        // Código para atualizar a trilha no sistema
        Trilha trilha = new Trilha(idTrilhaInt,sessaoRelacionadaInt,nomeTrilha);
        TrilhaController trilhaController = new TrilhaController();
        trilhaController.atualizar(trilha);

        // Exibir mensagem de sucesso ou limpar os campos
    }
}
