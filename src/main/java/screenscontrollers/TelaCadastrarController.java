package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.UsuarioService;

import java.util.List;

@Controller
public class TelaCadastrarController implements IControladorTelas {

    @Autowired
    private UsuarioService usuarioService;

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtIdade;
    @FXML
    private TextField txtInstituicao;
    @FXML
    private TextField txtTipoDeUsuario;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnCadastrar;

    @FXML
    private void onVoltar() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        mostrarTela("/screens/tela_inicial.fxml", stage);
    }

    @FXML
    public void onCadastrar() {
        try {
            System.out.println(usuarioService);
            Usuario usuarioCadastrado = new Usuario();
            usuarioCadastrado.setLogin(txtUsuario.getText());
            usuarioCadastrado.setCpf(txtCPF.getText());
            usuarioCadastrado.setSenha(txtSenha.getText());
            usuarioCadastrado.setIdade(Integer.parseInt(txtIdade.getText()));
            usuarioCadastrado.setNome(txtNome.getText());
            usuarioCadastrado.setInstituicao(txtInstituicao.getText());
            usuarioCadastrado.setTipoDeUsuario(txtTipoDeUsuario.getText());

            if (checarUsuario(usuarioCadastrado.getLogin())) {
                exibirAlerta("Esse nome de usuário já existe.");
                return;
            }
            if (checarCPF(usuarioCadastrado.getCpf())) {
                exibirAlerta("Esse CPF já foi cadastrado.");
                return;
            }
            if (!checarTipoUsuario(usuarioCadastrado.getTipoDeUsuario())) {
                exibirAlerta("Digite um tipo de usuário disponível.");
                return;
            }
            if (!checarIdade(usuarioCadastrado.getIdade())) {
                exibirAlerta("Digite uma idade válida.");
                return;
            }

            if (usuarioService.adicionarUsuario(usuarioCadastrado) != null) {
                Stage stage = (Stage) btnCadastrar.getScene().getWindow();
                verificarTela(usuarioCadastrado, stage);
            } else {
                exibirAlerta("Digite um CPF válido.");
            }
        } catch (Exception e) {
            exibirAlerta("Erro ao cadastrar usuário.");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        txtIdade.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtIdade.setText(newValue.replaceAll("[^\\d]", "")); // Remove caracteres não numéricos
            }
        });
    }

    private void verificarTela(Usuario usuario, Stage stage) {
        if (usuario.getTipoDeUsuario().equalsIgnoreCase("COMUM")) {
            mostrarTela("/screens/MenuUsuario.fxml", stage);
        } else if (usuario.getTipoDeUsuario().equalsIgnoreCase("PALESTRANTE")) {
            mostrarTela("/screens/Menu_Palestrante.fxml", stage);
        } else {
            exibirAlerta("Erro inesperado no tipo de usuário.");
        }
    }

    private boolean checarUsuario(String usuario) {
        List<Usuario> lista = usuarioService.listarTodosUsuarios();
        return lista.stream().anyMatch(user -> user.getLogin().equals(usuario));
    }

    private boolean checarCPF(String cpf) {
        List<Usuario> lista = usuarioService.listarTodosUsuarios();
        return lista.stream().anyMatch(user -> user.getCpf().equals(cpf));
    }

    private boolean checarIdade(int idade) {
        return idade > 0 && idade <= 130;
    }

    private boolean checarTipoUsuario(String tipoUsuario) {
        return tipoUsuario.equalsIgnoreCase("COMUM") || tipoUsuario.equalsIgnoreCase("PALESTRANTE");
    }

    @FXML
    public void onKeyReleased() {
        boolean cadastrar;
        cadastrar = txtSenha.getText().isEmpty() ||
                txtNome.getText().isEmpty() ||
                txtIdade.getText().isEmpty() ||
                txtCPF.getText().isEmpty() ||
                txtUsuario.getText().isEmpty() ||
                txtInstituicao.getText().isEmpty() ||
                txtTipoDeUsuario.getText().isEmpty();
        btnCadastrar.setDisable(cadastrar);
    }

}
