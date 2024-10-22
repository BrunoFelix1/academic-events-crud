package screenscontrollers;

import java.util.List;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Inscricao;
import persistence.PersistenceInscricao;

public class CancelarInscricaoController extends MenuUsuarioController {

    @FXML
    private Button botaoCancelarInscricao;

    @FXML
    private TextField idEvento;

    @FXML
    private TextField idSubEvento;

    @FXML
    private TextField idSecao;

    @FXML
    private TextField idTrilha;

    @FXML
    private Text textoMensagem;

    private PersistenceInscricao persistence = new PersistenceInscricao();

    @FXML
    private void cancelarInscricao() {
        try {
            // Pega o ID do Trilha do campo de texto
            String TrilhaId = idTrilha.getText();
            String EventoId = idEvento.getText();
            String SubEventoId = idSubEvento.getText();
            String SecaoId = idSecao.getText();

            // Verifica se o campo não está vazio
            if (TrilhaId == null || TrilhaId.trim().isEmpty()
            || EventoId == null || EventoId.trim().isEmpty()
            || SubEventoId == null || SubEventoId.trim().isEmpty()
            || SecaoId == null || SecaoId.trim().isEmpty()) {
                textoMensagem.setText("Por favor, insira um ID nos campos.");
                return;
            }
            if (isInteger(TrilhaId) 
            && isInteger(EventoId)
            && isInteger(SubEventoId)
            && isInteger(SecaoId)
            && verificarInscricaoExistente(Integer.parseInt(EventoId), Integer.parseInt(SubEventoId), Integer.parseInt(SecaoId), Integer.parseInt(TrilhaId), persistence.getTodos())){
                Inscricao inscricaoDeletada = new Inscricao(UserContext.getInstance().getUsuario().getId(), Integer.parseInt(EventoId), Integer.parseInt(SubEventoId), Integer.parseInt(SecaoId), Integer.parseInt(TrilhaId));
                persistence.delete(inscricaoDeletada);
                textoMensagem.setText("Você cancelou com sucesso sua inscrição!");
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

    public static boolean verificarInscricaoExistente(int idEvento, int idSubEvento, int idSecao, int idTrilha, List<Inscricao> listaInscricaos) {
        for (Inscricao Inscricao : listaInscricaos) {
            if (Inscricao.getIdEvento() == idEvento
            && Inscricao.getIdSubEvento() == idSubEvento
            && Inscricao.getIdSecao() == idSecao
            && Inscricao.getIdTrilha() == idTrilha) {
                return true;
            }
        }
        return false;
    }
}