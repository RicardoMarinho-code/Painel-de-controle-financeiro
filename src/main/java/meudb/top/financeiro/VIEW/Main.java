package meudb.top.financeiro.VIEW;

import meudb.top.financeiro.DAO.AtivoDAO;
import meudb.top.financeiro.DAO.ConexaoMySQL;
import meudb.top.financeiro.DAO.InterfaceConexao;
import meudb.top.financeiro.DAO.PassivoDAO;
import meudb.top.financeiro.MODEL.Ativo;
import meudb.top.financeiro.MODEL.Passivo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InterfaceConexao conexao = new ConexaoMySQL();
    private static final AtivoDAO ativoDAO = new AtivoDAO(conexao);
    private static final PassivoDAO passivoDAO = new PassivoDAO(conexao);

    // Para simplificar, vamos usar um ID de usuário fixo.
    // Em uma aplicação real, isso viria de uma tela de login.
    private static final String ID_USUARIO_LOGADO = "1a2b3c4d-5e6f-7g8h-9i0j-1k2l3m4n5o6p";

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Painel de Controle Financeiro!");

        while (true) {
            exibirMenuPrincipal();
            int escolha = lerOpcao();

            switch (escolha) {
                case 1:
                    menuBalancoPatrimonial();
                    break;
                case 2:
                    System.out.println("Módulo de Orçamento Mensal ainda não implementado.");
                    break;
                case 3:
                    System.out.println("Módulo de Evolução Financeira ainda não implementado.");
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gerenciar Balanço Patrimonial");
        System.out.println("2. Gerenciar Orçamento Mensal");
        System.out.println("3. Ver Evolução Financeira");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuBalancoPatrimonial() {
        while (true) {
            System.out.println("\n--- BALANÇO PATRIMONIAL ---");
            listarBalanco();
            System.out.println("\n1. Adicionar Ativo");
            System.out.println("2. Atualizar Ativo");
            System.out.println("3. Deletar Ativo");
            System.out.println("4. Adicionar Passivo");
            System.out.println("5. Atualizar Passivo");
            System.out.println("6. Deletar Passivo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int escolha = lerOpcao();
            scanner.nextLine(); // Consumir nova linha

            switch (escolha) {
                case 1: adicionarAtivo(); break;
                case 2: atualizarAtivo(); break;
                case 3: deletarAtivo(); break;
                case 4: adicionarPassivo(); break;
                case 5: atualizarPassivo(); break;
                case 6: deletarPassivo(); break;
                case 0: return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarBalanco() {
        System.out.println("\n--- ATIVOS ---");
        List<Ativo> ativos = ativoDAO.listarAtivosPorUsuario(ID_USUARIO_LOGADO);
        BigDecimal totalAtivos = BigDecimal.ZERO;
        if (ativos.isEmpty()) {
            System.out.println("Nenhum ativo cadastrado.");
        } else {
            for (Ativo ativo : ativos) {
                System.out.println(ativo);
                totalAtivos = totalAtivos.add(ativo.getValor());
            }
        }

        System.out.println("\n--- PASSIVOS ---");
        List<Passivo> passivos = passivoDAO.listarPassivosPorUsuario(ID_USUARIO_LOGADO);
        BigDecimal totalPassivos = BigDecimal.ZERO;
        if (passivos.isEmpty()) {
            System.out.println("Nenhum passivo cadastrado.");
        } else {
            for (Passivo passivo : passivos) {
                System.out.println(passivo);
                totalPassivos = totalPassivos.add(passivo.getValor());
            }
        }

        System.out.println("\n-----------------------------------------");
        System.out.printf("Total de Ativos: R$ %.2f\n", totalAtivos);
        System.out.printf("Total de Passivos: R$ %.2f\n", totalPassivos);
        BigDecimal patrimonioLiquido = totalAtivos.subtract(totalPassivos);
        System.out.printf("PATRIMÔNIO LÍQUIDO: R$ %.2f\n", patrimonioLiquido);
        System.out.println("-----------------------------------------");
    }

    private static void adicionarAtivo() {
        System.out.print("Descrição do Ativo: ");
        String desc = scanner.nextLine();
        System.out.print("Valor do Ativo: ");
        BigDecimal valor = lerBigDecimal();
        ativoDAO.adicionarAtivo(new Ativo(null, desc, valor, ID_USUARIO_LOGADO));
        System.out.println("Ativo adicionado com sucesso!");
    }

    // Métodos para atualizarAtivo, deletarAtivo

    private static void adicionarPassivo() {
        System.out.print("Descrição do Passivo: ");
        String desc = scanner.nextLine();
        System.out.print("Valor do Passivo: ");
        BigDecimal valor = lerBigDecimal();
        passivoDAO.adicionarPassivo(new Passivo(null, desc, valor, ID_USUARIO_LOGADO));
        System.out.println("Passivo adicionado com sucesso!");
    }

    // Métodos para atualizarPassivo, deletarPassivo

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, insira um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static BigDecimal lerBigDecimal() {
        while (!scanner.hasNextBigDecimal()) {
            System.out.print("Por favor, insira um valor numérico válido (ex: 1234.56): ");
            scanner.next();
        }
        return scanner.nextBigDecimal();
    }

    private static void atualizarAtivo() {
        System.out.print("Digite o ID do ativo que deseja atualizar: ");
        String id = scanner.nextLine();
        System.out.print("Nova descrição do Ativo: ");
        String desc = scanner.nextLine();
        System.out.print("Novo valor do Ativo: ");
        BigDecimal valor = lerBigDecimal();
        scanner.nextLine(); // Consumir nova linha

        Ativo ativo = new Ativo(id, desc, valor, ID_USUARIO_LOGADO);
        ativoDAO.atualizarAtivo(ativo);
        System.out.println("Ativo atualizado com sucesso!");
    }

    private static void deletarAtivo() {
        System.out.print("Digite o ID do ativo que deseja deletar: ");
        String id = scanner.nextLine();
        ativoDAO.deletarAtivo(id);
        System.out.println("Ativo deletado com sucesso!");
    }

    private static void atualizarPassivo() {
        System.out.print("Digite o ID do passivo que deseja atualizar: ");
        String id = scanner.nextLine();
        System.out.print("Nova descrição do Passivo: ");
        String desc = scanner.nextLine();
        System.out.print("Novo valor do Passivo: ");
        BigDecimal valor = lerBigDecimal();
        scanner.nextLine(); // Consumir nova linha

        Passivo passivo = new Passivo(id, desc, valor, ID_USUARIO_LOGADO);
        passivoDAO.atualizarPassivo(passivo);
        System.out.println("Passivo atualizado com sucesso!");
    }

    private static void deletarPassivo() {
        System.out.print("Digite o ID do passivo que deseja deletar: ");
        String id = scanner.nextLine();
        passivoDAO.deletarPassivo(id);
        System.out.println("Passivo deletado com sucesso!");
    }

}

