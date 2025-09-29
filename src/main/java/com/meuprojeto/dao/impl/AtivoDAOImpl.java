package com.meuprojeto.dao.impl;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.dao.AtivoDAO;
import com.meuprojeto.modelo.Ativo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AtivoDAOImpl implements AtivoDAO {

    @Override
    public void adicionar(Ativo ativo) {
        String sql = "INSERT INTO Ativo (id_ativo, descricao, valor, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (ativo.getIdAtivo() == null) {
                ativo.setIdAtivo(UUID.randomUUID().toString());
            }

            stmt.setString(1, ativo.getIdAtivo());
            stmt.setString(2, ativo.getDescricao());
            stmt.setBigDecimal(3, ativo.getValor());
            stmt.setString(4, ativo.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Em um projeto real, trate essa exceção de forma mais robusta
        }
    }

    @Override
    public List<Ativo> listarPorUsuario(String idUsuario) {
        List<Ativo> ativos = new ArrayList<>();
        String sql = "SELECT * FROM Ativo WHERE id_usuario = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setIdAtivo(rs.getString("id_ativo"));
                ativo.setDescricao(rs.getString("descricao"));
                ativo.setValor(rs.getBigDecimal("valor"));
                ativo.setIdUsuario(rs.getString("id_usuario"));
                ativos.add(ativo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ativos;
    }

    @Override
    public Ativo buscarPorId(String id) {
        String sql = "SELECT * FROM Ativo WHERE id_ativo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Ativo ativo = new Ativo();
                ativo.setIdAtivo(rs.getString("id_ativo"));
                ativo.setDescricao(rs.getString("descricao"));
                ativo.setValor(rs.getBigDecimal("valor"));
                ativo.setIdUsuario(rs.getString("id_usuario"));
                return ativo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void atualizar(Ativo ativo) {
        String sql = "UPDATE Ativo SET descricao = ?, valor = ? WHERE id_ativo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ativo.getDescricao());
            stmt.setBigDecimal(2, ativo.getValor());
            stmt.setString(3, ativo.getIdAtivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(String id) {
        String sql = "DELETE FROM Ativo WHERE id_ativo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}