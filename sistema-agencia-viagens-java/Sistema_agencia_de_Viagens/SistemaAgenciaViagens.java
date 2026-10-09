
package sistemaagenciaviagens;


import java.util.Scanner;
public class SistemaAgenciaViagens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner entrada = new Scanner(System.in);
        
        System.out.println("--- BEM-VINDO À AGÊNCIA DE VIAGENS ---");
      
       System.out.println("\n--- 1. DADOS DO TRANSPORTE ---");
        System.out.print("Tipo de transporte (Ex: Aéreo): ");
        String tipoTransp = entrada.nextLine();
        
        System.out.print("Valor do transporte em Dólar: US$ ");
        float valorTransp = entrada.nextFloat();
        
        Transporte transporte = new Transporte(tipoTransp, valorTransp);
        System.out.println("\n-- Dados da Hospedagem --");
        System.out.print("Nome do Hotel: ");
        String descHotel = entrada.nextLine();
        
        System.out.print("Valor da Diária (US$): ");
        float valorDiaria = entrada.nextFloat();
        entrada.nextLine();
        
        Hospedagem hospedagem = new Hospedagem(descHotel, valorDiaria);

     
        System.out.println("\n-- Dados do Pacote --");
        System.out.print("Destino da viagem: ");
        String destino = entrada.nextLine();
        
        System.out.print("Quantidade de dias: ");
        int qtdDias = entrada.nextInt();
        entrada.nextLine();
        
        PacoteDeViagem pacote = new PacoteDeViagem(transporte, hospedagem, destino, qtdDias);

     
        System.out.println("\n-- Dados da Venda --");
        System.out.print("Nome do Cliente: ");
        String nomeCliente = entrada.nextLine();
        
        System.out.print("Forma de Pagamento (Ex: Pix, Cartão): ");
        String formaPag = entrada.nextLine();

        // Colocando o pacote dentro da venda
        Venda venda = new Venda(nomeCliente, formaPag, pacote);
        System.out.println("\n-- Valores do Dia --");
        System.out.print("Margem de lucro da agência (%): ");
        float margem = entrada.nextFloat();
        
        System.out.print("Taxas adicionais (US$): ");
        float taxas = entrada.nextFloat();
        
        System.out.print("Cotação do Dólar hoje (R$): ");
        float cotacao = entrada.nextFloat();

        // --- 6. EXIBINDO O RESULTADO FINAL ---
        System.out.println("\n================================");
        System.out.println("          RECIBO FINAL          ");
        System.out.println("================================");
        
        // Chamamos o método que faz toda a matemática mágica lá na classe Venda
        System.out.println( venda.mostrarResumo(margem, taxas, cotacao) );

        // Liberando o atendente
        entrada.close();
    }
    
}
