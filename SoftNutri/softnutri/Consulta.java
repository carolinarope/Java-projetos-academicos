/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softnutri;

/**
 *
 * @author Carolina
 */
public class Consulta {
    private String nome;
    private String cpf;
    private String telefone;
    private int idade;
    private String data_consulta;
    private boolean ja_paciente;
    private String observacoes;
    private boolean ja_realizado;

    public Consulta(String nome, String cpf, String telefone, String data_consulta, boolean ja_paciente) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.data_consulta = data_consulta;
        this.ja_paciente = ja_paciente;
        this.observacoes = "";
        this.ja_realizado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public Boolean getJa_paciente() {
        return ja_paciente;
    }

    public void setJa_paciente(Boolean ja_paciente) {
        this.ja_paciente = ja_paciente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getJa_realizado() {
        return ja_realizado;
    }

    public void setJa_realizado(Boolean ja_realizado) {
        this.ja_realizado = ja_realizado;
    }
    
    
}
