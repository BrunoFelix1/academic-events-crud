package controllersTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import controllers.SecaoController;
import models.Evento;
import models.Secao;
import models.SubEvento;
import repositories.SecaoDAO;

import java.util.Arrays;
import java.util.List;

class SecaoControllerTest {

    @Mock
    private SecaoDAO secaoDAO;

    @InjectMocks
    private SecaoController secaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarSecao() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento();
        Secao novaSecao = new Secao(evento, subEvento, "Secao 1", "Sala A", "10:00");
        when(secaoDAO.insertSecao(novaSecao)).thenReturn(true);

        boolean resultado = secaoController.adicionarSecao(novaSecao);

        assertTrue(resultado, "A seção deveria ser adicionada com sucesso.");
        verify(secaoDAO, times(1)).insertSecao(novaSecao);
    }

    @Test
    void testAdicionarSecaoFalha() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento();
        Secao novaSecao = new Secao(evento, subEvento, "Secao 1", "Sala A", "10:00");
        when(secaoDAO.insertSecao(novaSecao)).thenReturn(false);

        boolean resultado = secaoController.adicionarSecao(novaSecao);

        assertFalse(resultado, "A seção não deveria ser adicionada.");
        verify(secaoDAO, times(1)).insertSecao(novaSecao);
    }

    @Test
    void testAtualizarSecao() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento();
        Secao secaoExistente = new Secao(evento, subEvento, "Secao 1", "Sala A", "10:00");
        secaoExistente.setId(1L);
        Secao secaoAtualizada = new Secao(evento, subEvento, "Secao Atualizada", "Sala B", "14:00");

        when(secaoDAO.selectSecao(1L)).thenReturn(secaoExistente);
        when(secaoDAO.updateSecao(secaoExistente)).thenReturn(true);

        boolean resultado = secaoController.atualizarSecao(1L, secaoAtualizada);

        assertTrue(resultado, "A seção deveria ser atualizada com sucesso.");
        assertEquals("Secao Atualizada", secaoExistente.getNome());
        assertEquals("Sala B", secaoExistente.getLocal());
        assertEquals("14:00", secaoExistente.getHorario());
        verify(secaoDAO, times(1)).updateSecao(secaoExistente);
    }

    @Test
    void testAtualizarSecaoNaoEncontrada() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento();
        Secao secaoAtualizada = new Secao(evento, subEvento, "Secao Atualizada", "Sala B", "14:00");

        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            secaoController.atualizarSecao(1L, secaoAtualizada);
        });

        assertEquals("Secao não encontrada", exception.getMessage());
        verify(secaoDAO, never()).updateSecao(any());
    }

    @Test
    void testDeletarSecao() {
        when(secaoDAO.deleteSecao(1L)).thenReturn(true);

        boolean resultado = secaoController.deletarSecao(1L);

        assertTrue(resultado, "A seção deveria ser deletada com sucesso.");
        verify(secaoDAO, times(1)).deleteSecao(1L);
    }

    @Test
    void testDeletarSecaoNaoEncontrada() {
        when(secaoDAO.deleteSecao(1L)).thenReturn(false);

        boolean resultado = secaoController.deletarSecao(1L);

        assertFalse(resultado, "A seção não deveria ser deletada.");
        verify(secaoDAO, times(1)).deleteSecao(1L);
    }

    @Test
    void testListarTodasSecoes() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento();
        Secao secao1 = new Secao(evento, subEvento, "Secao 1", "Sala A", "10:00");
        Secao secao2 = new Secao(evento, subEvento, "Secao 2", "Sala B", "14:00");

        List<Secao> secoesEsperadas = Arrays.asList(secao1, secao2);
        when(secaoDAO.selectAllSecoes()).thenReturn(secoesEsperadas);

        List<Secao> secoes = secaoController.listarTodasSecoes();

        assertNotNull(secoes);
        assertEquals(2, secoes.size());
        assertEquals("Secao 1", secoes.get(0).getNome());
        assertEquals("Secao 2", secoes.get(1).getNome());
        verify(secaoDAO, times(1)).selectAllSecoes();
    }

    @Test
    void testBuscarSecaoPorId() {
        Evento evento = new Evento();
        SubEvento subEvento = new SubEvento();
        Secao secao = new Secao(evento, subEvento, "Secao 1", "Sala A", "10:00");
        secao.setId(1L);

        when(secaoDAO.selectSecao(1L)).thenReturn(secao);

        Secao secaoBuscada = secaoController.buscarSecaoPorId(1L);

        assertNotNull(secaoBuscada);
        assertEquals("Secao 1", secaoBuscada.getNome());
        assertEquals("Sala A", secaoBuscada.getLocal());
        verify(secaoDAO, times(1)).selectSecao(1L);
    }

    @Test
    void testBuscarSecaoPorIdNaoEncontrada() {
        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            secaoController.buscarSecaoPorId(1L);
        });

        assertEquals("Secao não encontrada", exception.getMessage());
        verify(secaoDAO, times(1)).selectSecao(1L);
    }
}
