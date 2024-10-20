package screenscontrollers;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Inscricao;
import persistence.PersistenceInscricao;

public class ParticiparDeEventoController extends MenuUsuarioController {

    @FXML
    private Button botaoParticiparDeEvento;

    @FXML
    private TextField campoEventoId;

    @FXML
    private Text textoMensagem;

    private PersistenceInscricao persistence = new PersistenceInscricao();

    @FXML
    private void participarDeEvento() {
        try {
            // Pega o ID do evento do campo de texto
            String eventoId = campoEventoId.getText();

            // Verifica se o campo não está vazio
            if (eventoId == null || eventoId.trim().isEmpty()) {
                textoMensagem.setText("Por favor, insira um ID de evento.");
                return;
            }

            Inscricao novaInscricao = new Inscricao(UserContext.getInstance().getUsuario().getId(), Integer.parseInt(eventoId), 0, 0, 0);
            // Chama o método de cadastrar usando o ID do evento
            persistence.add(novaInscricao);
            textoMensagem.setText("Você se cadastrou com sucesso no evento!");
        } catch (Exception e) {
            textoMensagem.setText("Erro!");
            System.out.println(e.getMessage());
        }
    }
}