package screenscontrollers;

import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.SubEvento;
import persistence.PersistenceSubEvento;

public class ListarSubEventosController extends MenuUsuarioController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        PersistenceSubEvento persistenceSubEvento = new PersistenceSubEvento();
        List<SubEvento> subEventos = persistenceSubEvento.getTodos(); // Método para carregar os subeventos
        populateScrollPane(subEventos); // Preencher o ScrollPane com os subeventos
    }

    public void populateScrollPane(List<SubEvento> subEventos) {
        VBox vbox = new VBox(10); // Adiciona espaçamento entre os subeventos
        vbox.setPadding(new Insets(10, 140, 10, 10)); // Define padding ao redor do VBox
        vbox.setStyle("-fx-background-color: #fffade;");

        for (SubEvento subEvento : subEventos) {
            // Criando os Labels para cada atributo do subevento
            Label idLabel = new Label("ID: " + subEvento.getId());
            Label eventoIdLabel = new Label("Evento ID: " + subEvento.getIdEvento());
            Label tituloLabel = new Label("Título: " + subEvento.getTitulo());
            Label localLabel = new Label("Local: " + subEvento.getLocal());
            Label horarioLabel = new Label("Horário: " + subEvento.getHorario());
            Label descricaoLabel = new Label("Descrição: " + subEvento.getDescricao());
            
            // Estilizando os Labels
            idLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c1477;");
            tituloLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            eventoIdLabel.setStyle("-fx-font-size: 12px;");
            localLabel.setStyle("-fx-font-size: 12px;");
            horarioLabel.setStyle("-fx-font-size: 12px;");
            descricaoLabel.setStyle("-fx-font-size: 12px;");
            
            // Adicionando os Labels ao VBox para um único subevento
            VBox subEventoBox = new VBox(5); // Espaçamento entre os atributos de um subevento
            subEventoBox.getChildren().addAll(idLabel, eventoIdLabel, tituloLabel, localLabel, horarioLabel, descricaoLabel);
            subEventoBox.setPadding(new Insets(5, 5, 5, 5)); // Define padding ao redor de cada subevento
            subEventoBox.setStyle("-fx-border-color: #7b7485; -fx-border-width: 1px; -fx-background-color: #e8e4fa;");

            // Adiciona o VBox do subevento ao VBox principal
            vbox.getChildren().add(subEventoBox);
        }

        // Adiciona o VBox ao ScrollPane
        scrollPane.setContent(vbox);
    }
}