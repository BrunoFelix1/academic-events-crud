package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_AtualizarSubEventoTela2Controller extends BaseSubEventoController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarSubEvento;

    @FXML
    private Button botaoListarSubEventos;

    @FXML
    private Button botaoAtualizarSubEvento;

    @FXML
    private Button botaoDeletarSubEvento;

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
    void voltarParaTelaAnterior() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaAtualizarSubEventoTela1(stage);
    }

    @FXML
    void irParaTelaAdicionarSubEvento() {
        Stage stage = (Stage) botaoAdicionarSubEvento.getScene().getWindow();
        irParaTelaAdicionarSubEvento(stage);
    }

    @FXML
    void irParaTelaListarSubEventos() {
        Stage stage = (Stage) botaoListarSubEventos.getScene().getWindow();
        irParaTelaListarSubEventos(stage);
    }

    @FXML
    void irParaTelaDeletarSubEvento() {
        Stage stage = (Stage) botaoDeletarSubEvento.getScene().getWindow();
        irParaTelaDeletarSubEvento(stage);
    }

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
