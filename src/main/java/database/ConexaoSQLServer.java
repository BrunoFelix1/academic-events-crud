package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLServer {
        String url = "jdbc:sqlserver://192.168.0.124:1433;databaseName=academic;encrypt=true;trustServerCertificate=true";
        //private String url = "jdbc:sqlserver://localhost:1433;databaseName=academic;encrypt=false";
        private String usuario = "sa";
        private String senha = "1234";
        public void  Conectar(){
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");

            // Fechar a conexão ao final
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
        }
        }
    }

