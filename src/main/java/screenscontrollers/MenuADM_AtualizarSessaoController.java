package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_AtualizarSessaoController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarSessao;

    @FXML
    private Button botaoListarSessoes;

    @FXML
    private Button botaoAtualizarSessao;

    @FXML
    private Button botaoDeletarSessao;

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField idSessaoField;

    @FXML
    private TextField nomeSessaoField;

    @FXML
    private TextField localSessaoField;

    @FXML
    private TextField horarioSessaoField;

    @FXML
    private TextField eventoRelacionadoField;

    @FXML
    private TextField subEventoRelacionadoField;

    @FXML
    void voltarParaGerenciarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaAdicionarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_Adicionar_Sessao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoAdicionarSessao.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarSessoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_ListarSessoes.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarSessoes.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaDeletarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_Deletar.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoDeletarSessao.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salvarAlteracoes() {
        // Implementar lógica para salvar as alterações da sessão
        String idSessao = idSessaoField.getText();
        String nomeSessao = nomeSessaoField.getText();
        String localSessao = localSessaoField.getText();
        String horarioSessao = horarioSessaoField.getText();
        String eventoRelacionado = eventoRelacionadoField.getText();
        String subEventoRelacionado = subEventoRelacionadoField.getText();

        // Código para atualizar a sessão no sistema

        // Exibir mensagem de sucesso ou limpar os campos
    }
}
