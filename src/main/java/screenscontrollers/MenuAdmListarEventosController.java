package screenscontrollers;

import java.io.IOException;
import java.util.ArrayList;

import controllers.EventoController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Evento;
import models.SubEvento;

public class MenuAdmListarEventosController {

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
    private TextArea textAreaEventos;

    @FXML
    public void initialize() {
        // Carregar os eventos e exibi-los no textAreaEventos
        // Exemplo:
        // textAreaEventos.setText("Evento 1\nEvento 2\nEvento 3");
        EventoController eventoc = new EventoController();
        ArrayList<Evento> eventos = (ArrayList<Evento>) eventoc.listar();
        StringBuilder sb = new StringBuilder();

        // Itera sobre os subeventos e adiciona ao StringBuilder
        for (Evento evento : eventos) {
            sb.append("ID: ").append(evento.getId()).append("\n")
                    .append("Nome: ").append(evento.getTitulo()).append("\n")
                    .append("Local: ").append(evento.getLocal()).append("\n")
                    .append("Horário: ").append(evento.getHorario()).append("\n")
                    .append("Descrição: ").append(evento.getDescricao()).append("\n")
                    .append("----------------------------\n");
        }


        textAreaEventos.setText(sb.toString());
    }

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
