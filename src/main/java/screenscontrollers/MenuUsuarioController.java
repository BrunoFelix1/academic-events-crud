package screenscontrollers;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuUsuarioController {

    @FXML
    private Button botaoCancelarInscricao;

    @FXML
    private Button botaoEmitirCertificadoDeTrilha;

    @FXML
    private Button botaoListarEventos;

    @FXML
    private Button botaoListarInscricoes;

    @FXML
    private Button botaoListarSecoes;

    @FXML
    private Button botaoListarSubEventos;

    @FXML
    private Button botaoListarTrilhas;

    @FXML
    private Button botaoParticiparDeEvento;

    @FXML
    private Button botaoParticiparDeTrilha;

    @FXML
    private Button botaoVoltar;

    @FXML
void irParaTelaCancelarInscricao() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_CancelarInscricao.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) botaoCancelarInscricao.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    void irParaTelaEmitirCertificadoDeTrilha() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_EmitirCertificadoDeTrilha.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoEmitirCertificadoDeTrilha.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarEventos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ListarEventos.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarEventos.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarInscricoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ListarInscricoes.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarInscricoes.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarSecoes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ListarSecoes.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarSecoes.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarSubEventos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ListarSubEventos.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarSubEventos.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaListarTrilhas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ListarTrilhas.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoListarTrilhas.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaParticiparDeEvento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ParticiparDeEvento.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoParticiparDeEvento.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaTelaParticiparDeTrilha() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/MenuUser_ParticiparDeTrilha.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoParticiparDeTrilha.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void voltarParaMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/tela_inicial.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
