package controllersTest;

import controllers.AtividadeController;
import models.Atividade;
import models.Trilha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.AtividadeDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtividadeControllerTest {

    @Mock
    private AtividadeDAO atividadeDAO;

    @InjectMocks
    private AtividadeController atividadeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAdicionarAtividade() {
        Trilha trilha = new Trilha();
        Atividade novaAtividade = new Atividade(1L, "Workshop", "Autor", "Resumo do Workshop", trilha);
        when(atividadeDAO.insertAtividade(novaAtividade)).thenReturn(true);

        boolean resultado = atividadeController.adicionarAtividade(novaAtividade);

        assertTrue(resultado, "A atividade deveria ser adicionada com sucesso.");
        verify(atividadeDAO, times(1)).insertAtividade(novaAtividade);
    }
    @Test
    void testAtualizarAtividadeNaoEncontrada() {
        when(atividadeDAO.selectAtividade(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            atividadeController.atualizarAtividade(1L, new Atividade());
        });

        assertEquals("Atividade não encontrada", exception.getMessage());
        verify(atividadeDAO, never()).updateAtividade(any());
    }

    @Test
    void testDeletarAtividade() {
        when(atividadeDAO.deleteAtividade(1L)).thenReturn(true);

        boolean resultado = atividadeController.deletarAtividade(1L);

        assertTrue(resultado, "A atividade deveria ser deletada com sucesso.");
        verify(atividadeDAO, times(1)).deleteAtividade(1L);
    }

    @Test
    void testListarTodasAtividades() {
        Trilha trilha = new Trilha();
        List<Atividade> atividades = Arrays.asList(
                new Atividade(1L, "Workshop", "Autor", "Resumo do Workshop", trilha),
                new Atividade(2L, "Seminário", "Outro Autor", "Resumo do Seminário", trilha)
        );

        when(atividadeDAO.selectAllAtividades()).thenReturn(atividades);

        List<Atividade> resultado = atividadeController.listarTodasAtividades();

        assertNotNull(resultado, "A lista de atividades não deveria ser nula.");
        assertEquals(2, resultado.size(), "A lista de atividades deveria conter 2 itens.");
        verify(atividadeDAO, times(1)).selectAllAtividades();
    }

    @Test
    void testBuscarAtividadePorId() {
        Trilha trilha = new Trilha();
        Atividade atividade = new Atividade(1L, "Workshop", "Autor", "Resumo do Workshop", trilha);

        when(atividadeDAO.selectAtividade(1L)).thenReturn(atividade);

        Atividade resultado = atividadeController.buscarAtividadePorId(1L);

        assertNotNull(resultado, "A atividade buscada não deveria ser nula.");
        assertEquals("Workshop", resultado.getTipoDeAtividade());
        verify(atividadeDAO, times(1)).selectAtividade(1L);
    }

    @Test
    void testBuscarAtividadePorIdNaoEncontrada() {
        when(atividadeDAO.selectAtividade(1L)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            atividadeController.buscarAtividadePorId(1L);
        });

        assertEquals("Atividade não encontrada", exception.getMessage());
        verify(atividadeDAO, times(1)).selectAtividade(1L);
    }
}
