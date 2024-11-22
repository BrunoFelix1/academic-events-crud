package persistence;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.SubEvento;

import java.sql.*;
import java.util.ArrayList;

public class PersistenceSubEvento implements IPersistenciaControlador<SubEvento> {

    // SQL Consultas
    private static final String SQL_GET_ALL = "SELECT id, idEvento, titulo, local, horario, descricao FROM SubEvento";
    private static final String SQL_INSERT = "INSERT INTO SubEvento (idEvento, titulo, local, horario, descricao) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM SubEvento WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE SubEvento SET idEvento = ?, titulo = ?, local = ?, horario = ?, descricao = ? WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM SubEvento WHERE id = ?";
    private static final String SQL_GET_BY_NAME = "SELECT * FROM SubEvento WHERE titulo = ?";

    // Conversão de ResultSet para Objeto SubEvento
    private SubEvento mapResultSetToSubEvento(ResultSet resultSet) throws SQLException {
        SubEvento subEvento = new SubEvento();
        subEvento.setId(resultSet.getInt("id"));
        subEvento.setIdEvento(resultSet.getInt("idEvento"));
        subEvento.setTitulo(resultSet.getString("titulo"));
        subEvento.setLocal(resultSet.getString("local"));
        subEvento.setHorario(resultSet.getString("horario"));
        subEvento.setDescricao(resultSet.getString("descricao"));
        return subEvento;
    }

    @Override
    public ArrayList<SubEvento> getTodos() {
        ArrayList<SubEvento> subEventos = new ArrayList<>();
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                subEventos.add(mapResultSetToSubEvento(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os subeventos: " + e.getMessage(), e);
        }
        return subEventos;
    }

    @Override
    public SubEvento add(SubEvento subEvento) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setInt(1, subEvento.getIdEvento());
            stmt.setString(2, subEvento.getTitulo());
            stmt.setString(3, subEvento.getLocal());
            stmt.setString(4, subEvento.getHorario());
            stmt.setString(5, subEvento.getDescricao());
            stmt.executeUpdate();

            System.out.println("Subevento adicionado com sucesso!");
            return subEvento;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar subevento: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(SubEvento subEvento) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, subEvento.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Subevento deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum subevento encontrado com o ID especificado.");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar subevento: " + e.getMessage(), e);
        }
    }

    @Override
    public SubEvento update(SubEvento subEventoAntigo, SubEvento subEventoNovo) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setInt(1, subEventoNovo.getIdEvento());
            stmt.setString(2, subEventoNovo.getTitulo());
            stmt.setString(3, subEventoNovo.getLocal());
            stmt.setString(4, subEventoNovo.getHorario());
            stmt.setString(5, subEventoNovo.getDescricao());
            stmt.setInt(6, subEventoAntigo.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Subevento atualizado com sucesso!");
                return subEventoNovo;
            } else {
                System.out.println("Nenhum subevento encontrado com o ID especificado.");
                return subEventoAntigo;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar subevento: " + e.getMessage(), e);
        }
    }

    public SubEvento getPorId(int id) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSubEvento(resultSet);
            } else {
                System.out.println("Nenhum subevento encontrado com o ID especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar subevento por ID: " + e.getMessage(), e);
        }
    }

    // Não sei oq essa função faz
    @Override
    public SubEvento getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        return null;
    }

    public SubEvento getPorNome(String nome) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_NAME)) {

            stmt.setString(1, nome);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSubEvento(resultSet);
            } else {
                System.out.println("Nenhum subevento encontrado com o nome especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar subevento por nome: " + e.getMessage(), e);
        }
    }
}
