package screenscontrollers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Inscricao;
import org.springframework.beans.factory.annotation.Autowired;
import services.InscricaoService;
import context.UserContext;

public class ListarInscricoesController extends MenuUsuarioController {
    @FXML
    private ScrollPane scrollPane;

    @Autowired
    private InscricaoService inscricaoService;
    
    @Autowired
    private UserContext userContext; // Injetar UserContext

    @FXML
    public void initialize() {
        try {
            // Obter o ID do usuário atual
            Long usuarioId = userContext.getUsuario().getId();
            
            // Utilizar o InscricaoService para obter as inscrições do usuário
            List<Inscricao> inscricoes = inscricaoService.listarInscricoesPorUsuario(usuarioId);
            populateScrollPane(inscricoes); // Preencher o ScrollPane com as inscrições
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }

    public void populateScrollPane(List<Inscricao> inscricoes) {
        VBox vbox = new VBox(10); // Adiciona espaçamento entre as inscrições
        vbox.setPadding(new Insets(10, 210, 10, 10)); // Define padding ao redor do VBox
        vbox.setStyle("-fx-background-color: #fffade;");

        for (Inscricao inscricao : inscricoes) {
            // Criando os Labels para cada atributo da inscrição
            Label usuarioIdLabel = new Label("ID Usuário: " + inscricao.getUsuario().getId());
            Label eventoIdLabel = new Label("ID Evento: " + inscricao.getEvento().getId());
            Label subEventoIdLabel = new Label("ID Subevento: " + inscricao.getSubEvento().getId());
            Label secaoIdLabel = new Label("ID Seção: " + inscricao.getSecao().getId());
            Label trilhaIdLabel = new Label("ID Trilha: " + inscricao.getTrilha().getId());

            // Estilizando os Labels
            usuarioIdLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c1477;");
            eventoIdLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            subEventoIdLabel.setStyle("-fx-font-size: 14px;");
            secaoIdLabel.setStyle("-fx-font-size: 12px;");
            trilhaIdLabel.setStyle("-fx-font-size: 12px;");

            // Adicionando os Labels ao VBox para uma única inscrição
            VBox inscricaoBox = new VBox(5); // Espaçamento entre os atributos de uma inscrição
            inscricaoBox.getChildren().addAll(usuarioIdLabel, eventoIdLabel, subEventoIdLabel, secaoIdLabel, trilhaIdLabel);
            inscricaoBox.setPadding(new Insets(5, 5, 5, 5)); // Define padding ao redor de cada inscrição
            inscricaoBox.setStyle("-fx-border-color: #7b7485; -fx-border-width: 1px; -fx-background-color: #e8e4fa;");

            // Adiciona o VBox da inscrição ao VBox principal
            vbox.getChildren().add(inscricaoBox);
        }

        // Adiciona o VBox ao ScrollPane
        scrollPane.setContent(vbox);
    }
}
