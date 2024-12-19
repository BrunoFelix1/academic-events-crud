package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import facade.Facade;
import interfaces.IControladorTelas;

public class MenuAdmDeletarSubEventoController extends MenuAdmGerenciarSubEventoController implements IControladorTelas {

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idSubEventoField;

    private Facade facade = new Facade();

    @FXML
    void deletarSubEvento() {
        try {
            String idSubEventoStr = idSubEventoField.getText();

            // Validar campo
            if (idSubEventoStr.isEmpty()) {
                exibirAlerta("Por favor, insira o ID do subevento.");
                return;
            }

            // Converter string para tipo apropriado
            Long idSubEvento = Long.parseLong(idSubEventoStr);

            // Deletar o subevento
            if (facade.deletarSubEvento(idSubEvento)) {
                exibirAlertaSucesso("Subevento deletado com sucesso!");
                idSubEventoField.clear();
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Erro na conversão do ID. Certifique-se de que o ID é válido.");
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }

}
