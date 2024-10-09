package persistence;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManipuladorArquivos {

    private static final Logger logger = Logger.getLogger(ManipuladorArquivos.class.getName());

    private String path;
    private File arquivo;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    
    public FileReader getFileReader() {
        return fileReader;
    }
    
    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
    
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
    
    public FileWriter getFileWriter() {
        return fileWriter;
    }
    
    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }
    
    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
    
    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }
    
    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }
    //CONSTRUTORES DA CLASSE
    public ManipuladorArquivos(String path) {
        this.path = path;
        this.arquivo = null;
        this.fileReader = null;
        this.bufferedReader = null;
        this.fileWriter = null;
        this.bufferedWriter = null;
    }

    public ManipuladorArquivos(){
        this.arquivo = null;
        this.fileReader = null;
        this.bufferedReader = null;
        this.fileWriter = null;
        this.bufferedWriter = null;
    }


    //ACESSORES
    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

    //ABAIXO: FUNÇÕES DE ABERTURA/FECHAMENTO E ESCRITA/LEITURA DE ARQUIVOS
    public void abrirArquivoParaLeitura() {
        try {
            arquivo = new File(path);
            fileReader = new FileReader(arquivo);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, e, () -> "Arquivo não encontrado: " + path);

        }
    }
    
    public String lerLinhaArquivo() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao ler o arquivo: " + path);
            return null;
        }
    }

    public boolean fecharArquivoParaLeitura() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
                return true;
            }
            return false;   
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao fechar o arquivo de leitura: " + path);
            return false;
        }
    }

    public boolean abrirArquivoParaEscrita() {
        try {
            arquivo = new File(path);
            //Se deixar true ele vai escrever no final do arquivo
            fileWriter = new FileWriter(arquivo, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao abrir o arquivo para escrita: " + path);
            return false;
        }
    }

    public void abrirArquivoParaEscrita(int inteiro) {
        //Esse daqui vai reescrever o arquivo quando for escrever
        try {
            arquivo = new File(path);
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao abrir o arquivo para escrita: " + path);
        }
    }

    public void escreverNoArquivoPorUltimo(String conteudo) {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.write(conteudo);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao escrever no arquivo: " + path);
        }
    }

    //Esse método abaixo reescreve as linhas
    public void escreverNoArquivo(String conteudo) {
        try {
            bufferedWriter.write(conteudo);
            bufferedWriter.newLine(); 
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao escrever no arquivo: " + path);

        }
    }

    public boolean fecharArquivoEscrita() {
        try {
            if (bufferedWriter != null){
                bufferedWriter.close();
                return true;
            } 
            return false;
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Erro ao fechar o arquivo de escrita: " + path);
            return false;
        }
    }
}
