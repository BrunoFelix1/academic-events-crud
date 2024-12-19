package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import models.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeSecaoTest extends BaseIntegrationTest {

    @Test
    void addSecaoTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        assertEquals(true, facade.adicionarSecao(secao));
        secoesCriadas.add(secao.getId()); // Rastrear o ID da seção criada
    }

    @Test
    void listSecoesTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao1 = new Secao(evento, subEvento, "Secao Teste1", "Local C", "15:00");
        Secao secao2 = new Secao(evento, subEvento, "Secao Teste2", "Local D", "16:00");

        facade.adicionarSecao(secao1);
        facade.adicionarSecao(secao2);

        // Rastrear os IDs das seções criadas
        secoesCriadas.add(secao1.getId());
        secoesCriadas.add(secao2.getId());

        List<Secao> secoes = facade.listarSecoes();
        assertNotNull(secoes);
        assertTrue(secoes.size() >= 2);
    }

    @Test
    void updateSecaoTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secaoAntiga = new Secao(evento, subEvento, "Secao Teste1", "Local C", "15:00");
        facade.adicionarSecao(secaoAntiga);
        Long secaoAntigaId = secaoAntiga.getId();
        secoesCriadas.add(secaoAntigaId); // Rastrear o ID da seção criada

        Secao secaoNova = new Secao(evento, subEvento, "Secao Teste2", "Local D", "16:00");
        assertTrue(facade.atualizarSecao(secaoAntigaId, secaoNova));
    }

    @Test
    void deleteSecaoTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        Long secaoId = secao.getId();
        secoesCriadas.add(secaoId); // Rastrear o ID da seção criada

        assertTrue(facade.deletarSecao(secaoId));
    }
}