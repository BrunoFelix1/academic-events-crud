package app;

import database.MongoDBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/screens/tela_inicial.fxml")); // Ajuste o caminho correto do FXML
        Scene scene = new Scene(root);
        primaryStage.setTitle("Sistema de Cadastro De Eventos");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Teste pra ver se o db tá conectando da forma certa
        MongoDBConnection.IniciarConexao();
    }
}
