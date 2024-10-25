package screenscontrollers;

import controllers.AtividadeController;
import controllers.EventoController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Atividade;
import models.Evento;

import java.util.ArrayList;

public class MenuPalestranteListarAtividadesController implements IControladorTelas {
    @FXML
    private TextArea box;
    @FXML
    private Button btnVoltar;

    private void trocarTela(String caminho, Button botao) {
        Stage stage = (Stage) botao.getScene().getWindow();
        mostrarTela(caminho, stage);
    }

    @FXML
    private void onVoltar() {
        trocarTela("/screens/Menu_Palestrante.fxml", btnVoltar);
    }


    @FXML
    public void on() {
        // Carregar os eventos e exibi-los no textAreaEventos
        // Exemplo:
        // textAreaEventos.setText("Evento 1\nEvento 2\nEvento 3");
        AtividadeController atividadec = new AtividadeController();
        ArrayList<Atividade> atividades = (ArrayList<Atividade>)atividadec.listar();
        StringBuilder sb = new StringBuilder();

        // Itera sobre os subeventos e adiciona ao StringBuilder
        for (Atividade atividade : atividades) {
            sb.append("ID: ").append(atividade.getId()).append("\n")
                    .append("Nome: ").append(atividade.getResumo()).append("\n")
                    .append("Local: ").append(atividade.getAutor()).append("\n")
                    .append("Horário: ").append(atividade.getTipoSubmissao()).append("\n")
                    .append("Descrição: ").append(atividade.getIdTrilha()).append("\n")
                    .append("Descrição: ").append(atividade.getClass()).append("\n")

                    .append("----------------------------\n");
        }


        box.setText(sb.toString());
    }
}