package screenscontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.Trilha;

import controllers.TrilhaController;

import java.util.List;

public class MenuAdmListarTrilhasController extends MenuAdmGerenciarTrilhaController {
    @FXML
    private TextArea textAreaTrilhas;

    private TrilhaController trilhaController = new TrilhaController();

    @FXML
    public void initialize() {
        try {
            // Utilizar o TrilhaController para obter as trilhas
            List<Trilha> trilhas = trilhaController.listarTodasTrilhas();
            StringBuilder sb = new StringBuilder();

            // Iterar sobre as trilhas e adicionar ao StringBuilder
            for (Trilha trilha : trilhas) {
                sb.append("ID: ").append(trilha.getId()).append("\n")
                  .append("Nome: ").append(trilha.getNome()).append("\n")
                  .append("Sessão relacionada: ").append(trilha.getSecao().getId()).append("\n")
                  .append("----------------------------\n");
            }

            textAreaTrilhas.setText(sb.toString());
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }
}
