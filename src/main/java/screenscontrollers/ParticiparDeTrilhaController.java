package screenscontrollers;

import context.UserContext;
import facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Trilha;
import models.Inscricao;
import interfaces.IControladorTelas;

public class ParticiparDeTrilhaController extends MenuUsuarioController implements IControladorTelas {

    @FXML
    private Button botaoParticiparDeTrilha;

    @FXML
    private TextField campoTrilhaId;

    @FXML
    private Text textoMensagem;

    private Facade facade = new Facade();

    @FXML
    private void participarDeTrilha() {
        try {
            // Pega o ID da trilha do campo de texto
            String trilhaId = campoTrilhaId.getText();

            // Verifica se o campo não está vazio
            if (trilhaId == null || trilhaId.trim().isEmpty()) {
                exibirAlerta("Por favor, insira um ID de trilha.");
                return;
            }

            if (isInteger(trilhaId)) {
                Long trilhaIdLong = Long.parseLong(trilhaId);

                // Verificar se a trilha existe
                Trilha trilha = facade.buscarTrilha(trilhaIdLong);
                if (trilha == null) {
                    exibirAlerta("Trilha não encontrada.");
                    return;
                }

                // Criar nova inscrição
                Inscricao novaInscricao = new Inscricao();
                novaInscricao.setUsuario(UserContext.getInstance().getUsuario());
                novaInscricao.setTrilha(trilha);

                // Chamar o serviço para cadastrar a inscrição
                if (facade.adicionarInscricao(novaInscricao)) {
                    exibirAlertaSucesso("Você se cadastrou com sucesso na trilha!");
                    campoTrilhaId.clear();
                }
            } else {
                exibirAlerta("Erro: O ID informado não é válido.");
            }
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
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