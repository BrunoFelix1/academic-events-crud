package controllersTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import controllers.UsuarioController;
import exception.UsuarioNaoEncontradoException;
import models.Usuario;
import repositories.UsuarioDAO;

import java.util.Arrays;
import java.util.List;

class UsuarioControllerTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarUsuario() {
        Usuario novoUsuario = new Usuario(1L, "12345678901", "João", 30, "Instituição X", "COMUM", "joao", "senha123");
        when(usuarioDAO.insertUser(novoUsuario)).thenReturn(true);

        boolean resultado = usuarioController.adicionarUsuario(novoUsuario);

        assertTrue(resultado, "O usuário deveria ser adicionado com sucesso.");
        verify(usuarioDAO, times(1)).insertUser(novoUsuario);
    }

    @Test
    void testAtualizarUsuario() {
        Usuario usuarioExistente = new Usuario(1L, "12345678901", "João", 30, "Instituição X", "COMUM", "joao", "senha123");
        Usuario usuarioAtualizado = new Usuario(1L, "12345678901", "João Atualizado", 35, "Instituição Y", "ADMINISTRADOR", "joao", "novaSenha");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuarioExistente);
        when(usuarioDAO.updateUser(usuarioExistente)).thenReturn(true);

        boolean resultado = usuarioController.atualizarUsuario(1L, usuarioAtualizado);

        assertTrue(resultado, "O usuário deveria ser atualizado com sucesso.");
        assertEquals("João Atualizado", usuarioExistente.getNome());
        assertEquals(35, usuarioExistente.getIdade());
        assertEquals("ADMINISTRADOR", usuarioExistente.getTipoDeUsuario());
        verify(usuarioDAO, times(1)).updateUser(usuarioExistente);
    }

    @Test
    void testAtualizarUsuarioNaoEncontrado() {
        Usuario usuarioAtualizado = new Usuario(1L, "12345678901", "João Atualizado", 35, "Instituição Y", "ADMINISTRADOR", "joao", "novaSenha");

        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioController.atualizarUsuario(1L, usuarioAtualizado);
        });

        assertEquals("Usuario não encontrado", exception.getMessage());
        verify(usuarioDAO, never()).updateUser(any());
    }

    @Test
    void testDeletarUsuario() {
        when(usuarioDAO.deleteUser(1L)).thenReturn(true);

        boolean resultado = usuarioController.deletarUsuario(1L);

        assertTrue(resultado, "O usuário deveria ser deletado com sucesso.");
        verify(usuarioDAO, times(1)).deleteUser(1L);
    }

    @Test
    void testDeletarUsuarioNaoEncontrado() {
        when(usuarioDAO.deleteUser(1L)).thenReturn(false);

        boolean resultado = usuarioController.deletarUsuario(1L);

        assertFalse(resultado, "O usuário não deveria ser deletado.");
        verify(usuarioDAO, times(1)).deleteUser(1L);
    }

    @Test
    void testListarTodosUsuarios() {
        Usuario usuario1 = new Usuario(1L, "12345678901", "João", 30, "Instituição X", "COMUM", "joao", "senha123");
        Usuario usuario2 = new Usuario(2L, "98765432109", "Maria", 25, "Instituição Y", "ADMINISTRADOR", "maria", "senha456");

        List<Usuario> usuariosEsperados = Arrays.asList(usuario1, usuario2);
        when(usuarioDAO.selectAllUsers()).thenReturn(usuariosEsperados);

        List<Usuario> usuarios = usuarioController.listarTodosUsuarios();

        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        assertEquals("João", usuarios.get(0).getNome());
        assertEquals("Maria", usuarios.get(1).getNome());
        verify(usuarioDAO, times(1)).selectAllUsers();
    }

    @Test
    void testBuscarUsuarioPorId() {
        Usuario usuario = new Usuario(1L, "12345678901", "João", 30, "Instituição X", "COMUM", "joao", "senha123");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuario);

        Usuario usuarioBuscado = usuarioController.buscarUsuarioPorId(1L);

        assertNotNull(usuarioBuscado);
        assertEquals("João", usuarioBuscado.getNome());
        verify(usuarioDAO, times(1)).selectUser(1L);
    }

    @Test
    void testBuscarUsuarioPorIdNaoEncontrado() {
        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioController.buscarUsuarioPorId(1L);
        });

        assertEquals("Usuario não encontrado", exception.getMessage());
        verify(usuarioDAO, times(1)).selectUser(1L);
    }

    @Test
    void testAutenticarUsuario() throws UsuarioNaoEncontradoException {
        Usuario usuario1 = new Usuario(1L, "12345678901", "João", 30, "Instituição X", "COMUM", "joao", "senha123");
        Usuario usuario2 = new Usuario(2L, "98765432109", "Maria", 25, "Instituição Y", "ADMINISTRADOR", "maria", "senha456");

        when(usuarioDAO.selectAllUsers()).thenReturn(Arrays.asList(usuario1, usuario2));

        Usuario usuarioAutenticado = usuarioController.autenticarUsuario("joao", "senha123");

        assertNotNull(usuarioAutenticado);
        assertEquals("João", usuarioAutenticado.getNome());
        verify(usuarioDAO, times(1)).selectAllUsers();
    }

    @Test
    void testAutenticarUsuarioFalha() {
        Usuario usuario1 = new Usuario(1L, "12345678901", "João", 30, "Instituição X", "COMUM", "joao", "senha123");
        Usuario usuario2 = new Usuario(2L, "98765432109", "Maria", 25, "Instituição Y", "ADMINISTRADOR", "maria", "senha456");

        when(usuarioDAO.selectAllUsers()).thenReturn(Arrays.asList(usuario1, usuario2));

        Exception exception = assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioController.autenticarUsuario("joao", "senhaErrada");
        });

        assertEquals("Usuário ou senha inválidos.", exception.getMessage());
        verify(usuarioDAO, times(1)).selectAllUsers();
    }
}
