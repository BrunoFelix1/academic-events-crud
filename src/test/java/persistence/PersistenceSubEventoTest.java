package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import models.SubEvento;

public class PersistenceSubEventoTest {
    private PersistenceSubEvento persistenceSubEvento;
    ManipuladorArquivos mockManipuladorArquivos = mock(ManipuladorArquivos.class);

    @BeforeEach
    public void setUp() {
        persistenceSubEvento = new PersistenceSubEvento();
        persistenceSubEvento.setManipulador(mockManipuladorArquivos);
    }

    @Test
    public void testSubEventoToCSV() {
        String linhaEsperada = "1,101,Mini Workshop,Sala A,2024-11-15 14:00,Workshop introdutório";
        SubEvento mockSubEvento = mock(SubEvento.class);

        when(mockSubEvento.getId()).thenReturn(1);
        when(mockSubEvento.getIdEvento()).thenReturn(101);
        when(mockSubEvento.getTitulo()).thenReturn("Mini Workshop");
        when(mockSubEvento.getLocal()).thenReturn("Sala A");
        when(mockSubEvento.getHorario()).thenReturn("2024-11-15 14:00");
        when(mockSubEvento.getDescricao()).thenReturn("Workshop introdutório");

        assertEquals(linhaEsperada, persistenceSubEvento.SubEventoToCSV(mockSubEvento));
    }

    @Test
    public void testGetTodos() {
        when(mockManipuladorArquivos.lerLinhaArquivo())
            .thenReturn("id,evento_id,titulo,local,horario,descricao")
            .thenReturn("1,101,Sessão de Abertura,Auditório,2024-10-21 10:00,Sessão oficial de abertura")
            .thenReturn("2,102,Palestra,Sala B,2024-10-22 11:00,Palestra sobre novas tecnologias")
            .thenReturn(null); // Indica o fim do arquivo

        ArrayList<SubEvento> subEventos = persistenceSubEvento.getTodos();

        assertEquals(2, subEventos.size());
        assertEquals("Sessão de Abertura", subEventos.get(0).getTitulo());
        assertEquals("Palestra", subEventos.get(1).getTitulo());
        assertEquals(101, subEventos.get(0).getIdEvento());
        assertEquals(102, subEventos.get(1).getIdEvento());
    }

    @Test
    public void testAdd() {
        SubEvento subEvento = new SubEvento(1, 101, "Palestra", "Sala A", "2024-12-01 09:00", "Palestra sobre inovação");
        String linhaEsperada = "1,101,Palestra,Sala A,2024-12-01 09:00,Palestra sobre inovação";

        persistenceSubEvento.add(subEvento);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita();
        verify(mockManipuladorArquivos).escreverNoArquivoPorUltimo(linhaEsperada);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }

    @Test
    public void testDeleteSubEvento() {
        PersistenceSubEvento persistenceSubEventoSpy = spy(new PersistenceSubEvento());
        persistenceSubEventoSpy.setManipulador(mockManipuladorArquivos);
        SubEvento subEvento1 = new SubEvento(1, 101, "Sessão de Abertura", "Auditório", "2024-10-21 10:00", "Sessão oficial de abertura");
        SubEvento subEvento2 = new SubEvento(2, 102, "Palestra", "Sala B", "2024-10-22 11:00", "Palestra sobre novas tecnologias");
        SubEvento subEvento3 = new SubEvento(3, 103, "Mesa Redonda", "Sala C", "2024-10-23 16:00", "Discussão sobre IA");

        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos.add(subEvento1);
        subEventos.add(subEvento2);
        subEventos.add(subEvento3);

        doReturn(subEventos).when(persistenceSubEventoSpy).getTodos();

        // Espera-se que a escrita no arquivo contenha os subeventos restantes
        String linhaSubEvento2 = "2,102,Palestra,Sala B,2024-10-22 11:00,Palestra sobre novas tecnologias";
        String linhaSubEvento3 = "3,103,Mesa Redonda,Sala C,2024-10-23 16:00,Discussão sobre IA";

        persistenceSubEventoSpy.delete(subEvento1);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita(1);
        verify(mockManipuladorArquivos).escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaSubEvento2);
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaSubEvento3);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }

    @Test
    public void testUpdateSubEvento() {
        SubEvento subEventoAntigo = new SubEvento(1, 101, "Workshop", "Sala A", "2024-11-15 14:00", "Workshop sobre tecnologia");
        SubEvento subEventoNovo = new SubEvento(1, 101, "Mini Conferência", "Auditório", "2024-12-01 10:00", "Conferência anual");
        SubEvento subEvento2 = new SubEvento(2, 102, "Palestra", "Sala B", "2024-10-22 11:00", "Palestra sobre novas tecnologias");

        PersistenceSubEvento persistenceSubEventoSpy = spy(new PersistenceSubEvento());
        persistenceSubEventoSpy.setManipulador(mockManipuladorArquivos);

        ArrayList<SubEvento> subEventos = new ArrayList<>();
        subEventos.add(subEventoAntigo);
        subEventos.add(subEvento2);

        doReturn(subEventos).when(persistenceSubEventoSpy).getTodos();

        // A linha esperada para o arquivo após a atualização
        String linhaSubEventoAtualizado = persistenceSubEventoSpy.SubEventoToCSV(subEventoNovo);
        String linhaSubEvento2 = persistenceSubEventoSpy.SubEventoToCSV(subEvento2);

        persistenceSubEventoSpy.update(subEventoAntigo, subEventoNovo);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita(1);
        verify(mockManipuladorArquivos).escreverNoArquivo("id,evento_id,titulo,local,horario,descricao");
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaSubEventoAtualizado);
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaSubEvento2);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }
}
