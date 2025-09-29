package com.meuprojeto.dao.impl;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.dao.PassivoDAO;
import com.meuprojeto.modelo.Passivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PassivoDAOImpl implements PassivoDAO {

    @Override
    public void adicionar(Passivo passivo) {
        String sql = "INSERT INTO Passivo (id_passivo, descricao, valor, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (passivo.getIdPassivo() == null) {
                passivo.setIdPassivo(UUID.randomUUID().toString());
            }

            stmt.setString(1, passivo.getIdPassivo());
            stmt.setString(2, passivo.getDescricao());
            stmt.setBigDecimal(3, passivo.getValor());
            stmt.setString(4, passivo.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Em um projeto real, trate essa exceção de forma mais robusta
        }
    }

    @Override
    public List<Passivo> listarPorUsuario(String idUsuario) {
        List<Passivo> passivos = new ArrayList<>();
        String sql = "SELECT * FROM Passivo WHERE id_usuario = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Passivo passivo = new Passivo();
                passivo.setIdPassivo(rs.getString("id_passivo"));
                passivo.setDescricao(rs.getString("descricao"));
                passivo.setValor(rs.getBigDecimal("valor"));
                passivo.setIdUsuario(rs.getString("id_usuario"));
                passivos.add(passivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passivos;
    }

    @Override
    public Passivo buscarPorId(String id) {
        String sql = "SELECT * FROM Passivo WHERE id_passivo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Passivo passivo = new Passivo();
                passivo.setIdPassivo(rs.getString("id_passivo"));
                passivo.setDescricao(rs.getString("descricao"));
                passivo.setValor(rs.getBigDecimal("valor"));
                passivo.setIdUsuario(rs.getString("id_usuario"));
                return passivo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void atualizar(Passivo passivo) {
        String sql = "UPDATE Passivo SET descricao = ?, valor = ? WHERE id_passivo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passivo.getDescricao());
            stmt.setBigDecimal(2, passivo.getValor());
            stmt.setString(3, passivo.getIdPassivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(String id) {
        String sql = "DELETE FROM Passivo WHERE id_passivo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}