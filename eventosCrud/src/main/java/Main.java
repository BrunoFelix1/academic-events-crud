import Exception.UsuarioNaoEncontradoException;
import UI.MenuPrincipal;

public class Main {
    public static void main(String[] args) throws UsuarioNaoEncontradoException {
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciarSistema();
    }
}
