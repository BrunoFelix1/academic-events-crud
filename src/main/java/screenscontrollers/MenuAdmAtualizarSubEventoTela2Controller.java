/* package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.SubEvento;

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

    private SubeventoController subeventoController = new SubeventoController();

    @FXML
    void initialize() {
        botaoSalvarAlteracoes.setOnAction(event -> salvarAlteracoes());
    }

    @FXML
    void salvarAlteracoes() {
        try {
            String nome = nomeSubEventoField.getText();
            String local = localSubEventoField.getText();
            String descricao = descricaoSubEventoField.getText();
            String horario = horarioSubEventoField.getText();
            int eventoAssociado = Integer.parseInt(eventoAssociadoField.getText());

            SubEvento subEventoExistente = subeventoController.buscarPorId(eventoAssociado);

            if (subEventoExistente != null) {
                subEventoExistente.setTitulo(nome);
                subEventoExistente.setLocal(local);
                subEventoExistente.setDescricao(descricao);
                subEventoExistente.setHorario(horario);

                subeventoController.atualizar(subEventoExistente, subEventoExistente);

                showAlert("Sucesso", "SubEvento atualizado com sucesso!", Alert.AlertType.INFORMATION);
                limparCampos();
            } else {
                showAlert("Erro", "SubEvento não encontrado!", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Erro", "ID do evento associado deve ser um número válido!", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao atualizar SubEvento: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limparCampos() {
        nomeSubEventoField.clear();
        localSubEventoField.clear();
        descricaoSubEventoField.clear();
        horarioSubEventoField.clear();
        eventoAssociadoField.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
 */