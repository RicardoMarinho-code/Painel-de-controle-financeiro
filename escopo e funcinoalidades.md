# Visão Geral do Projeto: Painel de Controle Financeiro

## 1. Escopo (Tema)

O projeto é um **Painel de Controle Financeiro**.

O objetivo principal do sistema é fornecer a um usuário as ferramentas necessárias para realizar a gestão de suas finanças pessoais. O foco é permitir o registro e acompanhamento de todos os seus bens e direitos (Ativos) e suas dívidas e obrigações (Passivos).

Além disso, o sistema se propõe a oferecer funcionalidades de orçamentação e acompanhamento do patrimônio líquido ao longo do tempo, permitindo uma visão clara da saúde financeira do usuário.

## 2. Funcionalidades do Sistema

Com base na estrutura do banco de dados e nas classes de acesso a dados (DAO), o sistema suporta as seguintes funcionalidades:

* **Gestão de Usuários:**
    * O sistema é multiusuário, onde cada registro financeiro está estritamente ligado a um usuário específico.
    * Armazena informações básicas do usuário (nome, email, senha).

* **Gestão de Ativos (CRUD Completo):**
    * **Create:** Permite ao usuário adicionar novos ativos (ex: "Ações da Empresa X", "Poupança").
    * **Read:** Permite ao usuário listar todos os seus ativos cadastrados ou buscar um ativo específico por ID.
    * **Update:** Permite ao usuário atualizar informações de um ativo existente (descrição ou valor).
    * **Delete:** Permite ao usuário remover um ativo.

* **Gestão de Passivos (CRUD Completo):**
    * **Create:** Permite ao usuário adicionar novos passivos (ex: "Financiamento do Apartamento").
    * **Read:** Permite ao usuário listar todos os seus passivos cadastrados ou buscar um passivo específico por ID.
    * **Update:** Permite ao usuário atualizar informações de um passivo existente (descrição ou valor).
    * **Delete:** Permite ao usuário remover um passivo.

* **Gestão de Orçamento:**
    * Permite a criação de um orçamento base por mês/ano para um usuário.
    * Permite a definição de categorias dentro desse orçamento (ex: "Alimentação", "Transporte").
    * Permite o acompanhamento de valores planejados contra valores realizados para cada categoria.

* **Acompanhamento de Histórico Financeiro:**
    * O sistema está preparado para armazenar um "snapshot" (registro) do patrimônio líquido (Ativos - Passivos) do usuário, mês a mês.
    * Garante que só exista um registro de histórico por mês/ano para cada usuário, permitindo a visualização da evolução patrimonial.