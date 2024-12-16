package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import interfaces.IPersistenciaControlador;
import models.Atividade;

class AtividadeControllerTest {

    @Mock
    private IPersistenciaControlador<Atividade> persistenciaMock;

    @InjectMocks
    private AtividadeController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        // Dados simulados
        Atividade atividade1 = new Atividade(1, "Atividade 1", "Descrição 1", "Meio ambiente", 5);
        Atividade atividade2 = new Atividade(2, "Atividade 2", "Descrição 2", "High tecs", 2);
        List<Atividade> atividades = Arrays.asList(atividade1, atividade2);

        // Simulando comportamento do mock
        when(persistenciaMock.getTodos()).thenReturn(new ArrayList<>(atividades));

        // Executando o método a ser testado
        List<Atividade> resultado = controller.listar();

        // Verificando resultado
        assertEquals(2, resultado.size());
        assertEquals("Atividade 1", resultado.get(0).getTipoSubmissao());
        assertEquals("Atividade 2", resultado.get(1).getTipoSubmissao());
    }


    @Test
    void testDeletar_Falha() {
        // Simulando comportamento do mock para retornar null quando a atividade não é encontrada
        when(persistenciaMock.getPorId(1)).thenReturn(null);

        // Executando o método a ser testado
        boolean resultado = controller.deletar(1);

        // Verificando se a deleção falhou
        assertFalse(resultado);
        verify(persistenciaMock, never()).delete(any(Atividade.class)); // delete não deve ser chamado
    }
}
