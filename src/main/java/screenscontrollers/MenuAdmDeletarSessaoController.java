package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import facade.Facade;
import interfaces.IControladorTelas;

public class MenuAdmDeletarSessaoController extends MenuAdmGerSessaoController implements IControladorTelas {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idSessaoField;

    private Facade facade = new Facade();

    @FXML
    void deletarSessao() {
        try {
            String idSessaoStr = idSessaoField.getText();

            // Validar campo
            if (idSessaoStr.isEmpty()) {
                exibirAlerta("Por favor, insira o ID da sessão.");
                return;
            }

            // Converter string para tipo apropriado
            Long idSessao = Long.parseLong(idSessaoStr);

            // Deletar a sessão
            if (facade.deletarSecao(idSessao)) {
                exibirAlertaSucesso("Sessão deletada com sucesso!");
                idSessaoField.clear();
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Erro na conversão do ID. Certifique-se de que o ID é válido.");
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
