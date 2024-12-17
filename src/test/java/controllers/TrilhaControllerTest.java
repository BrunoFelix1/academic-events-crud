package controllers;

import models.Trilha;
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

class TrilhaControllerTest {

    @Mock
    private IPersistenciaControlador<Trilha> persistenciaMockTrilha;

    @InjectMocks
    private TrilhaController trilhaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeListarTrilhas() {
        Trilha trilha1 = new Trilha(1, 1, "teste1");
        Trilha trilha2 = new Trilha(2, 2, "teste2");
        List<Trilha> trilhasMock = Arrays.asList(trilha1, trilha2);

        when(persistenciaMockTrilha.getTodos()).thenReturn(new ArrayList<>(trilhasMock));

        List<Trilha> trilhas = trilhaController.listar();

        assertEquals(2, trilhas.size(), "O número de trilhas retornadas não é o esperado.");
        assertEquals("teste1", trilhas.get(0).getNome(), "O nome da primeira trilha não é o esperado.");
        assertEquals("teste2", trilhas.get(1).getNome(), "O nome da segunda trilha não é o esperado.");

        verify(persistenciaMockTrilha, times(1)).getTodos();
    }

    @Test
    void testeCadastrarTrilha() {
        Trilha trilha1 = new Trilha(1, 1, "teste1");
        when(persistenciaMockTrilha.getTodos()).thenReturn(new ArrayList<>(Arrays.asList(trilha1)));

        Trilha novaTrilha = new Trilha(2, 2, "teste2");

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            Trilha trilha = (Trilha) args[0];
            return trilha;
        }).when(persistenciaMockTrilha).add(novaTrilha);

        Trilha resultado = trilhaController.cadastrar(novaTrilha);

        verify(persistenciaMockTrilha, times(1)).add(novaTrilha);
        assertEquals(2, novaTrilha.getId(), "O ID da nova trilha não é o esperado.");
        assertEquals(novaTrilha, resultado, "A trilha retornada não é a esperada.");
    }


    @Test
    void testeAtualizarTrilhaComDuasInstancias() {
        Trilha trilhaExistente = new Trilha(1, 1, "teste1");
        when(persistenciaMockTrilha.getPorId(1)).thenReturn(trilhaExistente);

        Trilha trilhaAtualizada = new Trilha(1, 1, "teste2");

        Trilha resultado = trilhaController.atualizar(trilhaExistente, trilhaAtualizada);

        verify(persistenciaMockTrilha, times(1)).update(eq(trilhaExistente), eq(trilhaAtualizada));
        assertEquals(trilhaAtualizada, resultado, "A trilha não foi atualizada corretamente.");
    }

    @Test
    void testeAtualizarTrilhaComUmaInstancia() {
        Trilha trilhaExistente = new Trilha(1, 1, "teste1");
        when(persistenciaMockTrilha.getPorId(1)).thenReturn(trilhaExistente);

        Trilha trilhaAtualizada = new Trilha(1, 1, "teste2");

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            Trilha trilhaOriginal = (Trilha) args[0];
            Trilha trilhaNova = (Trilha) args[1];

            assertEquals(trilhaExistente, trilhaOriginal, "A trilha original passada não é a esperada.");
            assertEquals(trilhaAtualizada, trilhaNova, "A trilha atualizada passada não é a esperada.");
            return null;
        }).when(persistenciaMockTrilha).update(eq(trilhaExistente), eq(trilhaAtualizada));

        Trilha resultado = trilhaController.atualizar(trilhaAtualizada);

        verify(persistenciaMockTrilha, times(1)).update(eq(trilhaExistente), eq(trilhaAtualizada));
        assertEquals(trilhaAtualizada, resultado, "A trilha não foi atualizada corretamente.");
    }


    @Test
    void testeDeletarTrilhaPorId() {
        Trilha trilhaExistente = new Trilha(1, 1, "teste1");
        when(persistenciaMockTrilha.getPorId(1)).thenReturn(trilhaExistente);

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            Trilha trilha = (Trilha) args[0];
            assertEquals(trilhaExistente, trilha, "A trilha passada para o método delete não é a esperada.");
            return null;
        }).when(persistenciaMockTrilha).delete(trilhaExistente);

        boolean resultado = trilhaController.deletar(1);

        verify(persistenciaMockTrilha, times(1)).delete(trilhaExistente);
        assertTrue(resultado, "A deleção não foi bem-sucedida.");
    }


    @Test
    void testeDeletarTrilhaNaoEncontrada() {
        when(persistenciaMockTrilha.getPorId(1)).thenReturn(null);


        boolean resultado = trilhaController.deletar(1);

        verify(persistenciaMockTrilha, never()).delete(any(Trilha.class));
        assertFalse(resultado, "A deleção não deveria ter ocorrido.");
    }
}
