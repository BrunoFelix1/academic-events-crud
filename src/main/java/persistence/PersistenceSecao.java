package persistence;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Secao;

import java.sql.*;
import java.util.ArrayList;

public class PersistenceSecao implements IPersistenciaControlador<Secao> {

    // SQL Consultas
    private static final String SQL_GET_ALL = "SELECT id, id_evento, id_subEvento, local, horario, nome FROM Secao";
    private static final String SQL_INSERT = "INSERT INTO Secao (id_evento, id_subEvento, local, horario, nome) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Secao WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Secao SET id_evento = ?, id_subEvento = ?, local = ?, horario = ?, nome = ? WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM Secao WHERE id = ?";
    private static final String SQL_GET_BY_NAME = "SELECT * FROM Secao WHERE nome = ?";

    // Conversão de ResultSet para Objeto Secao
    private Secao mapResultSetToSecao(ResultSet resultSet) throws SQLException {
        Secao secao = new Secao();
        secao.setId(resultSet.getInt("id"));
        secao.setId_evento(resultSet.getInt("id_evento"));
        secao.setId_subEvento(resultSet.getInt("id_subEvento"));
        secao.setLocal(resultSet.getString("local"));
        secao.setHorario(resultSet.getString("horario"));
        secao.setNome(resultSet.getString("nome"));
        return secao;
    }

    @Override
    public ArrayList<Secao> getTodos() {
        ArrayList<Secao> secoes = new ArrayList<>();
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                secoes.add(mapResultSetToSecao(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as seções: " + e.getMessage(), e);
        }
        return secoes;
    }

    @Override
    public void add(Secao secao) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setInt(1, secao.getId_evento());
            stmt.setInt(2, secao.getId_subEvento());
            stmt.setString(3, secao.getLocal());
            stmt.setString(4, secao.getHorario());
            stmt.setString(5, secao.getNome());
            stmt.executeUpdate();

            System.out.println("Seção adicionada com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar seção: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Secao secao) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, secao.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Seção deletada com sucesso!");
            } else {
                System.out.println("Nenhuma seção encontrada com o ID especificado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar seção: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Secao secaoAntiga, Secao secaoNova) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setInt(1, secaoNova.getId_evento());
            stmt.setInt(2, secaoNova.getId_subEvento());
            stmt.setString(3, secaoNova.getLocal());
            stmt.setString(4, secaoNova.getHorario());
            stmt.setString(5, secaoNova.getNome());
            stmt.setInt(6, secaoAntiga.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Seção atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma seção encontrada com o ID especificado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar seção: " + e.getMessage(), e);
        }
    }

    public Secao getPorId(int id) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSecao(resultSet);
            } else {
                System.out.println("Nenhuma seção encontrada com o ID especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar seção por ID: " + e.getMessage(), e);
        }
    }

    //Ainda nao sei oq isso faz
    @Override
    public Secao getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        return null;
    }

    public Secao getPorNome(String nome) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_NAME)) {

            stmt.setString(1, nome);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSecao(resultSet);
            } else {
                System.out.println("Nenhuma seção encontrada com o nome especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar seção por nome: " + e.getMessage(), e);
        }
    }
}
