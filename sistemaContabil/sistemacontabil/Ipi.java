/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontabil;

/**
 *
 * @author Carolina
 */
public class Ipi implements Imposto {
    private float valorproduto;
    private float frete;
    private float valorAliquota;
    private float seguro;
    private float outrasDespesas;

    public Ipi(float valorproduto, float frete, float valorAliquota, float seguro, float outrasDespesas) {
        this.valorproduto = valorproduto;
        this.frete = frete;
        this.valorAliquota = valorAliquota;
        this.seguro = seguro;
        this.outrasDespesas = outrasDespesas;
    }

    public float getValorproduto() {
        return valorproduto;
    }

    public void setValorproduto(float valorproduto) {
        this.valorproduto = valorproduto;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }

    public float getValorAliquota() {
        return valorAliquota;
    }

    public void setValorAliquota(float valorAliquota) {
        this.valorAliquota = valorAliquota;
    }

    public float getSeguro() {
        return seguro;
    }

    public void setSeguro(float seguro) {
        this.seguro = seguro;
    }

    public float getOutrasDespesas() {
        return outrasDespesas;
    }

    public void setOutrasDespesas(float outrasDespesas) {
        this.outrasDespesas = outrasDespesas;
    }
    @Override
    public String getDescricao(){
        return "IPI";
    }
    @Override
    public float calcularImposto(){
        return(this.valorproduto + this.frete + this.seguro + this.outrasDespesas) * (this.valorAliquota/ 100f);
    }
}