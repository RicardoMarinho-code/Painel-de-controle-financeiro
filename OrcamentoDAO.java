// src/main/java/com/meuprojeto/dao/OrcamentoDAO.java
package com.meuprojeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.modelo.Orcamento;

public class OrcamentoDAO {

    public void adicionarOrcamento(Orcamento orcamento) {
        String sql = "INSERT INTO Orcamento (id_orcamento, mes, ano, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, orcamento.getIdOrcamento());
            stmt.setInt(2, orcamento.getMes());
            stmt.setInt(3, orcamento.getAno());
            stmt.setString(4, orcamento.getIdUsuario());
            stmt.executeUpdate();
            System.out.println("Orçamento adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar orçamento: " + e.getMessage());
        }
    }

    public Orcamento buscarOrcamentoPorId(String idOrcamento) {
        String sql = "SELECT * FROM Orcamento WHERE id_orcamento = ?";
        Orcamento orcamento = null;
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, idOrcamento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orcamento = new Orcamento();
                    orcamento.setIdOrcamento(rs.getString("id_orcamento"));
                    orcamento.setMes(rs.getInt("mes"));
                    orcamento.setAno(rs.getInt("ano"));
                    orcamento.setIdUsuario(rs.getString("id_usuario"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar orçamento por ID: " + e.getMessage());
        }
        return orcamento;
    }

    public List<Orcamento> listarOrcamentosPorUsuario(String idUsuario) {
        String sql = "SELECT * FROM Orcamento WHERE id_usuario = ?";
        List<Orcamento>orcamentos = new ArrayList<>();
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Orcamento orcamento = new Orcamento();
                    orcamento.setIdOrcamento(rs.getString("id_orcamento"));
                    orcamento.setMes(rs.getInt("mes"));
                    orcamento.setAno(rs.getInt("ano"));
                    orcamento.setIdUsuario(rs.getString("id_usuario"));
                    orcamentos.add(orcamento);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar orçamentos por usuário: " + e.getMessage());
        }
        return orcamentos;
    }

    public void atualizarOrcamento(Orcamento orcamento) {
        String sql = "UPDATE Orcamento SET mes = ?, ano = ?, id_usuario = ? WHERE id_orcamento = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, orcamento.getMes());
            stmt.setInt(2, orcamento.getAno());
            stmt.setString(3, orcamento.getIdUsuario());
            stmt.setString(4, orcamento.getIdOrcamento());
            stmt.executeUpdate();
            System.out.println("Orçamento atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar orçamento: " + e.getMessage());
        }
    }

    public void deletarOrcamento(String idOrcamento) {
        String sql = "DELETE FROM Orcamento WHERE id_orcamento = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, idOrcamento);
            stmt.executeUpdate();
            System.out.println("Orçamento deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar orçamento: " + e.getMessage());
        }
    }
}