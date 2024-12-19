package controllersTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import controllers.TrilhaController;
import models.Secao;
import models.Trilha;
import repositories.TrilhaDAO;

import java.util.Arrays;
import java.util.List;

class TrilhaControllerTest {

    @Mock
    private TrilhaDAO trilhaDAO;

    @InjectMocks
    private TrilhaController trilhaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarTrilha() {
        Secao secao = new Secao();
        Trilha novaTrilha = new Trilha(secao, "Trilha 1");
        when(trilhaDAO.insertTrilha(novaTrilha)).thenReturn(true);

        boolean resultado = trilhaController.adicionarTrilha(novaTrilha);

        assertTrue(resultado, "A trilha deveria ser adicionada com sucesso.");
        verify(trilhaDAO, times(1)).insertTrilha(novaTrilha);
    }

    @Test
    void testAdicionarTrilhaFalha() {
        Secao secao = new Secao();
        Trilha novaTrilha = new Trilha(secao, "Trilha 1");
        when(trilhaDAO.insertTrilha(novaTrilha)).thenReturn(false);

        boolean resultado = trilhaController.adicionarTrilha(novaTrilha);

        assertFalse(resultado, "A trilha não deveria ser adicionada.");
        verify(trilhaDAO, times(1)).insertTrilha(novaTrilha);
    }

    @Test
    void testAtualizarTrilha() {
        Secao secao = new Secao();
        Trilha trilhaExistente = new Trilha(secao, "Trilha 1");
        trilhaExistente.setId(1L);
        Trilha trilhaAtualizada = new Trilha(secao, "Trilha Atualizada");

        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilhaExistente);
        when(trilhaDAO.updateTrilha(trilhaExistente)).thenReturn(true);

        boolean resultado = trilhaController.atualizarTrilha(1L, trilhaAtualizada);

        assertTrue(resultado, "A trilha deveria ser atualizada com sucesso.");
        assertEquals("Trilha Atualizada", trilhaExistente.getNome());
        verify(trilhaDAO, times(1)).updateTrilha(trilhaExistente);
    }

    @Test
    void testAtualizarTrilhaNaoEncontrada() {
        Secao secao = new Secao();
        Trilha trilhaAtualizada = new Trilha(secao, "Trilha Atualizada");

        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.atualizarTrilha(1L, trilhaAtualizada);
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
        verify(trilhaDAO, never()).updateTrilha(any());
    }

    @Test
    void testDeletarTrilha() {
        when(trilhaDAO.deleteTrilha(1L)).thenReturn(true);

        boolean resultado = trilhaController.deletarTrilha(1L);

        assertTrue(resultado, "A trilha deveria ser deletada com sucesso.");
        verify(trilhaDAO, times(1)).deleteTrilha(1L);
    }

    @Test
    void testDeletarTrilhaNaoEncontrada() {
        when(trilhaDAO.deleteTrilha(1L)).thenReturn(false);

        boolean resultado = trilhaController.deletarTrilha(1L);

        assertFalse(resultado, "A trilha não deveria ser deletada.");
        verify(trilhaDAO, times(1)).deleteTrilha(1L);
    }

    @Test
    void testListarTodasTrilhas() {
        Secao secao = new Secao();
        Trilha trilha1 = new Trilha(secao, "Trilha 1");
        Trilha trilha2 = new Trilha(secao, "Trilha 2");

        List<Trilha> trilhasEsperadas = Arrays.asList(trilha1, trilha2);
        when(trilhaDAO.selectAllTrilhas()).thenReturn(trilhasEsperadas);

        List<Trilha> trilhas = trilhaController.listarTodasTrilhas();

        assertNotNull(trilhas);
        assertEquals(2, trilhas.size());
        assertEquals("Trilha 1", trilhas.get(0).getNome());
        assertEquals("Trilha 2", trilhas.get(1).getNome());
        verify(trilhaDAO, times(1)).selectAllTrilhas();
    }

    @Test
    void testBuscarTrilhaPorId() {
        Secao secao = new Secao();
        Trilha trilha = new Trilha(secao, "Trilha 1");
        trilha.setId(1L);

        when(trilhaDAO.selectTrilha(1L)).thenReturn(trilha);

        Trilha trilhaBuscada = trilhaController.buscarTrilhaPorId(1L);

        assertNotNull(trilhaBuscada);
        assertEquals("Trilha 1", trilhaBuscada.getNome());
        verify(trilhaDAO, times(1)).selectTrilha(1L);
    }

    @Test
    void testBuscarTrilhaPorIdNaoEncontrada() {
        when(trilhaDAO.selectTrilha(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            trilhaController.buscarTrilhaPorId(1L);
        });

        assertEquals("Trilha não encontrada", exception.getMessage());
        verify(trilhaDAO, times(1)).selectTrilha(1L);
    }
}
