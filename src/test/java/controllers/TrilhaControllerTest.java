package controllers;

import models.Trilha;
import models.Secao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.TrilhaDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrilhaControllerTest {
    private TrilhaController trilhaController;
    private TrilhaDAO trilhaDAO;

    @BeforeEach
    void setUp() {
        trilhaDAO = mock(TrilhaDAO.class);
        trilhaController = new TrilhaController();
        trilhaController.trilhaDAO = trilhaDAO;
    }

    @Test
    void adicionarTrilha_comDadosValidos() {
        Secao secao = new Secao();
        Trilha trilha = new Trilha(secao, "Trilha Teste");

        when(trilhaDAO.insertTrilha(trilha)).thenReturn(true);

        boolean resultado = trilhaController.adicionarTrilha(trilha);
        assertTrue(resultado);
        verify(trilhaDAO, times(1)).insertTrilha(trilha);
    }

    @Test
    void adicionarTrilha_comDadosInvalidos_lancaExcecao() {
        Trilha trilha = new Trilha();

        Exception exception = assertThrows(RuntimeException.class, () ->
            trilhaController.adicionarTrilha(trilha)
        );
        assertEquals("Nome da trilha é obrigatório", exception.getMessage());
    }
}