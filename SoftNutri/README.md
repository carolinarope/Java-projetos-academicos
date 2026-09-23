# 🥗 SoftNutri

Sistema desktop desenvolvido em **Java Swing** durante minha formação técnica em **Desenvolvimento de Sistemas pelo Senac**.

O projeto foi desenvolvido para praticar a construção de uma aplicação com interface gráfica, organização de classes, armazenamento de dados em memória, validação de informações e implementação de regras de negócio relacionadas ao cadastro e gerenciamento de consultas nutricionais.

> 📚 Projeto acadêmico desenvolvido a partir dos requisitos trabalhados durante o curso. O código presente neste repositório corresponde à versão desenvolvida durante a atividade.

## 🎯 Objetivo

Desenvolver uma aplicação capaz de organizar informações de pacientes e consultas nutricionais por meio de uma interface gráfica.

## 🖥️ Funcionalidades

- Cadastro de consultas
- Validação de campos
- Validação de CPF
- Validação de telefone
- Validação de data
- Visualização de consultas em tabela
- Exclusão de consulta
- Acesso aos detalhes da consulta
- Registro de observações
- Finalização de consulta
- Atualização do status da consulta

## 🧩 Conceitos praticados

- Java
- Programação Orientada a Objetos
- Classes e objetos
- Encapsulamento
- Construtores
- Métodos
- Organização em pacotes
- `ArrayList`
- Java Swing
- `JFrame`
- `JTable`
- `JOptionPane`
- Campos e componentes de interface
- Eventos
- Validação de dados
- Regras de negócio
- Manipulação de datas com `LocalDate`

## 🛠️ Tecnologias

- Java
- Java Swing
- NetBeans
- GUI Builder do NetBeans

## 📁 Estrutura principal

```text
SoftNutri/
├── src/
│   └── softnutri/
│       ├── Consulta.java
│       ├── Dados.java
│       ├── SoftNutri.java
│       ├── TelaCadastro.java
│       ├── TelaCadastro.form
│       ├── TelaDetalhesConsulta.java
│       ├── TelaDetalhesConsulta.form
│       ├── TelaInicial.java
│       └── TelaInicial.form
│
├── nbproject/
├── build.xml
└── manifest.mf
```

### Principais classes

**Consulta.java**  
Representa os dados de uma consulta e utiliza atributos, construtor, getters e setters.

**Dados.java**  
Mantém a coleção de consultas utilizando `ArrayList`.

**TelaCadastro.java**  
Responsável pela entrada dos dados e pelas validações do cadastro.

**TelaInicial.java**  
Apresenta as consultas cadastradas em uma `JTable` e permite acessar as ações principais.

**TelaDetalhesConsulta.java**  
Permite visualizar uma consulta, registrar observações e finalizar o atendimento.

## 💾 Armazenamento

Nesta versão acadêmica, os dados são mantidos em memória por meio de um `ArrayList`.

Portanto, esta versão não utiliza banco de dados nem persistência em arquivo.

## 🚀 Evolução

Este projeto representa uma etapa importante da minha prática em Java porque reúne conceitos estudados separadamente em uma aplicação com várias telas, validações e regras de negócio.

Meu próximo passo de evolução é aprofundar persistência de dados, banco de dados, APIs e desenvolvimento backend.
