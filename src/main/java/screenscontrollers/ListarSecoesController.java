package screenscontrollers;

import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import controllers.SecaoController;
import models.Secao;

public class ListarSecoesController extends MenuUsuarioController {
    
    private String fonteRepetida = "-fx-font-size: 12px;";

    @FXML
    private ScrollPane scrollPane;

    private SecaoController secaoController = new SecaoController();

    @FXML
    public void initialize() {
        try {
            // Utilizar o SecaoController para obter as seções
            List<Secao> secoes = secaoController.listarTodasSecoes();
            populateScrollPane(secoes); // Preencher o ScrollPane com as seções
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }

    public void populateScrollPane(List<Secao> secoes) {
        VBox vbox = new VBox(10); // Adiciona espaçamento entre as seções
        vbox.setPadding(new Insets(10, 10, 10, 10)); // Define padding ao redor do VBox
        vbox.setStyle("-fx-background-color: #fffade;");

        for (Secao secao : secoes) {
            // Criando os Labels para cada atributo da seção
            Label idLabel = new Label("ID: " + secao.getId());
            Label nomeLabel = new Label("Nome: " + secao.getNome());
            Label descricaoLabel = new Label("Descrição: " + secao.getDescricao());
            
            // Estilizando os Labels
            idLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c1477;");
            nomeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            descricaoLabel.setStyle(fonteRepetida);
            
            // Adicionando os Labels ao VBox para uma única seção
            VBox secaoBox = new VBox(5); // Espaçamento entre os atributos de uma seção
            secaoBox.getChildren().addAll(idLabel, nomeLabel, descricaoLabel);
            secaoBox.setPadding(new Insets(5, 5, 5, 5)); // Define padding ao redor de cada seção
            secaoBox.setStyle("-fx-border-color: #7b7485; -fx-border-width: 1px; -fx-background-color: #e8e4fa;");

            // Adiciona o VBox da seção ao VBox principal
            vbox.getChildren().add(secaoBox);
        }

        // Adiciona o VBox ao ScrollPane
        scrollPane.setContent(vbox);
    }
}
