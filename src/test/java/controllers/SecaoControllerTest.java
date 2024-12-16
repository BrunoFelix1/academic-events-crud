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
        // Mockando a lista de seções retornada pela persistência
        Secao secao1 = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        Secao secao2 = new Secao(2, 2, 2, "UPE", "16:00", "Oficina");
        List<Secao> secoesMock = Arrays.asList(secao1, secao2);

        // Simulando o retorno do método getTodos
        when(persistenciaMockSecao.getTodos()).thenReturn(new ArrayList<>(secoesMock));

        // Testando o método listar()
        List<Secao> secoes = secaoController.listar();

        // Verificando se as seções retornadas são as esperadas
        assertEquals(2, secoes.size(), "O número de seções retornadas não é o esperado.");
        assertEquals("Palestra", secoes.get(0).getNome(), "O nome da primeira seção não é o esperado.");
        assertEquals("Oficina", secoes.get(1).getNome(), "O nome da segunda seção não é o esperado.");

        // Verificando se o método getTodos foi chamado
        verify(persistenciaMockSecao, times(1)).getTodos();
    }

    @Test
    void testeCadastrarSecao() {
        // Mockando a lista de seções existente
        Secao secao1 = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        when(persistenciaMockSecao.getTodos()).thenReturn(new ArrayList<>(Arrays.asList(secao1)));

        // Criando uma nova seção para cadastro
        Secao novaSecao = new Secao(2, 2, 2, "Polo", "15:00", "Show");

        // Simulando o comportamento de persistência no método add
        doAnswer(invocation -> {
            // Lógica do que acontece quando o método add é chamado
            return null; // O método add não retorna nada, então podemos retornar null
        }).when(persistenciaMockSecao).add(novaSecao);

        // Testando o método cadastrar()
        secaoController.cadastrar(novaSecao);

        // Verificando se o método add foi chamado com a nova seção
        verify(persistenciaMockSecao, times(1)).add(novaSecao);
        assertEquals(2, novaSecao.getId(), "O ID da nova seção não é o esperado.");
    }

    @Test
    void testeAtualizarSecao() {
        // Mockando uma seção existente
        Secao secaoExistente = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        when(persistenciaMockSecao.getPorId(1)).thenReturn(secaoExistente);

        // Criando uma nova seção com informações atualizadas
        Secao secaoAtualizada = new Secao(1, 1, 1, "Armazém", "19:00", "Palestra");

        // Simulando o comportamento de persistência no método update
        when(persistenciaMockSecao.update(eq(secaoExistente), eq(secaoAtualizada))).thenReturn(secaoAtualizada);

        // Testando o método atualizar()
        Secao resultado = secaoController.atualizar(secaoExistente, secaoAtualizada);

        // Verificando se o método update foi chamado
        verify(persistenciaMockSecao, times(1)).update(eq(secaoExistente), eq(secaoAtualizada));
        assertEquals(secaoAtualizada, resultado, "A seção não foi atualizada corretamente.");
    }

    @Test
    void testeDeletarSecao() {
        // Mockando uma seção existente
        Secao secaoExistente = new Secao(1, 1, 1, "Armazém", "18:00", "Palestra");
        when(persistenciaMockSecao.getPorId(1)).thenReturn(secaoExistente);

        // Simulando o comportamento de persistência no método delete
        when(persistenciaMockSecao.delete(secaoExistente)).thenReturn(true);

        // Testando o método deletar()
        boolean resultado = secaoController.deletar(1);

        // Verificando se o método delete foi chamado
        verify(persistenciaMockSecao, times(1)).delete(secaoExistente);
        assertTrue(resultado, "A deleção não foi bem-sucedida.");
    }

    @Test
    void testeDeletarSecaoNaoEncontrada() {
        // Mockando um retorno nulo para uma seção que não existe
        when(persistenciaMockSecao.getPorId(1)).thenReturn(null);

        // Testando o método deletar()
        boolean resultado = secaoController.deletar(1);

        // Verificando se o método delete não foi chamado
        verify(persistenciaMockSecao, never()).delete(any(Secao.class));
        assertFalse(resultado, "A deleção não deveria ter ocorrido.");
    }
}
