package cenaflix;

/**
 * Classe principal de inicialização da aplicação Cenaflix.
 * Inicia a interface gráfica exibindo a tela inicial de cadastro e navegação.
 * 
 * @author Carolina
 * @version 2.0
 */
public class Cenaflix {

    /**
     * Ponto de entrada principal da aplicação.
     * Define o visual Nimbus (se disponível) e exibe a {@link TelaInicial}.
     * 
     * @param args argumentos de linha de comando.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Cenaflix.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}
