package controllers;

import models.SubEvento;
import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.SubEventoDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubEventoControllerTest {
    private SubEventoController subEventoController;
    private SubEventoDAO subEventoDAO;

    @BeforeEach
    void setUp() {
        subEventoDAO = mock(SubEventoDAO.class);
        subEventoController = new SubEventoController();
        subEventoController.subEventoDAO = subEventoDAO;
    }

    @Test
    void adicionarSubEvento_comDadosValidos() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");

        when(subEventoDAO.insertSubEvento(subEvento)).thenReturn(true);

        boolean resultado = subEventoController.adicionarSubEvento(subEvento);
        assertTrue(resultado);
        verify(subEventoDAO, times(1)).insertSubEvento(subEvento);
    }

    @Test
    void adicionarSubEvento_comDadosInvalidos_lancaExcecao() {
        SubEvento subEvento = new SubEvento();

        Exception exception = assertThrows(RuntimeException.class, () ->
            subEventoController.adicionarSubEvento(subEvento)
        );
        assertEquals("Nome do subevento é obrigatório", exception.getMessage());
    }
}