/*  package controllers;

import interfaces.IPersistenciaControlador;
import models.Atividade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void testCadastrar() {
        // Dados simulados
        Atividade novaAtividade = new Atividade(0, "Palestra", "Descrição Nova", "vida nova", 2);
        ArrayList<Atividade> atividadesExistentes = new ArrayList<>(Arrays.asList(
                new Atividade(1, "Atividade 1", "Descrição 1", "Resumo 1", 1)
        ));

        // Simulando comportamento do mock
        when(persistenciaMock.getTodos()).thenReturn(atividadesExistentes);
        doNothing().when(persistenciaMock).add(any(Atividade.class));

        // Executando o método a ser testado
        controller.cadastrar(novaAtividade);

        // Verificando se o ID foi setado corretamente e se o método "add" foi chamado
        assertEquals(2, novaAtividade.getId()); // Já que existe 1 atividade, a próxima teria o ID 2
        verify(persistenciaMock, times(1)).add(novaAtividade);
    }

    @Test
    void testAtualizar() {
        // Dados simulados
        Atividade atividadeAntiga = new Atividade(1, "Atividade 1", "Descrição Antiga", "Antigo", 2);
        Atividade atividadeAtualizada = new Atividade(1, "Atividade 1 Atualizada", "Descrição Atualizada", "Atualizado", 3);

        // Simulando comportamento do mock
        when(persistenciaMock.getPorId(1)).thenReturn(atividadeAntiga);
        doNothing().when(persistenciaMock).update(atividadeAntiga, atividadeAtualizada);

        // Executando o método a ser testado
        controller.atualizar(atividadeAtualizada);

        // Verificando se o método "update" foi chamado corretamente
        verify(persistenciaMock, times(1)).update(atividadeAntiga, atividadeAtualizada);
    }

    @Test
    void testDeletar_Sucesso() {
        // Dados simulados
        Atividade atividade = new Atividade(1, "Atividade 1", "Descrição 1", "Resumo 1", 1);

        // Simulando comportamento do mock
        when(persistenciaMock.getPorId(1)).thenReturn(atividade);
        doNothing().when(persistenciaMock).delete(atividade);

        // Executando o método a ser testado
        boolean resultado = controller.deletar(1);

        // Verificando se a deleção foi realizada com sucesso
        assertTrue(resultado);
        verify(persistenciaMock, times(1)).delete(atividade);
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
  */