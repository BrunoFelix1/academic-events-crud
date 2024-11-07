package screenscontrollers;

import java.io.IOException;
import java.util.ArrayList;

import controllers.SecaoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Secao;
import models.SubEvento;

public class MenuAdmListarSessoesController extends MenuAdmGerSessaoController {

    @FXML
    private ListView<String> listViewSessoes;
    @FXML
    private TextArea textAreaSecao;

    @FXML
    public void initialize() {
        // Carregar as sessões e exibi-las
        SecaoController secaoController = new SecaoController();
        ArrayList<Secao> secoes = (ArrayList<Secao>) secaoController.listar();

        StringBuilder sb = new StringBuilder();

        for (Secao secao : secoes) {
            sb.append("ID: ").append(secao.getId()).append("\n")
                    .append("Nome: ").append(secao.getNome()).append("\n")
                    .append("Local: ").append(secao.getLocal()).append("\n")
                    .append("Horário: ").append(secao.getHorario()).append("\n")
                    .append("ID do SubEvento que se relaciona: ").append(secao.getId_subEvento()).append("\n")
                    .append("ID do Evento que se relaciona: ").append(secao.getId_evento()).append("\n")
                    .append("----------------------------\n");
        }

        // Define o conteúdo formatado no campo de texto
        textAreaSecao.setText(sb.toString());

    }
}
