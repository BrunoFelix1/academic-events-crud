package integration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import facade.Facade;
import models.*;

public class FacadeTest {

    private Facade facade;
    private List<Evento> estadoInicialEventos;
    private List<Atividade> estadoInicialAtividades;
    private List<Secao> estadoInicialSecoes;
    private List<Usuario> estadoInicialUsuarios;
    private List<Trilha> estadoInicialTrilhas;
    private List<SubEvento> estadoInicialSubEventos;
    private List<Inscricao> estadoInicialInscricoes;

    @BeforeEach
    void setUp() {
        facade = new Facade();
        estadoInicialEventos = new ArrayList<>(facade.listarEventos());
        estadoInicialAtividades = new ArrayList<>(facade.listarAtividades());
        estadoInicialSecoes = new ArrayList<>(facade.listarSecoes());
        estadoInicialUsuarios = new ArrayList<>(facade.listarUsuarios());
        estadoInicialTrilhas = new ArrayList<>(facade.listarTrilhas());
        estadoInicialSubEventos = new ArrayList<>(facade.listarSubEventos());
        estadoInicialInscricoes = new ArrayList<>(facade.listarInscricoes());
    }

    @AfterEach
    void tearDown() {
        limparEventos();
        limparAtividades();
        limparSecoes();
        limparUsuarios();
        limparTrilhas();
        limparSubEventos();
        limparInscricoes();
    }

    private void limparEventos() {
        List<Evento> estadoAtualEventos = new ArrayList<>(facade.listarEventos());
        for (Evento evento : estadoAtualEventos) {
            if (!estadoInicialEventos.contains(evento)) {
                facade.deletarEvento(evento.getId());
            }
        }
    }

    private void limparAtividades() {
        List<Atividade> estadoAtualAtividades = new ArrayList<>(facade.listarAtividades());
        for (Atividade atividade : estadoAtualAtividades) {
            if (!estadoInicialAtividades.contains(atividade)) {
                facade.deletarAtividade(atividade.getId());
            }
        }
    }

    private void limparSecoes() {
        List<Secao> estadoAtualSecoes = new ArrayList<>(facade.listarSecoes());
        for (Secao secao : estadoAtualSecoes) {
            if (!estadoInicialSecoes.contains(secao)) {
                facade.deletarSecao(secao.getId());
            }
        }
    }

    private void limparUsuarios() {
        List<Usuario> estadoAtualUsuarios = new ArrayList<>(facade.listarUsuarios());
        for (Usuario usuario : estadoAtualUsuarios) {
            if (!estadoInicialUsuarios.contains(usuario)) {
                facade.deletarUsuario(usuario.getId());
            }
        }
    }

    private void limparTrilhas() {
        List<Trilha> estadoAtualTrilhas = new ArrayList<>(facade.listarTrilhas());
        for (Trilha trilha : estadoAtualTrilhas) {
            if (!estadoInicialTrilhas.contains(trilha)) {
                facade.deletarTrilha(trilha.getId());
            }
        }
    }

    private void limparSubEventos() {
        List<SubEvento> estadoAtualSubEventos = new ArrayList<>(facade.listarSubEventos());
        for (SubEvento subEvento : estadoAtualSubEventos) {
            if (!estadoInicialSubEventos.contains(subEvento)) {
                facade.deletarSubEvento(subEvento.getId());
            }
        }
    }

    private void limparInscricoes() {
        List<Inscricao> estadoAtualInscricoes = new ArrayList<>(facade.listarInscricoes());
        for (Inscricao inscricao : estadoAtualInscricoes) {
            if (!estadoInicialInscricoes.contains(inscricao)) {
                facade.deletarInscricao(inscricao.getId());
            }
        }
    }

