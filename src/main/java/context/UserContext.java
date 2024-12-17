package context;

import models.Usuario;

public class UserContext {
    private static UserContext instance;
    private Usuario usuario;

    // Construtor que cria a instância se ainda não existir
    private UserContext() {}

    public static UserContext getInstance() {
        if (instance == null) {
            instance = new UserContext();  // Cria a instância se não existir
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
