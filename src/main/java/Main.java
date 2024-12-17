import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan(basePackages = {"screenscontrollers", "repositories", "models", "services", "context"})
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "models") 

public class Main extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        // Inicializa o contexto Spring
        springContext = SpringApplication.run(Main.class);
        // Permite ao Spring injetar dependências na classe Main
        springContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega a interface gráfica via FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/tela_inicial.fxml"));
        // Configura o controlador do FXML com o contexto Spring
        fxmlLoader.setControllerFactory(springContext::getBean);
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // Fecha o contexto Spring quando a aplicação JavaFX for fechada
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);  // Lança a aplicação JavaFX
    }
}
