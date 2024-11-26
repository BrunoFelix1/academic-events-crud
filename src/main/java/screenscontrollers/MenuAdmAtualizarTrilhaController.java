package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;

public class MenuAdmAtualizarTrilhaController extends MenuAdmGerenciarTrilhaController {

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField idTrilhaField;

    @FXML
    private TextField nomeTrilhaField;

    @FXML
    private TextField localTrilhaField;

    @FXML
    private TextField horarioTrilhaField;

    @FXML
    private TextField sessaoRelacionadaField;

    @FXML
    void salvarAlteracoes() {
        // Implementar lógica para salvar as alterações da trilha
        String idTrilha = idTrilhaField.getText();
        String nomeTrilha = nomeTrilhaField.getText();
        String localTrilha = localTrilhaField.getText();
        String horarioTrilha = horarioTrilhaField.getText();
        String sessaoRelacionada = sessaoRelacionadaField.getText();
        int sessaoRelacionadaInt = Integer.parseInt(sessaoRelacionada);
        int idTrilhaInt = Integer.parseInt(idTrilha);
        // Código para atualizar a trilha no sistema
        Trilha trilha = new Trilha(idTrilhaInt,sessaoRelacionadaInt,nomeTrilha);
        TrilhaController trilhaController = new TrilhaController();
        trilhaController.atualizar(trilha);

        // Exibir mensagem de sucesso ou limpar os campos
    }
}
