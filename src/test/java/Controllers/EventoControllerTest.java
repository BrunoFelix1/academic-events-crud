package Controllers;

import controllers.EventoController;
import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import interfaces.IPersistenciaControlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoControllerTest {

    @Mock
    private IPersistenciaControlador<Evento> persistenciaMockEvento;

    @InjectMocks
    private EventoController eventoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeListarEventos() {
        // Mockando a lista de eventos retornada pela persistência
        Evento evento1 = new Evento(1, "Super", "Armazem", "15:30", "Super palestra");
        Evento evento2 = new Evento(2, "JCE", "Armazem", "15:10", "JCE palestra");
        List<Evento> eventosMock = Arrays.asList(evento1, evento2);

        // Simulando o retorno do método getTodos (usando um cast explícito para ArrayList)
        when(persistenciaMockEvento.getTodos()).thenReturn(new ArrayList<>(eventosMock));

        // Testando o método listar()
        List<Evento> eventos = eventoController.listar();
        assertEquals(2, eventos.size());
        assertEquals("Super", eventos.get(0).getTitulo());
        assertEquals("JCE", eventos.get(1).getTitulo());

        // Verificando se o método getTodos foi chamado
        verify(persistenciaMockEvento, times(1)).getTodos();
    }


    @Test
    public void testeCadastrarEvento() {
        // Mockando a lista de eventos existente
        Evento evento1 = new Evento(1, "Super", "Armazem", "15:30", "Super palestra");
        when(persistenciaMockEvento.getTodos()).thenReturn(new ArrayList<>(Arrays.asList(evento1)));

        // Criando um novo evento para cadastro
        Evento novoEvento = new Evento(0, "JCE", "Armazem", "15:10", "JCE palestra");

        // Simulando o comportamento de persistência no método add
        doNothing().when(persistenciaMockEvento).add(novoEvento);

        // Testando o método cadastrar()
        eventoController.cadastrar(novoEvento);

        // Verificando se o método add foi chamado com o novo evento
        verify(persistenciaMockEvento, times(1)).add(novoEvento);
        assertEquals(2, novoEvento.getId()); // O id deve ser 2 porque já existe um evento com id 1
    }

    @Test
    public void testeAtualizarEvento() {
        // Mockando um evento existente
        Evento eventoExistente = new Evento(1, "Super", "Armazem", "15:30", "Super palestra");
        when(persistenciaMockEvento.getPorId(1)).thenReturn(eventoExistente);

        // Criando um novo evento com informações atualizadas
        Evento eventoAtualizado = new Evento(1, "Super Updated", "Armazem 2", "16:00", "Palestra Atualizada");

        // Simulando o comportamento de persistência no método update
        doNothing().when(persistenciaMockEvento).update(eventoExistente, eventoAtualizado);

        // Testando o método atualizar()
        eventoController.atualizar(eventoAtualizado);

        // Verificando se o método update foi chamado com os eventos corretos
        verify(persistenciaMockEvento, times(1)).update(eventoExistente, eventoAtualizado);
    }

    @Test
    public void testeDeletarEvento() {
        // Mockando um evento existente
        Evento eventoExistente = new Evento(1, "Super", "Armazem", "15:30", "Super palestra");
        when(persistenciaMockEvento.getPorId(1)).thenReturn(eventoExistente);

        // Simulando o comportamento de persistência no método delete
        doNothing().when(persistenciaMockEvento).delete(eventoExistente);

        // Testando o método deletar()
        boolean resultado = eventoController.deletar(1);

        // Verificando se o método delete foi chamado
        verify(persistenciaMockEvento, times(1)).delete(eventoExistente);
        assertTrue(resultado);
    }

    @Test
    public void testeDeletarEventoNaoEncontrado() {
        // Mockando um retorno nulo para um evento que não existe
        when(persistenciaMockEvento.getPorId(1)).thenReturn(null);

        // Testando o método deletar()
        boolean resultado = eventoController.deletar(1);

        // Verificando se o método delete não foi chamado
        verify(persistenciaMockEvento, never()).delete(any(Evento.class));
        assertFalse(resultado);
    }
}
