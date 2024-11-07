package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuAdmDeletarTrilhaController extends MenuAdmGerenciarTrilhaController {
    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idTrilhaField;

    @FXML
    void deletarTrilha() {
        // Implementar lógica para deletar a trilha
        String idTrilha = idTrilhaField.getText();
        int idTrilhaInt = Integer.parseInt(idTrilha);
        // Código para deletar a trilha do sistema
        TrilhaController trilhaController= new TrilhaController();
        trilhaController.deletar(idTrilhaInt);
        // Exibir mensagem de sucesso ou limpar o campo
        idTrilhaField.clear();
    }
}
