package screenscontrollers;

import controllers.TrilhaController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.Trilha;

import java.util.ArrayList;

public class MenuAdmListarTrilhasController extends MenuAdmGerenciarTrilhaController {
    @FXML
    private TextArea textAreaTrilhas;

    @FXML
    public void initialize() {
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
}
