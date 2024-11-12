package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;
import persistence.PersistenceAtividade;

import java.util.List;


public class MenuPalestranteSubmeterAtividadeController implements IControladorTelas {
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private TextField idTrilha;
    @FXML
    private TextField idAtividade;
    @FXML
    private TextField tipoSubmissao;
    @FXML
    private TextField autorAtv;
    @FXML
    private TextField resumoAtv;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onKeyReleased() {
        boolean camposPreenchidos =
                !idTrilha.getText().isEmpty() &&
                        !idAtividade.getText().isEmpty() &&
                        !tipoSubmissao.getText().isEmpty() &&
                        !autorAtv.getText().isEmpty() &&
                        !resumoAtv.getText().isEmpty();
        btnConfirmar.setDisable(!camposPreenchidos);
    }

    @FXML
    private void onConfirmar() {
        try {
            PersistenceAtividade atividadeP = new PersistenceAtividade();
            Atividade novaAtividade = new Atividade();
            novaAtividade.setIdTrilha(Integer.parseInt(idTrilha.getText()));
            novaAtividade.setId(Integer.parseInt(idAtividade.getText()));
            novaAtividade.setTipoSubmissao(tipoSubmissao.getText());
            novaAtividade.setAutor(autorAtv.getText());
            novaAtividade.setResumo(resumoAtv.getText());
            List<Atividade> lista = atividadeP.getTodos();
            novaAtividade.setId(lista.size() +1);
            atividadeP.add(novaAtividade);
            exibirAlertaSucesso("Atividade submetida com sucesso!");
        } catch (Exception e) {
            exibirAlerta("Falha ao submeter a atividade.");
        }
    }
}