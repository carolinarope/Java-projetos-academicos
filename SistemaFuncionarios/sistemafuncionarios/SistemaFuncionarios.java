
package sistemafuncionarios;

/**
 *
 * @author Carolina
 */
import java.util.Scanner;
public class SistemaFuncionarios {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Funcionario[] listaDeFuncionarios = new Funcionario[10];
        int totalCadastrados = 0;
          
        System.out.println("====== SISTEMA DE RH ======");
        while (totalCadastrados < 10){
           System.out.println("\n--- Cadastro do Funcionario" + (totalCadastrados + 1) + "---");
           System.out.print("Qual o tipo?(1- Assalariado | 2 - Horista): ");
           int tipo = entrada.nextInt();
            entrada.nextLine();
           
             System.out.print("Nome: ");
            String nome = entrada.nextLine();
            System.out.print("CPF: ");
            String cpf = entrada.nextLine();
            System.out.print("Endereco: ");
            String endereco = entrada.nextLine();
            System.out.print("Telefone: ");
            String telefone = entrada.nextLine();
            System.out.print("Setor: ");
            String setor = entrada.nextLine();   
            
            if(tipo == 1){
                System.out.print("Salario Mensal (R$) ");
                float salario = entrada.nextFloat();
                entrada.nextLine();
                
               listaDeFuncionarios[totalCadastrados] = new Assalariado(nome,cpf,endereco,telefone,setor,salario);
                
            }
            else if (tipo == 2){
                System.out.print("Horas trabalhadas no mês: ");
                float horas = entrada.nextFloat();
                System.out.print("Valor da Hora (R$): ");
                float valorHora = entrada.nextFloat();
                entrada.nextLine();
                listaDeFuncionarios[totalCadastrados] = new Horista(nome, cpf, endereco, telefone, setor, horas, valorHora);
            } else {
                System.out.println("Tipo invalido! Tente novamente.");
                continue; 
            }
            totalCadastrados++;
   
            if (totalCadastrados < 10) {
                System.out.print("\nDeseja cadastrar mais um funcionario? (S/N): ");
                String resposta = entrada.nextLine();
                if (resposta.equalsIgnoreCase("N")) {
                    break; 
                }
            }
        }
        System.out.println("\n====== FOLHA DE PAGAMENTO (ATUAL) ======");
        for (int i = 0; i < totalCadastrados; i++) {
            System.out.println("Nome: " + listaDeFuncionarios[i].getNome() + 
                               " | CPF: " + listaDeFuncionarios[i].getCpf() + 
                               " | Pagamento: R$ " + listaDeFuncionarios[i].calcularPagamento());
        }
        System.out.println("\n====== HORA DO AUMENTO ======");
        System.out.print("Qual a porcentagem de aumento geral? (%): ");
        float porcentagem = entrada.nextFloat();

        System.out.println("\n====== FOLHA DE PAGAMENTO (ATUALIZADA) ======");
        for (int i = 0; i < totalCadastrados; i++) {
            
            // O Java descobre sozinho de qual filha é o método!
            listaDeFuncionarios[i].aplicarAumento(porcentagem);
            
            System.out.println("Nome: " + listaDeFuncionarios[i].getNome() + 
                               " | Novo Pagamento: R$ " + listaDeFuncionarios[i].calcularPagamento());
        }
        System.out.println("\nSistema encerrado.");
        entrada.close();
        }
    }
    
