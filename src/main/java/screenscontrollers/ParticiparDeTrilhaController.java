package screenscontrollers;

import java.util.List;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Trilha;
import models.Inscricao;
import persistence.PersistenceTrilha;
import persistence.PersistenceInscricao;

public class ParticiparDeTrilhaController extends MenuUsuarioController {

    @FXML
    private Button botaoParticiparDeTrilha;

    @FXML
    private TextField campoTrilhaId;

    @FXML
    private Text textoMensagem;

    private PersistenceInscricao persistence = new PersistenceInscricao();
    private PersistenceTrilha TrilhaPersistence = new PersistenceTrilha();

    @FXML
    private void participarDeTrilha() {
        try {
            // Pega o ID do Trilha do campo de texto
            String TrilhaId = campoTrilhaId.getText();

            // Verifica se o campo não está vazio
            if (TrilhaId == null || TrilhaId.trim().isEmpty()) {
                textoMensagem.setText("Por favor, insira um ID de Trilha.");
                return;
            }
            if (isInteger(TrilhaId) && verificarTrilhaExistente(Integer.parseInt(TrilhaId), TrilhaPersistence.getTodos())){
                Inscricao novaInscricao = new Inscricao(UserContext.getInstance().getUsuario().getId(), 0, 0, 0, Integer.parseInt(TrilhaId));
                // Chama o método de cadastrar usando o ID do Trilha
                persistence.add(novaInscricao);
                textoMensagem.setText("Você se cadastrou com sucesso na Trilha!");
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

    public static boolean verificarTrilhaExistente(int idTrilha, List<Trilha> listaTrilhas) {
        for (Trilha Trilha : listaTrilhas) {
            if (Trilha.getId() == idTrilha) {
                return true;
            }
        }
        return false;
    }
}