package screenscontrollers;

import facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class CancelarInscricaoController extends MenuUsuarioController {

    @FXML
    private Button botaoCancelarInscricao;

    @FXML
    private TextField idInscricao;

    @FXML
    private Text textoMensagem;

    private Facade facade = new Facade();

    @FXML
    private void cancelarInscricao() {
        try {
            // Pega o ID da Inscrição do campo de texto
            String inscricaoId = idInscricao.getText();

            // Verifica se o campo não está vazio
            if (inscricaoId == null || inscricaoId.trim().isEmpty()) {
                textoMensagem.setText("Por favor, insira um ID de inscrição.");
                return;
            }
            if (isInteger(inscricaoId)) {
                Long inscricaoIdLong = Long.parseLong(inscricaoId);
                
                // Chamar o serviço para cancelar a inscrição
                facade.cancelarInscricao(inscricaoIdLong);
                
                textoMensagem.setText("Você cancelou com sucesso sua inscrição!");
            } else {
                textoMensagem.setText("Erro! ID inválido.");
            }
        } catch (Exception e) {
            textoMensagem.setText("Erro ao cancelar inscrição!");
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
}