package meudb.top.financeiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import meudb.top.financeiro.MODEL.Orcamento;

public class OrcamentoDAO {
    private final InterfaceConexao conexao;

    public OrcamentoDAO(InterfaceConexao conexao) {
        this.conexao = conexao;
    }

    public Orcamento buscarOuCriarOrcamento(int mes, int ano, String idUsuario) {
        Orcamento orcamento = buscarOrcamentoPorMesAno(mes, ano, idUsuario);
        if (orcamento == null) {
            return criarOrcamento(mes, ano, idUsuario);
        }
        return orcamento;
    }

    public Orcamento buscarOrcamentoPorMesAno(int mes, int ano, String idUsuario) {
        String sql = "SELECT id_orcamento, mes, ano, id_usuario FROM Orcamento WHERE mes = ? AND ano = ? AND id_usuario = ?";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            stmt.setString(3, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Orcamento(
                            rs.getString("id_orcamento"),
                            rs.getInt("mes"),
                            rs.getInt("ano"),
                            rs.getString("id_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar orçamento: " + e.getMessage());
        }
        return null;
    }

    private Orcamento criarOrcamento(int mes, int ano, String idUsuario) {
        String id = UUID.randomUUID().toString();
        String sql = "INSERT INTO Orcamento (id_orcamento, mes, ano, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            stmt.setString(4, idUsuario);
            stmt.executeUpdate();
            return new Orcamento(id, mes, ano, idUsuario);
        } catch (SQLException e) {
            System.out.println("Erro ao criar orçamento: " + e.getMessage());
        }
        return null;
    }

    public void copiarPlanoMesAnterior(Orcamento orcamentoAtual, Orcamento orcamentoAnterior, CategoriaOrcamentoDAO categoriaDAO) {
        List<meudb.top.financeiro.MODEL.CategoriaOrcamento> categoriasAnteriores = categoriaDAO.listarCategoriasPorOrcamento(orcamentoAnterior.getId());
        for (meudb.top.financeiro.MODEL.CategoriaOrcamento cat : categoriasAnteriores) {
            // Cria nova categoria com valor planejado do mês anterior, mas zerando o realizado
            categoriaDAO.adicionarCategoria(new meudb.top.financeiro.MODEL.CategoriaOrcamento(
                    null, cat.getNome(), cat.getValorPlanejado(), java.math.BigDecimal.ZERO, orcamentoAtual.getId()
            ));
        }
        System.out.println("Plano copiado do mês anterior!");
    }
}