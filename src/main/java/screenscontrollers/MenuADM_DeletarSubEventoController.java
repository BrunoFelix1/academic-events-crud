package screenscontrollers;

import controllers.SubeventoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuADM_DeletarSubEventoController extends BaseSubEventoController {

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
    private Button botaoDeletar;

    @FXML
    private TextField nomeSubEventoField;

    @FXML
    void voltarParaGerenciarSubEvento() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarSubEvento(stage);
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
    void irParaTelaAtualizarSubEvento() {
        Stage stage = (Stage) botaoAtualizarSubEvento.getScene().getWindow();
        irParaTelaAtualizarSubEventoTela1(stage);
    }

    @FXML
    void deletarSubEvento() {
        // Obtendo o valor do campo de texto
        String nomeSubEventoStr = nomeSubEventoField.getText();

        // Verificar se o campo de texto contém um valor numérico válido
        int idSubEvento;
        try {
            // Converter o valor do campo de texto em inteiro
            idSubEvento = Integer.parseInt(nomeSubEventoStr);
        } catch (NumberFormatException e) {
            // Exibir mensagem de erro caso o valor não seja um número válido
            System.out.println("O campo 'nome do subevento' deve conter um número válido.");
            return;
        }

        // Implementar lógica para deletar o subevento usando o ID
        SubeventoController subeventoController = new SubeventoController();
        subeventoController.deletar(idSubEvento);

        // Limpar o campo de texto após a operação
        nomeSubEventoField.clear();

        // Exibir mensagem de sucesso ou feedback
        System.out.println("Subevento deletado com sucesso!");
    }

}
