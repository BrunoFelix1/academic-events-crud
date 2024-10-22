package screenscontrollers;

import controllers.UsuarioController;
import exception.UsuarioNaoEncontradoException;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    private PasswordField txtSenha;

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/tela_inicial.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onLogin() {
        if (onBtnLogin()) {
            UserContext.getInstance().setUsuario(usuarioAutenticado);
            if (UserContext.getInstance().getUsuario().getTipoDeUsuario().equals("COMUM")){
                mostrarTela("/screens/MenuUsuario.fxml", (Stage) btnLogin.getScene().getWindow());
            }
            else if (UserContext.getInstance().getUsuario().getTipoDeUsuario().equals("ADMINISTRADOR")){
                mostrarTela("/screens/MenuADM.fxml", (Stage) btnLogin.getScene().getWindow());
            }
            else if (UserContext.getInstance().getUsuario().getTipoDeUsuario().equals("PALESTRANTE")){
                mostrarTela("/screens/Menu_Palestrante.fxml", (Stage) btnLogin.getScene().getWindow());
            }
        } else {
            exibirAlerta("Usuário ou senha inválidos.");
        }
    }

    @FXML
    private void onCadastrar() {
        mostrarTela("/screens/tela_cadastro.fxml", (Stage) btnCadastrar.getScene().getWindow());
    }

    private boolean onBtnLogin() {
        String usuario = txtUsuario.getText();
        String senha = txtSenha.getText();
        UsuarioController usuarioController = new UsuarioController();
        try {
            usuarioAutenticado = usuarioController.AutenticarUsuario(usuario, senha);
            return true;
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return false;
        }
    }
}
