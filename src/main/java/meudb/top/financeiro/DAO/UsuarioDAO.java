package meudb.top.financeiro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import meudb.top.financeiro.MODEL.Usuario;

public class UsuarioDAO {
    private final InterfaceConexao conexao;

    public UsuarioDAO(InterfaceConexao conexao) {
        this.conexao = conexao;
    }

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (id_usuario, nome, email, senha) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha()); // Lembre-se de usar hashing para senhas em um projeto real
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }
    // Outros métodos como buscarUsuarioPorEmail, etc. podem ser adicionados aqui.
}