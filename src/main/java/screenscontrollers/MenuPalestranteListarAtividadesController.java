package screenscontrollers;

import controllers.AtividadeController;
import controllers.EventoController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Atividade;
import persistence.PersistenceAtividade;

import java.util.List;

public class MenuPalestranteListarAtividadesController implements IControladorTelas {

    @FXML
    private Button btnVoltar;
    @FXML
    private ScrollPane scrollPane;

    private String fonteRepetida = "-fx-font-size: 12px;";

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    public void initialize() {
        PersistenceAtividade persistenceAtividade = new PersistenceAtividade();
        List<Atividade> atividades = persistenceAtividade.getTodos();
        populateScrollPane(atividades);
    }

    public void populateScrollPane(List<Atividade> atividades) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-background-color: #fffade;");

        for (Atividade atividade : atividades) {

            Label idLabel = new Label("ID da Trilha associada: " + atividade.getIdTrilha());
            Label tituloLabel = new Label("ID: " + atividade.getId());
            Label localLabel = new Label("Tipo de Submissão: " + atividade.getTipoSubmissao());
            Label horarioLabel = new Label("Autor: " + atividade.getAutor());
            Label descricaoLabel = new Label("Resumo: " + atividade.getResumo());

            idLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c1477;");
            tituloLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            localLabel.setStyle(fonteRepetida);
            horarioLabel.setStyle(fonteRepetida);
            descricaoLabel.setStyle(fonteRepetida);

            VBox eventoBox = new VBox(5);
            eventoBox.getChildren().addAll(idLabel, tituloLabel, localLabel, horarioLabel, descricaoLabel);
            eventoBox.setPadding(new Insets(5, 5, 5, 5));
            eventoBox.setStyle("-fx-border-color: #7b7485; -fx-border-width: 1px; -fx-background-color: #e8e4fa;");
            vbox.getChildren().add(eventoBox);
        }

        scrollPane.setContent(vbox);
    }
}
