package integration;

import facade.Facade;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {

    protected Facade facade;
    protected List<Long> usuariosCriados;
    protected List<Long> eventosCriados;
    protected List<Long> secoesCriadas;
    protected List<Long> trilhasCriadas;
    protected List<Long> subEventosCriados;
    protected List<Long> inscricoesCriadas;
    protected List<Long> atividadesCriadas;

    @BeforeAll
    void setUp() {
        facade = new Facade();
        usuariosCriados = new ArrayList<>();
        eventosCriados = new ArrayList<>();
        secoesCriadas = new ArrayList<>();
        trilhasCriadas = new ArrayList<>();
        subEventosCriados = new ArrayList<>();
        inscricoesCriadas = new ArrayList<>();
        atividadesCriadas = new ArrayList<>();
    }

    //A SEQUENCIA AQUI É EXTREMAMENTE IMPORTANTE
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

    protected void limparUsuarios() {
        for (Long usuarioId : usuariosCriados) {
            facade.deletarUsuario(usuarioId);
        }
        usuariosCriados.clear();
    }

    protected void limparEventos() {
        for (Long eventoId : eventosCriados) {
            facade.deletarEvento(eventoId);
        }
        eventosCriados.clear();
    }

    protected void limparSecoes() {
        for (Long secaoId : secoesCriadas) {
            facade.deletarSecao(secaoId);
        }
        secoesCriadas.clear();
    }

    protected void limparTrilhas() {
        for (Long trilhaId : trilhasCriadas) {
            facade.deletarTrilha(trilhaId);
        }
        trilhasCriadas.clear();
    }

    protected void limparSubEventos() {
        for (Long subEventoId : subEventosCriados) {
            facade.deletarSubEvento(subEventoId);
        }
        subEventosCriados.clear();
    }

    protected void limparInscricoes() {
        for (Long inscricaoId : inscricoesCriadas) {
            facade.deletarInscricao(inscricaoId);
        }
        inscricoesCriadas.clear();
    }

    protected void limparAtividades() {
        for (Long atividadeId : atividadesCriadas) {
            facade.deletarAtividade(atividadeId);
        }
        atividadesCriadas.clear();
    }
}