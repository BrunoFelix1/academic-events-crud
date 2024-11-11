package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuAdmAtualizarSessaoController extends MenuAdmGerSessaoController {

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField idSessaoField;

    @FXML
    private TextField nomeSessaoField;

    @FXML
    private TextField localSessaoField;

    @FXML
    private TextField horarioSessaoField;

    @FXML
    private TextField eventoRelacionadoField;

    @FXML
    private TextField subEventoRelacionadoField;

    @FXML
    void salvarAlteracoes() {
        // Implementar lógica para salvar as alterações da sessão
        String idSessao = idSessaoField.getText();
        String nomeSessao = nomeSessaoField.getText();
        String localSessao = localSessaoField.getText();
        String horarioSessao = horarioSessaoField.getText();
        String eventoRelacionado = eventoRelacionadoField.getText();
        String subEventoRelacionado = subEventoRelacionadoField.getText();

        // Código para atualizar a sessão no sistema

        // Exibir mensagem de sucesso ou limpar os campos
    }
}
