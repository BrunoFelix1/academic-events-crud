package persistence;

import java.sql.*;
import java.util.ArrayList;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Atividade;


public class PersistenceAtividade implements IPersistenciaControlador<Atividade> {
    //Instanciando manipulador e adicionando o path da tabela de atividades
    private String pathAtividade = "src\\main\\resources\\database\\Atividades.txt";
    private ManipuladorArquivos manipulador = new ManipuladorArquivos(pathAtividade);

    //Retorna um objeto atividade em formato de linha String
    private String atividadeToCSV(Atividade atividade){
        String linha = atividade.getId() + "," + atividade.getTipoSubmissao()+ "," + atividade.getAutor() + "," +
        atividade.getResumo()+ "," + atividade.getIdTrilha();
        return linha;
    }

    //Retorna uma lista de todos os atividades no momento
    public ArrayList<Atividade> getTodos() {

        String sql = "Select id, tiposubmissao, autor, resumo, idtrilha FROM atividade";
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        try {
            Connection conexao = database.ConexaoSQLServer.Conectar();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()){
                Atividade atividade = new Atividade();
                atividade.setId(resultado.getInt("id"));
                atividade.setTipoSubmissao(resultado.getString("tiposubmissao"));
                atividade.setAutor(resultado.getString("autor"));
                atividade.setResumo(resultado.getString("resumo"));
                atividade.setIdTrilha(resultado.getInt("idTrilha"));
                atividades.add(atividade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return atividades;

    }

    //Adiciona um atividade na tabela
    public void add(Atividade atividade) {
        String sql = "Insert into atividade(tiposubmissao, autor, resumo, idtrilha) values(?,?,?,?)";
        try {
            Connection conexao = ConexaoSQLServer.Conectar();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1,atividade.getTipoSubmissao());
            ps.setString(2,atividade.getAutor());
            ps.setString(3, atividade.getResumo());
            ps.setInt(4, atividade.getIdTrilha());
            int linhasAfetadas = ps.executeUpdate();
            System.out.println("Inserção realizada com sucesso! Linhas afetadas: " + linhasAfetadas);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete (Atividade atividade) {
        String sql = "Delete from atividade where id = "+ atividade.getId();
        try {
            Connection conexao = ConexaoSQLServer.Conectar();
            PreparedStatement ps = conexao.prepareStatement(sql);
            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas>0){
                System.out.println("Deletado com sucesso!");
            }
            else {
                System.out.println("Nenhum registro encontrado com esse id.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Atividade atividadeAntiga, Atividade atividadeNova) {
        String sql = "UPDATE Atividade SET tipoSubmissao = ?, autor = ?, resumo = ?, idTrilha = ? WHERE id = ?";

        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Define os novos valores
            stmt.setString(1, atividadeNova.getTipoSubmissao());
            stmt.setString(2, atividadeNova.getAutor());
            stmt.setString(3, atividadeNova.getResumo());
            stmt.setInt(4, atividadeNova.getIdTrilha());

            // Condição para identificar o registro antigo
            stmt.setInt(5, atividadeAntiga.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Atividade atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma atividade encontrada com o ID especificado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar atividade: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Atividade getPorId(int id) {
        String sql = "Select * from atividade where id="+id;
        try {
            Connection conexao = database.ConexaoSQLServer.Conectar();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            Atividade atividade = new Atividade();
            atividade.setId(resultado.getInt("id"));
            atividade.setResumo(resultado.getString("resumo"));
            atividade.setAutor(resultado.getString("autor"));
            atividade.setIdTrilha(resultado.getInt("idTrilha"));
            atividade.setTipoSubmissao(resultado.getString("tiposubmissao"));
            return atividade;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Usar apenas na persistência da inscrição
    public Atividade getPorIdInscricao(int id, int id2, int id3, int id4, int id5){
        Atividade Atividade = new Atividade();
        return Atividade;
    }
   
}
