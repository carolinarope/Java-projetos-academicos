package cenaflix.dao;

import cenaflix.Conexao;
import cenaflix.beans.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (Data Access Object - DAO) para operações pertinentes aos filmes.
 * Responsável pelas operações de CRUD (Create, Read, Update, Delete) e filtros dinâmicos
 * no banco de dados do Cenaflix.
 * 
 * Cada operação gerencia de forma segura sua própria conexão via try-with-resources.
 * 
 * @author Carolina
 * @version 2.0
 */
public class FilmeDAO {

    /**
     * Estabelece uma conexão ativa com o banco de dados Cenaflix.
     * 
     * @return {@link Connection} aberta com o banco.
     * @throws SQLException se a conexão não puder ser estabelecida.
     */
    private Connection abrirConexao() throws SQLException {
        Connection conexao = new Conexao().getConexao();
        if (conexao == null) {
            throw new SQLException("Não foi possível conectar ao banco de dados do Cenaflix.");
        }
        return conexao;
    }

    /**
     * Insere um novo filme na tabela de filmes.
     * 
     * @param filme objeto {@link Filme} contendo nome, data de lançamento e categoria.
     * @throws SQLException se ocorrer erro na execução do comando SQL de inserção.
     */
    public void inserir(Filme filme) throws SQLException {
        String sql = "INSERT INTO filmes (nome, datalancamento, categoria) VALUES (?, ?, ?)";
        try (Connection conn = abrirConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, filme.getNome());
            if (filme.getDatalancamento() == null) {
                stmt.setNull(2, Types.DATE);
            } else {
                stmt.setDate(2, java.sql.Date.valueOf(filme.getDatalancamento()));
            }
            stmt.setString(3, filme.getCategoria());
            stmt.executeUpdate();
        }
    }

    /**
     * Consulta e retorna um filme a partir de seu identificador único (ID).
     * 
     * @param id identificador do filme desejado.
     * @return objeto {@link Filme} encontrado ou {@code null} caso não exista.
     * @throws SQLException se ocorrer erro durante a execução da consulta SQL.
     */
    public Filme getFilme(int id) throws SQLException {
        String sql = "SELECT id, nome, datalancamento, categoria FROM filmes WHERE id = ?";
        try (Connection conn = abrirConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFilme(rs);
                }
            }
        }
        return null;
    }

    /**
     * Lista todos os filmes cadastrados na base de dados sem aplicação de filtros.
     * 
     * @return {@link List} contendo todos os filmes encontrados.
     * @throws SQLException se houver falha na consulta SQL.
     */
    public List<Filme> listarTodos() throws SQLException {
        return listarTodos(null);
    }

    /**
     * Lista filmes cadastrados com suporte a filtro por categoria.
     * Se a categoria informada for nula, vazia ou "Todas", retorna todos os registros.
     * Realiza busca exata ou por início do termo, evitando que categorias com sufixos semelhantes
     * (por exemplo "Animação" ao buscar por "Ação") se misturem indevidamente.
     * 
     * @param categoria termo ou nome da categoria a ser filtrada.
     * @return {@link List} de filmes correspondentes ao filtro informado.
     * @throws SQLException se houver falha na consulta SQL.
     */
    public List<Filme> listarTodos(String categoria) throws SQLException {
        boolean filtrar = categoria != null && !categoria.isBlank() && !categoria.equalsIgnoreCase("Todas");
        String sql = "SELECT id, nome, datalancamento, categoria FROM filmes"
                + (filtrar ? " WHERE LOWER(categoria) = LOWER(?) OR LOWER(categoria) LIKE LOWER(?)" : "") 
                + " ORDER BY id";
        List<Filme> filmes = new ArrayList<>();
        try (Connection conn = abrirConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (filtrar) {
                String termo = categoria.trim();
                stmt.setString(1, termo);
                stmt.setString(2, termo + "%");
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    filmes.add(mapearFilme(rs));
                }
            }
        }
        return filmes;
    }

    /**
     * Recupera a lista de categorias distintas existentes no banco de dados
     * para preenchimento dinâmico de componentes visuais (ex: ComboBox).
     * 
     * @return {@link List} de {@link String} contendo os nomes das categorias únicas.
     * @throws SQLException se houver falha na consulta SQL.
     */
    public List<String> listarCategorias() throws SQLException {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT DISTINCT categoria FROM filmes WHERE categoria IS NOT NULL AND categoria <> '' ORDER BY categoria";
        try (Connection conn = abrirConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString(1));
            }
        }
        return categorias;
    }

    /**
     * Atualiza os dados de um filme existente (nome, data de lançamento e categoria)
     * a partir de seu identificador único.
     * 
     * @param filme objeto {@link Filme} com os novos dados e o ID correspondente.
     * @return {@code true} se o registro foi atualizado com sucesso; {@code false} caso contrário.
     * @throws SQLException se houver erro na execução do comando SQL de atualização.
     */
    public boolean atualizarFilme(Filme filme) throws SQLException {
        String sql = "UPDATE filmes SET nome = ?, datalancamento = ?, categoria = ? WHERE id = ?";
        try (Connection conn = abrirConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, filme.getNome());
            if (filme.getDatalancamento() == null) {
                stmt.setNull(2, Types.DATE);
            } else {
                stmt.setDate(2, java.sql.Date.valueOf(filme.getDatalancamento()));
            }
            stmt.setString(3, filme.getCategoria());
            stmt.setInt(4, filme.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Exclui um filme do banco de dados a partir de seu identificador único (ID).
     * 
     * @param id identificador do filme a ser excluído.
     * @return {@code true} se a exclusão foi realizada com sucesso; {@code false} caso nenhum registro tenha sido afetado.
     * @throws SQLException se houver erro na execução do comando SQL de exclusão.
     */
    public boolean excluirFilme(int id) throws SQLException {
        String sql = "DELETE FROM filmes WHERE id = ?";
        try (Connection conn = abrirConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Converte o registro atual do {@link ResultSet} em uma instância de {@link Filme}.
     * 
     * @param rs cursor posicionado no registro retornado pelo banco.
     * @return instância de {@link Filme} preenchida com os dados da linha.
     * @throws SQLException se ocorrer erro de leitura das colunas.
     */
    private Filme mapearFilme(ResultSet rs) throws SQLException {
        Filme filme = new Filme();
        filme.setId(rs.getInt("id"));
        filme.setNome(rs.getString("nome"));
        java.sql.Date data = rs.getDate("datalancamento");
        if (data != null) {
            filme.setDatalancamento(data.toLocalDate());
        }
        filme.setCategoria(rs.getString("categoria"));
        return filme;
    }
}
