package screenscontrollers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuAdmAtualizarEventoTela2Controller extends MenuAdmGerEventController{

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
