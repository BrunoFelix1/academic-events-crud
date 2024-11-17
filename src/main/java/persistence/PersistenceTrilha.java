package persistence;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Trilha;

import java.sql.*;
import java.util.ArrayList;

public class PersistenceTrilha implements IPersistenciaControlador<Trilha> {

    // SQL Consultas
    private static final String SQL_GET_ALL = "SELECT id, secao_id, nome FROM Trilha";
    private static final String SQL_INSERT = "INSERT INTO Trilha (secao_id, nome) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Trilha WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Trilha SET secao_id = ?, nome = ? WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM Trilha WHERE id = ?";
    private static final String SQL_GET_BY_NOME = "SELECT * FROM Trilha WHERE nome = ?";

    // Conversão de ResultSet para Objeto Trilha
    private Trilha mapResultSetToTrilha(ResultSet resultSet) throws SQLException {
        Trilha trilha = new Trilha();
        trilha.setId(resultSet.getInt("id"));
        trilha.setIdSecao(resultSet.getInt("secao_id"));
        trilha.setNome(resultSet.getString("nome"));
        return trilha;
    }

    @Override
    public ArrayList<Trilha> getTodos() {
        ArrayList<Trilha> trilhas = new ArrayList<>();
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                trilhas.add(mapResultSetToTrilha(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as trilhas: " + e.getMessage(), e);
        }
        return trilhas;
    }

    @Override
    public void add(Trilha trilha) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setInt(1, trilha.getIdSecao());
            stmt.setString(2, trilha.getNome());
            stmt.executeUpdate();

            System.out.println("Trilha adicionada com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar trilha: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Trilha trilha) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, trilha.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Trilha deletada com sucesso!");
            } else {
                System.out.println("Nenhuma trilha encontrada com o ID especificado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar trilha: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Trilha trilhaAntiga, Trilha trilhaNova) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setInt(1, trilhaNova.getIdSecao());
            stmt.setString(2, trilhaNova.getNome());
            stmt.setInt(3, trilhaAntiga.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Trilha atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma trilha encontrada com o ID especificado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar trilha: " + e.getMessage(), e);
        }
    }

    public Trilha getPorId(int id) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTrilha(resultSet);
            } else {
                System.out.println("Nenhuma trilha encontrada com o ID especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar trilha por ID: " + e.getMessage(), e);
        }
    }

    //Isso ainda tá aqui vey ;-;
    @Override
    public Trilha getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        return null;
    }

    public Trilha getPorNome(String nome) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_NOME)) {

            stmt.setString(1, nome);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTrilha(resultSet);
            } else {
                System.out.println("Nenhuma trilha encontrada com o nome especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar trilha por nome: " + e.getMessage(), e);
        }
    }
}
