package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_AdicionarSubEventoController extends BaseSubEventoController {

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
    private Button botaoSalvarSubEvento;

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
    void voltarParaGerenciarSubEvento() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarSubEvento(stage);
    }

    @FXML
    void irParaTelaListarSubEventos() {
        Stage stage = (Stage) botaoListarSubEventos.getScene().getWindow();
        irParaTelaListarSubEventos(stage);
    }

    @FXML
    void irParaTelaAtualizarSubEvento() {
        Stage stage = (Stage) botaoAtualizarSubEvento.getScene().getWindow();
        irParaTelaAtualizarSubEventoTela1(stage);
    }

    @FXML
    void irParaTelaDeletarSubEvento() {
        Stage stage = (Stage) botaoDeletarSubEvento.getScene().getWindow();
        irParaTelaDeletarSubEvento(stage);
    }

    @FXML
    void salvarSubEvento() {
        // Implementar lógica para salvar o subevento
        String nome = nomeSubEventoField.getText();
        String local = localSubEventoField.getText();
        String descricao = descricaoSubEventoField.getText();
        String horario = horarioSubEventoField.getText();
        String eventoAssociado = eventoAssociadoField.getText();

        // Código para salvar o subevento no sistema

        // Exibir mensagem de sucesso ou limpar os campos
        nomeSubEventoField.clear();
        localSubEventoField.clear();
        descricaoSubEventoField.clear();
        horarioSubEventoField.clear();
        eventoAssociadoField.clear();
    }
}
