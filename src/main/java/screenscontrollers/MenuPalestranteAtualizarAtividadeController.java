package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;
import models.Trilha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.AtividadeService;
import services.TrilhaService;

@Controller
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

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private TrilhaService trilhaService;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onConfirmar() {
        try {
            String idString = idAtividade.getText();

            // Validar campos
            if (idString.isEmpty() || tipoSubmissao.getText().isEmpty() || atvResumo.getText().isEmpty()
                || idTrilha.getText().isEmpty() || atvAutor.getText().isEmpty()) {
                exibirAlerta("Erro: Por favor, preencha todos os campos.");
                return;
            }

            // Converter string para tipo apropriado
            Long id = Long.parseLong(idString);
            Long trilhaId = Long.parseLong(idTrilha.getText());
            Trilha trilha = trilhaService.buscarTrilhaPorId(trilhaId);

            // Buscar a atividade existente
            Atividade atividadeAntiga = atividadeService.buscarAtividadePorId(id);

            // Atualizar os atributos da atividade
            atividadeAntiga.setTipoDeAtividade(tipoSubmissao.getText());
            atividadeAntiga.setResumo(atvResumo.getText());
            atividadeAntiga.setTrilha(trilha);
            atividadeAntiga.setAutor(atvAutor.getText());

            // Utilizar o AtividadeService para atualizar a atividade
            atividadeService.atualizarAtividade(id, atividadeAntiga);

            // Limpar os campos após atualizar
            idAtividade.clear();
            tipoSubmissao.clear();
            atvResumo.clear();
            idTrilha.clear();
            atvAutor.clear();

            exibirAlertaSucesso("Atividade atualizada com sucesso!");
        } catch (NumberFormatException e) {
            exibirAlerta("Erro: O ID informado não é válido.");
        } catch (Exception e) {
            exibirAlerta("Falha ao atualizar a atividade.");
            e.printStackTrace();
        }
    }
}