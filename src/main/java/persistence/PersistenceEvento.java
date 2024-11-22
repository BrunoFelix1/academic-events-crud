package persistence;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Evento;

import java.sql.*;
import java.util.ArrayList;

public class PersistenceEvento implements IPersistenciaControlador<Evento> {

    // SQL Consultas
    private static final String SQL_GET_ALL = "SELECT id, titulo, local, horario, descricao FROM Evento";
    private static final String SQL_INSERT = "INSERT INTO Evento (titulo, local, horario, descricao) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Evento WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Evento SET titulo = ?, local = ?, horario = ?, descricao = ? WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM Evento WHERE id = ?";
    private static final String SQL_GET_BY_NAME = "SELECT * FROM Evento WHERE titulo = ?";

    // Conversão de ResultSet para Objeto Evento
    private Evento mapResultSetToEvento(ResultSet resultSet) throws SQLException {
        Evento evento = new Evento();
        evento.setId(resultSet.getInt("id"));
        evento.setTitulo(resultSet.getString("titulo"));
        evento.setLocal(resultSet.getString("local"));
        evento.setHorario(resultSet.getString("horario"));
        evento.setDescricao(resultSet.getString("descricao"));
        return evento;
    }

    @Override
    public ArrayList<Evento> getTodos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                eventos.add(mapResultSetToEvento(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os eventos: " + e.getMessage(), e);
        }
        return eventos;
    }

    @Override
    public Evento add(Evento evento) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, evento.getTitulo());
            stmt.setString(2, evento.getLocal());
            stmt.setString(3, evento.getHorario());
            stmt.setString(4, evento.getDescricao());
            stmt.executeUpdate();

            System.out.println("Evento adicionado com sucesso!");
            return evento;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar evento: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Evento evento) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, evento.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Evento deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum evento encontrado com o ID especificado.");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar evento: " + e.getMessage(), e);
        }
    }

    @Override
    public Evento update(Evento eventoAntigo, Evento eventoNovo) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setString(1, eventoNovo.getTitulo());
            stmt.setString(2, eventoNovo.getLocal());
            stmt.setString(3, eventoNovo.getHorario());
            stmt.setString(4, eventoNovo.getDescricao());
            stmt.setInt(5, eventoAntigo.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Evento atualizado com sucesso!");
                return eventoNovo;
            } else {
                System.out.println("Nenhum evento encontrado com o ID especificado.");
                return eventoAntigo;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar evento: " + e.getMessage(), e);
        }
    }

    public Evento getPorId(int id) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEvento(resultSet);
            } else {
                System.out.println("Nenhum evento encontrado com o ID especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar evento por ID: " + e.getMessage(), e);
        }
    }

    // SEM USO, PERGUNTAR SE É PRA EXCLUIR
    @Override
    public Evento getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        return null;
    }

    public Evento getPorNome(String nome) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_NAME)) {

            stmt.setString(1, nome);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEvento(resultSet);
            } else {
                System.out.println("Nenhum evento encontrado com o nome especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar evento por nome: " + e.getMessage(), e);
        }
    }
}
