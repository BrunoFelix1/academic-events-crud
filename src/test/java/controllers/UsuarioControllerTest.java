package controllers;

import models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.UsuarioDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {
    private UsuarioController usuarioController;
    private UsuarioDAO usuarioDAO;

    @BeforeEach
    void setUp() {
        usuarioDAO = mock(UsuarioDAO.class);
        usuarioController = new UsuarioController();
        usuarioController.usuarioDAO = usuarioDAO;
    }

    @Test
    void adicionarUsuario_comDadosValidos() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste");
        usuario.setInstituicao("Instituicao Teste");
        usuario.setTipoDeUsuario("COMUM");

        when(usuarioDAO.insertUsuario(usuario)).thenReturn(true);

        boolean resultado = usuarioController.adicionarUsuario(usuario);
        assertTrue(resultado);
        verify(usuarioDAO, times(1)).insertUsuario(usuario);
    }

    @Test
    void adicionarUsuario_comDadosInvalidos_lancaExcecao() {
        Usuario usuario = new Usuario();

        Exception exception = assertThrows(RuntimeException.class, () ->
            usuarioController.adicionarUsuario(usuario)
        );
        assertEquals("Login é obrigatório", exception.getMessage());
    }
}