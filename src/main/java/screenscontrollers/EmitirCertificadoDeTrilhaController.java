package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Trilha;
import interfaces.IControladorTelas;
import controllers.TrilhaController;

public class EmitirCertificadoDeTrilhaController extends MenuUsuarioController implements IControladorTelas {

    @FXML
    private Button botaoEmitirCertificadoDeTrilha;

    @FXML
    private TextField nomeTrilha;

    private TrilhaController trilhaController = new TrilhaController();

    @FXML
    private void emitirCertificado() {
        try {
            String trilha = nomeTrilha.getText();

            // Verifica se o campo não está vazio
            if (trilha == null || trilha.trim().isEmpty()) {
                exibirAlerta("Por favor, insira o nome da trilha.");
                return;
            }

            // Buscar a trilha pelo nome usando o TrilhaController
            Trilha trilhaEncontrada = trilhaController.buscarTrilhaPorNome(trilha);
            if (trilhaEncontrada != null) {
                exibirAlertaSucesso("Certificado da Trilha '" + trilha + "' está sendo emitido e enviado para você...");
            } else {
                exibirAlerta("Erro! Trilha não encontrada.");
            }
        } catch (Exception e) {
            exibirAlerta("Erro ao emitir certificado!");
            System.out.println(e.getMessage());
        }
    }

}
