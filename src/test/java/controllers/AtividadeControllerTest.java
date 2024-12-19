package controllers;

import models.Atividade;
import models.Trilha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.AtividadeDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AtividadeControllerTest {
    private AtividadeController atividadeController;
    private AtividadeDAO atividadeDAO;

    @BeforeEach
    void setUp() {
        atividadeDAO = mock(AtividadeDAO.class);
        atividadeController = new AtividadeController();
        atividadeController.atividadeDAO = atividadeDAO;
    }

    @Test
    void adicionarAtividade_comDadosValidos() {
        Atividade atividade = new Atividade();
        atividade.setTipoDeAtividade("Palestra");
        atividade.setAutor("Autor Exemplo");
        atividade.setTrilha(new Trilha());

        when(atividadeDAO.insertAtividade(atividade)).thenReturn(true);

        boolean resultado = atividadeController.adicionarAtividade(atividade);
        assertTrue(resultado);
        verify(atividadeDAO, times(1)).insertAtividade(atividade);
    }

    @Test
    void adicionarAtividade_comDadosInvalidos_lancaExcecao() {
        Atividade atividade = new Atividade();

        Exception exception = assertThrows(RuntimeException.class, () ->
            atividadeController.adicionarAtividade(atividade)
        );
        assertEquals("Tipo de atividade é obrigatório", exception.getMessage());
    }
}