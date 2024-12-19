package screenscontrollers;

import exception.UsuarioNaoEncontradoException;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;
import context.UserContext;
import facade.Facade;

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

    private Facade facade = new Facade();

    @FXML
    private void onVoltar() {
        mostrarTela("/screens/tela_inicial.fxml", (Stage) btnVoltar.getScene().getWindow());
    }

    @FXML
    private void onLogin() {
        try {
            String email = txtUsuario.getText();
            String senha = txtSenha.getText();

            // Validar campos
            if (email.isEmpty() || senha.isEmpty()) {
                exibirAlerta("Por favor, preencha todos os campos.");
                return;
            }

            // Autenticar usuário
            Usuario usuario = facade.autenticarUsuario(email, senha);
            if (usuario != null) {
                UserContext.getInstance().setUsuario(usuario);
                exibirAlertaSucesso("Login realizado com sucesso!");
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
                exibirAlerta("Email ou senha inválidos.");
            }
        } catch (UsuarioNaoEncontradoException e) {
            exibirAlerta("Usuário não encontrado. Verifique suas credenciais.");
        } catch (RuntimeException e) {
            exibirAlerta(e.getMessage());
        }
    }

    @FXML
    private void onCadastrar() {
        mostrarTela("/screens/tela_cadastro.fxml", (Stage) btnCadastrar.getScene().getWindow());
    }
}
