import Exception.UsuarioNaoEncontradoException;
import UI.MenuPrincipal;
//LEMBRE DE TROCAR O PATH DOS ARQUIVOS
public class Main {
    public static void main(String[] args) throws UsuarioNaoEncontradoException {
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciarSistema();
    }
}
