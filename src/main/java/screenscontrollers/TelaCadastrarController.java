package screenscontrollers;

import controllers.UsuarioController;
import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;

import java.util.List;

public class TelaCadastrarController implements IControladorTelas {

    private UsuarioController usuarioP = new UsuarioController();

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
    private void onCadastrar() {
        Usuario usuarioCadastrado = new Usuario();
        usuarioCadastrado.setLogin(txtUsuario.getText());
        usuarioCadastrado.setCPF(txtCPF.getText());
        usuarioCadastrado.setSenha(txtSenha.getText());
        usuarioCadastrado.setIdade(Integer.parseInt(txtIdade.getText()));
        usuarioCadastrado.setNome(txtNome.getText());
        usuarioCadastrado.setInstituicao(txtInstituicao.getText());
        usuarioCadastrado.setTipoDeUsuario(txtTipoDeUsuario.getText());

        if (checarUsuario(usuarioCadastrado.getLogin())) {
            exibirAlerta("Esse nome de usuário já existe.");
            return;
        }
        if (checarCPF(usuarioCadastrado.getCPF())) {
            exibirAlerta("Esse CPF já foi cadastrado.");
            return;
        }
        if (!(checarTipoUsuario(usuarioCadastrado.getTipoDeUsuario()))) {
            exibirAlerta("Digite um tipo de usuário disponível.");
            return;
        }

        if(!checarIdade(usuarioCadastrado.getIdade())) {
            exibirAlerta("Digite uma idade válida.");
            return;
        }
        if (usuarioP.cadastrar(usuarioCadastrado)) {
            Stage stage = (Stage) btnCadastrar.getScene().getWindow();
            mostrarTela("/screens/MenuUsuario.fxml", stage);
        } else {
            exibirAlerta("Digite um CPF válido.");
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

    private boolean checarUsuario(String usuario) {
        List<Usuario> lista = usuarioP.listar();
        return lista.stream().anyMatch(user -> user.getLogin().equals(usuario));
    }

    private boolean checarCPF(String cpf) {
        List<Usuario> lista = usuarioP.listar();
        return lista.stream().anyMatch(user -> user.getCPF().equals(cpf));
    }

    private boolean checarIdade(int idade) {
        return idade > 0 && idade <= 100;
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
