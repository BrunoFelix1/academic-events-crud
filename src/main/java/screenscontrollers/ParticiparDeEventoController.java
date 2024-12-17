package screenscontrollers;

import java.util.List;

import context.UserContext;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Evento;
import models.Inscricao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.InscricaoService;
import services.EventoService;

@Controller
public class ParticiparDeEventoController extends MenuUsuarioController {

    @FXML
    private Button botaoParticiparDeEvento;

    @FXML
    private TextField campoEventoId;

    @FXML
    private Text textoMensagem;

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private EventoService eventoService;

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

            if (isInteger(eventoId)) {
                Long eventoIdLong = Long.parseLong(eventoId);

                // Verificar se o evento existe
                Evento evento = eventoService.buscarEventoPorId(eventoIdLong);
                if (evento == null) {
                    textoMensagem.setText("Evento não encontrado.");
                    return;
                }

                // Criar nova inscrição
                Inscricao novaInscricao = new Inscricao();
                novaInscricao.setUsuario(UserContext.getInstance().getUsuario());
                novaInscricao.setEvento(evento);

                // Chamar o serviço para cadastrar a inscrição
                inscricaoService.adicionarInscricao(novaInscricao);
                textoMensagem.setText("Você se cadastrou com sucesso no evento!");
            } else {
                textoMensagem.setText("Erro: O ID informado não é válido.");
            }
        } catch (Exception e) {
            textoMensagem.setText("Erro ao se cadastrar no evento.");
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