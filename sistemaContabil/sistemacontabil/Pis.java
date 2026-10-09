/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontabil;

/**
 *
 * @author Carolina
 */
public class Pis implements Imposto {
    private float debito;
    private float credito;

    public Pis(float debito, float credito) {
        this.debito = debito;
        this.credito = credito;
    }

    public float getDebito() {
        return debito;
    }

    public void setDebito(float debito) {
        this.debito = debito;
    }

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }
    @Override
    public String getDescricao(){
        return "PIS";
    }
    @Override
    public float calcularImposto(){
        return(this.debito - this.credito)* 0.0165f;
    }
}
