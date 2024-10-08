package Persistence;

import java.io.*;

public class ManipuladorArquivos {

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
            System.out.println("O arquivo não foi encontrado para leitura");
            e.printStackTrace();
        }
    }
    
    public String lerLinhaArquivo() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void fecharArquivoParaLeitura() {
        try {
            if (bufferedReader != null) bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void abrirArquivoParaEscrita() {
        try {
            arquivo = new File(path);
            //Se deixar true ele vai escrever no final do arquivo
            fileWriter = new FileWriter(arquivo, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo para escrita: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void abrirArquivoParaEscrita(int inteiro) {
        //Esse daqui vai reescrever o arquivo quando for escrever
        try {
            arquivo = new File(path);
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo para escrita: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void escreverNoArquivoPorUltimo(String conteudo) {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.write(conteudo);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Esse método abaixo reescreve as linhas
    public void escreverNoArquivo(String conteudo) {
        try {
            bufferedWriter.write(conteudo);
            bufferedWriter.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fecharArquivoEscrita() {
        try {
            if (bufferedWriter != null) bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
