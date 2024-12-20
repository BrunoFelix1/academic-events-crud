package repositories;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Inscricao;
import models.Usuario;
import models.Evento;
import models.SubEvento;
import models.Secao;
import models.Trilha;

import java.util.List;
import java.util.Arrays;

public class InscricaoDAOTest {

    private InscricaoDAO inscricaoDAOMock;
    private Inscricao inscricao;
    private Usuario usuarioMock;
    private Evento eventoMock;
    private SubEvento subEventoMock;
    private Secao secaoMock;
    private Trilha trilhaMock;

    @BeforeEach
    public void setUp() {
        inscricaoDAOMock = mock(InscricaoDAO.class);

        // Mocks para entidades relacionadas
        usuarioMock = mock(Usuario.class);
        eventoMock = mock(Evento.class);
        subEventoMock = mock(SubEvento.class);
        secaoMock = mock(Secao.class);
        trilhaMock = mock(Trilha.class);

        // Instância de Inscricao com os mocks
        inscricao = new Inscricao(usuarioMock, eventoMock, subEventoMock, secaoMock, trilhaMock);
    }

    @Test
    public void testInsertInscricao() {
        when(inscricaoDAOMock.insertInscricao(inscricao)).thenReturn(true);

        boolean result = inscricaoDAOMock.insertInscricao(inscricao);

        assertTrue(result);
        verify(inscricaoDAOMock, times(1)).insertInscricao(inscricao);
    }

    @Test
    public void testSelectInscricao() {
        when(inscricaoDAOMock.selectInscricao(1L)).thenReturn(inscricao);

        Inscricao result = inscricaoDAOMock.selectInscricao(1L);

        assertNotNull(result);
        assertEquals(usuarioMock, result.getUsuario());
        assertEquals(eventoMock, result.getEvento());
        verify(inscricaoDAOMock, times(1)).selectInscricao(1L);
    }

    @Test
    public void testSelectAllInscricoes() {
        List<Inscricao> inscricoes = Arrays.asList(
                new Inscricao(usuarioMock, eventoMock, subEventoMock, secaoMock, trilhaMock),
                new Inscricao(usuarioMock, eventoMock, null, null, trilhaMock)
        );

        when(inscricaoDAOMock.selectAllInscricoes()).thenReturn(inscricoes);

        List<Inscricao> result = inscricaoDAOMock.selectAllInscricoes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(eventoMock, result.get(0).getEvento());
        verify(inscricaoDAOMock, times(1)).selectAllInscricoes();
    }

    @Test
    public void testDeleteInscricao() {
        when(inscricaoDAOMock.deleteInscricao(1L)).thenReturn(true);

        boolean result = inscricaoDAOMock.deleteInscricao(1L);

        assertTrue(result);
        verify(inscricaoDAOMock, times(1)).deleteInscricao(1L);
    }

    @Test
    public void testUpdateInscricao() {
        when(inscricaoDAOMock.updateInscricao(inscricao)).thenReturn(true);

        boolean result = inscricaoDAOMock.updateInscricao(inscricao);

        assertTrue(result);
        verify(inscricaoDAOMock, times(1)).updateInscricao(inscricao);
    }

    @Test
    public void testFindByUsuarioId() {
        List<Inscricao> inscricoes = Arrays.asList(
                new Inscricao(usuarioMock, eventoMock, null, null, trilhaMock)
        );

        when(inscricaoDAOMock.findByUsuarioId(1L)).thenReturn(inscricoes);

        List<Inscricao> result = inscricaoDAOMock.findByUsuarioId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuarioMock, result.get(0).getUsuario());
        verify(inscricaoDAOMock, times(1)).findByUsuarioId(1L);
    }

    @Test
    public void testUsuarioJaInscritoNoEvento() {
        when(inscricaoDAOMock.usuarioJaInscritoNoEvento(1L, 2L)).thenReturn(true);

        boolean result = inscricaoDAOMock.usuarioJaInscritoNoEvento(1L, 2L);

        assertTrue(result);
        verify(inscricaoDAOMock, times(1)).usuarioJaInscritoNoEvento(1L, 2L);
    }

    @Test
    public void testInscricaoJaExiste() {
        when(inscricaoDAOMock.inscricaoJaExiste(1L, 2L, 3L, 4L, 5L)).thenReturn(true);

        boolean result = inscricaoDAOMock.inscricaoJaExiste(1L, 2L, 3L, 4L, 5L);

        assertTrue(result);
        verify(inscricaoDAOMock, times(1)).inscricaoJaExiste(1L, 2L, 3L, 4L, 5L);
    }
}
