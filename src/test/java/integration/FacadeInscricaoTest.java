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

        SubEvento subEvento1 = new SubEvento(evento, "SubEvento Teste 1", "Local B", "14:00", "Descrição SubEvento 1");
        facade.adicionarSubEvento(subEvento1);
        subEventosCriados.add(subEvento1.getId());

        SubEvento subEvento2 = new SubEvento(evento, "SubEvento Teste 2", "Local C", "15:00", "Descrição SubEvento 2");
        facade.adicionarSubEvento(subEvento2);
        subEventosCriados.add(subEvento2.getId());

        Secao secao1 = new Secao(evento, subEvento1, "Secao Teste 1", "Local D", "16:00");
        facade.adicionarSecao(secao1);
        secoesCriadas.add(secao1.getId());

        Secao secao2 = new Secao(evento, subEvento2, "Secao Teste 2", "Local E", "17:00");
        facade.adicionarSecao(secao2);
        secoesCriadas.add(secao2.getId());

        Trilha trilha1 = new Trilha(secao1, "Trilha Teste 1");
        facade.adicionarTrilha(trilha1);
        trilhasCriadas.add(trilha1.getId());

        Trilha trilha2 = new Trilha(secao2, "Trilha Teste 2");
        facade.adicionarTrilha(trilha2);
        trilhasCriadas.add(trilha2.getId());

        Inscricao inscricao1 = new Inscricao(usuario, evento, subEvento1, secao1, trilha1);
        Inscricao inscricao2 = new Inscricao(usuario, evento, subEvento2, secao2, trilha2);

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
