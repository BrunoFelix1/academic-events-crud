package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import models.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeInscricaoTest extends BaseIntegrationTest {

    @Test
    void addInscricaoTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste");
        usuario.setInstituicao("Instituicao Teste");
        usuario.setTipoDeUsuario("COMUM");
        facade.adicionarUsuario(usuario);
        usuariosCriados.add(usuario.getId());

        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        secoesCriadas.add(secao.getId());

        Trilha trilha = new Trilha(secao, "Trilha Teste");
        facade.adicionarTrilha(trilha);
        trilhasCriadas.add(trilha.getId());

        Inscricao inscricao = new Inscricao(usuario, evento, subEvento, secao, trilha);

        assertTrue(facade.adicionarInscricao(inscricao));
        inscricoesCriadas.add(inscricao.getId()); // Rastrear o ID da inscrição criada
    }

    @Test
    void listInscricoesTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste");
        usuario.setInstituicao("Instituicao Teste");
        usuario.setTipoDeUsuario("COMUM");
        facade.adicionarUsuario(usuario);
        usuariosCriados.add(usuario.getId());

        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        secoesCriadas.add(secao.getId());

        Trilha trilha = new Trilha(secao, "Trilha Teste");
        facade.adicionarTrilha(trilha);
        trilhasCriadas.add(trilha.getId());

        Inscricao inscricao1 = new Inscricao(usuario, evento, subEvento, secao, trilha);
        Inscricao inscricao2 = new Inscricao(usuario, evento, subEvento, secao, trilha);

        facade.adicionarInscricao(inscricao1);
        facade.adicionarInscricao(inscricao2);

        // Rastrear os IDs das inscrições criadas
        inscricoesCriadas.add(inscricao1.getId());
        inscricoesCriadas.add(inscricao2.getId());

        List<Inscricao> inscricoes = facade.listarInscricoes();
        assertNotNull(inscricoes);
        assertTrue(inscricoes.size() >= 2);
    }

    @Test
    void updateInscricaoTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste");
        usuario.setInstituicao("Instituicao Teste");
        usuario.setTipoDeUsuario("COMUM");
        facade.adicionarUsuario(usuario);
        usuariosCriados.add(usuario.getId());

        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        secoesCriadas.add(secao.getId());

        Trilha trilha = new Trilha(secao, "Trilha Teste");
        facade.adicionarTrilha(trilha);
        trilhasCriadas.add(trilha.getId());

        Inscricao inscricaoAntiga = new Inscricao(usuario, evento, subEvento, secao, trilha);
        facade.adicionarInscricao(inscricaoAntiga);
        Long inscricaoAntigaId = inscricaoAntiga.getId();
        inscricoesCriadas.add(inscricaoAntigaId); // Rastrear o ID da inscrição criada

        Inscricao inscricaoNova = new Inscricao(usuario, evento, subEvento, secao, trilha);
        assertTrue(facade.atualizarInscricao(inscricaoAntigaId, inscricaoNova));
    }

    @Test
    void deleteInscricaoTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("usuarioTeste");
        usuario.setCpf("641.528.610-29");
        usuario.setSenha("senhaTeste");
        usuario.setIdade(25);
        usuario.setNome("Nome Teste");
        usuario.setInstituicao("Instituicao Teste");
        usuario.setTipoDeUsuario("COMUM");
        facade.adicionarUsuario(usuario);
        usuariosCriados.add(usuario.getId());

        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        secoesCriadas.add(secao.getId());

        Trilha trilha = new Trilha(secao, "Trilha Teste");
        facade.adicionarTrilha(trilha);
        trilhasCriadas.add(trilha.getId());

        Inscricao inscricao = new Inscricao(usuario, evento, subEvento, secao, trilha);
        facade.adicionarInscricao(inscricao);
        Long inscricaoId = inscricao.getId();
        inscricoesCriadas.add(inscricaoId); // Rastrear o ID da inscrição criada

        boolean resultado = facade.deletarInscricao(inscricaoId);
        assertTrue(resultado);

        // Remover o ID rastreado, pois já foi deletado
        inscricoesCriadas.remove(inscricaoId);
    }
}
