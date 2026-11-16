# Como executar o Cenaflix

Este guia reúne os passos para configurar e executar o Cenaflix localmente. O projeto foi desenvolvido em **Java 21**, com **NetBeans/Ant**, interface **Swing** e persistência em **MySQL via JDBC**.

[← Voltar à apresentação do Cenaflix](../README.md)

## Pré-requisitos

- **JDK 21**;
- **Apache NetBeans** com suporte a projetos Java SE/Ant;
- **MySQL Server** instalado e em execução (o MySQL Workbench pode ser usado para executar o script SQL);
- **MySQL Connector/J 9.7.0**.

## 1. Preparar o banco de dados

No MySQL Workbench, abra e execute o arquivo [`database/schema_exemplo.sql`](../database/schema_exemplo.sql).

O script cria a base `cenaflix` e a tabela `filmes`, com as colunas `id`, `nome`, `datalancamento` e `categoria`. A tabela começa vazia: os registros podem ser inseridos pela aplicação.

**Observação:** este é um esquema de exemplo compatível com o código publicado. Não é o script original fornecido durante o curso, no qual o banco era chamado `ATIVIDADE1`.

## 2. Adicionar o driver JDBC

1. Baixe o [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/).
2. Na raiz do projeto, coloque o arquivo `mysql-connector-j-9.7.0.jar` na pasta `driver/` (crie a pasta se necessário).
3. No NetBeans, confira a referência em **Propriedades do projeto → Bibliotecas**.

O `.jar` do driver não está incluído no repositório. A configuração atual espera **esse nome e esse caminho**. Se utilizar outra versão, ajuste as referências em `nbproject/project.properties`, `build.xml` e `manifest.mf`.

## 3. Configurar a conexão com o MySQL

A classe `Conexao.java` lê três **variáveis de ambiente**, sem colocar credenciais no código:

| Variável | Valor ou orientação |
| --- | --- |
| `CENAFLIX_DB_URL` | `jdbc:mysql://localhost:3306/cenaflix` |
| `CENAFLIX_DB_USER` | Usuário do MySQL com permissão para acessar e alterar a base |
| `CENAFLIX_DB_PASSWORD` | Senha desse usuário, configurada apenas na sua máquina |

**No Windows:** abra *Editar as variáveis de ambiente da sua conta*, cadastre os três valores em **Variáveis de usuário** e depois **reinicie o NetBeans**.

`localhost` indica o computador em que o MySQL está instalado. Se a porta ou o nome do banco forem diferentes, adapte a URL. O projeto utiliza `System.getenv()` e **não carrega arquivos `.env` automaticamente**.

## 4. Abrir e iniciar o projeto

1. No NetBeans, escolha **Arquivo → Abrir projeto** e selecione a pasta `Cenaflix_java_jdbc` (a que contém `build.xml`).
2. Confira se o projeto utiliza **JDK 21**.
3. Execute **Limpar e construir** para compilar e gerar o JAR.
4. Execute o projeto pela IDE. A classe principal é `cenaflix.Cenaflix`.
5. Cadastre um filme e abra **Consultar Filmes** para testar a listagem, os filtros, a alteração e a exclusão.

### Executar o JAR fora da IDE

Após a compilação e com as variáveis de ambiente configuradas, abra um terminal na pasta do projeto e execute:

```bash
java -jar dist/cenaflix_2.jar
```

Mantenha a pasta `dist/lib/` junto do JAR, pois ela contém o driver copiado durante a construção. O MySQL precisa estar acessível durante o uso.

## Se ocorrer um erro

- **Driver não encontrado na compilação:** confira se `driver/mysql-connector-j-9.7.0.jar` existe com esse nome exato.
- **Erro de conexão:** confira se o MySQL está em execução, se o banco `cenaflix` foi criado e se as três variáveis de ambiente foram configuradas.
- **Variáveis de ambiente não reconhecidas:** feche e abra novamente o NetBeans após configurá-las.

[← Voltar à apresentação do Cenaflix](../README.md)
