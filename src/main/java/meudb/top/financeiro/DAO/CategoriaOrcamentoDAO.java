package meudb.top.financeiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import meudb.top.financeiro.MODEL.CategoriaOrcamento;

public class CategoriaOrcamentoDAO {
    private final InterfaceConexao conexao;

    public CategoriaOrcamentoDAO(InterfaceConexao conexao) {
        this.conexao = conexao;
    }

    public void adicionarCategoria(CategoriaOrcamento categoria) {
        String sql = "INSERT INTO CategoriaOrcamento (id_categoria, nome, valor_planejado, valor_realizado, id_orcamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, categoria.getNome());
            stmt.setBigDecimal(3, categoria.getValorPlanejado());
            stmt.setBigDecimal(4, categoria.getValorRealizado());
            stmt.setString(5, categoria.getIdOrcamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar categoria: " + e.getMessage());
        }
    }

    public List<CategoriaOrcamento> listarCategoriasPorOrcamento(String idOrcamento) {
        List<CategoriaOrcamento> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nome, valor_planejado, valor_realizado, id_orcamento FROM CategoriaOrcamento WHERE id_orcamento = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idOrcamento);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new CategoriaOrcamento(
                            rs.getString("id_categoria"),
                            rs.getString("nome"),
                            rs.getBigDecimal("valor_planejado"),
                            rs.getBigDecimal("valor_realizado"),
                            rs.getString("id_orcamento")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarCategoria(CategoriaOrcamento categoria) {
        String sql = "UPDATE CategoriaOrcamento SET nome = ?, valor_planejado = ?, valor_realizado = ? WHERE id_categoria = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setBigDecimal(2, categoria.getValorPlanejado());
            stmt.setBigDecimal(3, categoria.getValorRealizado());
            stmt.setString(4, categoria.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    public void deletarCategoria(String idCategoria) {
        String sql = "DELETE FROM CategoriaOrcamento WHERE id_categoria = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idCategoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar categoria: " + e.getMessage());
        }
    }
}