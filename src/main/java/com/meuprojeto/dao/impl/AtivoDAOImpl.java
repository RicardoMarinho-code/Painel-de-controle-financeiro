package com.meuprojeto.dao.impl;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.dao.AtivoDAO;
import com.meuprojeto.modelo.Ativo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Aqui temos a implementação da interface AtivoDAO, 
// que é a classe que faz as operações de banco de dados para os Ativos.
public class AtivoDAOImpl implements AtivoDAO {

    @Override
    public void adicionar(Ativo ativo) {

        // Comando SQL para inserir um novo Ativo, os "?" são apenas placeholders.
        String sql = "INSERT INTO Ativo (id_ativo, descricao, valor, id_usuario) VALUES (?, ?, ?, ?)";

        // O 'try-with-resources' garante que a conexão e o PreparedStatement sejam fechados automaticamente.
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Se o passivo ainda não tiver um ID, gera um UUID (Identificador Único Universal) para ele.
            if (ativo.getIdAtivo() == null) {
                ativo.setIdAtivo(UUID.randomUUID().toString());
            }

            // Define os valores para cada placeholder '?' no comando SQL.
            stmt.setString(1, ativo.getIdAtivo());
            stmt.setString(2, ativo.getDescricao());
            stmt.setBigDecimal(3, ativo.getValor());
            stmt.setString(4, ativo.getIdUsuario());

            // Executa o comando sql para que os dados sejam inseridos na BD.
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Em caso de erro de SQL, imprime o stack trace. Precisa ser atualizado para um logger!!!!
            e.printStackTrace(); 
        }
    }

    @Override
    public List<Ativo> listarPorUsuario(String idUsuario) {

        // Aqui é criado uma lista vazia para armazenar os resultados dessa consulta.
        List<Ativo> ativos = new ArrayList<>();

        // Comando para selecionar a informação que você quer ver dos Ativos.
        String sql = "SELECT * FROM Ativo WHERE id_usuario = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o ID do usuário no placeholder "?" da cláusula "WHERE" do select.
            stmt.setString(1, idUsuario);

            // Executa e armazena os resultados.
            ResultSet rs = stmt.executeQuery();

            // Loop para percorrer cada resultado encontrado.
            while (rs.next()) {
                // Cria um objeto Ativo para cada resultado.
                Ativo ativo = new Ativo();

                // Preenche o objeto com os dados do banco.
                ativo.setIdAtivo(rs.getString("id_ativo"));
                ativo.setDescricao(rs.getString("descricao"));
                ativo.setValor(rs.getBigDecimal("valor"));
                ativo.setIdUsuario(rs.getString("id_usuario"));

                // Adiciona o Ativo na lista.
                ativos.add(ativo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ativos;
    }

    @Override
    public Ativo buscarPorId(String id) {
        // Comando SQL para selecionar um único passivo pelo seu ID.
        String sql = "SELECT * FROM Ativo WHERE id_ativo = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Informa qual ID deve ser buscado.
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            // Apenas uma verificação se foi encontrado um resultado.
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
        // Se não encontrar nada, retorna null.
        return null;
    }

    @Override
    public void atualizar(Ativo ativo) {
        // Comando para dar update na descrição, valor e um id ativo específico desejado.
        String sql = "UPDATE Ativo SET descricao = ?, valor = ? WHERE id_ativo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definição dos valores e ID para a identificação do registro que precisa ser atualizado.
            stmt.setString(1, ativo.getDescricao());
            stmt.setBigDecimal(2, ativo.getValor());
            stmt.setString(3, ativo.getIdAtivo());

            // Execução do comando UPDATE.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(String id) {
        // Comando para deletar um registro da tabela Ativo.
        String sql = "DELETE FROM Ativo WHERE id_ativo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Informa qual o ID do passivo a ser apagado.
            stmt.setString(1, id);

            // Executa o comando para apagar.
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}