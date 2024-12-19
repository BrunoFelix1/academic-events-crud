package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repositories.UsuarioDAO;
import models.Usuario;

import java.util.List;
import java.util.Arrays;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAOMock;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuarioDAOMock = mock(UsuarioDAO.class);
        usuario = new Usuario(1L, "12345678909", "João", 30, "Instituição X", "COMUM", "joao", "senha123");
    }

    @Test
    public void testInsertUser() {
        when(usuarioDAOMock.insertUser(usuario)).thenReturn(true);

        boolean result = usuarioDAOMock.insertUser(usuario);

        assertTrue(result);
        verify(usuarioDAOMock, times(1)).insertUser(usuario);
    }

    @Test
    public void testSelectUser() {
        when(usuarioDAOMock.selectUser(1L)).thenReturn(usuario);

        Usuario result = usuarioDAOMock.selectUser(1L);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(usuarioDAOMock, times(1)).selectUser(1L);
    }

    @Test
    public void testSelectAllUsers() {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario(1L, "12345678909", "João", 30, "Instituição X", "COMUM", "joao", "senha123"),
                new Usuario(2L, "98765432109", "Maria", 25, "Instituição Y", "ADMINISTRADOR", "maria", "senha456")
        );

        when(usuarioDAOMock.selectAllUsers()).thenReturn(usuarios);

        List<Usuario> result = usuarioDAOMock.selectAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(usuarioDAOMock, times(1)).selectAllUsers();
    }

    @Test
    public void testDeleteUser() {
        when(usuarioDAOMock.deleteUser(1L)).thenReturn(true);

        boolean result = usuarioDAOMock.deleteUser(1L);

        assertTrue(result);
        verify(usuarioDAOMock, times(1)).deleteUser(1L);
    }

    @Test
    public void testUpdateUser() {
        when(usuarioDAOMock.updateUser(usuario)).thenReturn(true);

        boolean result = usuarioDAOMock.updateUser(usuario);

        assertTrue(result);
        verify(usuarioDAOMock, times(1)).updateUser(usuario);
    }
}
