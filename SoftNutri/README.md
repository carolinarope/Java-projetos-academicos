# 🥗 SoftNutri

Sistema desktop desenvolvido em **Java Swing** durante minha formação técnica em **Desenvolvimento de Sistemas pelo Senac**.

O projeto foi desenvolvido para praticar a construção de uma aplicação com interface gráfica, organização de classes, validação de dados e implementação de regras de negócio relacionadas ao cadastro e gerenciamento de consultas nutricionais.

> 📚 **Projeto acadêmico:** esta aplicação foi desenvolvida durante o curso a partir dos requisitos propostos na atividade. O código presente neste repositório corresponde à versão desenvolvida durante os estudos.

## 🎯 Objetivo

Desenvolver uma aplicação capaz de cadastrar, consultar e gerenciar informações relacionadas a consultas nutricionais por meio de uma interface gráfica.

## 🖥️ Funcionalidades

* Cadastro de consultas
* Validação dos dados informados
* Validação de CPF
* Validação de telefone
* Validação de data
* Visualização das consultas cadastradas
* Exibição dos dados em tabela
* Exclusão de consulta
* Visualização dos detalhes da consulta
* Registro de observações
* Finalização da consulta
* Atualização do status da consulta

## 🧩 Conceitos praticados

* Java
* Programação Orientada a Objetos
* Classes e objetos
* Encapsulamento
* Construtores
* Métodos
* Organização em pacotes
* `ArrayList`
* Java Swing
* `JFrame`
* `JTable`
* `JOptionPane`
* Eventos de interface
* Validação de dados
* Regras de negócio
* `LocalDate`

## 🛠️ Tecnologias

* **Java**
* **Java Swing**
* **NetBeans**

## 📁 Estrutura do projeto

Para manter o repositório simples de navegar, esta publicação contém a documentação e os arquivos-fonte Java:

```text
SoftNutri/
├── README.md
└── softnutri/
    ├── Consulta.java
    ├── Dados.java
    ├── SoftNutri.java
    ├── TelaCadastro.java
    ├── TelaDetalhesConsulta.java
    └── TelaInicial.java
```

### Principais classes

**Consulta.java**
Representa os dados de uma consulta e contém os atributos, construtor, getters e setters utilizados pelo sistema.

**Dados.java**
Responsável por manter a coleção de consultas utilizando `ArrayList`.

**SoftNutri.java**
Classe de inicialização da aplicação.

**TelaCadastro.java**
Responsável pelo cadastro das consultas e pelas validações dos dados informados.

**TelaInicial.java**
Apresenta as consultas cadastradas em uma tabela e disponibiliza as principais ações do sistema.

**TelaDetalhesConsulta.java**
Permite visualizar os detalhes de uma consulta, registrar observações e finalizar o atendimento.

## 💾 Armazenamento dos dados

Nesta versão acadêmica, as consultas são armazenadas em memória utilizando `ArrayList`.

Por isso, esta versão não utiliza banco de dados nem persistência em arquivo.

## 📚 Contexto acadêmico

Projeto desenvolvido como atividade prática durante o curso Técnico em **Desenvolvimento de Sistemas pelo Senac**.

O objetivo principal foi aplicar, em uma única aplicação, conceitos estudados ao longo das aulas de Java e Programação Orientada a Objetos.

## 🚀 Evolução

Este projeto representa uma etapa da minha evolução prática em Java, reunindo conceitos de programação, interface gráfica, validação e regras de negócio em uma aplicação com múltiplas telas.

Como próximos passos de estudo, pretendo aprofundar conhecimentos em **persistência de dados, banco de dados, APIs e desenvolvimento backend**.
