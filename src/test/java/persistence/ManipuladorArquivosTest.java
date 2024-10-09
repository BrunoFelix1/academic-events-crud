package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ManipuladorArquivosTest {

    private ManipuladorArquivos manipuladorArquivos;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        manipuladorArquivos = new ManipuladorArquivos();
        tempFile = File.createTempFile("tempFile", ".txt");
        manipuladorArquivos.setPath(tempFile.getAbsolutePath());
    }

    @Test
    public void testSetAndGetPath() {
        manipuladorArquivos.setPath("novoCaminho.txt");
        assertEquals("novoCaminho.txt", manipuladorArquivos.getPath());
    }

    @Test
    public void testAbrirArquivoParaLeitura() throws IOException {
        manipuladorArquivos.abrirArquivoParaLeitura();
    
        assertNotNull(manipuladorArquivos.getArquivo());
        assertNotNull(manipuladorArquivos.getFileReader());
        assertNotNull(manipuladorArquivos.getBufferedReader());
    
        tempFile.delete();
    }


    @Test
    public void testLerLinhaArquivo() throws IOException{
        FileWriter filewriter = new FileWriter(tempFile);
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        String linhaTeste = "Teste";
        bufferedWriter.write(linhaTeste);
        bufferedWriter.close();
        manipuladorArquivos.abrirArquivoParaLeitura();
        assertEquals(linhaTeste, manipuladorArquivos.lerLinhaArquivo());
    }

    @Test
    public void testFecharArquivoParaLeitura() throws IOException{
        manipuladorArquivos.abrirArquivoParaLeitura();
        assertEquals(true, manipuladorArquivos.fecharArquivoParaLeitura());
    }

    @Test
    public void testAbrirArquivoParaEscrita(){
        assertEquals(true, manipuladorArquivos.abrirArquivoParaEscrita());
    }

    @Test
    public void testEscreverNoArquivoPorUltimo(){
        String linhaTeste1 = "Teste 1";
        String linhaTeste2 = "Teste 2";
        manipuladorArquivos.abrirArquivoParaEscrita();
        manipuladorArquivos.escreverNoArquivoPorUltimo(linhaTeste1);
        manipuladorArquivos.escreverNoArquivoPorUltimo(linhaTeste2);
        manipuladorArquivos.fecharArquivoEscrita();
        manipuladorArquivos.abrirArquivoParaLeitura();
        assertTrue(manipuladorArquivos.lerLinhaArquivo().contains(linhaTeste1));
        assertTrue(manipuladorArquivos.lerLinhaArquivo().contains(linhaTeste2));
    }

    @Test
    public void testEscreverNoArquivo(){
        String linhaTeste = "Teste";
        manipuladorArquivos.abrirArquivoParaEscrita();
        manipuladorArquivos.escreverNoArquivoPorUltimo(linhaTeste);
        manipuladorArquivos.fecharArquivoEscrita();
        manipuladorArquivos.abrirArquivoParaLeitura();
        assertTrue(manipuladorArquivos.lerLinhaArquivo().contains(linhaTeste));
    }

    @Test
    public void testFecharArquivoEscrita(){
        manipuladorArquivos.abrirArquivoParaEscrita();
        assertEquals(true, manipuladorArquivos.fecharArquivoEscrita());
    }
}
