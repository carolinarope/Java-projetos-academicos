# MySQL Connector/J

Este projeto utiliza o driver JDBC do MySQL, versão **9.7.0**. O arquivo binário não é versionado neste repositório.

1. Baixe o MySQL Connector/J pelo site oficial: https://dev.mysql.com/downloads/connector/j/
2. Coloque `mysql-connector-j-9.7.0.jar` nesta pasta `driver/`.
3. Confirme que o arquivo tem esse nome exato ou altere a referência em `nbproject/project.properties`, `build.xml` e `manifest.mf`.
4. Execute **Limpar e construir** no NetBeans. O Ant copia o driver para `dist/lib/` na construção do JAR.

Não adicione senhas, arquivos privados ou credenciais ao repositório. Configure `CENAFLIX_DB_URL`, `CENAFLIX_DB_USER` e `CENAFLIX_DB_PASSWORD` na máquina.
