package app;

import exception.UsuarioNaoEncontradoException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.MenuPrincipal;
//LEMBRE DE TROCAR O PATH DOS ARQUIVOS
public class Main extends Application {
    public static void main(String[] args) throws UsuarioNaoEncontradoException {
        launch(args);
        
        //MenuPrincipal menu = new MenuPrincipal();
        //menu.iniciarSistema();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Academia dos Devs");
        Button botao = new Button("Clique aqui");
        botao.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Clicou");
                throw new UnsupportedOperationException("Unimplemented method 'handle'");
            }
            
        });
        StackPane root = new StackPane();
        root.getChildren().addAll(botao);
        primaryStage.setScene(new Scene(root,300,250));
        primaryStage.show();
    }
}
