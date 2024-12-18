package integration;

import models.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacadeUsuarioTest extends BaseIntegrationTest {

    @Test
    void addUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste");
        usuario.setInstituicao("Instituicao Teste");
        usuario.setTipoDeUsuario("COMUM");

        assertTrue(facade.adicionarUsuario(usuario));
        usuariosCriados.add(usuario.getId()); // Rastrear o ID do usuário criado
    }

    @Test
    void updateUsuarioTest() {
        Usuario usuarioAntigo = new Usuario();
        usuarioAntigo.setLogin("usuarioAntigo");
        usuarioAntigo.setCpf("123.456.789-00");
        usuarioAntigo.setSenha("senhaAntiga");
        usuarioAntigo.setIdade(30);
        usuarioAntigo.setNome("Nome Antigo");
        usuarioAntigo.setInstituicao("Instituicao Antiga");
        usuarioAntigo.setTipoDeUsuario("COMUM");

        facade.adicionarUsuario(usuarioAntigo);
        Long usuarioAntigoId = usuarioAntigo.getId();
        usuariosCriados.add(usuarioAntigoId); // Rastrear o ID do usuário criado

        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setLogin("usuarioNovo");
        usuarioNovo.setCpf("987.654.321-00");
        usuarioNovo.setSenha("senhaNova");
        usuarioNovo.setIdade(35);
        usuarioNovo.setNome("Nome Novo");
        usuarioNovo.setInstituicao("Instituicao Nova");
        usuarioNovo.setTipoDeUsuario("ADMIN");

        assertTrue(facade.atualizarUsuario(usuarioAntigoId, usuarioNovo));
    }

    @Test
    void deleteUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste1");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste1");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste1");
        usuario.setInstituicao("Instituicao Teste1");
        usuario.setTipoDeUsuario("COMUM");

        facade.adicionarUsuario(usuario);
        Long usuarioId = usuario.getId();
        usuariosCriados.add(usuarioId); // Rastrear o ID do usuário criado

        boolean resultado = facade.deletarUsuario(usuarioId);
        assertTrue(resultado);

        // Remover o ID rastreado, pois já foi deletado
        usuariosCriados.remove(usuarioId);
    }
}