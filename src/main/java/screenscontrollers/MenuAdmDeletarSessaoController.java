package screenscontrollers;



import controllers.SecaoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuAdmDeletarSessaoController extends MenuAdmGerSessaoController {
    private ControllersFactory controllerFactory = new DefaultControllersFactory();

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField idSessaoField;

    @FXML
    void deletarSessao() {
        // Implementar lógica para deletar a sessão
        String idSessao = idSessaoField.getText();

        // Código para deletar a sessão do sistema
        SecaoController secaoController = controllerFactory.createSecaoController();
        int id = Integer.parseInt(idSessao);
        secaoController.deletar(id);
        // Exibir mensagem de sucesso ou limpar o campo
        idSessaoField.clear();
    }
}
