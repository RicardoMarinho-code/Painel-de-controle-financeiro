package meudb.top.financeiro.DAO;

import meudb.top.financeiro.MODEL.Ativo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AtivoDAO {
    private final InterfaceConexao conexao;

    public AtivoDAO(InterfaceConexao conexao) {
        this.conexao = conexao;
    }

    public void adicionarAtivo(Ativo ativo) {
        String sql = "INSERT INTO Ativo (id_ativo, descricao, valor, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, ativo.getDescricao());
            stmt.setBigDecimal(3, ativo.getValor());
            stmt.setString(4, ativo.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar ativo: " + e.getMessage());
        }
    }

    public List<Ativo> listarAtivosPorUsuario(String idUsuario) {
        List<Ativo> lista = new ArrayList<>();
        String sql = "SELECT id_ativo, descricao, valor, id_usuario FROM Ativo WHERE id_usuario = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ativo ativo = new Ativo(
                            rs.getString("id_ativo"),
                            rs.getString("descricao"),
                            rs.getBigDecimal("valor"),
                            rs.getString("id_usuario")
                    );
                    lista.add(ativo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar ativos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarAtivo(Ativo ativo) {
        String sql = "UPDATE Ativo SET descricao = ?, valor = ? WHERE id_ativo = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ativo.getDescricao());
            stmt.setBigDecimal(2, ativo.getValor());
            stmt.setString(3, ativo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar ativo: " + e.getMessage());
        }
    }

    public void deletarAtivo(String idAtivo) {
        String sql = "DELETE FROM Ativo WHERE id_ativo = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idAtivo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar ativo: " + e.getMessage());
        }
    }
}