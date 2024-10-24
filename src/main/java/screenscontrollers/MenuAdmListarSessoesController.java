package screenscontrollers;

import java.io.IOException;
import java.util.ArrayList;

import controllers.SecaoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Secao;
import models.SubEvento;

public class MenuAdmListarSessoesController {

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoAdicionarSessao;

    @FXML
    private Button botaoListarSessoes;

    @FXML
    private Button botaoAtualizarSessao;

    @FXML
    private Button botaoDeletarSessao;

    @FXML
    private ListView<String> listViewSessoes;
    @FXML
    private TextArea textAreaSecao;

    @FXML
    public void initialize() {
        // Carregar as sessões e exibi-las
        SecaoController secaoController = new SecaoController();
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

    @FXML
    void voltarParaGerenciarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaAdicionarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_Adicionar_Sessao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoAdicionarSessao.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaAtualizarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_Atualizar_Sessao.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoAtualizarSessao.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaDeletarSessao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_Deletar.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoDeletarSessao.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
