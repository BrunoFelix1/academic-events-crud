package persistence;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class PersistenceUsuario implements IPersistenciaControlador<Usuario> {

    // Consultas SQL
    private static final String SQL_GET_ALL = "SELECT id, CPF, nome, idade, instituicao, tipoDeUsuario, login, senha FROM Usuario";
    private static final String SQL_INSERT = "INSERT INTO Usuario (CPF, nome, idade, instituicao, tipoDeUsuario, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Usuario WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Usuario SET CPF = ?, nome = ?, idade = ?, instituicao = ?, tipoDeUsuario = ?, login = ?, senha = ? WHERE id = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM Usuario WHERE id = ?";
    private static final String SQL_GET_BY_LOGIN = "SELECT * FROM Usuario WHERE login = ?";

    // Conversão de ResultSet para Objeto Usuario
    private Usuario mapResultSetToUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setCPF(resultSet.getString("CPF"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setIdade(resultSet.getInt("idade"));
        usuario.setInstituicao(resultSet.getString("instituicao"));
        usuario.setTipoDeUsuario(resultSet.getString("tipoDeUsuario"));
        usuario.setLogin(resultSet.getString("login"));
        usuario.setSenha(resultSet.getString("senha"));
        return usuario;
    }

    @Override
    public ArrayList<Usuario> getTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                usuarios.add(mapResultSetToUsuario(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os usuários: " + e.getMessage(), e);
        }
        return usuarios;
    }

    @Override
    public Usuario add(Usuario usuario) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, usuario.getCPF());
            stmt.setString(2, usuario.getNome());
            stmt.setInt(3, usuario.getIdade());
            stmt.setString(4, usuario.getInstituicao());
            stmt.setString(5, usuario.getTipoDeUsuario());
            stmt.setString(6, usuario.getLogin());
            stmt.setString(7, usuario.getSenha());
            stmt.executeUpdate();

            System.out.println("Usuário adicionado com sucesso!");
            return usuario;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Usuario usuario) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, usuario.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum usuário encontrado com o ID especificado.");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public Usuario update(Usuario usuarioAntigo, Usuario usuarioNovo) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setString(1, usuarioNovo.getCPF());
            stmt.setString(2, usuarioNovo.getNome());
            stmt.setInt(3, usuarioNovo.getIdade());
            stmt.setString(4, usuarioNovo.getInstituicao());
            stmt.setString(5, usuarioNovo.getTipoDeUsuario());
            stmt.setString(6, usuarioNovo.getLogin());
            stmt.setString(7, usuarioNovo.getSenha());
            stmt.setInt(8, usuarioAntigo.getId());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso!");
                return usuarioNovo;
            } else {
                System.out.println("Nenhum usuário encontrado com o ID especificado.");
                return usuarioAntigo;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    public Usuario getPorId(int id) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUsuario(resultSet);
            } else {
                System.out.println("Nenhum usuário encontrado com o ID especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage(), e);
        }
    }

    public Usuario getPorLogin(String login) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_LOGIN)) {

            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUsuario(resultSet);
            } else {
                System.out.println("Nenhum usuário encontrado com o login especificado.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por login: " + e.getMessage(), e);
        }
    }

    // Esta porr* nunca é  utilizada
    @Override
    public Usuario getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha) {
        return null;
    }
}
