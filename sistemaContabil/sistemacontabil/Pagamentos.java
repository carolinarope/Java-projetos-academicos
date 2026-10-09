/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontabil;

/**
 *
 * @author Carolina
 */
import java.util.ArrayList;
import java.util.List;

public class Pagamentos {
    private String nomeEmpresa;
    private List<Imposto> listaDeImpostos;

    public Pagamentos(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.listaDeImpostos = new ArrayList<>();
    }
    public void adicionarImposto(Imposto imposto){
        this.listaDeImpostos.add(imposto);
        
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
    public List<Imposto> getListaDeImpostos(){
        return listaDeImpostos;
    }
}
