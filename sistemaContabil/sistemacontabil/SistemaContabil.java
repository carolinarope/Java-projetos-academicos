/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemacontabil;

/**
 *
 * @author Carolina
 */
import java.util.Scanner;
public class SistemaContabil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    System.out.println("Insira o Nome da Empresa");
    String nomeEmpresa = entrada.nextLine();
    Pagamentos meusPagamentos = new Pagamentos(nomeEmpresa);
    while(true){
        System.out.println("\nQual Imposto deseja cadastar? (Digite PIS IPi ou 'pare' para encerrar):");
        String opcao = entrada.next();
    if (opcao.equalsIgnoreCase("pare")){
        break;
    }
    if (opcao.equalsIgnoreCase("PIS")){
        System.out.println("Digite o valor do debito");
        float debito = entrada.nextFloat();
        System.out.println("Digite o valor do credito");
        float credito = entrada.nextFloat();
        Pis impostoPis = new Pis(debito,credito);
        meusPagamentos.adicionarImposto(impostoPis);
        System.out.println("Pis adicionado com sucesso na lista!");
     
     }
    else if(opcao.equalsIgnoreCase("Ipi")){
        System.out.println("Digite o valor do produto:");
        float valorproduto = entrada.nextFloat();
        System.out.println("Digite o frete:");
        float frete = entrada.nextFloat();
        System.out.println("Digite a Aliquota (em%):");
        float valorAliquota = entrada.nextFloat();
        System.out.println(" Digite o seguro:");
        float seguro = entrada.nextFloat();
        System.out.println("Digite outras despesas");
        float outrasDespesas = entrada.nextFloat();
        Ipi impostoIpi = new Ipi(valorproduto, frete, valorAliquota, seguro, outrasDespesas);
        meusPagamentos.adicionarImposto(impostoIpi);
        System.out.println("IPI adicionado com sucesso na lista!");
    }else{
    System.out.println("Opcao invalida. Tente novamente.");    
    }
    }
    System.out.println("\n==========================================");
        System.out.println("Relatorio de Impostos da Empresa: " + meusPagamentos.getNomeEmpresa());
        System.out.println("==========================================");
    
    for (Imposto item : meusPagamentos.getListaDeImpostos()) {
        System.out.println("Descricao: " + item.getDescricao());
            System.out.println("Valor a pagar: R$ " + item.calcularImposto());
            System.out.println("------------------------------------------");
    }
        System.out.println("Programa encerrado.");
    }
    
}