    @Test
    void listEventosTest() {
        Evento evento1 = new Evento();
        evento1.setTitulo("Evento Teste1");
        evento1.setLocal("Local A");
        evento1.setHorario(LocalDateTime.parse("2023-01-01T13:00:00"));
        evento1.setDescricao("Descrição Teste1");

        Evento evento2 = new Evento();
        evento2.setTitulo("Evento Teste2");
        evento2.setLocal("Local B");
        evento2.setHorario(LocalDateTime.parse("2023-01-01T13:00:00"));
        evento2.setDescricao("Descrição Teste2");

        facade.adicionarEvento(evento1);
        facade.adicionarEvento(evento2);

        List<Evento> eventos = facade.listarEventos();
        assertNotNull(eventos);
        assertTrue(eventos.size() >= 2);
    }

    @Test
    void updateEventoTest() {
        Evento eventoAntigo = new Evento();
        eventoAntigo.setTitulo("Evento Teste1");
        eventoAntigo.setLocal("Local A");
        eventoAntigo.setHorario(LocalDateTime.parse("2023-01-01T13:00:00"));
        eventoAntigo.setDescricao("Descrição Teste1");

        facade.adicionarEvento(eventoAntigo);
        Long eventoAntigoId = eventoAntigo.getId();

        Evento eventoNovo = new Evento();
        eventoNovo.setTitulo("Evento Teste2");
        eventoNovo.setLocal("Local B");
        eventoNovo.setHorario(LocalDateTime.parse("2023-01-01T13:00:00"));
        eventoNovo.setDescricao("Descrição Teste2");

        facade.atualizarEvento(eventoAntigoId, eventoNovo);

        Evento eventoAtualizado = facade.buscarEvento(eventoAntigoId);
        assertEquals(eventoNovo.getTitulo(), eventoAtualizado.getTitulo());
        assertEquals(eventoNovo.getLocal(), eventoAtualizado.getLocal());
        assertEquals(eventoNovo.getHorario(), eventoAtualizado.getHorario());
        assertEquals(eventoNovo.getDescricao(), eventoAtualizado.getDescricao());
    }

    @Test
    void deleteEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste1");
        evento.setLocal("Local A");
        evento.setHorario(LocalDateTime.parse("2023-01-01T13:00:00"));
        evento.setDescricao("Descrição Teste1");

        facade.adicionarEvento(evento);
        Long eventoId = evento.getId();

        boolean resultado = facade.deletarEvento(eventoId);
        assertTrue(resultado);

        List<Evento> eventos = facade.listarEventos();
        assertFalse(eventos.contains(evento));
    }

    @Test
    void addEventoTest() {
        Evento evento = new Evento();
        evento.setTitulo("Evento Teste");
        evento.setLocal("Local A");
        evento.setHorario(LocalDateTime.parse("2023-01-01T13:00:00"));
        evento.setDescricao("Descrição Teste");

        facade.adicionarEvento(evento);
        Evento eventoAdicionado = facade.buscarEvento(evento.getId());
        assertEquals(evento, eventoAdicionado);
    }
    
    // Adicionando testes para os outros controllers

    @Test
    void addAtividadeTest() {
        Atividade atividade = new Atividade();
        atividade.setTipoDeAtividade("Atividade Teste");
        atividade.setResumo("Descrição Atividade Teste");

        facade.adicionarAtividade(atividade);
        Atividade atividadeAdicionada = facade.buscarAtividade(atividade.getId());
        assertEquals(atividade, atividadeAdicionada);
    }

    @Test
    void addSecaoTest() {
        Secao secao = new Secao();
        secao.setNome("Secao Teste");
        secao.setHorario(LocalDateTime.parse("2023-01-01T15:00:00"));
        secao.setLocal("Local Secao Teste");

        facade.adicionarSecao(secao);
        Secao secaoAdicionada = facade.buscarSecao(secao.getId());
        assertEquals(secao, secaoAdicionada);
    }

    @Test
    void addUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuário Teste");
        usuario.setLogin("usuario@teste.com");

        facade.adicionarUsuario(usuario);
        Usuario usuarioAdicionado = facade.buscarUsuario(usuario.getId());
        assertEquals(usuario, usuarioAdicionado);
    }
}
