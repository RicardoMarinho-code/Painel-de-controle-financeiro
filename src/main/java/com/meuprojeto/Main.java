package com.meuprojeto;

import com.meuprojeto.dao.AtivoDAO;
import com.meuprojeto.dao.PassivoDAO;
import com.meuprojeto.dao.impl.AtivoDAOImpl;
import com.meuprojeto.dao.impl.PassivoDAOImpl;
import com.meuprojeto.modelo.Ativo;
import com.meuprojeto.modelo.Passivo;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando teste de DAO...");

        // Para este teste funcionar, você precisa ter um usuário no banco de dados.
        // Vamos assumir que existe um usuário com o ID abaixo.
        // Você pode inserir um manualmente no seu banco com:
        // INSERT INTO Usuario (id_usuario, nome, email, senha) VALUES ('123e4567-e89b-12d3-a456-426614174000', 'Usuário Teste', 'teste@email.com', 'senha123');
        String idUsuarioTeste = "123e4567-e89b-12d3-a456-426614174000";

        // --- TESTE COM PASSIVO ---
        System.out.println("\n--- INICIANDO TESTE DE PASSIVO ---");
        
        // 1. Instanciar os DAOs
        PassivoDAO passivoDAO = new PassivoDAOImpl();

        // 2. Criar um novo objeto Passivo
        Passivo novoPassivo = new Passivo();
        novoPassivo.setIdPassivo(UUID.randomUUID().toString()); // Gera um ID único
        novoPassivo.setDescricao("Financiamento do Apartamento");
        novoPassivo.setValor(new BigDecimal("850.75"));
        novoPassivo.setIdUsuario(idUsuarioTeste);

        // 3. Adicionar o passivo ao banco de dados
        System.out.println("Adicionando novo passivo...");
        passivoDAO.adicionar(novoPassivo);
        System.out.println("Passivo '" + novoPassivo.getDescricao() + "' adicionado com sucesso!");

        // --- TESTE COM ATIVO ---
        System.out.println("\n--- INICIANDO TESTE DE ATIVO ---");
        AtivoDAO ativoDAO = new AtivoDAOImpl();

        Ativo novoAtivo = new Ativo();
        novoAtivo.setIdAtivo(UUID.randomUUID().toString());
        novoAtivo.setDescricao("Ações da Empresa X");
        novoAtivo.setValor(new BigDecimal("5200.40"));
        novoAtivo.setIdUsuario(idUsuarioTeste);

        System.out.println("Adicionando novo ativo...");
        ativoDAO.adicionar(novoAtivo);
        System.out.println("Ativo '" + novoAtivo.getDescricao() + "' adicionado com sucesso!");


        // --- LISTAGEM FINAL ---
        System.out.println("\n--- LISTANDO ITENS DO USUÁRIO ---");

        // 4. Listar todos os passivos do usuário
        System.out.println("\nPASSIVOS do usuário " + idUsuarioTeste + ":");
        List<Passivo> passivosDoUsuario = passivoDAO.listarPorUsuario(idUsuarioTeste);

        if (passivosDoUsuario.isEmpty()) {
            System.out.println("Nenhum passivo encontrado para este usuário.");
        } else {
            for (Passivo p : passivosDoUsuario) {
                System.out.println(
                    " - ID: " + p.getIdPassivo() + 
                    " | Descrição: " + p.getDescricao() + 
                    " | Valor: R$ " + p.getValor()
                );
            }
        }

        // 5. Listar todos os ativos do usuário
        System.out.println("\nATIVOS do usuário " + idUsuarioTeste + ":");
        List<Ativo> ativosDoUsuario = ativoDAO.listarPorUsuario(idUsuarioTeste);

        if (ativosDoUsuario.isEmpty()) {
            System.out.println("Nenhum ativo encontrado para este usuário.");
        } else {
            for (Ativo a : ativosDoUsuario) {
                System.out.println(
                    " - ID: " + a.getIdAtivo() +
                    " | Descrição: " + a.getDescricao() +
                    " | Valor: R$ " + a.getValor()
                );
            }
        }
        System.out.println("\nTeste finalizado.");
    }
}