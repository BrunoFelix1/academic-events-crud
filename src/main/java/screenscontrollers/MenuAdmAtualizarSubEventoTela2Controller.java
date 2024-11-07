package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuAdmAtualizarSubEventoTela2Controller extends MenuAdmGerenciarSubEventoController {

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField nomeSubEventoField;

    @FXML
    private TextField localSubEventoField;

    @FXML
    private TextField descricaoSubEventoField;

    @FXML
    private TextField horarioSubEventoField;

    @FXML
    private TextField eventoAssociadoField;


    @FXML
    void salvarAlteracoes() {
        // Implementar lógica para salvar as alterações do subevento
        String nome = nomeSubEventoField.getText();
        String local = localSubEventoField.getText();
        String descricao = descricaoSubEventoField.getText();
        String horario = horarioSubEventoField.getText();
        String eventoAssociado = eventoAssociadoField.getText();

        // Código para atualizar o subevento no sistema

        // Exibir mensagem de sucesso ou limpar os campos
    }
}
