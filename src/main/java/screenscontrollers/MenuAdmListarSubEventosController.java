package screenscontrollers;

import controllers.SubeventoController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import models.SubEvento;

import java.util.ArrayList;

public class MenuAdmListarSubEventosController extends MenuAdmGerenciarSubEventoController {

    @FXML
    private TextArea textAreaSubEventos;

    @FXML
    public void initialize() {
        // Instancia o controlador de subeventos
        SubeventoController subeventoController = new SubeventoController();

        // Obtém a lista de subeventos
        ArrayList<SubEvento> subeventos = (ArrayList<SubEvento>) subeventoController.listar();

        // StringBuilder para formatar a exibição dos subeventos
        StringBuilder sb = new StringBuilder();

        // Itera sobre os subeventos e adiciona ao StringBuilder
        for (SubEvento subevento : subeventos) {
            sb.append("ID: ").append(subevento.getId()).append("\n")
                    .append("Nome: ").append(subevento.getTitulo()).append("\n")
                    .append("Local: ").append(subevento.getLocal()).append("\n")
                    .append("Horário: ").append(subevento.getHorario()).append("\n")
                    .append("Descrição: ").append(subevento.getDescricao()).append("\n")
                    .append("----------------------------\n");
        }

        // Define o conteúdo formatado no campo de texto
        textAreaSubEventos.setText(sb.toString());
    }
}
