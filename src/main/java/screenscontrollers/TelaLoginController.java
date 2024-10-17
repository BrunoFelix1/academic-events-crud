package screenscontrollers;

import controllers.UsuarioController;
import exception.UsuarioNaoEncontradoException;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;

import context.UserContext;

public class TelaLoginController implements IControladorTelas {
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
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        mostrarTela("/screens/tela_inicial.fxml", stage);
    }

    @FXML
    private void onLogin() {
        if (onBtnLogin()) {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            mostrarTela("/screens/MenuUsuario.fxml", stage);

            // Armazenando o usuário autenticado na classe de contexto
            UserContext.getInstance().setUsuario(usuarioAutenticado);
        } else {
            exibirAlerta("Usuário ou senha inválidos.");
        }
    }

    @FXML
    private void onCadastrar() {
        Stage stage = (Stage) btnCadastrar.getScene().getWindow();
        mostrarTela("/screens/tela_cadastro.fxml", stage);
    }

    private boolean onBtnLogin() {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();
        UsuarioController usuarioController = new UsuarioController();
        try {
            usuarioAutenticado = usuarioController.AutenticarUsuario(senha, usuario);
            return true;
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return false;
        }
    }
}
