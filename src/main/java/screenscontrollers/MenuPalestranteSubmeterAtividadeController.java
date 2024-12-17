package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Atividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.AtividadeService;
import models.Trilha;
import services.TrilhaService;

@Controller
public class MenuPalestranteSubmeterAtividadeController implements IControladorTelas {
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnConfirmar;
    @FXML
    private TextField idTrilha;
    @FXML
    private TextField tipoSubmissao;
    @FXML
    private TextField autorAtv;
    @FXML
    private TextField resumoAtv;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private TrilhaService trilhaService;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onKeyReleased() {
        boolean camposPreenchidos =
                !idTrilha.getText().isEmpty() &&
                        !tipoSubmissao.getText().isEmpty() &&
                        !autorAtv.getText().isEmpty() &&
                        !resumoAtv.getText().isEmpty();
        btnConfirmar.setDisable(!camposPreenchidos);
    }

    @FXML
    private void onConfirmar() {
        try {
            // Validar campos
            if (idTrilha.getText().isEmpty() || tipoSubmissao.getText().isEmpty() ||
                    autorAtv.getText().isEmpty() || resumoAtv.getText().isEmpty()) {
                exibirAlerta("Erro: Por favor, preencha todos os campos.");
                return;
            }

            // Converter string para tipo apropriado
            Long trilhaId = Long.parseLong(idTrilha.getText());
            Trilha trilha = trilhaService.buscarTrilhaPorId(trilhaId);

            // Criar nova atividade
            Atividade novaAtividade = new Atividade();
            novaAtividade.setTrilha(trilha);
            novaAtividade.setTipoDeAtividade(tipoSubmissao.getText());
            novaAtividade.setAutor(autorAtv.getText());
            novaAtividade.setResumo(resumoAtv.getText());

            // Utilizar o AtividadeService para submeter a nova atividade
            atividadeService.adicionarAtividade(novaAtividade);

            // Limpar os campos após submeter
            idTrilha.clear();
            tipoSubmissao.clear();
            autorAtv.clear();
            resumoAtv.clear();

            exibirAlertaSucesso("Atividade submetida com sucesso!");
        } catch (NumberFormatException e) {
            exibirAlerta("Erro: O ID informado não é válido.");
        } catch (Exception e) {
            exibirAlerta("Falha ao submeter a atividade.");
            e.printStackTrace();
        }
    }
}