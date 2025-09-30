package com.meuprojeto.dao.impl;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.dao.PassivoDAO;
import com.meuprojeto.modelo.Passivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Aqui temos a implementação da interface PassivoDAO, 
// que é a classe que faz as operações de banco de dados para os Passivos.
public class PassivoDAOImpl implements PassivoDAO {

    @Override
    public void adicionar(Passivo passivo) {

        // Comando SQL para inserir um novo Passivo, os "?" são apenas placeholders.
        String sql = "INSERT INTO Passivo (id_passivo, descricao, valor, id_usuario) VALUES (?, ?, ?, ?)";

        // O 'try-with-resources' garante que a conexão e o PreparedStatement sejam fechados automaticamente.
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Se o passivo ainda não tiver um ID, gera um UUID (Identificador Único Universal) para ele.
            if (passivo.getIdPassivo() == null) {
                passivo.setIdPassivo(UUID.randomUUID().toString());
            }

            // Define os valores para cada placeholder '?' no comando SQL.
            stmt.setString(1, passivo.getIdPassivo());
            stmt.setString(2, passivo.getDescricao());
            stmt.setBigDecimal(3, passivo.getValor());
            stmt.setString(4, passivo.getIdUsuario());

            // Executa o comando sql para que os dados sejam inseridos na BD.
            stmt.executeUpdate();

        } catch (SQLException e) {
            // Em caso de erro de SQL, imprime o stack trace. Precisa ser atualizado para um logger!!!!
            e.printStackTrace(); 
        }
    }

    @Override
    public List<Passivo> listarPorUsuario(String idUsuario) {

        // Aqui é criado uma lista vazia para armazenar os resultados dessa consulta.
        List<Passivo> passivos = new ArrayList<>();

        // Comando para selecionar a informação que você quer ver dos Passivos.
        String sql = "SELECT * FROM Passivo WHERE id_usuario = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Define o ID do usuário no placeholder "?" da cláusula "WHERE" do select.
            stmt.setString(1, idUsuario);

            // Executa e armazena os resultados.
            ResultSet rs = stmt.executeQuery();

            // Loop para percorrer cada resultado encontrado.
            while (rs.next()) {
                // Cria um objeto Passivo para cada resultado.
                Passivo passivo = new Passivo();

                // Preenche o objeto com os dados do banco.
                passivo.setIdPassivo(rs.getString("id_passivo"));
                passivo.setDescricao(rs.getString("descricao"));
                passivo.setValor(rs.getBigDecimal("valor"));
                passivo.setIdUsuario(rs.getString("id_usuario"));

                // Adiciona o passivo na lista.
                passivos.add(passivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passivos;
    }

    @Override
    public Passivo buscarPorId(String id) {
        // Comando SQL para selecionar um único passivo pelo seu ID.
        String sql = "SELECT * FROM Passivo WHERE id_passivo = ?";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Informa qual ID deve ser buscado.
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            
            // Apenas uma verificação se foi encontrado um resultado.
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
        // Se não encontrar nada, retorna null.
        return null;
    }

    @Override
    public void atualizar(Passivo passivo) {
        // Comando para dar update na descrição, valor e um id passivo específico desejado.
        String sql = "UPDATE Passivo SET descricao = ?, valor = ? WHERE id_passivo = ?";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definição dos valores e ID para a identificação do registro que precisa ser atualizado.
            stmt.setString(1, passivo.getDescricao());
            stmt.setBigDecimal(2, passivo.getValor());
            stmt.setString(3, passivo.getIdPassivo());

            // Execução do comando UPDATE.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(String id) {
        // Comando para deletar um registro da tabela Passivo.
        String sql = "DELETE FROM Passivo WHERE id_passivo = ?";
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