package controllers;

import models.Secao;
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

class SecaoControllerTest {

    @Mock
    private IPersistenciaControlador<Secao> persistenciaMockSecao;

    @InjectMocks
    private SecaoController secaoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeListarSecoes() {
        Secao secao1 = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        Secao secao2 = new Secao(2, 2, 2, "UPE", "16:00", "Oficina");
        List<Secao> secoesMock = Arrays.asList(secao1, secao2);

        when(persistenciaMockSecao.getTodos()).thenReturn(new ArrayList<>(secoesMock));

        List<Secao> secoes = secaoController.listar();

        assertEquals(2, secoes.size(), "O número de seções retornadas não é o esperado.");
        assertEquals("Palestra", secoes.get(0).getNome(), "O nome da primeira seção não é o esperado.");
        assertEquals("Oficina", secoes.get(1).getNome(), "O nome da segunda seção não é o esperado.");


        verify(persistenciaMockSecao, times(1)).getTodos();
    }

    @Test
    void testeCadastrarSecao() {
        Secao secao1 = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        when(persistenciaMockSecao.getTodos()).thenReturn(new ArrayList<>(Arrays.asList(secao1)));

        Secao novaSecao = new Secao(2, 2, 2, "Polo", "15:00", "Show");

        doAnswer(invocation -> {
            return null;
        }).when(persistenciaMockSecao).add(novaSecao);

        secaoController.cadastrar(novaSecao);

        verify(persistenciaMockSecao, times(1)).add(novaSecao);
        assertEquals(2, novaSecao.getId(), "O ID da nova seção não é o esperado.");
    }

    @Test
    void testeAtualizarSecao() {
        Secao secaoExistente = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        when(persistenciaMockSecao.getPorId(1)).thenReturn(secaoExistente);

        Secao secaoAtualizada = new Secao(1, 1, 1, "Armazém", "19:00", "Palestra");

        when(persistenciaMockSecao.update(eq(secaoExistente), eq(secaoAtualizada))).thenReturn(secaoAtualizada);

        Secao resultado = secaoController.atualizar(secaoExistente, secaoAtualizada);

        verify(persistenciaMockSecao, times(1)).update(eq(secaoExistente), eq(secaoAtualizada));
        assertEquals(secaoAtualizada, resultado, "A seção não foi atualizada corretamente.");
    }

    @Test
    void testeDeletarSecao() {
        Secao secaoExistente = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        when(persistenciaMockSecao.getPorId(1)).thenReturn(secaoExistente);

        when(persistenciaMockSecao.delete(secaoExistente)).thenReturn(true);

        boolean resultado = secaoController.deletar(1);

        verify(persistenciaMockSecao, times(1)).delete(secaoExistente);
        assertTrue(resultado, "A deleção não foi bem-sucedida.");
    }

    @Test
    void testeDeletarSecaoNaoEncontrada() {
        when(persistenciaMockSecao.getPorId(1)).thenReturn(null);

        // Testando o método deletar()
        boolean resultado = secaoController.deletar(1);

        // Verificando se o método delete não foi chamado
        verify(persistenciaMockSecao, never()).delete(any(Secao.class));
        assertFalse(resultado, "A deleção não deveria ter ocorrido.");
    }
}
