package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import models.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeAtividadeTest extends BaseIntegrationTest {

    @Test
    void addAtividadeTest() {
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

        Atividade atividade = new Atividade();
        atividade.setTipoDeAtividade("Tipo Teste");
        atividade.setAutor("Autor Teste");
        atividade.setResumo("Resumo Teste");
        atividade.setTrilha(trilha);

        assertEquals(true, facade.adicionarAtividade(atividade));
        atividadesCriadas.add(atividade.getId()); // Rastrear o ID da atividade criada
    }

    @Test
    void listAtividadesTest() {
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

        Atividade atividade1 = new Atividade();
        atividade1.setTipoDeAtividade("Tipo Teste1");
        atividade1.setAutor("Autor Teste1");
        atividade1.setResumo("Resumo Teste1");
        atividade1.setTrilha(trilha);

        Atividade atividade2 = new Atividade();
        atividade2.setTipoDeAtividade("Tipo Teste2");
        atividade2.setAutor("Autor Teste2");
        atividade2.setResumo("Resumo Teste2");
        atividade2.setTrilha(trilha);

        facade.adicionarAtividade(atividade1);
        facade.adicionarAtividade(atividade2);

        // Rastrear os IDs das atividades criadas
        atividadesCriadas.add(atividade1.getId());
        atividadesCriadas.add(atividade2.getId());

        List<Atividade> atividades = facade.listarAtividades();
        assertNotNull(atividades);
        assertTrue(atividades.size() >= 2);
    }

    @Test
    void updateAtividadeTest() {
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

        Atividade atividadeAntiga = new Atividade();
        atividadeAntiga.setTipoDeAtividade("Tipo Teste1");
        atividadeAntiga.setAutor("Autor Teste1");
        atividadeAntiga.setResumo("Resumo Teste1");
        atividadeAntiga.setTrilha(trilha);

        facade.adicionarAtividade(atividadeAntiga);
        Long atividadeAntigaId = atividadeAntiga.getId();
        atividadesCriadas.add(atividadeAntigaId); // Rastrear o ID da atividade criada

        Atividade atividadeNova = new Atividade();
        atividadeNova.setTipoDeAtividade("Tipo Teste2");
        atividadeNova.setAutor("Autor Teste2");
        atividadeNova.setResumo("Resumo Teste2");
        atividadeNova.setTrilha(trilha);

        assertTrue(facade.atualizarAtividade(atividadeAntigaId, atividadeNova));
    }

    @Test
    void deleteAtividadeTest() {
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

        Atividade atividade = new Atividade();
        atividade.setTipoDeAtividade("Tipo Teste1");
        atividade.setAutor("Autor Teste1");
        atividade.setResumo("Resumo Teste1");
        atividade.setTrilha(trilha);

        facade.adicionarAtividade(atividade);
        Long atividadeId = atividade.getId();
        atividadesCriadas.add(atividadeId); // Rastrear o ID da atividade criada

        boolean resultado = facade.deletarAtividade(atividadeId);
        assertTrue(resultado);

        // Remover o ID rastreado, pois já foi deletado
        atividadesCriadas.remove(atividadeId);
    }
}
