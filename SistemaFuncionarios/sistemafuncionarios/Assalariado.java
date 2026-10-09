/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemafuncionarios;

/**
 *
 * @author Carolina
 */
public class Assalariado extends Funcionario{
    private float salarioMensal;

    public Assalariado(String nome,String cpf, String endereco, String telefone, String setor,float salarioMensal) {
        super(nome, cpf, endereco, telefone, setor);
        this.salarioMensal = salarioMensal;
    }

    public float getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(float salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    @Override
    public float calcularPagamento() {
        return this.salarioMensal;
    }
    

    @Override
    public void aplicarAumento(float percentual) {
        float valorAumento = this.salarioMensal * (percentual / 100);
        this.salarioMensal = this.salarioMensal + valorAumento;
    }
        
    
    
}
