package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import models.Evento;

class PersistenceEventoTest {
    private PersistenceEvento persistenceEvento;
    ManipuladorArquivos mockManipuladorArquivos = mock(ManipuladorArquivos.class);

    @BeforeEach
    public void setUp() {
        persistenceEvento = new PersistenceEvento();
        persistenceEvento.setManipulador(mockManipuladorArquivos);
    }

    @Test
    void testEventoToCSV() {
        String linhaEsperada = "1,Conferência,Auditório,2024-10-21 10:00,Descrição da conferência";
        Evento mockEvento = mock(Evento.class);

        when(mockEvento.getId()).thenReturn(1);
        when(mockEvento.getTitulo()).thenReturn("Conferência");
        when(mockEvento.getLocal()).thenReturn("Auditório");
        when(mockEvento.getHorario()).thenReturn("2024-10-21 10:00");
        when(mockEvento.getDescricao()).thenReturn("Descrição da conferência");

        assertEquals(linhaEsperada, persistenceEvento.eventoToCSV(mockEvento));
    }

    @Test
    void testGetTodos() {
        when(mockManipuladorArquivos.lerLinhaArquivo())
            .thenReturn("id,titulo,local,horario,descricao")
            .thenReturn("1,Workshop,Sala A,2024-11-15 14:00,Um workshop sobre tecnologia")
            .thenReturn("2,Seminário,Sala B,2024-12-01 09:00,Seminário de final de ano")
            .thenReturn(null); // Indica o fim do arquivo

        ArrayList<Evento> eventos = persistenceEvento.getTodos();

        assertEquals(2, eventos.size());
        assertEquals("Workshop", eventos.get(0).getTitulo());
        assertEquals("Seminário", eventos.get(1).getTitulo());
        assertEquals("Sala A", eventos.get(0).getLocal());
        assertEquals("Sala B", eventos.get(1).getLocal());
    }

    @Test
    void testAdd() {
        Evento evento = new Evento(1, "Seminário", "Sala A", "2024-12-01 09:00", "Seminário sobre inovação");
        String linhaEsperada = "1,Seminário,Sala A,2024-12-01 09:00,Seminário sobre inovação";

        persistenceEvento.add(evento);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita();
        verify(mockManipuladorArquivos).escreverNoArquivoPorUltimo(linhaEsperada);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }

    @Test
    void testDeleteEvento() {
        PersistenceEvento persistenceEventoSpy = spy(new PersistenceEvento());
        persistenceEventoSpy.setManipulador(mockManipuladorArquivos);
        Evento evento1 = new Evento(1, "Workshop", "Sala A", "2024-11-15 14:00", "Workshop sobre tecnologia");
        Evento evento2 = new Evento(2, "Seminário", "Sala B", "2024-12-01 09:00", "Seminário de final de ano");
        Evento evento3 = new Evento(3, "Palestra", "Sala C", "2024-12-10 15:00", "Palestra motivacional");

        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);
        eventos.add(evento3);

        doReturn(eventos).when(persistenceEventoSpy).getTodos();

        // Espera-se que a escrita no arquivo contenha os eventos restantes
        String linhaEvento2 = "2,Seminário,Sala B,2024-12-01 09:00,Seminário de final de ano";
        String linhaEvento3 = "3,Palestra,Sala C,2024-12-10 15:00,Palestra motivacional";

        persistenceEventoSpy.delete(evento1);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita(1);
        verify(mockManipuladorArquivos).escreverNoArquivo("id,titulo,local,horario,descricao");
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaEvento2);
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaEvento3);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }

    @Test
    void testUpdateEvento() {
        Evento eventoAntigo = new Evento(1, "Workshop", "Sala A", "2024-11-15 14:00", "Workshop sobre tecnologia");
        Evento eventoNovo = new Evento(1, "Conferência", "Auditório", "2024-12-01 10:00", "Conferência anual");
        Evento evento2 = new Evento(2, "Seminário", "Sala B", "2024-12-01 09:00", "Seminário de final de ano");

        PersistenceEvento persistenceEventoSpy = spy(new PersistenceEvento());
        persistenceEventoSpy.setManipulador(mockManipuladorArquivos);

        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(eventoAntigo);
        eventos.add(evento2);

        doReturn(eventos).when(persistenceEventoSpy).getTodos();

        // A linha esperada para o arquivo após a atualização
        String linhaEventoAtualizado = persistenceEventoSpy.eventoToCSV(eventoNovo);
        String linhaEvento2 = persistenceEventoSpy.eventoToCSV(evento2);

        persistenceEventoSpy.update(eventoAntigo, eventoNovo);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita(1);
        verify(mockManipuladorArquivos).escreverNoArquivo("id,titulo,local,horario,descricao");
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaEventoAtualizado);
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaEvento2);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }
}
