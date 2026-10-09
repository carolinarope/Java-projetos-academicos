/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemafuncionarios;

/**
 *
 * @author Carolina
 */
public class Horista extends Funcionario{
    private float horasTrabalhadas;
    private float valorDaHora;

    public Horista(String nome, String cpf, String endereco, String telefone, String setor, float horasTrabalhadas, float valorDaHora) {
        super(nome, cpf, endereco, telefone, setor);
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorDaHora = valorDaHora;
    }
  public float getHorasTrabalhadas() {
      return horasTrabalhadas;
}
   

    public void setHorasTrabalhadas(float horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public float getValorDaHora() {
        return valorDaHora;
    }

    public void setValorDaHora(float valorDaHora) {
        this.valorDaHora = valorDaHora;
    }
    
    @Override
    public float calcularPagamento() {
      return this.horasTrabalhadas * this.valorDaHora;
    
    }

    @Override
    public void aplicarAumento(float percentual) {
      float valorAumento = this.valorDaHora * (percentual / 100);
        this.valorDaHora = this.valorDaHora + valorAumento;  
    }

      
}
