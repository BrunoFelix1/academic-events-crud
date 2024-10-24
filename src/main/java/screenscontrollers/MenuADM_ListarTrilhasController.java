package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Secao;
import models.Trilha;

import java.util.ArrayList;

public class MenuADM_ListarTrilhasController extends BaseTrilhaController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarTrilha;

    @FXML
    private Button botaoListarTrilhas;

    @FXML
    private Button botaoAtualizarTrilha;

    @FXML
    private Button botaoDeletarTrilha;

    @FXML
    private TextArea textAreaTrilhas;

    @FXML
    public void initialize() {
        // Carregar as trilhas e exibi-las no textAreaTrilhas
        // Exemplo:
        // textAreaTrilhas.setText("Trilha 1\nTrilha 2\nTrilha 3");
        TrilhaController trilhaController = new TrilhaController();
        ArrayList<Trilha> trilhas = (ArrayList<Trilha>) trilhaController.listar();

        StringBuilder sb = new StringBuilder();

        for (Trilha trilha : trilhas) {
            sb.append("ID: ").append(trilha.getId()).append("\n")
                    .append("Nome: ").append(trilha.getNome()).append("\n")
                    .append("Sessão relacionada: ").append(trilha.getIdSecao()).append("\n")
                    .append("----------------------------\n");
        }
        textAreaTrilhas.setText(sb.toString());
    }

    @FXML
    void voltarParaGerenciarTrilha() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarTrilha(stage);
    }

    @FXML
    void irParaTelaAdicionarTrilha() {
        Stage stage = (Stage) botaoAdicionarTrilha.getScene().getWindow();
        irParaTelaAdicionarTrilha(stage);
    }

    @FXML
    void irParaTelaAtualizarTrilha() {
        Stage stage = (Stage) botaoAtualizarTrilha.getScene().getWindow();
        irParaTelaAtualizarTrilha(stage);
    }

    @FXML
    void irParaTelaDeletarTrilha() {
        Stage stage = (Stage) botaoDeletarTrilha.getScene().getWindow();
        irParaTelaDeletarTrilha(stage);
    }
}
