package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Secao;
import models.Evento;
import models.SubEvento;

import java.util.List;
import java.util.Arrays;

public class SecaoDAOTest {

    private SecaoDAO secaoDAOMock;
    private Secao secao;
    private Evento eventoMock;
    private SubEvento subEventoMock;

    @BeforeEach
    public void setUp() {
        secaoDAOMock = mock(SecaoDAO.class);

        // Mock dos objetos relacionados
        eventoMock = mock(Evento.class);
        subEventoMock = mock(SubEvento.class);

        // Instância de Secao com construtor adequado
        secao = new Secao(eventoMock, subEventoMock, "Secao Teste", "Local Teste", "10:00");
        secao.setDescricao("Descrição da seção teste");
    }

    @Test
    public void testInsertSecao() {
        when(secaoDAOMock.insertSecao(secao)).thenReturn(true);

        boolean result = secaoDAOMock.insertSecao(secao);

        assertTrue(result);
        verify(secaoDAOMock, times(1)).insertSecao(secao);
    }

    @Test
    public void testSelectSecao() {
        when(secaoDAOMock.selectSecao(1L)).thenReturn(secao);

        Secao result = secaoDAOMock.selectSecao(1L);

        assertNotNull(result);
        assertEquals("Secao Teste", result.getNome());
        assertEquals("Descrição da seção teste", result.getDescricao());
        verify(secaoDAOMock, times(1)).selectSecao(1L);
    }

    @Test
    public void testSelectAllSecoes() {
        List<Secao> secoes = Arrays.asList(
                new Secao(eventoMock, subEventoMock, "Secao 1", "Local 1", "09:00"),
                new Secao(eventoMock, subEventoMock, "Secao 2", "Local 2", "14:00")
        );

        secoes.get(0).setDescricao("Descrição da Seção 1");
        secoes.get(1).setDescricao("Descrição da Seção 2");

        when(secaoDAOMock.selectAllSecoes()).thenReturn(secoes);

        List<Secao> result = secaoDAOMock.selectAllSecoes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Secao 1", result.get(0).getNome());
        assertEquals("Descrição da Seção 1", result.get(0).getDescricao());
        verify(secaoDAOMock, times(1)).selectAllSecoes();
    }

    @Test
    public void testDeleteSecao() {
        when(secaoDAOMock.deleteSecao(1L)).thenReturn(true);

        boolean result = secaoDAOMock.deleteSecao(1L);

        assertTrue(result);
        verify(secaoDAOMock, times(1)).deleteSecao(1L);
    }

    @Test
    public void testUpdateSecao() {
        when(secaoDAOMock.updateSecao(secao)).thenReturn(true);

        boolean result = secaoDAOMock.updateSecao(secao);

        assertTrue(result);
        verify(secaoDAOMock, times(1)).updateSecao(secao);
    }
}
