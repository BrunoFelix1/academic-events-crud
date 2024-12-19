package screenscontrollers;

import interfaces.IControladorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;

import facade.Facade;

import java.util.List;

public class TelaCadastrarController implements IControladorTelas {

    private Facade facade = new Facade();

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtCPF;
    @FXML
    private PasswordField txtSenha;
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

            if (facade.adicionarUsuario(usuarioCadastrado)) {
                exibirAlertaSucesso("Usuário cadastrado com sucesso!");
                txtUsuario.clear();
                txtCPF.clear();
                txtSenha.clear();
                txtNome.clear();
                txtIdade.clear();
                txtInstituicao.clear();
                txtTipoDeUsuario.clear();
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

    //FUNÇÃO PARA IR PARA A TELA LOGANDO DIRETO
    @SuppressWarnings("unused")
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
        List<Usuario> lista = facade.listarUsuarios();
        return lista.stream().anyMatch(user -> user.getLogin().equals(usuario));
    }

    private boolean checarCPF(String cpf) {
        List<Usuario> lista = facade.listarUsuarios();
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
