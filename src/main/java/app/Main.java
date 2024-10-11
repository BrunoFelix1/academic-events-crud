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
public class Main {
    public static void main(String[] args) throws UsuarioNaoEncontradoException {
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciarSistema();
    }
}
