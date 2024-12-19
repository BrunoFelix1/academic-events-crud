package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import models.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeTrilhaTest extends BaseIntegrationTest {

    @Test
    void addTrilhaTest() {
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
        assertTrue(facade.adicionarTrilha(trilha));
        trilhasCriadas.add(trilha.getId()); // Rastrear o ID da trilha criada
    }

    @Test
    void listTrilhasTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        secoesCriadas.add(secao.getId());

        Trilha trilha1 = new Trilha(secao, "Trilha Teste 1");
        Trilha trilha2 = new Trilha(secao, "Trilha Teste 2");

        facade.adicionarTrilha(trilha1);
        facade.adicionarTrilha(trilha2);

        // Rastrear os IDs das trilhas criadas
        trilhasCriadas.add(trilha1.getId());
        trilhasCriadas.add(trilha2.getId());

        List<Trilha> trilhas = facade.listarTrilhas();
        assertNotNull(trilhas);
        assertTrue(trilhas.size() >= 2);
    }

    @Test
    void updateTrilhaTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        subEventosCriados.add(subEvento.getId());

        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");
        facade.adicionarSecao(secao);
        secoesCriadas.add(secao.getId());

        Trilha trilhaAntiga = new Trilha(secao, "Trilha Teste 1");
        facade.adicionarTrilha(trilhaAntiga);
        Long trilhaAntigaId = trilhaAntiga.getId();
        trilhasCriadas.add(trilhaAntigaId); // Rastrear o ID da trilha criada

        Trilha trilhaNova = new Trilha(secao, "Trilha Teste Atualizada");
        assertTrue(facade.atualizarTrilha(trilhaAntigaId, trilhaNova));
    }

    @Test
    void deleteTrilhaTest() {
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
        Long trilhaId = trilha.getId();
        trilhasCriadas.add(trilhaId); // Rastrear o ID da trilha criada

        boolean resultado = facade.deletarTrilha(trilhaId);
        assertTrue(resultado);

        // Remover o ID rastreado, pois já foi deletado
        trilhasCriadas.remove(trilhaId);
    }
}