package controllers;

import models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.UsuarioNaoEncontradoException;
import repositories.UsuarioDAO;

import java.util.Arrays;
import java.util.List;

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
    void testAdicionarUsuario_Sucesso() {
        Usuario usuario = new Usuario(1L, "14040160401", "Nome Teste", 25, "Instituicao Teste", "COMUM", "usuarioTeste", "senhaTeste");

        when(usuarioDAO.insertUser(usuario)).thenReturn(true);

        boolean resultado = usuarioController.adicionarUsuario(usuario);
        assertTrue(resultado);
        verify(usuarioDAO).insertUser(usuario);
    }

    @Test
    void testAdicionarUsuario_DadosInvalidos() {
        Usuario usuario = new Usuario();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            usuarioController.adicionarUsuario(usuario)
        );
        assertEquals("CPF é obrigatório", exception.getMessage());
    }

    @Test
    void testAtualizarUsuario_Sucesso() {
        Usuario usuarioExistente = new Usuario(1L, "14040160401", "Nome Teste", 25, "Instituicao Teste", "COMUM", "usuarioTeste", "senhaTeste");

        Usuario usuarioAtualizado = new Usuario(1L, "14040160401", "Nome Atualizado", 26, "Instituicao Atualizada", "PALESTRANTE", "usuarioAtualizado", "senhaAtualizada");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuarioExistente);
        when(usuarioDAO.updateUser(any())).thenReturn(true);

        boolean resultado = usuarioController.atualizarUsuario(1L, usuarioAtualizado);

        assertTrue(resultado);
        verify(usuarioDAO).updateUser(any());
    }

    @Test
    void testAtualizarUsuario_NaoEncontrado() {
        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioController.atualizarUsuario(1L, new Usuario());
        });

        assertEquals("Usuario não encontrado", exception.getMessage());
    }

    @Test
    void testDeletarUsuario_Sucesso() {
        Usuario usuario = new Usuario(1L, "14040160401", "Nome Teste", 25, "Instituicao Teste", "COMUM", "usuarioTeste", "senhaTeste");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuario);
        when(usuarioDAO.deleteUser(1L)).thenReturn(true);

        boolean resultado = usuarioController.deletarUsuario(1L);

        assertTrue(resultado);
        verify(usuarioDAO).deleteUser(1L);
    }

    @Test
    void testDeletarUsuario_NaoEncontrado() {
        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioController.deletarUsuario(1L);
        });

        assertEquals("Usuario não encontrado", exception.getMessage());
    }

    @Test
    void testListarTodosUsuarios() {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario(1L, "14040160401", "Nome Teste 1", 25, "Instituicao Teste 1", "COMUM", "usuarioTeste1", "senhaTeste1"),
                new Usuario(2L, "24040160402", "Nome Teste 2", 26, "Instituicao Teste 2", "PALESTRANTE", "usuarioTeste2", "senhaTeste2")
        );

        when(usuarioDAO.selectAllUsers()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioController.listarTodosUsuarios();

        assertEquals(2, resultado.size());
        verify(usuarioDAO).selectAllUsers();
    }

    @Test
    void testBuscarUsuarioPorId_Sucesso() {
        Usuario usuario = new Usuario(1L, "14040160401", "Nome Teste", 25, "Instituicao Teste", "COMUM", "usuarioTeste", "senhaTeste");

        when(usuarioDAO.selectUser(1L)).thenReturn(usuario);

        Usuario resultado = usuarioController.buscarUsuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(usuarioDAO).selectUser(1L);
    }

    @Test
    void testBuscarUsuarioPorId_NaoEncontrado() {
        when(usuarioDAO.selectUser(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioController.buscarUsuarioPorId(1L);
        });

        assertEquals("Usuario não encontrado", exception.getMessage());
    }

    @Test
    void testAutenticarUsuario_Sucesso() {

        try{
            Usuario usuario = new Usuario(1L, "14040160401", "Nome Teste", 25, "Instituicao Teste", "COMUM", "usuarioTeste", "senhaTeste");
    
            when(usuarioDAO.selectAllUsers()).thenReturn(Arrays.asList(usuario));
    
            Usuario resultado = usuarioController.autenticarUsuario("usuarioTeste", "senhaTeste");
    
            assertNotNull(resultado);
            assertEquals("usuarioTeste", resultado.getLogin());
            verify(usuarioDAO).selectAllUsers();

        }catch(UsuarioNaoEncontradoException e){
            System.out.println(e);
        }
        
        
    }

    @Test
    void testAutenticarUsuario_Falha() {
        when(usuarioDAO.selectAllUsers()).thenReturn(Arrays.asList());

        UsuarioNaoEncontradoException exception = assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioController.autenticarUsuario("usuarioInexistente", "senhaInexistente");
        });

        assertEquals("Usuário ou senha inválidos.", exception.getMessage());
    }
}