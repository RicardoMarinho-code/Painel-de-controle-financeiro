package meudb.top.financeiro.VIEW;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import meudb.top.financeiro.DAO.AtivoDAO;
import meudb.top.financeiro.DAO.CategoriaOrcamentoDAO;
import meudb.top.financeiro.DAO.ConexaoMySQL;
import meudb.top.financeiro.DAO.HistoricoDAO;
import meudb.top.financeiro.DAO.InterfaceConexao;
import meudb.top.financeiro.DAO.OrcamentoDAO;
import meudb.top.financeiro.DAO.PassivoDAO;
import meudb.top.financeiro.MODEL.Ativo;
import meudb.top.financeiro.MODEL.CategoriaOrcamento;
import meudb.top.financeiro.MODEL.Historico;
import meudb.top.financeiro.MODEL.Orcamento;
import meudb.top.financeiro.MODEL.Passivo;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InterfaceConexao conexao = new ConexaoMySQL();

    // DAOs
    private static final AtivoDAO ativoDAO = new AtivoDAO(conexao);
    private static final PassivoDAO passivoDAO = new PassivoDAO(conexao);
    private static final OrcamentoDAO orcamentoDAO = new OrcamentoDAO(conexao);
    private static final CategoriaOrcamentoDAO categoriaDAO = new CategoriaOrcamentoDAO(conexao);
    private static final HistoricoDAO historicoDAO = new HistoricoDAO(conexao);


    // usa um ID de usuário fixo.
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
                    menuOrcamentoMensal();
                    break;
                case 3:
                    menuEvolucaoFinanceira();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Ate logo!");
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gerenciar Balanco Patrimonial");
        System.out.println("2. Gerenciar Orcamento Mensal");
        System.out.println("3. Ver Evolucao Financeira");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    // --- MÓDULO 1: BALANÇO PATRIMONIAL ---
    // (Seu código original, que está correto)

    private static void menuBalancoPatrimonial() {
        while (true) {
            System.out.println("\n--- BALANÇO PATRIMONIAL ---");
            // F1.3: Cálculo Automático do Patrimônio Líquido [cite: 28, 29, 30]
            listarBalanco();
            System.out.println("\n1. Adicionar Ativo");
            System.out.println("2. Atualizar Ativo");
            System.out.println("3. Deletar Ativo");
            System.out.println("4. Adicionar Passivo");
            System.out.println("5. Atualizar Passivo");
            System.out.println("6. Deletar Passivo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

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
                default: System.out.println("Opcao inválida.");
            }
        }
    }

    private static BigDecimal calcularTotalAtivos() {
        List<Ativo> ativos = ativoDAO.listarAtivosPorUsuario(ID_USUARIO_LOGADO);
        BigDecimal totalAtivos = BigDecimal.ZERO;
        for (Ativo ativo : ativos) {
            totalAtivos = totalAtivos.add(ativo.getValor());
        }
        return totalAtivos;
    }

    private static BigDecimal calcularTotalPassivos() {
        List<Passivo> passivos = passivoDAO.listarPassivosPorUsuario(ID_USUARIO_LOGADO);
        BigDecimal totalPassivos = BigDecimal.ZERO;
        for (Passivo passivo : passivos) {
            totalPassivos = totalPassivos.add(passivo.getValor());
        }
        return totalPassivos;
    }


    private static void listarBalanco() {
        System.out.println("\n--- ATIVOS ---");
        List<Ativo> ativos = ativoDAO.listarAtivosPorUsuario(ID_USUARIO_LOGADO);
        if (ativos.isEmpty()) {
            System.out.println("Nenhum ativo cadastrado.");
        } else {
            for (Ativo ativo : ativos) {
                System.out.println(ativo);
            }
        }

        System.out.println("\n--- PASSIVOS ---");
        List<Passivo> passivos = passivoDAO.listarPassivosPorUsuario(ID_USUARIO_LOGADO);
        if (passivos.isEmpty()) {
            System.out.println("Nenhum passivo cadastrado.");
        } else {
            for (Passivo passivo : passivos) {
                System.out.println(passivo);
            }
        }

        BigDecimal totalAtivos = calcularTotalAtivos();
        BigDecimal totalPassivos = calcularTotalPassivos();
        BigDecimal patrimonioLiquido = totalAtivos.subtract(totalPassivos);

        System.out.println("\n-----------------------------------------");
        System.out.printf("Total de Ativos: R$ %.2f\n", totalAtivos);
        System.out.printf("Total de Passivos: R$ %.2f\n", totalPassivos);
        System.out.printf("PATRIMONIO LIQUIDO: R$ %.2f\n", patrimonioLiquido);
        System.out.println("-----------------------------------------");
    }

    // F1.1: Gerenciamento de Ativos [cite: 22]
    private static void adicionarAtivo() {
        System.out.print("Descrição do Ativo: ");
        String desc = scanner.nextLine();
        System.out.print("Valor do Ativo: ");
        BigDecimal valor = lerBigDecimal();
        ativoDAO.adicionarAtivo(new Ativo(null, desc, valor, ID_USUARIO_LOGADO));
        System.out.println("Ativo adicionado com sucesso!");
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

    // F1.2: Gerenciamento de Passivos [cite: 26]
    private static void adicionarPassivo() {
        System.out.print("Descrição do Passivo: ");
        String desc = scanner.nextLine();
        System.out.print("Valor do Passivo: ");
        BigDecimal valor = lerBigDecimal();
        passivoDAO.adicionarPassivo(new Passivo(null, desc, valor, ID_USUARIO_LOGADO));
        System.out.println("Passivo adicionado com sucesso!");
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


    // --- MÓDULO 2: ORÇAMENTO MENSAL ---
    // F2.1: Seleção e Criação de Orçamento [cite: 32, 33]
    private static void menuOrcamentoMensal() {
        System.out.println("\n--- ORÇAMENTO MENSAL ---");
        System.out.print("Digite o Mes (1-12): ");
        int mes = lerOpcao();
        System.out.print("Digite o Ano (ex: 2025): ");
        int ano = lerOpcao();
        scanner.nextLine(); // Consumir nova linha

        Orcamento orcamento = orcamentoDAO.buscarOuCriarOrcamento(mes, ano, ID_USUARIO_LOGADO);
        if (orcamento == null) {
            System.out.println("Nao foi possível carregar ou criar o orcamento.");
            return;
        }

        System.out.printf("Gerenciando Orcamento de %d/%d\n", mes, ano);

        // F2.4: Utilitário de Cópia [cite: 42, 43]
        List<CategoriaOrcamento> categorias = categoriaDAO.listarCategoriasPorOrcamento(orcamento.getId());
        if (categorias.isEmpty()) {
            System.out.print("Orçamento vazio. Deseja copiar o plano do mes anterior? (S/N): ");
            String copiar = scanner.nextLine();
            if (copiar.equalsIgnoreCase("S")) {
                LocalDate dataAnterior = LocalDate.of(ano, mes, 1).minusMonths(1);
                Orcamento orcamentoAnterior = orcamentoDAO.buscarOrcamentoPorMesAno(dataAnterior.getMonthValue(), dataAnterior.getYear(), ID_USUARIO_LOGADO);
                if (orcamentoAnterior != null) {
                    orcamentoDAO.copiarPlanoMesAnterior(orcamento, orcamentoAnterior, categoriaDAO);
                } else {
                    System.out.println("Nenhum orcamento encontrado para o mes anterior.");
                }
            }
        }


        while (true) {
            System.out.println("\n--- ORÇAMENTO: " + mes + "/" + ano + " ---");
            listarCategorias(orcamento.getId());
            System.out.println("\n1. Adicionar Categoria");
            System.out.println("2. Editar Valor Planejado");
            System.out.println("3. Registrar Valor Realizado"); // [cite: 38]
            System.out.println("4. Deletar Categoria");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int escolha = lerOpcao();
            scanner.nextLine(); // Consumir nova linha

            switch (escolha) {
                case 1: adicionarCategoria(orcamento.getId()); break;
                case 2: atualizarCategoria(orcamento.getId(), "planejado"); break;
                case 3: atualizarCategoria(orcamento.getId(), "realizado"); break;
                case 4: deletarCategoria(orcamento.getId()); break;
                case 0: return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // F2.3: Cálculo de Saldo por Categoria [cite: 41]
    private static void listarCategorias(String idOrcamento) {
        List<CategoriaOrcamento> categorias = categoriaDAO.listarCategoriasPorOrcamento(idOrcamento);
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada para este orçamento.");
            return;
        }
        
        BigDecimal totalPlanejado = BigDecimal.ZERO;
        BigDecimal totalRealizado = BigDecimal.ZERO;
        
        for (CategoriaOrcamento cat : categorias) {
            System.out.println(cat); // toString() da CategoriaOrcamento formata a saída
            totalPlanejado = totalPlanejado.add(cat.getValorPlanejado());
            totalRealizado = totalRealizado.add(cat.getValorRealizado());
        }

        System.out.println("\n-----------------------------------------");
        System.out.printf("Total Planejado: R$ %.2f\n", totalPlanejado);
        System.out.printf("Total Realizado: R$ %.2f\n", totalRealizado);
        System.out.printf("Saldo Total: R$ %.2f\n", totalPlanejado.subtract(totalRealizado));
        System.out.println("-----------------------------------------");
    }

    // F2.2: Gerenciamento de Categorias (Adicionar) [cite: 36]
    private static void adicionarCategoria(String idOrcamento) {
        System.out.print("Nome da Categoria: ");
        String nome = scanner.nextLine();
        System.out.print("Valor Planejado: ");
        BigDecimal planejado = lerBigDecimal();
        scanner.nextLine(); // Consumir

        categoriaDAO.adicionarCategoria(new CategoriaOrcamento(null, nome, planejado, BigDecimal.ZERO, idOrcamento));
        System.out.println("Categoria adicionada!");
    }

    // F2.2: Gerenciamento de Categorias (Editar) [cite: 36]
    private static void atualizarCategoria(String idOrcamento, String campo) {
        System.out.print("Digite o ID da categoria que deseja atualizar: ");
        String idCat = scanner.nextLine();

        // Buscar categoria para não ter que pedir todos os dados
        CategoriaOrcamento categoria = null;
        for (CategoriaOrcamento cat : categoriaDAO.listarCategoriasPorOrcamento(idOrcamento)) {
            if (cat.getId().equals(idCat)) {
                categoria = cat;
                break;
            }
        }

        if (categoria == null) {
            System.out.println("Categoria nao encontrada.");
            return;
        }

        if (campo.equals("planejado")) {
            System.out.print("Novo Valor Planejado (Atual: " + categoria.getValorPlanejado() + "): ");
            categoria.setValorPlanejado(lerBigDecimal());
            scanner.nextLine();
        } else if (campo.equals("realizado")) {
            // [cite: 38]
            System.out.print("Novo Valor Realizado (Atual: " + categoria.getValorRealizado() + "): ");
            categoria.setValorRealizado(lerBigDecimal());
            scanner.nextLine();
        }

        categoriaDAO.atualizarCategoria(categoria);
        System.out.println("Categoria atualizada!");
    }

    private static void deletarCategoria(String idOrcamento) {
        System.out.print("Digite o ID da categoria que deseja deletar: ");
        String idCat = scanner.nextLine();
        categoriaDAO.deletarCategoria(idCat);
        System.out.println("Categoria deletada!");
    }


    // --- MÓDULO 3: EVOLUÇÃO FINANCEIRA ---
    
    private static void menuEvolucaoFinanceira() {
        System.out.println("\n--- EVOLUCAO FINANCEIRA ---");

        while (true) {
            System.out.println("\n1. Visualizar Historico de Patrimonio"); // [cite: 49]
            System.out.println("2. Salvar Patrimonio do Mes Atual (\"Fechar Mes\")"); // [cite: 46]
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int escolha = lerOpcao();
            scanner.nextLine(); // Consumir nova linha

            switch (escolha) {
                case 1: visualizarHistorico(); break;
                case 2: salvarPatrimonioAtual(); break;
                case 0: return;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    // F3.2: Visualização do Histórico [cite: 49]
    private static void visualizarHistorico() {
        List<Historico> historico = historicoDAO.listarHistoricoPorUsuario(ID_USUARIO_LOGADO);
        if (historico.isEmpty()) {
            System.out.println("Nenhum registro histórico encontrado.");
        } else {
            System.out.println("\n--- Historico de Patrimonio Liquido ---");
            for (Historico h : historico) {
                System.out.println(h); // toString() de Historico formata a saída
            }
        }
    }

    // F3.1: Registro do Histórico de Patrimônio [cite: 46]
    private static void salvarPatrimonioAtual() {
        System.out.println("Calculando patrimonio liquido atual...");
        BigDecimal totalAtivos = calcularTotalAtivos();
        BigDecimal totalPassivos = calcularTotalPassivos();
        BigDecimal patrimonioLiquido = totalAtivos.subtract(totalPassivos);
        
        LocalDate hoje = LocalDate.now();
        int mes = hoje.getMonthValue();
        int ano = hoje.getYear();

        System.out.printf("Patrimonio Líquido atual (Mes/Ano: %d/%d): R$ %.2f\n", mes, ano, patrimonioLiquido);
        System.out.print("Deseja salvar/atualizar este valor no historico? (S/N): ");
        String confirmar = scanner.nextLine();

        if (confirmar.equalsIgnoreCase("S")) {
            Historico historico = new Historico(null, mes, ano, patrimonioLiquido, ID_USUARIO_LOGADO);
            historicoDAO.salvarHistorico(historico);
            System.out.println("Histórico salvo com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }


    // --- UTILITÁRIOS ---

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
}