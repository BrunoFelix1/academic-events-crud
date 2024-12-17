package screenscontrollers;


import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import services.UsuarioService;
import services.TrilhaService;
import services.InscricaoService;
import org.springframework.stereotype.Controller;

@Controller
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

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TrilhaService trilhaService;

    @Autowired
    private InscricaoService inscricaoService;

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
            && isInteger(SecaoId)){
                Usuario usuario = usuarioService.buscarUsuarioPorId(UserContext.getInstance().getUsuario().getId());
                Long trilhaIdLong = Long.parseLong(TrilhaId);
                trilhaService.buscarTrilhaPorId(trilhaIdLong);
                
                // Chamar o serviço para cancelar a inscrição
                inscricaoService.cancelarInscricao(
                    usuario.getId(), 
                    Long.parseLong(EventoId), 
                    Long.parseLong(SubEventoId), 
                    Long.parseLong(SecaoId), 
                    trilhaIdLong
                );
                
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
}