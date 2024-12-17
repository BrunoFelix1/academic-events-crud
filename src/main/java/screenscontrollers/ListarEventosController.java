package screenscontrollers;

import java.util.List;

import facade.Facade;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Evento;

public class ListarEventosController extends MenuUsuarioController {
    
    private String fonteRepetida = "-fx-font-size: 12px;";

    @FXML
    private ScrollPane scrollPane;

    private Facade facade = new Facade();

    @FXML
    public void initialize() {
        try {
            // Utilizar a Facade para obter os eventos
            List<Evento> eventos = facade.listarEventos();
            populateScrollPane(eventos); // Preencher o ScrollPane com os eventos
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }

    public void populateScrollPane(List<Evento> eventos) {
        VBox vbox = new VBox(10); // Adiciona espaçamento entre os eventos
        vbox.setPadding(new Insets(10, 10, 10, 10)); // Define padding ao redor do VBox
        vbox.setStyle("-fx-background-color: #fffade;");

        for (Evento evento : eventos) {
            // Criando os Labels para cada atributo do evento
            Label idLabel = new Label("ID: " + evento.getId());
            Label tituloLabel = new Label("Título: " + evento.getTitulo());
            Label localLabel = new Label("Local: " + evento.getLocal());
            Label horarioLabel = new Label("Horário: " + evento.getHorario());
            Label descricaoLabel = new Label("Descrição: " + evento.getDescricao());
            
            // Estilizando os Labels
            idLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c1477;");
            tituloLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            localLabel.setStyle(fonteRepetida);
            horarioLabel.setStyle(fonteRepetida);
            descricaoLabel.setStyle(fonteRepetida);
            
            // Adicionando os Labels ao VBox para um único evento
            VBox eventoBox = new VBox(5); // Espaçamento entre os atributos de um evento
            eventoBox.getChildren().addAll(idLabel, tituloLabel, localLabel, horarioLabel, descricaoLabel);
            eventoBox.setPadding(new Insets(5, 5, 5, 5)); // Define padding ao redor de cada evento
            eventoBox.setStyle("-fx-border-color: #7b7485; -fx-border-width: 1px; -fx-background-color: #e8e4fa;");

            // Adiciona o VBox do evento ao VBox principal
            vbox.getChildren().add(eventoBox);
        }

        // Adiciona o VBox ao ScrollPane
        scrollPane.setContent(vbox);
    }

}
