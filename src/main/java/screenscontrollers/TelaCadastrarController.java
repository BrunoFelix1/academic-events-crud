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
import java.util.List;

public class TelaCadastrarController {

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
        mostrarTela("/screens/tela_inicial.fxml");
    }

    private void mostrarTela(String caminhoTela) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoTela));
            Pane newScene = loader.load();
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            mostrarTela("/screens/MenuUsuario.fxml");
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
        return idade>0 && idade<=100;
    }

    private boolean checarTipoUsuario(String tipoUsuario) {
        return tipoUsuario.equalsIgnoreCase("COMUM") || tipoUsuario.equalsIgnoreCase("PALESTRANTE");
    }


    private void exibirAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
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
