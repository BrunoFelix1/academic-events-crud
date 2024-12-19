package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import models.Usuario;
import repositories.UsuarioDAO;
import exception.UsuarioNaoEncontradoException;
import java.util.Arrays;
import java.util.List;

public class UsuarioControllerTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        usuarioController = new UsuarioController();
        usuarioController.usuarioDAO = usuarioDAO; // Injeta o mock do DAO
    }

    @Test
    public void testAdicionarUsuario() {
        Usuario usuario = new Usuario(1L, "12345678901", "João", 25, "Instituição X", "COMUM", "joao", "senha123");

        when(usuarioDAO.insertUser(usuario)).thenReturn(true);

        boolean resultado = usuarioController.adicionarUsuario(usuario);

        assertTrue(resultado);
        verify(usuarioDAO, times(1)).insertUser(usuario); // Verifica se o método foi chamado
    }

    @Test
    public void testAtualizarUsuario() {
        Usuario usuarioExistente = new Usuario(1L, "12345678901", "João", 25, "Instituição X", "COMUM", "joao", "senha123");
        Usuario usuarioAtualizado = new Usuario(1L, "12345678901", "João Silva", 26, "Instituição Y", "PALESTRANTE", "joao_silva", "senha1234");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuarioExistente);
        when(usuarioDAO.updateUser(usuarioAtualizado)).thenReturn(true);

        boolean resultado = usuarioController.atualizarUsuario(1L, usuarioAtualizado);

        assertTrue(resultado);
        verify(usuarioDAO, times(1)).selectUser(1L); // Verifica se o usuário foi buscado
        verify(usuarioDAO, times(1)).updateUser(usuarioAtualizado); // Verifica se o método de atualização foi chamado
    }

    @Test
    public void testAtualizarUsuario_UsuarioNaoEncontrado() {
        Usuario usuarioAtualizado = new Usuario(1L, "12345678901", "João Silva", 26, "Instituição Y", "PALESTRANTE", "joao_silva", "senha1234");

        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> usuarioController.atualizarUsuario(1L, usuarioAtualizado));
    }

    @Test
    public void testDeletarUsuario() {
        when(usuarioDAO.deleteUser(1L)).thenReturn(true);

        boolean resultado = usuarioController.deletarUsuario(1L);

        assertTrue(resultado);
        verify(usuarioDAO, times(1)).deleteUser(1L); // Verifica se o método foi chamado
    }

    @Test
    public void testListarTodosUsuarios() {
        Usuario usuario1 = new Usuario(1L, "12345678901", "João", 25, "Instituição X", "COMUM", "joao", "senha123");
        Usuario usuario2 = new Usuario(2L, "23456789012", "Maria", 30, "Instituição Y", "ADMINISTRADOR", "maria", "senha1234");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioDAO.selectAllUsers()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioController.listarTodosUsuarios();

        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        verify(usuarioDAO, times(1)).selectAllUsers(); // Verifica se o método foi chamado
    }

    @Test
    public void testBuscarUsuarioPorId() {
        Usuario usuario = new Usuario(1L, "12345678901", "João", 25, "Instituição X", "COMUM", "joao", "senha123");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuario);

        Usuario resultado = usuarioController.buscarUsuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(usuarioDAO, times(1)).selectUser(1L); // Verifica se o método foi chamado
    }

    @Test
    public void testBuscarUsuarioPorId_UsuarioNaoEncontrado() {
        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> usuarioController.buscarUsuarioPorId(1L));
    }

    @Test
    public void testAutenticarUsuario_Sucesso() throws UsuarioNaoEncontradoException {
        Usuario usuario = new Usuario(1L, "12345678901", "João", 25, "Instituição X", "COMUM", "joao", "senha123");

        when(usuarioDAO.selectAllUsers()).thenReturn(Arrays.asList(usuario));

        Usuario resultado = usuarioController.autenticarUsuario("joao", "senha123");

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(usuarioDAO, times(1)).selectAllUsers(); // Verifica se o método foi chamado
    }

    @Test
    public void testAutenticarUsuario_UsuarioNaoEncontrado() {
        when(usuarioDAO.selectAllUsers()).thenReturn(Arrays.asList());

        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioController.autenticarUsuario("joao", "senha123"));
    }
}
