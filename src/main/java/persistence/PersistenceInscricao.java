package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConexaoSQLServer;
import interfaces.IPersistenciaControlador;
import models.Inscricao;
import models.Usuario;



public class PersistenceInscricao implements IPersistenciaControlador<Inscricao> {
    private static final String SQL_GET_ALL = "SELECT idUsuario, idEvento, idSubevento,idSecao, idTrilha from inscricao";
    private static final String SQL_INSERT = "INSERT INTO Inscricao (id_usuario, id_evento, id_subevento, id_secao, id_trilha) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Inscricao WHERE id_usuario = ? AND id_evento = ? AND id_subevento = ? AND id_secao = ? AND id_trilha = ?";
    private static final String SQL_UPDATE = "UPDATE Inscricao SET id_usuario = ?, id_evento = ?, id_subevento = ?, id_secao = ?, id_trilha = ? WHERE id_usuario = ? AND id_evento = ? AND id_subevento = ? AND id_secao = ? AND id_trilha = ?";
    private static final String SQL_GET_BY_ID = "SELECT * FROM Inscricao WHERE id_usuario = ? AND id_evento = ?";

    private Inscricao mapearResultSet(ResultSet inscricao){
        Inscricao inscricaoMapeada = new Inscricao();
        try {
            inscricaoMapeada.setIdUsuario(inscricao.getInt("idUsuario"));
            inscricaoMapeada.setIdEvento(inscricao.getInt("idEvento"));
            inscricaoMapeada.setIdSubEvento(inscricao.getInt("idSubEvento"));
            inscricaoMapeada.setIdSecao(inscricao.getInt("idSecao"));
            inscricaoMapeada.setIdTrilha(inscricao.getInt("idTrilha"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inscricaoMapeada;
    }

    //Retorna uma lista de todas as Inscricoes no momento
    public ArrayList<Inscricao> getTodos() {
        ArrayList<Inscricao> inscricoes = new ArrayList<>();

        Connection conexao = ConexaoSQLServer.Conectar();
        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
            Inscricao novaInscricao = mapearResultSet(rs);
            inscricoes.add(novaInscricao);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as inscrições: " + e.getMessage(), e);
        }
        return inscricoes;
    }


    @Override
    public void add(Inscricao inscricao) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {

            stmt.setInt(1, inscricao.getIdUsuario());
            stmt.setInt(2, inscricao.getIdEvento());
            stmt.setInt(3, inscricao.getIdSubEvento());
            stmt.setInt(4, inscricao.getIdSecao());
            stmt.setInt(5, inscricao.getIdTrilha());
            stmt.executeUpdate();

            System.out.println("Inscrição adicionada com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar inscrição: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Inscricao inscricao) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, inscricao.getIdUsuario());
            stmt.setInt(2, inscricao.getIdEvento());
            stmt.setInt(3, inscricao.getIdSubEvento());
            stmt.setInt(4, inscricao.getIdSecao());
            stmt.setInt(5, inscricao.getIdTrilha());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inscrição deletada com sucesso!");
            } else {
                System.out.println("Nenhuma inscrição encontrada com os dados especificados.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar inscrição: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Inscricao inscricaoAntiga, Inscricao inscricaoNova) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {

            stmt.setInt(1, inscricaoNova.getIdUsuario());
            stmt.setInt(2, inscricaoNova.getIdEvento());
            stmt.setInt(3, inscricaoNova.getIdSubEvento());
            stmt.setInt(4, inscricaoNova.getIdSecao());
            stmt.setInt(5, inscricaoNova.getIdTrilha());
            stmt.setInt(6, inscricaoAntiga.getIdUsuario());
            stmt.setInt(7, inscricaoAntiga.getIdEvento());
            stmt.setInt(8, inscricaoAntiga.getIdSubEvento());
            stmt.setInt(9, inscricaoAntiga.getIdSecao());
            stmt.setInt(10, inscricaoAntiga.getIdTrilha());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Inscrição atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma inscrição encontrada com os dados especificados.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar inscrição: " + e.getMessage(), e);
        }
    }

    @Override
    public Inscricao getPorId(int id) {
        // Nao sei oq isso faz
        return null;
    }

    public Inscricao getPorId(int idUsuario, int idEvento) {
        try (Connection conexao = ConexaoSQLServer.Conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_GET_BY_ID)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idEvento);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return mapearResultSet(resultSet);
            } else {
                System.out.println("Nenhuma inscrição encontrada com os dados especificados.");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar inscrição por ID: " + e.getMessage(), e);
        }
    }

        //Usar apenas na persistência da inscrição
    public Inscricao getPorIdInscricao(int idUsuario, int idEvento, int idSubEvento, int idSecao, int idTrilha){
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao i : inscricoes) {
            if (idUsuario == i.getIdUsuario() &&
                idEvento == i.getIdEvento() &&
                idSubEvento == i.getIdSecao() &&
                idSecao == i.getIdSecao() &&
                idTrilha == i.getIdTrilha()) {
                return i;
            }
        }
        return null; // Caso não encontre o ID da Inscrição
    }

    public Inscricao getPorIdInscricaoEvento(int idUsuario, int idEvento){
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao i : inscricoes) {
            if (idUsuario == i.getIdUsuario() &&
                    idEvento == i.getIdEvento() ) {
                return i;
            }
        }
        return null; // Caso não encontre o ID da Inscrição
    }
    public Inscricao getPorIdInscricaoTrilha(int idUsuario, int idTrilha){
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao i : inscricoes) {
            if (idUsuario == i.getIdUsuario() &&
                    idTrilha == i.getIdTrilha() ) {
                return i;
            }
        }
        return null; // Caso não encontre o ID da Inscrição
    }


    // Método para verificar se o usuário está inscrito em uma trilha
    public boolean estaInscritoEmTrilha(int idUsuario, int idTrilha) {
        ArrayList<Inscricao> inscricoes = getTodos();
        for (Inscricao inscricao : inscricoes) {
            if (inscricao.getIdUsuario() == idUsuario && inscricao.getIdTrilha() == idTrilha) {
                return true;
            }
        }
        return false;
    }
}
