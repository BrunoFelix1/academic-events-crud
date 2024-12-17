package screenscontrollers;

import java.util.List;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Trilha;
import models.Inscricao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.InscricaoService;
import services.TrilhaService;

@Controller
public class ParticiparDeTrilhaController extends MenuUsuarioController {

    @FXML
    private Button botaoParticiparDeTrilha;

    @FXML
    private TextField campoTrilhaId;

    @FXML
    private Text textoMensagem;

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private TrilhaService trilhaService;

    @FXML
    private void participarDeTrilha() {
        try {
            // Pega o ID do Trilha do campo de texto
            String trilhaId = campoTrilhaId.getText();

            // Verifica se o campo não está vazio
            if (trilhaId == null || trilhaId.trim().isEmpty()) {
                textoMensagem.setText("Por favor, insira um ID de Trilha.");
                return;
            }

            if (isInteger(trilhaId)) {
                Long trilhaIdLong = Long.parseLong(trilhaId);

                // Verificar se a trilha existe
                Trilha trilha = trilhaService.buscarTrilhaPorId(trilhaIdLong);
                if (trilha == null) {
                    textoMensagem.setText("Trilha não encontrada.");
                    return;
                }

                // Criar nova inscrição
                Inscricao novaInscricao = new Inscricao();
                novaInscricao.setUsuario(UserContext.getInstance().getUsuario());
                novaInscricao.setTrilha(trilha);

                // Chamar o serviço para cadastrar a inscrição
                inscricaoService.adicionarInscricao(novaInscricao);
                textoMensagem.setText("Você se cadastrou com sucesso na Trilha!");
            } else {
                textoMensagem.setText("Erro: O ID informado não é válido.");
            }
        } catch (Exception e) {
            textoMensagem.setText("Erro ao se cadastrar na trilha.");
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