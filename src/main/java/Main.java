import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega a interface gráfica via FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/tela_inicial.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Sistema de Gerenciamento de Eventos"); // Adiciona o título
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Lança a aplicação JavaFX
    }
}
