package meudb.top.financeiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import meudb.top.financeiro.MODEL.Historico;

public class HistoricoDAO {
    private final InterfaceConexao conexao;

    public HistoricoDAO(InterfaceConexao conexao) {
        this.conexao = conexao;
    }

    public void salvarHistorico(Historico historico) {
        // Verifica se já existe um registro para esse mês/ano e usuário
        String sqlSelect = "SELECT id_historico FROM Historico WHERE mes = ? AND ano = ? AND id_usuario = ?";
        String sqlUpdate = "UPDATE Historico SET patrimonio_liquido = ? WHERE id_historico = ?";
        String sqlInsert = "INSERT INTO Historico (id_historico, mes, ano, patrimonio_liquido, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexao.conectar()) {
            String idExistente = null;
            try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
                stmtSelect.setInt(1, historico.getMes());
                stmtSelect.setInt(2, historico.getAno());
                stmtSelect.setString(3, historico.getIdUsuario());
                try (ResultSet rs = stmtSelect.executeQuery()) {
                    if (rs.next()) {
                        idExistente = rs.getString("id_historico");
                    }
                }
            }

            if (idExistente != null) {
                // Atualiza
                try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                    stmtUpdate.setBigDecimal(1, historico.getPatrimonioLiquido());
                    stmtUpdate.setString(2, idExistente);
                    stmtUpdate.executeUpdate();
                }
            } else {
                // Insere
                try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                    stmtInsert.setString(1, UUID.randomUUID().toString());
                    stmtInsert.setInt(2, historico.getMes());
                    stmtInsert.setInt(3, historico.getAno());
                    stmtInsert.setBigDecimal(4, historico.getPatrimonioLiquido());
                    stmtInsert.setString(5, historico.getIdUsuario());
                    stmtInsert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public List<Historico> listarHistoricoPorUsuario(String idUsuario) {
        List<Historico> lista = new ArrayList<>();
        String sql = "SELECT id_historico, mes, ano, patrimonio_liquido, id_usuario FROM Historico WHERE id_usuario = ? ORDER BY ano, mes";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Historico(
                            rs.getString("id_historico"),
                            rs.getInt("mes"),
                            rs.getInt("ano"),
                            rs.getBigDecimal("patrimonio_liquido"),
                            rs.getString("id_usuario")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }
        return lista;
    }
}