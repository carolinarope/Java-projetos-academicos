/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaagenciaviagens;

/**
 *
 * @author Carolina
 */
public class Hospedagem {
    private String descricao;
    private float valorD;

    public Hospedagem(String descricao, float valorD) {
        this.descricao = descricao;
        this.valorD = valorD;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorD() {
        return valorD;
    }

    public void setValorD(float valorD) {
        this.valorD = valorD;
    }
    
}
