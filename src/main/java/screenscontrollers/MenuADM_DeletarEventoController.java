package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_DeletarEventoController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarEvento;

    @FXML
    private Button botaoListarEventos;

    @FXML
    private Button botaoAtualizarEvento;

    @FXML
    private Button botaoDeletarEvento;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField nomeEventoField;

    @FXML
    void voltarParaGerenciarEvento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaAdicionarEvento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event_AddEvent.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoAdicionarEvento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarEventos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event_listarevent.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarEventos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaAtualizarEvento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event_AtualizarEvento_tela1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoAtualizarEvento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deletarEvento() {
        // Implementar lógica para deletar o evento
        String nomeEvento = nomeEventoField.getText();

        // Código para deletar o evento do sistema

        // Exibir mensagem de sucesso ou limpar o campo
        nomeEventoField.clear();
    }
}
