package screenscontrollers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Secao;

import controllers.SecaoController;

public class MenuAdmListarSessoesController extends MenuAdmGerSessaoController {

    @FXML
    private ListView<String> listViewSessoes;
    @FXML
    private TextArea textAreaSecao;

    private SecaoController secaoController = new SecaoController();

    @FXML
    public void initialize() {
        try {
            // Utilizar o SecaoController para obter as sessões
            List<Secao> secoes = secaoController.listarTodasSecoes();
            StringBuilder sb = new StringBuilder();

            // Iterar sobre as sessões e adicionar ao StringBuilder
            for (Secao secao : secoes) {
                sb.append("ID: ").append(secao.getId()).append("\n")
                  .append("Nome: ").append(secao.getNome()).append("\n")
                  .append("Local: ").append(secao.getLocal()).append("\n")
                  .append("Horário: ").append(secao.getHorario()).append("\n")
                  .append("ID do SubEvento que se relaciona: ").append(secao.getSubEvento().getId()).append("\n")
                  .append("ID do Evento que se relaciona: ").append(secao.getEvento().getId()).append("\n")
                  .append("----------------------------\n");
            }

            // Define o conteúdo formatado no campo de texto
            textAreaSecao.setText(sb.toString());
        } catch (Exception e) {
            // ...tratamento de erro...
            System.out.println(e.getMessage());
        }
    }
}
