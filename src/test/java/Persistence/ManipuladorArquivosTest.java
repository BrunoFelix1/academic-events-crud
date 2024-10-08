package Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class ManipuladorArquivosTest {

    private ManipuladorArquivos manipuladorArquivos;
    private String mockFilePath = "mock/FileTest.txt";

    @BeforeAll
    public void setUp() {
        manipuladorArquivos = new ManipuladorArquivos(mockFilePath);
    }

    @Test
    public void testSetAndGetPath() {
        manipuladorArquivos.setPath("novoCaminho.txt");
        assertEquals("novoCaminho.txt", manipuladorArquivos.getPath());
    }

    @Test
    public void testAbrirArquivoParaLeitura(){
        manipuladorArquivos.abrirArquivoParaLeitura();
        assertNotEquals(null, manipuladorArquivos.getArquivo());
        assertNotEquals(null, manipuladorArquivos.getFileReader());
        assertNotEquals(null, manipuladorArquivos.getBufferedReader());
    }

    @Test
    public void testLerLinhaArquivo(){

    }

    @Test
    public void testFecharArquivoParaLeitura(){
        
    }

    @Test
    public void testAbrirArquivoParaEscrita(){

    }

    @Test
    public void testEscreverNoArquivoPorUltimo(){

    } 

    @Test
    public void testEscreverNoArquivo(){

    }

    @Test
    public void testFecharArquivoEscrita(){

    }
}
