package screenscontrollers;

import context.UserContext;
import facade.Facade;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Evento;
import models.Inscricao;

public class ParticiparDeEventoController extends MenuUsuarioController implements IControladorTelas {

    @FXML
    private Button botaoParticiparDeEvento;

    @FXML
    private TextField campoEventoId;

    @FXML
    private Text textoMensagem;

    private Facade facade = new Facade();

    @FXML
    private void participarDeEvento() {
        try {
            // Pega o ID do evento do campo de texto
            String eventoId = campoEventoId.getText();

            // Verifica se o campo não está vazio
            if (eventoId == null || eventoId.trim().isEmpty()) {
                exibirAlerta("Por favor, insira um ID de evento.");
                return;
            }

            if (isInteger(eventoId)) {
                Long eventoIdLong = Long.parseLong(eventoId);

                // Verificar se o evento existe
                Evento evento = facade.buscarEvento(eventoIdLong);
                if (evento == null) {
                    exibirAlerta("Evento não encontrado.");
                    return;
                }

                // Criar nova inscrição
                Inscricao novaInscricao = new Inscricao();
                novaInscricao.setUsuario(UserContext.getInstance().getUsuario());
                novaInscricao.setEvento(evento);

                // Chamar o serviço para cadastrar a inscrição
                if (facade.adicionarInscricao(novaInscricao)) {
                    exibirAlertaSucesso("Você se cadastrou com sucesso no evento!");
                    campoEventoId.clear();
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