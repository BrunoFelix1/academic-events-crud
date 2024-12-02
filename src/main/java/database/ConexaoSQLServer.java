package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLServer {
        private static String url = "jdbc:sqlserver://192.168.0.124:1433;databaseName=academic;encrypt=true;trustServerCertificate=true";
        //private String url = "jdbc:sqlserver://localhost:1433;databaseName=academic;encrypt=false";
        private static String usuario = "sa";
        private static String senha = "1234";

        public static Connection  Conectar(){
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");
            return conexao;

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        }
    }

