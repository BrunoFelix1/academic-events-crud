package screenscontrollers;

import java.io.IOException;

import controllers.SecaoController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuAdmDeletarSessaoController {

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
    private Button botaoDeletar;

    @FXML
    private TextField idSessaoField;

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
    void irParaTelaAtualizarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_Atualizar_Sessao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoAtualizarSessao.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deletarSessao() {
        // Implementar lógica para deletar a sessão
        String idSessao = idSessaoField.getText();

        // Código para deletar a sessão do sistema
        SecaoController secaoController = new SecaoController();
        int id = Integer.parseInt(idSessao);
        secaoController.deletar(id);
        // Exibir mensagem de sucesso ou limpar o campo
        idSessaoField.clear();
    }
}
