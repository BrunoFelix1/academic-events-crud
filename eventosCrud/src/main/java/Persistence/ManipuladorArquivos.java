package Persistence;

import java.io.*;

public class ManipuladorArquivos {

    private String path;
    private File arquivo;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    //CONSTRUTOR DA CLASSE
    public ManipuladorArquivos(String path) {
        this.path = path;
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
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo para escrita: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void escreverNoArquivo(String conteudo) {
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

    public void fecharArquivoEscrita() {
        try {
            if (bufferedWriter != null) bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
