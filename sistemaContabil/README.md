# 🧾 Sistema Contábil

Projeto acadêmico desenvolvido em **Java** durante minha formação técnica em **Desenvolvimento de Sistemas pelo Senac**.

O projeto foi criado para praticar **interfaces, polimorfismo, coleções e implementação de regras de cálculo** em um contexto relacionado a impostos.

> 📚 Projeto acadêmico desenvolvido durante os estudos de Java. O código presente neste repositório corresponde à versão desenvolvida na atividade.

## 🎯 Objetivo

Criar uma aplicação simples para cadastrar diferentes tipos de impostos e gerar um relatório com os valores calculados.

## 🖥️ Funcionalidades

- Cadastro de empresa
- Cadastro de PIS
- Cadastro de IPI
- Armazenamento dos impostos em uma lista
- Cálculo dos valores
- Exibição de relatório

## 🧩 Conceitos praticados

- Java
- Programação Orientada a Objetos
- Interfaces
- Implementação de interfaces
- Polimorfismo
- `List`
- `ArrayList`
- Classes e objetos
- Encapsulamento
- Construtores
- Métodos
- `Scanner`
- Regras de cálculo

## 📁 Estrutura

```text
sistemaContabil/
├── README.md
└── sistemacontabil/
    ├── Imposto.java
    ├── Ipi.java
    ├── Pis.java
    ├── Pagamentos.java
    └── SistemaContabil.java
```

## 📚 Contexto acadêmico

Projeto desenvolvido durante o curso Técnico em **Desenvolvimento de Sistemas pelo Senac** para praticar interfaces, polimorfismo e coleções.

Os arquivos de configuração e compilação do NetBeans não fazem parte desta publicação; o repositório apresenta o código-fonte Java e sua documentação.

## 🚀 O que pratiquei

A interface `Imposto` define um contrato comum para os diferentes tipos de imposto. As classes `Pis` e `Ipi` implementam esse contrato e podem ser armazenadas juntas em uma coleção `List<Imposto>`.

Esse exercício ajudou a praticar a utilização de polimorfismo em um cenário baseado em regras de negócio.
