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

    private static UsuarioController usuarioController;
    private static List<Usuario> estadoInicial;

    @BeforeEach
    void setUp() {
        usuarioController = new UsuarioController();
        // Salva o estado inicial completo do "banco" (arquivo CSV).
        estadoInicial = new ArrayList<>(usuarioController.listar());
    }

    @AfterEach
    void tearDown() {
        // Restaura o estado inicial do "banco" (arquivo CSV).
        List<Usuario> estadoAtual = new ArrayList<>(usuarioController.listar());

        // Remove usuários adicionados durante os testes.
        for (Usuario usuario : estadoAtual) {
            if (!estadoInicial.contains(usuario)) {
                usuarioController.deletar(usuario);
            }
        }

        // Adiciona usuários que foram removidos durante os testes.
        for (Usuario usuario : estadoInicial) {
            if (!estadoAtual.contains(usuario)) {
                usuarioController.cadastrar(usuario);
            }
        }
    }

    @Test
    void addUsuarioTest() {
        Usuario usuario = new Usuario(999, "74351067047", "Nome Teste", 25, "Instituição X", "Aluno", "loginTeste", "senhaTeste");
        int numeroUsuarios = usuarioController.listar().size();
        boolean usuarioAdicionado = usuarioController.cadastrar(usuario);

        assertEquals(numeroUsuarios + 1, usuarioController.listar().size(), "Deveria haver 1 usuário a mais na lista.");
        assertEquals(true, usuarioAdicionado, "O usuário adicionado deveria ser igual ao usuário cadastrado.");
    }

    @Test
    void listUsuariosTest() {
        Usuario usuario1 = new Usuario(999, "74351067047", "Nome 1", 30, "Instituição Y", "Professor", "login1", "senha1");
        Usuario usuario2 = new Usuario(1000, "41543030076", "Nome 2", 40, "Instituição Z", "Admin", "login2", "senha2");

        usuarioController.cadastrar(usuario1);
        usuarioController.cadastrar(usuario2);

        List<Usuario> usuarios = usuarioController.listar();
        assertEquals(estadoInicial.size() + 2, usuarios.size(), "Deveria listar todos os usuários iniciais mais os dois novos.");
    }

    @Test
    void updateUsuarioTest() {
        Usuario usuarioAntigo = new Usuario(999, "74351067047", "Nome Antigo", 35, "Instituição W", "Aluno", "loginAntigo", "senhaAntiga");
        Usuario usuarioNovo = new Usuario(999, "41543030076", "Nome Novo", 36, "Instituição W", "Professor", "loginNovo", "senhaNova");

        usuarioController.cadastrar(usuarioAntigo);
        Usuario usuarioAtualizado = usuarioController.atualizar(usuarioAntigo, usuarioNovo);

        List<Usuario> usuarios = usuarioController.listar();
        assertEquals(estadoInicial.size() + 1, usuarios.size(), "Deveria haver 1 usuário na lista além dos usuários iniciais.");
        assertEquals(usuarioNovo, usuarioAtualizado, "O usuário atualizado deveria ser igual ao usuário novo.");
    }

    @Test
    void deleteUsuarioTest() {
        Usuario usuario = new Usuario(999, "74351067047", "Nome Deletável", 28, "Instituição V", "Admin", "loginDeletavel", "senhaDeletavel");

        usuarioController.cadastrar(usuario);
        boolean deletado = usuarioController.deletar(usuario);

        assertTrue(deletado, "Deveria retornar verdadeiro ao deletar o usuário.");
        assertEquals(estadoInicial.size(), usuarioController.listar().size(), "O banco deveria ter voltado ao estado inicial.");
    }
}
