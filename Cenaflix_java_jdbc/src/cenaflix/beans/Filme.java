package cenaflix.beans;

import java.time.LocalDate;

/**
 * Representa a entidade Filme no sistema Cenaflix.
 * Armazena as informações básicas referentes a cada filme: identificador,
 * título/nome, data de lançamento e categoria.
 * 
 * @author Carolina
 * @version 2.0
 */
public class Filme {

    private int id;
    private String nome;
    private LocalDate datalancamento;
    private String categoria;

    /**
     * Construtor padrão da classe Filme.
     */
    public Filme() {
    }

    /**
     * Construtor parametrizado para facilitar a criação de novos filmes.
     * 
     * @param nome título do filme.
     * @param datalancamento data em que o filme foi lançado.
     * @param categoria gênero ou categoria à qual o filme pertence.
     */
    public Filme(String nome, LocalDate datalancamento, String categoria) {
        this.nome = nome;
        this.datalancamento = datalancamento;
        this.categoria = categoria;
    }

    /**
     * Construtor completo com identificador único.
     * 
     * @param id identificador do filme no banco de dados.
     * @param nome título do filme.
     * @param datalancamento data de lançamento do filme.
     * @param categoria gênero ou categoria do filme.
     */
    public Filme(int id, String nome, LocalDate datalancamento, String categoria) {
        this.id = id;
        this.nome = nome;
        this.datalancamento = datalancamento;
        this.categoria = categoria;
    }

    /**
     * Obtém o identificador do filme.
     * 
     * @return identificador numérico único (ID).
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do filme.
     * 
     * @param id identificador numérico único.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o título ou nome do filme.
     * 
     * @return nome do filme.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o título ou nome do filme.
     * 
     * @param nome nome do filme.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a data de lançamento do filme.
     * 
     * @return data de lançamento no formato {@link LocalDate}.
     */
    public LocalDate getDatalancamento() {
        return datalancamento;
    }

    /**
     * Define a data de lançamento do filme.
     * 
     * @param datalancamento data no formato {@link LocalDate}.
     */
    public void setDatalancamento(LocalDate datalancamento) {
        this.datalancamento = datalancamento;
    }

    /**
     * Obtém a categoria ou gênero do filme.
     * 
     * @return categoria do filme.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria ou gênero do filme.
     * 
     * @param categoria categoria do filme.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
