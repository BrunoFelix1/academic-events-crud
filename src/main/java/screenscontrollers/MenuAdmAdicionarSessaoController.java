package screenscontrollers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Secao;
import persistence.PersistenceSecao;

public class MenuAdmAdicionarSessaoController {

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
    private Button botaoSalvarSessao;

    @FXML
    private TextField nomeSessaoField;

    @FXML
    private TextField localSessaoField;

    @FXML
    private TextField horarioSessaoField;

    @FXML
    private TextField eventoRelacionadoField;

    @FXML
    private TextField subEventoRelacionadoField;

    private PersistenceSecao persistence = new PersistenceSecao();

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
    void irParaTelaListarSessoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuADM_ger_Sessao_ListarSessoes.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarSessoes.getScene().getWindow();
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

    @FXML
    void salvarSessao() {
        String nomeSessao = nomeSessaoField.getText();
        String localSessao = localSessaoField.getText();
        String horarioSessao = horarioSessaoField.getText();
        int idEventoRelacionado = Integer.parseInt(eventoRelacionadoField.getText());
        int idSubEventoRelacionado = Integer.parseInt(subEventoRelacionadoField.getText());
        int idSecao = persistence.getTodos().size()+1;

        Secao novaSecao = new Secao(idSecao, idEventoRelacionado, idSubEventoRelacionado, localSessao, horarioSessao, nomeSessao);

        persistence.add(novaSecao);


        nomeSessaoField.clear();
        localSessaoField.clear();
        horarioSessaoField.clear();
        eventoRelacionadoField.clear();
        subEventoRelacionadoField.clear();
    }
}
