// src/main/java/com/meuprojeto/MainApp.java
package com.meuprojeto;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import com.meuprojeto.conexao.ConexaoMySQL;
import com.meuprojeto.dao.OrcamentoDAO;
import com.meuprojeto.dao.UsuarioDAO;
import com.meuprojeto.modelo.Orcamento;
import com.meuprojeto.modelo.Usuario; // Para gerar IDs únicos

public class MainApp {
    public static void main(String[] args) {

        // Testando a conexão
        Connection conexao = ConexaoMySQL.getConnection();
        if (conexao != null) {
            System.out.println("Conexão bem-sucedida no MainApp!");
            ConexaoMySQL.closeConnection(conexao);
        } else {
            System.err.println("Falha na conexão no MainApp.");
            return; // Sai se a conexão falhar
        }

        // --- Testes para UsuarioDAO ---
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // 1. Adicionar um novo usuário
        String novoUsuarioId = UUID.randomUUID().toString(); // Gerar um ID único
        Usuario novoUsuario = new Usuario(novoUsuarioId, "teste@email.com", "senha123", "João Teste");
        usuarioDAO.adicionarUsuario(novoUsuario);

        // 2. Buscar um usuário por ID
        Usuario usuarioBuscado = usuarioDAO.buscarUsuarioPorId(novoUsuarioId);
        if (usuarioBuscado != null) {
            System.out.println("Usuário encontrado: " + usuarioBuscado);
        } else {
            System.out.println("Usuário com ID " + novoUsuarioId + " não encontrado.");
        }

        // 3. Atualizar um usuário
        if (usuarioBuscado != null) {
            usuarioBuscado.setNome("João da Silva");
            usuarioDAO.atualizarUsuario(usuarioBuscado);
            System.out.println("Usuário atualizado: " + usuarioDAO.buscarUsuarioPorId(novoUsuarioId));
        }

        // 4. Listar todos os usuários
        System.out.println("\n--- Lista de Usuários ---");
        List<Usuario> usuarios = usuarioDAO.listarTodosUsuarios();
        usuarios.forEach(System.out::println);

        // --- Testes para OrcamentoDAO ---
        OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

        // 1. Adicionar um novo orçamento para o usuário existente
        String novoOrcamentoId = UUID.randomUUID().toString();
        Orcamento novoOrcamento = new Orcamento(novoOrcamentoId, 7, 2023, novoUsuarioId);
        orcamentoDAO.adicionarOrcamento(novoOrcamento);

        // 2. Buscar um orçamento por ID
        Orcamento orcamentoBuscado = orcamentoDAO.buscarOrcamentoPorId(novoOrcamentoId);
        if (orcamentoBuscado != null) {
            System.out.println("Orçamento encontrado: " + orcamentoBuscado);
        } else {
            System.out.println("Orçamento com ID " + novoOrcamentoId + " não encontrado.");
        }

        // 3. Atualizar um orçamento
        if (orcamentoBuscado != null) {
            orcamentoBuscado.setMes(8);
            orcamentoDAO.atualizarOrcamento(orcamentoBuscado);
            System.out.println("Orçamento atualizado: " + orcamentoDAO.buscarOrcamentoPorId(novoOrcamentoId));
        }

        // 4. Listar orçamentos de um usuário específico
        System.out.println("\n--- Orçamentos do Usuário " + novoUsuario.getNome() + " ---");
        List<Orcamento> orcamentosDoUsuario = orcamentoDAO.listarOrcamentosPorUsuario(novoUsuarioId);
        orcamentosDoUsuario.forEach(System.out::println);


        // 5. Deletar o orçamento (opcional, pode descomentar para testar)
        // orcamentoDAO.deletarOrcamento(novoOrcamentoId);

        // 6. Deletar o usuário (opcional, pode descomentar para testar, cuidado com chaves estrangeiras!)
        // usuarioDAO.deletarUsuario(novoUsuarioId);

        System.out.println("\nTestes concluídos.");
    }
}
