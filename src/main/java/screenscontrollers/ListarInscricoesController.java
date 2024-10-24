package screenscontrollers;

import java.util.List;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Inscricao;
import models.Usuario; // Importando o modelo Usuario
import persistence.PersistenceInscricao;

public class ListarInscricoesController extends MenuUsuarioController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        // Obtendo o usuário do contexto
        Usuario usuario = UserContext.getInstance().getUsuario();
        PersistenceInscricao persistenceInscricao = new PersistenceInscricao();
        List<Inscricao> inscricoes = persistenceInscricao.getTodos(usuario); // Método para carregar as inscrições
        populateScrollPane(inscricoes); // Preencher o ScrollPane com as inscrições
    }

    public void populateScrollPane(List<Inscricao> inscricoes) {
        VBox vbox = new VBox(10); // Adiciona espaçamento entre as inscrições
        vbox.setPadding(new Insets(10, 210, 10, 10)); // Define padding ao redor do VBox
        vbox.setStyle("-fx-background-color: #fffade;");

        for (Inscricao inscricao : inscricoes) {
            // Criando os Labels para cada atributo da inscrição
            Label usuarioIdLabel = new Label("ID Usuário: " + inscricao.getIdUsuario());
            Label eventoIdLabel = new Label("ID Evento: " + inscricao.getIdEvento());
            Label subEventoIdLabel = new Label("ID Subevento: " + inscricao.getIdSubEvento());
            Label secaoIdLabel = new Label("ID Seção: " + inscricao.getIdSecao());
            Label trilhaIdLabel = new Label("ID Trilha: " + inscricao.getIdTrilha());

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
