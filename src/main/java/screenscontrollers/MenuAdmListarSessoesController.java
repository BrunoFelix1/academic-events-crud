package screenscontrollers;

import java.util.ArrayList;

import controllers.SecaoController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Secao;

public class MenuAdmListarSessoesController extends MenuAdmGerSessaoController {
    private ControllersFactory controllerFactory = new DefaultControllersFactory();


    @FXML
    private ListView<String> listViewSessoes;
    @FXML
    private TextArea textAreaSecao;

    @FXML
    public void initialize() {
        // Carregar as sessões e exibi-las
        SecaoController secaoController = controllerFactory.createSecaoController();
        ArrayList<Secao> secoes = (ArrayList<Secao>) secaoController.listar();

        StringBuilder sb = new StringBuilder();

        for (Secao secao : secoes) {
            sb.append("ID: ").append(secao.getId()).append("\n")
                    .append("Nome: ").append(secao.getNome()).append("\n")
                    .append("Local: ").append(secao.getLocal()).append("\n")
                    .append("Horário: ").append(secao.getHorario()).append("\n")
                    .append("ID do SubEvento que se relaciona: ").append(secao.getId_subEvento()).append("\n")
                    .append("ID do Evento que se relaciona: ").append(secao.getId_evento()).append("\n")
                    .append("----------------------------\n");
        }

        // Define o conteúdo formatado no campo de texto
        textAreaSecao.setText(sb.toString());

    }
}
