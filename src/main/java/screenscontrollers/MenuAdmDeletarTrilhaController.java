package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import facade.Facade;
import interfaces.IControladorTelas;

public class MenuAdmDeletarTrilhaController extends MenuAdmGerenciarTrilhaController implements IControladorTelas {
    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idTrilhaField;

    private Facade facade = new Facade();

    @FXML
    void deletarTrilha() {
        try {
            String idTrilhaStr = idTrilhaField.getText();

            // Validar campo
            if (idTrilhaStr.isEmpty()) {
                exibirAlerta("Por favor, insira o ID da trilha.");
                return;
            }

            // Converter string para tipo apropriado
            Long idTrilha = Long.parseLong(idTrilhaStr);

            // Deletar a trilha
            if (facade.deletarTrilha(idTrilha)) {
                exibirAlertaSucesso("Trilha deletada com sucesso!");
                idTrilhaField.clear();
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Erro na conversão do ID. Certifique-se de que o ID é válido.");
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }
}
