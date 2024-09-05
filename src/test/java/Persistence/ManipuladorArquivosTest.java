package Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class ManipuladorArquivosTest {

    private ManipuladorArquivos manipuladorArquivos;
    private String testFilePath = "testFile.txt";

    @BeforeEach
    public void setUp() {
        manipuladorArquivos = new ManipuladorArquivos(testFilePath);
    }

    @Test
    public void testSetAndGetPath() {
        manipuladorArquivos.setPath("novoCaminho.txt");
        assertEquals("novoCaminho.txt", manipuladorArquivos.getPath());
    }

    @Test
    public void testAbrirArquivoParaLeituraArquivoNaoExistente() {
        manipuladorArquivos.setPath("arquivoInexistente.txt");
        manipuladorArquivos.abrirArquivoParaLeitura();
        assertFalse(new File("arquivoInexistente.txt").exists());
    }

    @Test
    public void testAbrirArquivoParaEscrita() throws IOException {
        manipuladorArquivos.abrirArquivoParaEscrita();
        File arquivo = new File(testFilePath);
        assertTrue(arquivo.exists());
        manipuladorArquivos.fecharArquivoEscrita();
    }

    @Test
    public void testEscreverNoArquivo() throws IOException {
        manipuladorArquivos.abrirArquivoParaEscrita();
        manipuladorArquivos.escreverNoArquivo("Linha de Teste");
        manipuladorArquivos.fecharArquivoEscrita();

        // Verifica se o conteúdo foi escrito no arquivo
        String conteudo = new String(Files.readAllBytes(Paths.get(testFilePath)));
        assertTrue(conteudo.contains("Linha de Teste"));
    }

    @Test
    public void testLerLinhaArquivo() throws IOException {
        manipuladorArquivos.abrirArquivoParaEscrita();
        manipuladorArquivos.escreverNoArquivo("Linha de Teste");
        manipuladorArquivos.fecharArquivoEscrita();

        manipuladorArquivos.abrirArquivoParaLeitura();
        String linha = manipuladorArquivos.lerLinhaArquivo();
        manipuladorArquivos.fecharArquivoParaLeitura();

        assertEquals("Linha de Teste", linha);
    }

    @Test
    public void testEscreverNoArquivoPorUltimo() throws IOException {
        manipuladorArquivos.abrirArquivoParaEscrita();
        manipuladorArquivos.escreverNoArquivo("Primeira Linha");
        manipuladorArquivos.fecharArquivoEscrita();

        manipuladorArquivos.abrirArquivoParaEscrita();
        manipuladorArquivos.escreverNoArquivoPorUltimo("Segunda Linha");
        manipuladorArquivos.fecharArquivoEscrita();

        String conteudo = new String(Files.readAllBytes(Paths.get(testFilePath)));
        assertTrue(conteudo.contains("Primeira Linha"));
        assertTrue(conteudo.contains("Segunda Linha"));
    }

    // Limpeza do arquivo após os testes
    @AfterEach
    public void tearDown() {
        File arquivo = new File(testFilePath);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }
}
