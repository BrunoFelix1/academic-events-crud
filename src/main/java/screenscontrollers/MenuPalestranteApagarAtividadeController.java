package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.AtividadeService;

@Controller
public class MenuPalestranteApagarAtividadeController implements IControladorTelas {
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private TextField idTXT;

    @Autowired
    private AtividadeService atividadeService;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onConfirmar() {
        try {
            String idSessao = idTXT.getText();

            // Validar campo
            if (idSessao.isEmpty()) {
                exibirAlerta("Erro: Por favor, insira o ID da atividade.");
                return;
            }

            // Converter string para tipo apropriado
            Long id = Long.parseLong(idSessao);

            // Deletar a atividade
            atividadeService.deletarAtividade(id);
            exibirAlertaSucesso("Atividade deletada com sucesso.");

            // Limpar o campo após deletar
            idTXT.clear();
        } catch (NumberFormatException e) {
            exibirAlerta("Erro: O ID informado não é válido.");
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlerta("Erro ao excluir atividade.");
        }
    }
}
