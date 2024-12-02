package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.UsuarioController;
import models.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControllerTest {
    //esse é o único que não herda de BaseControllerTest
    private static UsuarioController usuarioController;
    private static List<Usuario> estadoInicial;

    @BeforeEach
    void setUp() {
        usuarioController = new UsuarioController();
        estadoInicial = new ArrayList<>(usuarioController.listar());
    }
    
    @AfterEach
    void tearDown() {
        // Obtém o estado atual do banco de dados após o teste.
        List<Usuario> estadoAtual = new ArrayList<>(usuarioController.listar());
    
        // Identifica e remove usuários que foram adicionados durante os testes.
        for (Usuario usuario : estadoAtual) {
            if (!estadoInicial.contains(usuario)) {
                usuarioController.deletar(usuario);
            }
        }
    }

    @Test
    void addUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setCPF("02173752430");
        usuario.setNome("Usuario Teste");
        usuario.setIdade(25);
        usuario.setInstituicao("Instituição A");
        usuario.setTipoDeUsuario("Aluno");

        boolean usuarioAdicionado = usuarioController.cadastrar(usuario);
        assertEquals(true, usuarioAdicionado);
    }

    @Test
    void listUsuariosTest() {
        Usuario usuario1 = new Usuario();
        usuario1.setCPF("02173752430");
        usuario1.setNome("Usuario Teste1");
        usuario1.setIdade(22);
        usuario1.setInstituicao("Instituição A");
        usuario1.setTipoDeUsuario("Professor");

        Usuario usuario2 = new Usuario();
        usuario2.setCPF("02173752430");
        usuario2.setNome("Usuario Teste2");
        usuario2.setIdade(30);
        usuario2.setInstituicao("Instituição B");
        usuario2.setTipoDeUsuario("Aluno");

        usuarioController.cadastrar(usuario1);
        usuarioController.cadastrar(usuario2);

        List<Usuario> usuarios = usuarioController.listar();
        assertNotNull(usuarios);
        assertTrue(usuarios.size() >= 2);
    }

    @Test
    void updateUsuarioTest() {
        Usuario usuarioAntigo = new Usuario();
        usuarioAntigo.setCPF("02173752430");
        usuarioAntigo.setNome("Usuario Teste1");
        usuarioAntigo.setIdade(22);
        usuarioAntigo.setInstituicao("Instituição A");
        usuarioAntigo.setTipoDeUsuario("Aluno");

        usuarioController.cadastrar(usuarioAntigo);

        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setCPF("02173752430");
        usuarioNovo.setNome("Usuario Teste Atualizado");
        usuarioNovo.setIdade(23);
        usuarioNovo.setInstituicao("Instituição B");
        usuarioNovo.setTipoDeUsuario("Professor");

        Usuario usuarioAtualizado = usuarioController.atualizar(usuarioAntigo, usuarioNovo);

        assertEquals(usuarioNovo, usuarioAtualizado);
    }

    @Test
    void deleteUsuarioTest() {
        boolean resultado;
        Usuario usuario = new Usuario();
        usuario.setCPF("02173752430");
        usuario.setNome("Usuario Teste3");
        usuario.setIdade(27);
        usuario.setInstituicao("Instituição C");
        usuario.setTipoDeUsuario("Aluno");

        usuarioController.cadastrar(usuario);
        resultado = usuarioController.deletar(usuario);

        assertEquals(true, resultado);
    }
}
