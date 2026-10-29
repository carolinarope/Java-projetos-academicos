package cenaflix;

import cenaflix.beans.Filme;
import cenaflix.dao.FilmeDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 * Tela inicial de cadastro de filmes da aplicação Cenaflix.
 * Permite cadastrar novos filmes informando título, data de lançamento e categoria,
 * além de disponibilizar acesso direto à tela de consulta e gerenciamento do CRUD.
 * 
 * @author Carolina
 * @version 2.0
 */
public class TelaInicial extends javax.swing.JFrame {

    /** Formatador estrito de data no padrão dia/mês/ano (dd/MM/uuuu). */
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Construtor da tela inicial.
     * Inicializa componentes visuais e configura máscara de entrada para data.
     */
    public TelaInicial() {
        initComponents();
        configurarMascaraData();
        setLocationRelativeTo(null);
    }

    /**
     * Configura a máscara do campo de data de lançamento para aceitar no padrão dd/MM/aaaa.
     */
    private void configurarMascaraData() {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            txtDataLancamento.setFormatterFactory(new DefaultFormatterFactory(mascara));
            txtDataLancamento.setValue(null);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível configurar a máscara do campo de data.",
                    "Erro de Configuração",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * Limpa todos os campos de texto do formulário de cadastro.
     */
    public void limparCampos() {
        txtNomeFilme.setText("");
        txtCategoria.setText("");
        txtDataLancamento.setValue(null);
        txtNomeFilme.requestFocusInWindow();
    }

    /**
     * Realiza a validação das informações digitadas e persiste o novo filme no banco.
     */
    private void cadastrarFilme() {
        String nomeFilme = txtNomeFilme.getText().trim();
        String dataTexto = txtDataLancamento.getText().trim();
        String categoria = txtCategoria.getText().trim();

        if (nomeFilme.isEmpty() || dataTexto.contains("_") || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios (Nome, Data e Categoria)!",
                    "Campos Obrigatórios",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (nomeFilme.length() > 150) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome do filme não pode ultrapassar 150 caracteres.",
                    "Validação de Campo",
                    JOptionPane.WARNING_MESSAGE
            );
            txtNomeFilme.requestFocusInWindow();
            return;
        }

        if (categoria.length() > 100) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome da categoria não pode ultrapassar 100 caracteres.",
                    "Validação de Campo",
                    JOptionPane.WARNING_MESSAGE
            );
            txtCategoria.requestFocusInWindow();
            return;
        }

        LocalDate dataLancamento;
        try {
            dataLancamento = LocalDate.parse(dataTexto, FORMATO_DATA);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Data inválida! Informe uma data válida no formato dd/MM/aaaa.",
                    "Data Inválida",
                    JOptionPane.WARNING_MESSAGE
            );
            txtDataLancamento.requestFocusInWindow();
            return;
        }

        Filme filme = new Filme();
        filme.setNome(nomeFilme);
        filme.setDatalancamento(dataLancamento);
        filme.setCategoria(categoria);

        try {
            FilmeDAO filmeDAO = new FilmeDAO();
            filmeDAO.inserir(filme);
            JOptionPane.showMessageDialog(
                    this,
                    "Filme cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limparCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível inserir os dados! Por favor, verifique os valores digitados e tente novamente.",
                    "Erro ao Cadastrar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCadastro = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        txtNomeFilme = new javax.swing.JTextField();
        txtDataLancamento = new javax.swing.JFormattedTextField();
        txtCategoria = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cenaflix - Cadastro de Filmes");
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CENAFLIX");

        lblCadastro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCadastro.setText("CADASTRO DE FILME");

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblNome.setText("Nome do Filme:");

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblData.setText("Data de Lançamento:");

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblCategoria.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblCategoria.setText("Categoria:");

        txtNomeFilme.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtDataLancamento.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtCategoria.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setToolTipText("Cadastrar filme no banco de dados");
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);

        btnLimpar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.setToolTipText("Limpar todos os campos");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        btnConsultar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnConsultar.setText("Consultar Filmes");
        btnConsultar.setToolTipText("Abrir tela de consulta, edição e exclusão de filmes");
        btnConsultar.addActionListener(this::btnConsultarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblData)
                            .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtNomeFilme, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtDataLancamento))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        cadastrarFilme();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        new forms.FormConsulta().setVisible(true);
    }//GEN-LAST:event_btnConsultarActionPerformed

    /**
     * Ponto de entrada para execução da tela inicial.
     * 
     * @param args argumentos de linha de comando.
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel lblCadastro;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JFormattedTextField txtDataLancamento;
    private javax.swing.JTextField txtNomeFilme;
    // End of variables declaration//GEN-END:variables
}
