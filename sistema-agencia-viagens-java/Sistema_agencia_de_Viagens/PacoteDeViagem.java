/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaagenciaviagens;

/**
 *
 * @author Carolina
 */
public class PacoteDeViagem {
    private Transporte transporte;
    private Hospedagem hospedagem;
    private String destino;
    private int qtddias;

    public PacoteDeViagem(Transporte transporte, Hospedagem hospedagem, String destino, int qtddias) {
       this.transporte = transporte;
    this.hospedagem = hospedagem;
    this.destino = destino;
    this.qtddias = qtddias;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getQtddias() {
        return qtddias;
    }

    public void setQtddias(int qtddias) {
        this.qtddias = qtddias;
    }
    
    public float calcularTotalHospedagem(){
        return this.getQtddias() * this.getHospedagem().getValorD();
    }
    public float calcularLucro(float valorInformado, float margem){
    float valorDoLucro = valorInformado * (margem/100);
    return valorInformado + valorDoLucro;
    }
    
    
     public float totalPacote(float margem, float taxasAdicionais) {
    float valorT = this.getTransporte().getValorT();

    float totalHosp = this.calcularTotalHospedagem();

    
    float valorBase = valorT + totalHosp;
    float valorComLucro = this.calcularLucro(valorBase, margem);

    float valorFinal = valorComLucro + taxasAdicionais;
    return valorFinal;
}  
    }
