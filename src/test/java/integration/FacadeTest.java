package integration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import facade.Facade;
import models.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeTest {

    private Facade facade;
    private List<Long> eventosCriados;
    private List<Long> atividadesCriadas;
    private List<Long> secoesCriadas;
    private List<Long> usuariosCriados;
    private List<Long> trilhasCriadas;
    private List<Long> subEventosCriados;
    private List<InscricaoId> inscricoesCriadas;

    @BeforeAll
    void setUp() {
        facade = new Facade();

        // Inicializando listas de rastreamento de IDs criados durante os testes
        eventosCriados = new ArrayList<>();
        atividadesCriadas = new ArrayList<>();
        secoesCriadas = new ArrayList<>();
        usuariosCriados = new ArrayList<>();
        trilhasCriadas = new ArrayList<>();
        subEventosCriados = new ArrayList<>();
        inscricoesCriadas = new ArrayList<>();
    }

    @AfterAll
    void tearDown() {
        limparInscricoes();
        limparAtividades();
        limparTrilhas();
        limparSecoes();
        limparSubEventos();
        limparUsuarios();
        limparEventos();
        
    }

    private void limparEventos() {
        for (Long eventoId : eventosCriados) {
            facade.deletarEvento(eventoId);
        }
        eventosCriados.clear();
    }

    private void limparAtividades() {
        for (Long atividadeId : atividadesCriadas) {
            facade.deletarAtividade(atividadeId);
        }
        atividadesCriadas.clear();
    }

    private void limparSecoes() {
        for (Long secaoId : secoesCriadas) {
            facade.deletarSecao(secaoId);
        }
        secoesCriadas.clear();
    }

    private void limparUsuarios() {
        for (Long usuarioId : usuariosCriados) {
            facade.deletarUsuario(usuarioId);
        }
        usuariosCriados.clear();
    }

    private void limparTrilhas() {
        for (Long trilhaId : trilhasCriadas) {
            facade.deletarTrilha(trilhaId);
        }
        trilhasCriadas.clear();
    }

    private void limparSubEventos() {
        for (Long subEventoId : subEventosCriados) {
            facade.deletarSubEvento(subEventoId);
        }
        subEventosCriados.clear();
    }

    private void limparInscricoes() {
        for (InscricaoId inscricaoId : inscricoesCriadas) {
            facade.deletarInscricao(inscricaoId);
        }
        inscricoesCriadas.clear();
    }

    // Testes para Evento
    @Test
    void addEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste");

        assertEquals(true, facade.adicionarEvento(evento));
        eventosCriados.add(evento.getId()); // Rastrear o ID do evento criado
    }

    @Test
    void listEventosTest() {
        Evento evento1 = new Evento();
        evento1.setTitulo("Evento Teste1");
        evento1.setLocal("Local A");
        evento1.setHorario("13:00");
        evento1.setDescricao("Descrição Teste1");

        Evento evento2 = new Evento();
        evento2.setTitulo("Evento Teste2");
        evento2.setLocal("Local B");
        evento2.setHorario("13:00");
        evento2.setDescricao("Descrição Teste2");

        facade.adicionarEvento(evento1);
        facade.adicionarEvento(evento2);

        // Rastrear os IDs dos eventos criados
        eventosCriados.add(evento1.getId());
        eventosCriados.add(evento2.getId());

        List<Evento> eventos = facade.listarEventos();
        assertNotNull(eventos);
        assertTrue(eventos.size() >= 2);
    }

    @Test
    void updateEventoTest() {
        Evento eventoAntigo = new Evento();
        eventoAntigo.setTitulo("Evento Teste1");
        eventoAntigo.setLocal("Local A");
        eventoAntigo.setHorario("13:00");
        eventoAntigo.setDescricao("Descrição Teste1");

        facade.adicionarEvento(eventoAntigo);
        Long eventoAntigoId = eventoAntigo.getId();
        eventosCriados.add(eventoAntigoId); // Rastrear o ID do evento criado

        Evento eventoNovo = new Evento();
        eventoNovo.setTitulo("Evento Teste2");
        eventoNovo.setLocal("Local B");
        eventoNovo.setHorario("14:00");
        eventoNovo.setDescricao("Descrição Teste2");

        assertTrue(facade.atualizarEvento(eventoAntigoId, eventoNovo));
    }

    @Test
    void deleteEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste1");
        evento.setLocal("Local A");
        evento.setHorario("13:00");
        evento.setDescricao("Descrição Teste1");

        facade.adicionarEvento(evento);
        Long eventoId = evento.getId();
        eventosCriados.add(eventoId); // Rastrear o ID do evento criado

        boolean resultado = facade.deletarEvento(eventoId);
        assertTrue(resultado);

        // Remover o ID rastreado, pois já foi deletado
        eventosCriados.remove(eventoId);
    }

    // Testes para Atividade
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
