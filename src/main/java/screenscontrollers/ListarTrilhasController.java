package screenscontrollers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Trilha;
import persistence.PersistenceTrilha;

public class ListarTrilhasController extends MenuUsuarioController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        PersistenceTrilha persistenceTrilha = new PersistenceTrilha();
        List<Trilha> trilhas = persistenceTrilha.getTodos(); // Método para carregar os trilhas (você pode implementar isso)
        populateScrollPane(trilhas); // Preencher o ScrollPane com os trilhas
    }

    public void populateScrollPane(List<Trilha> trilhas) {
        VBox vbox = new VBox(10); // Adiciona espaçamento entre os trilhas
        vbox.setPadding(new Insets(10, 140, 10, 10)); // Define padding ao redor do VBox
        vbox.setStyle("-fx-background-color: #fffade;");

        for (Trilha trilha : trilhas) {
            // Criando os Labels para cada atributo do trilha
            Label idLabel = new Label("ID: " + trilha.getId());
            Label idSecaoLabel = new Label("ID Seção: " + trilha.getIdSecao());
            Label nomeLabel = new Label("Nome: " + trilha.getNome());
            
            // Estilizando os Labels
            idLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c1477;");
            idSecaoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            nomeLabel.setStyle("-fx-font-size: 12px;");

            
            // Adicionando os Labels ao VBox para um único trilha
            VBox trilhaBox = new VBox(5); // Espaçamento entre os atributos de um trilha
            trilhaBox.getChildren().addAll(idLabel, idSecaoLabel, nomeLabel);
            trilhaBox.setPadding(new Insets(5, 5, 5, 5)); // Define padding ao redor de cada trilha
            trilhaBox.setStyle("-fx-border-color: #7b7485; -fx-border-width: 1px; -fx-background-color: #e8e4fa;");

            // Adiciona o VBox do trilha ao VBox principal
            vbox.getChildren().add(trilhaBox);
        }

        // Adiciona o VBox ao ScrollPane
        scrollPane.setContent(vbox);
    }

}
