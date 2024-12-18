package integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import models.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeSubEventoTest extends BaseIntegrationTest {

    @Test
    void addSubEventoTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        assertEquals(true, facade.adicionarSubEvento(subEvento));
        subEventosCriados.add(subEvento.getId()); // Rastrear o ID do subevento criado
    }

    @Test
    void listSubEventosTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento1 = new SubEvento(evento, "SubEvento Teste1", "Local B", "14:00", "Descrição SubEvento1");
        SubEvento subEvento2 = new SubEvento(evento, "SubEvento Teste2", "Local C", "15:00", "Descrição SubEvento2");

        facade.adicionarSubEvento(subEvento1);
        facade.adicionarSubEvento(subEvento2);

        // Rastrear os IDs dos subeventos criados
        subEventosCriados.add(subEvento1.getId());
        subEventosCriados.add(subEvento2.getId());

        List<SubEvento> subEventos = facade.listarSubEventos();
        assertNotNull(subEventos);
        assertTrue(subEventos.size() >= 2);
    }

    @Test
    void updateSubEventoTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEventoAntigo = new SubEvento(evento, "SubEvento Teste1", "Local B", "14:00", "Descrição SubEvento1");
        facade.adicionarSubEvento(subEventoAntigo);
        Long subEventoAntigoId = subEventoAntigo.getId();
        subEventosCriados.add(subEventoAntigoId); // Rastrear o ID do subevento criado

        SubEvento subEventoNovo = new SubEvento(evento, "SubEvento Teste2", "Local C", "15:00", "Descrição SubEvento2");
        assertTrue(facade.atualizarSubEvento(subEventoAntigoId, subEventoNovo));
    }

    @Test
    void deleteSubEventoTest() {
        Evento evento = new Evento("Evento Teste", "Local A", "13:00", "Descrição Teste");
        facade.adicionarEvento(evento);
        eventosCriados.add(evento.getId());

        SubEvento subEvento = new SubEvento(evento, "SubEvento Teste", "Local B", "14:00", "Descrição SubEvento");
        facade.adicionarSubEvento(subEvento);
        Long subEventoId = subEvento.getId();
        subEventosCriados.add(subEventoId); // Rastrear o ID do subevento criado

        assertTrue(facade.deletarSubEvento(subEventoId));
    }
}
