package screenscontrollers;

import controllers.UsuarioController;
import exception.UsuarioNaoEncontradoException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Usuario;

import java.io.IOException;

import context.UserContext;

public class TelaLoginController {
    public Usuario usuarioAutenticado;

    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtSenha;

    @FXML
    private void onVoltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/tela_inicial.fxml"));
            Pane newScene = loader.load();
            Stage stage = (Stage) btnVoltar.getScene().getWindow(); // Alterado para btnVoltar
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogin() {
        if (onBtnLogin()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUsuario.fxml"));
                Pane newScene = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(new Scene(newScene));

                // Armazenando o usuário autenticado na classe de contexto
                UserContext.getInstance().setUsuario(usuarioAutenticado);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha inválidos.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onCadastrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/tela_cadastro.fxml"));
            Pane newScene = loader.load();
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean onBtnLogin() {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();
        UsuarioController usuarioController = new UsuarioController();
        try {
            usuarioAutenticado = usuarioController.AutenticarUsuario(senha, usuario); // Tá trocado aqui TODO DESTROCAR BOTÕES
            return true;
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return false;
        }
    }
}
