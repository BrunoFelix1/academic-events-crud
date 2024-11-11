package screenscontrollers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Evento;
import persistence.PersistenceEvento;

public class MenuAdmDeletarEventoController extends MenuAdmGerEventController {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField nomeEventoField;

    private PersistenceEvento persistence = new PersistenceEvento();

    @FXML
    void deletarEvento() {
        // Implementar lógica para deletar o evento
        String nomeEvento = nomeEventoField.getText();
        ArrayList<Evento> eventos = persistence.getTodos();
        for (Evento evento : eventos) {
            if (nomeEvento.equals(evento.getTitulo())){
                persistence.delete(evento);
            }
        }

        // Exibir mensagem de sucesso ou limpar o campo
        nomeEventoField.clear();
    }
}
