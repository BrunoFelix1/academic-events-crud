package controllers;

import models.Secao;
import models.Evento;
import models.SubEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import repositories.SecaoDAO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecaoControllerTest {

    @InjectMocks
    private SecaoController secaoController;

    @Mock
    private SecaoDAO secaoDAO;

    @Mock
    private Evento evento;  // Mockando o Evento

    @Mock
    private SubEvento subEvento;  // Mockando o SubEvento

    private Secao secao;

    @BeforeEach
    public void setUp() {
        // Mockando o Evento e o SubEvento
        evento = mock(Evento.class);
        subEvento = mock(SubEvento.class);

        // Criando e inicializando a Secao com Evento e SubEvento mockados
        secao = new Secao();
        secao.setId(1L);
        secao.setNome("Secao Teste");
        secao.setLocal("Local Teste");
        secao.setHorario("10:00");
        secao.setDescricao("Descrição da Secao");
        secao.setEvento(evento);  // Atribuindo o Evento mockado
        secao.setSubEvento(subEvento);  // Atribuindo o SubEvento mockado
    }

    @Test
    public void testAdicionarSecao_Sucesso() {
        when(secaoDAO.insertSecao(secao)).thenReturn(true);

        boolean result = secaoController.adicionarSecao(secao);

        assertTrue(result);
        verify(secaoDAO, times(1)).insertSecao(secao);
    }

    @Test
    public void testAdicionarSecao_Falha() {
        when(secaoDAO.insertSecao(secao)).thenReturn(false);

        boolean result = secaoController.adicionarSecao(secao);

        assertFalse(result);
        verify(secaoDAO, times(1)).insertSecao(secao);
    }

    @Test
    public void testAtualizarSecao_Sucesso() {
        Secao secaoAtualizada = new Secao();
        secaoAtualizada.setNome("Secao Atualizada");
        secaoAtualizada.setLocal("Novo Local");
        secaoAtualizada.setHorario("14:00");
        secaoAtualizada.setDescricao("Nova Descrição");
        secaoAtualizada.setEvento(evento);  // Atribuindo o Evento mockado
        secaoAtualizada.setSubEvento(subEvento);  // Atribuindo o SubEvento mockado

        when(secaoDAO.selectSecao(1L)).thenReturn(secao);
        when(secaoDAO.updateSecao(secao)).thenReturn(true);

        boolean result = secaoController.atualizarSecao(1L, secaoAtualizada);

        assertTrue(result);
        verify(secaoDAO, times(1)).selectSecao(1L);
        verify(secaoDAO, times(1)).updateSecao(secao);
    }

    @Test
    public void testAtualizarSecao_SecaoNaoEncontrada() {
        Secao secaoAtualizada = new Secao();
        secaoAtualizada.setNome("Secao Atualizada");

        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            secaoController.atualizarSecao(1L, secaoAtualizada);
        });

        assertEquals("Secao não encontrada", exception.getMessage());
        verify(secaoDAO, times(1)).selectSecao(1L);
        verify(secaoDAO, times(0)).updateSecao(any());
    }

    @Test
    public void testDeletarSecao_Sucesso() {
        when(secaoDAO.deleteSecao(1L)).thenReturn(true);

        boolean result = secaoController.deletarSecao(1L);

        assertTrue(result);
        verify(secaoDAO, times(1)).deleteSecao(1L);
    }

    @Test
    public void testDeletarSecao_Falha() {
        when(secaoDAO.deleteSecao(1L)).thenReturn(false);

        boolean result = secaoController.deletarSecao(1L);

        assertFalse(result);
        verify(secaoDAO, times(1)).deleteSecao(1L);
    }

    @Test
    public void testListarTodasSecoes() {
        Secao secao2 = new Secao();
        secao2.setId(2L);
        secao2.setNome("Secao Teste 2");
        secao2.setEvento(evento);  // Atribuindo o Evento mockado
        secao2.setSubEvento(subEvento);  // Atribuindo o SubEvento mockado

        List<Secao> secoes = Arrays.asList(secao, secao2);

        when(secaoDAO.selectAllSecoes()).thenReturn(secoes);

        List<Secao> result = secaoController.listarTodasSecoes();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(secaoDAO, times(1)).selectAllSecoes();
    }

    @Test
    public void testBuscarSecaoPorId_Sucesso() {
        when(secaoDAO.selectSecao(1L)).thenReturn(secao);

        Secao result = secaoController.buscarSecaoPorId(1L);

        assertNotNull(result);
        assertEquals(secao.getId(), result.getId());
        verify(secaoDAO, times(1)).selectSecao(1L);
    }

    @Test
    public void testBuscarSecaoPorId_SecaoNaoEncontrada() {
        when(secaoDAO.selectSecao(1L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            secaoController.buscarSecaoPorId(1L);
        });

        assertEquals("Secao não encontrada", exception.getMessage());
        verify(secaoDAO, times(1)).selectSecao(1L);
    }
}
