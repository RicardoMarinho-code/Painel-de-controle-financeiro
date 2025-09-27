package com.meuprojeto.dao;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.modelo.Passivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PassivoDAO {

    // --- CREATE ---
    public void adicionarPassivo(Passivo passivo) {
        String sql = "INSERT INTO Passivo (id, descricao, valor, usuario_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (passivo.getId() == null) {
                passivo.setId(UUID.randomUUID().toString());
            }

            stmt.setString(1, passivo.getId());
            stmt.setString(2, passivo.getDescricao());
            stmt.setBigDecimal(3, passivo.getValor());
            stmt.setString(4, passivo.getUsuarioId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- READ ---
    public List<Passivo> listarPassivosPorUsuario(String usuarioId) {
        List<Passivo> passivos = new ArrayList<>();
        String sql = "SELECT * FROM Passivo WHERE usuario_id = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passivo passivo = new Passivo();
                passivo.setId(rs.getString("id"));
                passivo.setDescricao(rs.getString("descricao"));
                passivo.setValor(rs.getBigDecimal("valor"));
                passivo.setUsuarioId(rs.getString("usuario_id"));
                passivos.add(passivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passivos;
    }

    public Passivo buscarPassivoPorId(String id) {
        String sql = "SELECT * FROM Passivo WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Passivo passivo = new Passivo();
                passivo.setId(rs.getString("id"));
                passivo.setDescricao(rs.getString("descricao"));
                passivo.setValor(rs.getBigDecimal("valor"));
                passivo.setUsuarioId(rs.getString("usuario_id"));
                return passivo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // --- UPDATE ---
    public void atualizarPassivo(Passivo passivo) {
        String sql = "UPDATE Passivo SET descricao = ?, valor = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passivo.getDescricao());
            stmt.setBigDecimal(2, passivo.getValor());
            stmt.setString(3, passivo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- DELETE ---
    public void deletarPassivo(String id) {
        String sql = "DELETE FROM Passivo WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}