package controllers;

import models.SubEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import persistence.PersistenceSubEvento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubeventoControllerTest {

    @Mock
    private PersistenceSubEvento persistenciaMockSubEvento;

    @InjectMocks
    private SubeventoController subeventoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeListarSubeventos() {
        SubEvento subEvento1 = new SubEvento(1,1,"teste","UPE","18:00","teste");
        SubEvento subEvento2 = new SubEvento(2,1,"teste","UPE","18:00","teste");
        List<SubEvento> subeventosMock = Arrays.asList(subEvento1, subEvento2);

        when(persistenciaMockSubEvento.getTodos()).thenReturn(new ArrayList<>(subeventosMock));

        List<SubEvento> subeventos = subeventoController.listar();

        assertEquals(2, subeventos.size(), "O número de subeventos retornados não é o esperado.");
        assertEquals("teste", subeventos.get(0).getTitulo(), "O nome do primeiro subevento não é o esperado.");
        assertEquals("teste", subeventos.get(1).getTitulo(), "O nome do segundo subevento não é o esperado.");

        verify(persistenciaMockSubEvento, times(1)).getTodos();
    }

    @Test
    void testeCadastrarSubevento() {
        SubEvento subEvento1 = new SubEvento(1,1,"teste","UPE","18:00","teste");
        when(persistenciaMockSubEvento.getTodos()).thenReturn(new ArrayList<>(Arrays.asList(subEvento1)));

        SubEvento novoSubEvento = new SubEvento(2,1,"teste","UPE","18:00","teste");

        doAnswer(invocation -> null).when(persistenciaMockSubEvento).add(novoSubEvento);

        SubEvento resultado = subeventoController.cadastrar(novoSubEvento);

        verify(persistenciaMockSubEvento, times(1)).add(novoSubEvento);
        assertEquals(2, resultado.getId(), "O ID do novo subevento não é o esperado.");
    }

    @Test
    void testeAtualizarSubevento() {
        SubEvento subEventoExistente = new SubEvento(1,1,"teste","UPE","18:00","teste");
        when(persistenciaMockSubEvento.getPorId(1)).thenReturn(subEventoExistente);

        SubEvento subEventoAtualizado = new SubEvento(1,1,"teste","UPE","19:00","teste");

        doAnswer(invocation -> null).when(persistenciaMockSubEvento).update(subEventoExistente, subEventoAtualizado);

        SubEvento resultado = subeventoController.atualizar(subEventoExistente, subEventoAtualizado);

        verify(persistenciaMockSubEvento, times(1)).update(subEventoExistente, subEventoAtualizado);
        assertEquals("teste", resultado.getTitulo(), "O subevento não foi atualizado corretamente.");
    }

    @Test
    void testeDeletarSubeventoPorId() {
        SubEvento subEventoExistente = new SubEvento(1,1,"teste","UPE","18:00","teste");
        when(persistenciaMockSubEvento.getPorId(1)).thenReturn(subEventoExistente);

        doAnswer(invocation -> null).when(persistenciaMockSubEvento).delete(subEventoExistente);

        boolean resultado = subeventoController.deletar(1);

        verify(persistenciaMockSubEvento, times(1)).delete(subEventoExistente);
        assertTrue(resultado, "A deleção não foi bem-sucedida.");
    }

    @Test
    void testeDeletarSubeventoNaoEncontrado() {
        when(persistenciaMockSubEvento.getPorId(1)).thenReturn(null);

        boolean resultado = subeventoController.deletar(1);

        verify(persistenciaMockSubEvento, never()).delete(any(SubEvento.class));
        assertFalse(resultado, "A deleção não deveria ter ocorrido.");
    }

    @Test
    void testeDeletarSubeventoPorObjeto() {
        SubEvento subEvento = new SubEvento(1,1,"teste","UPE","18:00","teste");

        doAnswer(invocation -> null).when(persistenciaMockSubEvento).delete(subEvento);

        boolean resultado = subeventoController.deletar(subEvento);

        verify(persistenciaMockSubEvento, times(1)).delete(subEvento);
        assertTrue(resultado, "A deleção não foi bem-sucedida.");
    }

    @Test
    void testeDeletarSubeventoPorObjetoNulo() {
        boolean resultado = subeventoController.deletar((SubEvento) null);

        verify(persistenciaMockSubEvento, never()).delete(any());
        assertFalse(resultado, "A deleção não deveria ter ocorrido.");
    }

    @Test
    void testeBuscarPorId() {
        SubEvento subEvento = new SubEvento(1,1,"teste","UPE","18:00","teste");

        when(persistenciaMockSubEvento.getPorId(1)).thenReturn(subEvento);

        SubEvento resultado = subeventoController.buscarPorId(1);

        assertNotNull(resultado, "O subevento retornado é nulo.");
        assertEquals(1, resultado.getId(), "O ID do subevento não é o esperado.");
        assertEquals("teste", resultado.getTitulo(), "O nome do subevento não é o esperado.");
    }
}
