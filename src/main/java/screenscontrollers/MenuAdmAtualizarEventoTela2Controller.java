package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuAdmAtualizarEventoTela2Controller extends MenuAdmGerEventController{

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoSalvarAlteracoes;

    @FXML
    private TextField tituloEventoField;

    @FXML
    private TextField localEventoField;

    @FXML
    private TextField horarioEventoField;

    @FXML
    private TextField descricaoEventoField;

    private String nomeEvento;

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
        // Carregar os dados atuais do evento e preencher os campos
    }

    @FXML
    void voltarParaTelaAnterior() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_event_AtualizarEvento_tela1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salvarAlteracoes() {
        // Implementar lógica para salvar as alterações do evento
        String tituloAtualizado = tituloEventoField.getText();
        String localAtualizado = localEventoField.getText();
        String horarioAtualizado = horarioEventoField.getText();
        String descricaoAtualizada = descricaoEventoField.getText();

        // Código para atualizar o evento no sistema

        // Exibir mensagem de sucesso ou redirecionar para outra tela
    }
}
