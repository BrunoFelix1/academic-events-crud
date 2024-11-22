package persistence;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Atividade;

import java.sql.*;
import java.util.ArrayList;

public class PersistenceAtividade implements IPersistenciaControlador<Atividade> {

    // SQL Consultas
    private static final String SQL_GET_ALL = "SELECT id, tipoSubmissao, autor, resumo, idTrilha FROM Atividade";
    private static final String SQL_INSERT = "INSERT INTO Atividade (tipoSubmissao, autor, resumo, idTrilha) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Atividade WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Atividade SET tipoSubmissao = ?, autor = ?, resumo = ?, idTrilha = ? WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM Atividade WHERE id = ?";
    private static final String SQL_GET_BY_AUTOR = "SELECT * FROM Atividade WHERE autor = ?";

    // Conversão de ResultSet para Objeto Atividade
    private Atividade mapResultSetToAtividade(ResultSet resultSet) throws SQLException {
        Atividade atividade = new Atividade();
        atividade.setId(resultSet.getInt("id"));
        atividade.setTipoSubmissao(resultSet.getString("tipoSubmissao"));
        atividade.setAutor(resultSet.getString("autor"));
        atividade.setResumo(resultSet.getString("resumo"));
        atividade.setIdTrilha(resultSet.getInt("idTrilha"));
        return atividade;
    }

    @Override
    public ArrayList<Atividade> getTodos() {
        ArrayList<Atividade> atividades = new ArrayList<>();
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                atividades.add(mapResultSetToAtividade(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as atividades: " + e.getMessage(), e);
        }
        return atividades;
    }

    @Override
    public Atividade add(Atividade atividade) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, atividade.getTipoSubmissao());
            stmt.setString(2, atividade.getAutor());
            stmt.setString(3, atividade.getResumo());
            stmt.setInt(4, atividade.getIdTrilha());
            stmt.executeUpdate();

            System.out.println("Atividade adicionada com sucesso!");
            return atividade;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar atividade: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Atividade atividade) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, atividade.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Atividade deletada com sucesso!");
                return true;
            } else {
                System.out.println("Nenhuma atividade encontrada com o ID especificado.");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar atividade: " + e.getMessage(), e);
        }
    }

    @Override
    public Atividade update(Atividade atividadeAntiga, Atividade atividadeNova) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setString(1, atividadeNova.getTipoSubmissao());
            stmt.setString(2, atividadeNova.getAutor());
            stmt.setString(3, atividadeNova.getResumo());
            stmt.setInt(4, atividadeNova.getIdTrilha());
            stmt.setInt(5, atividadeAntiga.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Atividade atualizada com sucesso!");
                return atividadeNova;
            } else {
                System.out.println("Nenhuma atividade encontrada com o ID especificado.");
                return atividadeAntiga;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar atividade: " + e.getMessage(), e);
        }
    }

    public Atividade getPorId(int id) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAtividade(resultSet);
            } else {
                System.out.println("Nenhuma atividade encontrada com o ID especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar atividade por ID: " + e.getMessage(), e);
        }
    }

    // Ainda nao sei oq faz
    @Override
    public Atividade getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        return null;
    }

    public Atividade getPorAutor(String autor) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_AUTOR)) {

            stmt.setString(1, autor);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAtividade(resultSet);
            } else {
                System.out.println("Nenhuma atividade encontrada com o autor especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar atividade por autor: " + e.getMessage(), e);
        }
    }
}
