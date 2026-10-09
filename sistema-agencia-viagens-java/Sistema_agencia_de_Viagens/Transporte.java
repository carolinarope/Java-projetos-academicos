/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaagenciaviagens;

/**
 *
 * @author Carolina
 */
public class Transporte {
    private String tipo;
   private float valorT;

    public Transporte(String tipo, float valorT) {
        this.tipo = tipo;
        this.valorT = valorT;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValorT() {
        return valorT;
    }

    public void setValorT(float valorT) {
        this.valorT = valorT;
    }
   
    
}
