package screenscontrollers;

import facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import context.UserContext;
import interfaces.IControladorTelas;

public class CancelarInscricaoController extends MenuUsuarioController implements IControladorTelas {

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
                exibirAlerta("Por favor, insira um ID de inscrição.");
                return;
            }
            if (isInteger(inscricaoId)) {
                Long inscricaoIdLong = Long.parseLong(inscricaoId);
                
                // Pega o ID do usuário do contexto
                Long userId = UserContext.getInstance().getUsuario().getId();
                
                // Chamar o serviço para cancelar a inscrição com o ID do usuário
                facade.cancelarInscricao(inscricaoIdLong, userId);
                
                exibirAlertaSucesso("Você cancelou com sucesso sua inscrição!");
            } else {
                exibirAlerta("Erro! ID inválido.");
            }
        } catch (Exception e) {
            exibirAlerta("Erro ao cancelar inscrição!");
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