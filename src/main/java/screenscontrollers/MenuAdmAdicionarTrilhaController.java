package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;

public class MenuAdmAdicionarTrilhaController extends MenuAdmGerenciarTrilhaController {
    private ControllersFactory controllerFactory = new DefaultControllersFactory();

    @FXML
    private Button botaoSalvarTrilha;

    @FXML
    private TextField nomeTrilhaField;

    @FXML
    private TextField localTrilhaField;

    @FXML
    private TextField horarioTrilhaField;

    @FXML
    private TextField sessaoRelacionadaField;

    @FXML
    void salvarTrilha() {
        // Implementar lógica para salvar a nova trilha
        TrilhaController trilhaController = controllerFactory.createTrilhaController();
        String nomeTrilha = nomeTrilhaField.getText();
        String sessaoRelacionada = sessaoRelacionadaField.getText();
        int sessaoRelacionadaInt = Integer.parseInt(sessaoRelacionada);
        // Código para salvar a trilha no sistema
        trilhaController.cadastrar(new Trilha(1,sessaoRelacionadaInt, nomeTrilha));

        // Exibir mensagem de sucesso ou limpar os campos
        nomeTrilhaField.clear();
        localTrilhaField.clear();
        horarioTrilhaField.clear();
        sessaoRelacionadaField.clear();
    }
}