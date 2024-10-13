package app;

import exception.UsuarioNaoEncontradoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MenuPrincipal;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screens/tela_inicial.fxml")); // Lembre-se de ajustar o caminho correto do seu FXML
        Scene scene = new Scene(root);
        primaryStage.setTitle("Minha Aplicação JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
        // MenuPrincipal menu = new MenuPrincipal();
        // try{
        //     menu.iniciarSistema();
        // } catch (UsuarioNaoEncontradoException e) {}
    }
}
