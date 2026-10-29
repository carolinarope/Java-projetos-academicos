package cenaflix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerencia a conexão JDBC com o MySQL.
 * As credenciais são lidas de variáveis de ambiente, nunca do código-fonte.
 *
 * @author Carolina
 * @version 2.1
 */
public class Conexao {

    /**
     * Abre uma conexão com o banco de dados configurado na máquina.
     *
     * @return conexão ativa ou {@code null} se faltar configuração ou a conexão falhar.
     */
    public Connection getConexao() {
        String url = System.getenv("CENAFLIX_DB_URL");
        String usuario = System.getenv("CENAFLIX_DB_USER");
        String senha = System.getenv("CENAFLIX_DB_PASSWORD");

        if (url == null || url.isBlank() || usuario == null || usuario.isBlank() || senha == null) {
            System.err.println("Configure CENAFLIX_DB_URL, CENAFLIX_DB_USER e CENAFLIX_DB_PASSWORD antes de executar.");
            return null;
        }

        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            return null;
        }
    }
}
