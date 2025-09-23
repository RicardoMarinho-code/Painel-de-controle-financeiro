// src/main/java/com/meuprojeto/conexao/ConexaoMySQL.java
package com.meuprojeto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco_de_dados?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "seu_usuario_mysql";
    private static final String SENHA = "sua_senha_mysql";

    public static Connection getConnection() {
        Connection conexao = null;
        try {
            // Carrega o driver JDBC do MySQL (se necessário, para versões mais antigas do JDBC)
            // Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            // Para depuração, você pode imprimir o stack trace
            // e.printStackTrace();
        }
        return conexao;
    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}