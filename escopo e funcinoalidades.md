# 1. Visão Geral
O projeto "Painel de Controle Financeiro Pessoal" consiste no desenvolvimento de
uma aplicação desktop em Java, destinada a fornecer ao usuário uma visão clara
e consolidada de sua saúde financeira. A ferramenta integrará três pilares
fundamentais das finanças pessoais: o balanço patrimonial (ativos e passivos), o
planejamento orçamentário mensal (fluxo de caixa) e a análise da evolução
financeira ao longo do tempo. O objetivo é criar uma solução intuitiva e focada,
que auxilie na tomada de decisões financeiras e no acompanhamento de metas
de longo prazo.

# 2. Objetivos do Projeto
• O1: Fornecer uma visão clara do patrimônio líquido do usuário através do
gerenciamento de ativos e passivos.
• O2: Habilitar o planejamento de orçamentos mensais, permitindo a
definição de metas de gastos por categoria.
• O3: Vizualização dos gastos
• O4: Ser uma ferramenta de uso simples

# 3. Público-Alvo
Indivíduos, estudantes e jovens profissionais que desejam iniciar o controle de
suas finanças de forma estruturada, mas que consideram as ferramentas de
mercado excessivamente complexas ou detalhistas. O foco é em usuários que
preferem uma abordagem de planejamento macro em vez do registro de cada
transação individual.

# 4. Funcionalidades Detalhadas
O sistema será dividido em três módulos principais:

## Módulo 1: Balanço Patrimonial (Tela de Balanço)
• F1.1: Gerenciamento de Ativos
    o O usuário poderá cadastrar, editar e excluir seus ativos financeiros.
    o Cada ativo terá, no mínimo, os campos: Descrição (ex: "Poupança
Caixa”) e Valor (ex: 5000.00).
• F1.2: Gerenciamento de Passivos
    o O usuário poderá cadastrar, editar e excluir seus passivos (dívidas).
    o Cada passivo terá, no mínimo, os campos: Descrição (ex: "Fatura
Cartão Nubank") e Valor (ex: 850.00).
• F1.3: Cálculo Automático do Patrimônio Líquido
    o A tela deverá exibir, em tempo real, os seguintes totais calculados:
Total de Ativos, Total de Passivos.
    o O sistema calculará e exibirá de forma destacada o Patrimônio
Líquido (Total de Ativos - Total de Passivos).

## Módulo 2: Orçamento Mensal (Tela de Orçamento)
• F2.1: Seleção e Criação de Orçamento
    o O usuário poderá selecionar um Mês e Ano para visualizar ou criar
um novo plano orçamentário.
• F2.2: Gerenciamento de Categorias do Orçamento
    o Dentro de um orçamento mensal, o usuário poderá adicionar, editar
e remover categorias de despesas.
    o Cada categoria terá os campos: Nome (ex: "Alimentação"), Valor
Planejado e Valor Realizado. O campo Valor Realizado será editável
para que o usuário insira o total gasto no período.
• F2.3: Cálculo de Saldo por Categoria
    o O sistema exibirá uma coluna adicional na tabela de orçamento
com o Saldo (Valor Planejado - Valor Realizado), mostrando se o
usuário economizou ou gastou além do planejado naquela
categoria.
• F2.4: Utilitário de Cópia
    o Ao criar um novo orçamento, o usuário terá a opção de "Copiar
plano do mês anterior" para agilizar o preenchimento das categorias
e valores planejados.

## Módulo 3: Evolução Financeira (Tela de Evolução)
• F3.1: Registro do Histórico de Patrimônio
    o O sistema terá uma função (ex: um botão "Fechar Mês" ou um
registro automático) para salvar um registro do Patrimônio Líquido
do usuário em uma tabela de histórico, associado ao mês e ano
correspondentes.
• F3.2: Visualização do Histórico
    o Uma tela dedicada exibirá uma tabela simples com o histórico do
Patrimônio Líquido, permitindo que o usuário veja sua progressão
financeira ao longo do tempo (ex: Mês/Ano | Valor do Patrimônio).