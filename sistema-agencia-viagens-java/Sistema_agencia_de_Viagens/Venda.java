/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaagenciaviagens;

/**
 *
 * @author Carolina
 */
public class Venda {
    private String nomeC;
   private String formapagamento;
   private PacoteDeViagem pacoteDeViagem;

    public Venda(String nomeC, String formapagamento, PacoteDeViagem pacoteDeViagem) {
        this.nomeC = nomeC;
        this.formapagamento = formapagamento;
        this.pacoteDeViagem = pacoteDeViagem;
    }

    public String getNomeC() {
        return nomeC;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    public PacoteDeViagem getPacoteDeViagem() {
        return pacoteDeViagem;
    }

    public void setPacoteDeViagem(PacoteDeViagem pacoteDeViagem) {
        this.pacoteDeViagem = pacoteDeViagem;
    }
  
public float converterParaReais(float valorEmDolar, float cotacaoDia) {
    return valorEmDolar * cotacaoDia;
}

public String mostrarResumo(float margem, float taxas, float cotacao) {
    float totalDolar = this.pacoteDeViagem.totalPacote(margem, taxas);

    float totalReais = this.converterParaReais(totalDolar, cotacao);

    return "Cliente: " + this.nomeC + 
           "\nTotal em Dólar: US$ " + totalDolar + 
           "\nTotal em Reais: R$ " + totalReais;
}
}
