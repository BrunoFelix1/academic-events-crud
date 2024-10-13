package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import models.Usuario;


public class PersistenceUsuarioTest {
    private PersistenceUsuario persistenceUsuario;
    ManipuladorArquivos mockManipuladorArquivos = mock(ManipuladorArquivos.class);

    @BeforeEach
    public void setUp(){
        persistenceUsuario = new PersistenceUsuario();
        persistenceUsuario.setManipulador(mockManipuladorArquivos);
    }

    @Test
    public void testUsuarioToCSV(){
        String linhaEsperada = "1,222.222.222-02,Bruno,13,UPE,COMUM,bruno,123";
        Usuario mockUsuario = mock(Usuario.class);

        when(mockUsuario.getId()).thenReturn(1);
        when(mockUsuario.getCPF()).thenReturn("222.222.222-02");
        when(mockUsuario.getNome()).thenReturn("Bruno");
        when(mockUsuario.getIdade()).thenReturn(13);
        when(mockUsuario.getInstituicao()).thenReturn("UPE");
        when(mockUsuario.getTipoDeUsuario()).thenReturn("COMUM");
        when(mockUsuario.getLogin()).thenReturn("bruno");
        when(mockUsuario.getSenha()).thenReturn("123");

        assertEquals(linhaEsperada, persistenceUsuario.usuarioToCSV(mockUsuario));
    }

    @Test
    public void testGetTodos(){
        when(mockManipuladorArquivos.lerLinhaArquivo()).thenReturn("id,cpf,nome,idade,instituicao,tipo_usuario,login,senha")
        .thenReturn("1,12345678901,Hugo,25,Universidade,Aluno,hugo123,senha123")
        .thenReturn("2,09876543210,Maria,30,Instituto,Professor,maria123,senha456")
        .thenReturn(null); // Retorna null para indicar o fim do arquivo

        ArrayList<Usuario> usuarios = persistenceUsuario.getTodos();
        
        assertEquals(2, usuarios.size());
        assertEquals("Hugo", usuarios.get(0).getNome());
        assertEquals("Maria", usuarios.get(1).getNome());
        assertEquals(25, usuarios.get(0).getIdade());
        assertEquals(30, usuarios.get(1).getIdade());
    }

    @Test
    public void testAdd() {
        Usuario usuario = new Usuario(1, "140.401.604-01", "nome", 21, "UPE", "COMUM", "nome", "123"); 
        String linhaEsperada = "1,140.401.604-01,nome,21,UPE,COMUM,nome,123"; 

        persistenceUsuario.add(usuario);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita();
        verify(mockManipuladorArquivos).escreverNoArquivoPorUltimo(linhaEsperada);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }

    @Test
    public void testDeleteUsuario() {
        PersistenceUsuario persistenceUsuarioSpy = spy(new PersistenceUsuario());
        persistenceUsuarioSpy.setManipulador(mockManipuladorArquivos);
        Usuario usuario1 = new Usuario(1, "140.401.604-01", "nome1", 21, "UPE", "COMUM", "nome1", "123");
        Usuario usuario2 = new Usuario(2, "140.401.604-02", "nome2", 22, "UPE", "COMUM", "nome2", "456");
        Usuario usuario3 = new Usuario(3, "140.401.604-03", "nome3", 23, "UPE", "COMUM", "nome3", "789");

        ArrayList<Usuario> usuarios = new ArrayList<>(); 
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        doReturn(usuarios).when(persistenceUsuarioSpy).getTodos(); 
        

        // Espera-se que a escrita no arquivo contenha os usuários restantes
        String linhaUsuario2 = "2,140.401.604-02,nome2,22,UPE,COMUM,nome2,456";
        String linhaUsuario3 = "3,140.401.604-03,nome3,23,UPE,COMUM,nome3,789";

        persistenceUsuarioSpy.delete(usuario1);

        verify(mockManipuladorArquivos).abrirArquivoParaEscrita(1);
        verify(mockManipuladorArquivos).escreverNoArquivo("id,cpf,nome,idade,instituicao,tipoDeUsuario");
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaUsuario2);
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaUsuario3);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }

@Test
    public void testUpdateUsuario() {
        Usuario usuario1Antigo = new Usuario(1, "140.401.604-01", "nomeAntigo", 21, "UPE", "COMUM", "nomeAntigo", "123");
        Usuario usuario1Novo = new Usuario(1, "140.401.604-01", "nomeNovo", 22, "UPE", "COMUM", "nomeNovo", "456");
        Usuario usuario2 = new Usuario(2, "140.401.604-02", "nome2", 22, "UPE", "COMUM", "nome2", "456");
        PersistenceUsuario persistenceUsuarioSpy = spy(new PersistenceUsuario());
        persistenceUsuarioSpy.setManipulador(mockManipuladorArquivos);

        ArrayList<Usuario> usuarios = new ArrayList<>(); 
        usuarios.add(usuario1Antigo);
        usuarios.add(usuario2);

        doReturn(usuarios).when(persistenceUsuarioSpy).getTodos(); 

        // A linha esperada para o arquivo após a atualização
        String linhaUsuarioAntigoAtualizado = persistenceUsuarioSpy.usuarioToCSV(usuario1Novo);
        String linhaUsuario2 = persistenceUsuarioSpy.usuarioToCSV(usuario2);

        persistenceUsuarioSpy.update(usuario1Antigo, usuario1Novo);

        // Assert
        verify(mockManipuladorArquivos).abrirArquivoParaEscrita(1);
        verify(mockManipuladorArquivos).escreverNoArquivo("id,cpf,nome,idade,instituicao,tipoDeUsuario");
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaUsuarioAntigoAtualizado);
        verify(mockManipuladorArquivos).escreverNoArquivo(linhaUsuario2);
        verify(mockManipuladorArquivos).fecharArquivoEscrita();
    }
}
