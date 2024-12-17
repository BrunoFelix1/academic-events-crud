package screenscontrollers;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@Controller
public class MenuAdmGerEventController {

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
    void voltarParaMenuADM() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM.fxml"));
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
    void irParaTelaDeletarEvento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event_deletar.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoDeletarEvento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
