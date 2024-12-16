package context;

import org.springframework.stereotype.Component;
import models.Usuario;

@Component
public class UserContext {
    private static UserContext instance;
    private Usuario usuario;

    public UserContext() {
        instance = this;
    }

    public static UserContext getInstance() {
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
