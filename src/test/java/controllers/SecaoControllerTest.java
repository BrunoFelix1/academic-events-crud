package controllers;

import models.Secao;
import models.SubEvento;
import models.Evento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.SecaoDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecaoControllerTest {
    private SecaoController secaoController;
    private SecaoDAO secaoDAO;

    @BeforeEach
    void setUp() {
        secaoDAO = mock(SecaoDAO.class);
        secaoController = new SecaoController();
        secaoController.secaoDAO = secaoDAO;
    }

    @Test
    void adicionarSecao_comDadosValidos() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        Secao secao = new Secao(evento, subEvento, "Secao Teste", "Local C", "15:00");

        when(secaoDAO.insertSecao(secao)).thenReturn(true);

        boolean resultado = secaoController.adicionarSecao(secao);
        assertTrue(resultado);
        verify(secaoDAO, times(1)).insertSecao(secao);
    }

    @Test
    void adicionarSecao_comDadosInvalidos_lancaExcecao() {
        Secao secao = new Secao();

        Exception exception = assertThrows(RuntimeException.class, () ->
            secaoController.adicionarSecao(secao)
        );
        assertEquals("Nome da seção é obrigatório", exception.getMessage());
    }
}