package meudb.top.financeiro.DAO;

import meudb.top.financeiro.MODEL.Passivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PassivoDAO {
    private final InterfaceConexao conexao;

    public PassivoDAO(InterfaceConexao conexao) {
        this.conexao = conexao;
    }

    public void adicionarPassivo(Passivo passivo) {
        String sql = "INSERT INTO Passivo (id_passivo, descricao, valor, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, passivo.getDescricao());
            stmt.setBigDecimal(3, passivo.getValor());
            stmt.setString(4, passivo.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar passivo: " + e.getMessage());
        }
    }

    public List<Passivo> listarPassivosPorUsuario(String idUsuario) {
        List<Passivo> lista = new ArrayList<>();
        String sql = "SELECT id_passivo, descricao, valor, id_usuario FROM Passivo WHERE id_usuario = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Passivo passivo = new Passivo(
                            rs.getString("id_passivo"),
                            rs.getString("descricao"),
                            rs.getBigDecimal("valor"),
                            rs.getString("id_usuario")
                    );
                    lista.add(passivo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar passivos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarPassivo(Passivo passivo) {
        String sql = "UPDATE Passivo SET descricao = ?, valor = ? WHERE id_passivo = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, passivo.getDescricao());
            stmt.setBigDecimal(2, passivo.getValor());
            stmt.setString(3, passivo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar passivo: " + e.getMessage());
        }
    }

    public void deletarPassivo(String idPassivo) {
        String sql = "DELETE FROM Passivo WHERE id_passivo = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPassivo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar passivo: " + e.getMessage());
        }
    }
}