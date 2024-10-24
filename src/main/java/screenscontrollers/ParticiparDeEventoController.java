package screenscontrollers;

import java.util.List;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Evento;
import models.Inscricao;
import persistence.PersistenceEvento;
import persistence.PersistenceInscricao;

public class ParticiparDeEventoController extends MenuUsuarioController {

    @FXML
    private Button botaoParticiparDeEvento;

    @FXML
    private TextField campoEventoId;

    @FXML
    private Text textoMensagem;

    private PersistenceInscricao persistence = new PersistenceInscricao();
    private PersistenceEvento eventoPersistence = new PersistenceEvento();

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
            if (isInteger(eventoId) && verificarEventoExistente(Integer.parseInt(eventoId), eventoPersistence.getTodos())){
                Inscricao novaInscricao = new Inscricao(UserContext.getInstance().getUsuario().getId(), Integer.parseInt(eventoId), 0, 0, 0);
                // Chama o método de cadastrar usando o ID do evento
                persistence.add(novaInscricao);
                textoMensagem.setText("Você se cadastrou com sucesso no evento!");
            } else {
                textoMensagem.setText("Erro!");
            }
        } catch (Exception e) {
            textoMensagem.setText("Erro!");
            System.out.println(e.getMessage());
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean verificarEventoExistente(int idEvento, List<Evento> listaEventos) {
        for (Evento evento : listaEventos) {
            if (evento.getId() == idEvento) {
                return true;
            }
        }
        return false;
    }
}