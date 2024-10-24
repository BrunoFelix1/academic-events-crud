package context;

import models.Usuario;
//Classe para compartilar o contexto do usuário logado entre os controladores das telas
public class UserContext {
    private static UserContext instance;
    private Usuario usuario;

    private UserContext() {
        
    }

    public static UserContext getInstance() {
        if (instance == null) {
            instance = new UserContext();
        }
        return instance;
    }

    public static void setInstance(boolean cancelarUsuario) {
        if (cancelarUsuario){
            instance = null;
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
