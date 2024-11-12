package screenscontrollers;


import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;
import persistence.PersistenceAtividade;

import java.util.List;

public class MenuPalestranteAtualizarAtividadeController implements IControladorTelas {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button Confirmar;

    @FXML
    private TextField idAtividade;

    @FXML
    private TextField tipoSubmissao;

    @FXML
    private TextField atvResumo;

    @FXML
    private TextField idTrilha;

    @FXML
    private TextField atvAutor;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onConfirmar() {
        try {
            String idString = idAtividade.getText();
            int id = Integer.parseInt(idString);
            PersistenceAtividade atividadeP = new PersistenceAtividade();
            Atividade atividadeAntiga = atividadeP.getPorId(id);
            Atividade atividadeNova = new Atividade();
            atividadeNova.setId(id);
            atividadeNova.setTipoSubmissao(tipoSubmissao.getText());
            atividadeNova.setResumo(atvResumo.getText());
            atividadeNova.setIdTrilha(Integer.parseInt(idTrilha.getText()));
            atividadeNova.setAutor(atvAutor.getText());
            atividadeP.update(atividadeAntiga, atividadeNova);
            exibirAlertaSucesso("Atividade atualizada com sucesso!");
        } catch (Exception e) {
            exibirAlerta("Falha ao atualizar a atividade.");
        }
    }
}