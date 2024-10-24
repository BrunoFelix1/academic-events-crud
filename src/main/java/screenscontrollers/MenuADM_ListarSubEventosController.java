package screenscontrollers;

import controllers.SubeventoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.SubEvento;

import java.util.ArrayList;

public class MenuADM_ListarSubEventosController extends BaseSubEventoController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarSubEvento;

    @FXML
    private Button botaoListarSubEventos;

    @FXML
    private Button botaoAtualizarSubEvento;

    @FXML
    private Button botaoDeletarSubEvento;

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


    @FXML
    void voltarParaGerenciarSubEvento() {
        Stage stage = (Stage) botaoVoltar.getScene().getWindow();
        irParaTelaGerenciarSubEvento(stage);
    }

    @FXML
    void irParaTelaAdicionarSubEvento() {
        Stage stage = (Stage) botaoAdicionarSubEvento.getScene().getWindow();
        irParaTelaAdicionarSubEvento(stage);
    }

    @FXML
    void irParaTelaAtualizarSubEvento() {
        Stage stage = (Stage) botaoAtualizarSubEvento.getScene().getWindow();
        irParaTelaAtualizarSubEventoTela1(stage);
    }

    @FXML
    void irParaTelaDeletarSubEvento() {
        Stage stage = (Stage) botaoDeletarSubEvento.getScene().getWindow();
        irParaTelaDeletarSubEvento(stage);
    }
}
