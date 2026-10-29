package forms;

import cenaflix.beans.Filme;
import cenaflix.dao.FilmeDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela gráfica para consulta, filtro dinâmico, atualização e exclusão (CRUD)
 * de filmes cadastrados no sistema Cenaflix.
 * 
 * Permite listar dados em uma {@link javax.swing.JTable}, filtrar em tempo real
 * por categoria e realizar manutenção dos registros no banco de dados.
 * 
 * @author Carolina
 * @version 2.0
 */
public class FormConsulta extends javax.swing.JFrame {

    /** Formatador estrito de data no padrão dia/mês/ano (dd/MM/uuuu). */
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    /** Flag de controle para evitar disparos cíclicos ao recarregar a ComboBox de categorias. */
    private boolean atualizandoCategorias = false;

    /**
     * Cria e inicializa os componentes da tela de consulta de filmes.
     */
    public FormConsulta() {
        initComponents();
        configurarInicializacao();
    }

    /**
     * Configurações complementares executadas após a inicialização dos componentes visuais.
     */
    private void configurarInicializacao() {
        setLocationRelativeTo(null);
        carregarCategorias();
        carregarFilmes();
    }

    /**
     * Carrega as categorias existentes no banco de dados para preencher o ComboBox de filtro.
     */
    private void carregarCategorias() {
        String selecionada = (String) filtroCategoria.getSelectedItem();
        atualizandoCategorias = true;
        try {
            filtroCategoria.removeAllItems();
            filtroCategoria.addItem("Todas");
            List<String> categorias = new FilmeDAO().listarCategorias();
            for (String cat : categorias) {
                filtroCategoria.addItem(cat);
            }
            if (selecionada != null) {
                filtroCategoria.setSelectedItem(selecionada);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível carregar as categorias cadastradas! Verifique a conexão com o banco de dados.",
                    "Erro ao Carregar Categorias",
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            atualizandoCategorias = false;
        }
    }

    /**
     * Consulta os filmes no banco de dados aplicando os filtros informados (seleção ou texto)
     * e preenche a JTable com os resultados obtidos.
     */
    public void carregarFilmes() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        String categoriaSelecionada = (String) filtroCategoria.getSelectedItem();
        String textoFiltro = txtFiltroTexto.getText().trim();

        // Determina qual termo de busca utilizar (campo de texto prioritário para busca dinâmica)
        String termoBusca = null;
        if (!textoFiltro.isEmpty()) {
            termoBusca = textoFiltro;
        } else if (categoriaSelecionada != null && !categoriaSelecionada.equalsIgnoreCase("Todas")) {
            termoBusca = categoriaSelecionada;
        }

        try {
            List<Filme> lista = new FilmeDAO().listarTodos(termoBusca);
            for (Filme f : lista) {
                String dataFormatada = (f.getDatalancamento() != null)
                        ? f.getDatalancamento().format(FORMATO_DATA)
                        : "";
                modelo.addRow(new Object[]{
                    f.getId(),
                    f.getNome(),
                    dataFormatada,
                    f.getCategoria()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível realizar a consulta dos filmes! Verifique se o banco de dados está ativo.",
                    "Falha na Consulta",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Preenche os campos do formulário inferior com os dados do filme
     * correspondente à linha clicada na tabela.
     */
    private void selecionarFilmeDaTabela() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            campoId.setText(tabela.getValueAt(linhaSelecionada, 0).toString());
            campoNome.setText(tabela.getValueAt(linhaSelecionada, 1).toString());
            Object dataObj = tabela.getValueAt(linhaSelecionada, 2);
            campoData.setText(dataObj != null ? dataObj.toString() : "");
            campoCategoria.setText(tabela.getValueAt(linhaSelecionada, 3).toString());
        }
    }

    /**
     * Valida os campos preenchidos no formulário e monta um objeto {@link Filme}.
     * 
     * @return objeto {@link Filme} válido ou {@code null} caso haja inconsistência de dados.
     */
    private Filme validarEObterFilme() {
        String idTexto = campoId.getText().trim();
        String nome = campoNome.getText().trim();
        String dataTexto = campoData.getText().trim();
        String categoria = campoCategoria.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, selecione primeiro um filme na tabela para realizar a operação.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return null;
        }

        if (nome.isEmpty() || dataTexto.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Todos os campos (Nome, Data e Categoria) são de preenchimento obrigatório!",
                    "Campos Obrigatórios",
                    JOptionPane.WARNING_MESSAGE
            );
            return null;
        }

        if (nome.length() > 150) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome do filme não pode ultrapassar 150 caracteres.",
                    "Validação de Campo",
                    JOptionPane.WARNING_MESSAGE
            );
            campoNome.requestFocusInWindow();
            return null;
        }

        if (categoria.length() > 100) {
            JOptionPane.showMessageDialog(
                    this,
                    "A categoria não pode ultrapassar 100 caracteres.",
                    "Validação de Campo",
                    JOptionPane.WARNING_MESSAGE
            );
            campoCategoria.requestFocusInWindow();
            return null;
        }

        LocalDate dataLancamento;
        try {
            dataLancamento = LocalDate.parse(dataTexto, FORMATO_DATA);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Data inválida! Informe uma data válida no formato dd/MM/aaaa (exemplo: 15/10/2023).",
                    "Data Inválida",
                    JOptionPane.WARNING_MESSAGE
            );
            campoData.requestFocusInWindow();
            return null;
        }

        try {
            int id = Integer.parseInt(idTexto);
            return new Filme(id, nome, dataLancamento, categoria);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Identificador de filme inválido.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    /**
     * Executa a atualização dos dados do filme selecionado no banco de dados.
     */
    private void atualizarFilme() {
        Filme filme = validarEObterFilme();
        if (filme == null) {
            return;
        }

        try {
            FilmeDAO dao = new FilmeDAO();
            boolean sucesso = dao.atualizarFilme(filme);
            if (sucesso) {
                JOptionPane.showMessageDialog(
                        this,
                        "Filme atualizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                carregarCategorias();
                carregarFilmes();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum filme foi atualizado! Verifique se o registro ainda existe.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível atualizar os dados do filme! Por favor, verifique os valores informados e a conexão.",
                    "Falha ao Atualizar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Executa a exclusão do filme selecionado no banco de dados após confirmação do usuário.
     */
    private void excluirFilme() {
        String idTexto = campoId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, selecione um filme na tabela para efetuar a exclusão.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza de que deseja excluir o filme \"" + campoNome.getText().trim() + "\"?",
                "Confirmação de Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            FilmeDAO dao = new FilmeDAO();
            boolean sucesso = dao.excluirFilme(id);
            if (sucesso) {
                JOptionPane.showMessageDialog(
                        this,
                        "Filme excluído com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                carregarCategorias();
                carregarFilmes();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum registro foi excluído.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível excluir o filme selecionado! Por favor, tente novamente.",
                    "Falha ao Excluir",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Limpa a seleção da JTable e redefine todos os campos do formulário de edição.
     */
    private void limparCampos() {
        tabela.clearSelection();
        campoId.setText("");
        campoNome.setText("");
        campoData.setText("");
        campoCategoria.setText("");
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
        lblSubtitulo = new javax.swing.JLabel();
        lblFiltro = new javax.swing.JLabel();
        filtroCategoria = new javax.swing.JComboBox<>();
        lblBuscaTexto = new javax.swing.JLabel();
        txtFiltroTexto = new javax.swing.JTextField();
        jScrollPaneTabela = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        pnlEdicao = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        campoId = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        campoCategoria = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnCadastrarNovo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cenaflix - Consulta e Gerenciamento de Filmes");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CENAFLIX");

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("CONSULTA E GERENCIAMENTO DE FILMES");

        lblFiltro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFiltro.setText("Categoria:");

        filtroCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filtroCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        filtroCategoria.addActionListener(this::filtroCategoriaActionPerformed);

        lblBuscaTexto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBuscaTexto.setText("Filtro Dinâmico:");

        txtFiltroTexto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFiltroTexto.setToolTipText("Digite parte da categoria para busca em tempo real");
        txtFiltroTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroTextoKeyReleased(evt);
            }
        });

        tabela.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome do Filme", "Data de Lançamento", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setRowHeight(26);
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPaneTabela.setViewportView(tabela);

        pnlEdicao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Filme Selecionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblId.setText("ID:");

        campoId.setEditable(false);
        campoId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoId.setToolTipText("Código do filme (não editável)");

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNome.setText("Nome:");

        campoNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblData.setText("Data (dd/MM/aaaa):");

        campoData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCategoria.setText("Categoria:");

        campoCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlEdicaoLayout = new javax.swing.GroupLayout(pnlEdicao);
        pnlEdicao.setLayout(pnlEdicaoLayout);
        pnlEdicaoLayout.setHorizontalGroup(
            pnlEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEdicaoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(lblNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEdicaoLayout.createSequentialGroup()
                        .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(lblData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campoNome))
                .addGap(20, 20, 20))
        );
        pnlEdicaoLayout.setVerticalGroup(
            pnlEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEdicaoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria)
                    .addComponent(campoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setToolTipText("Salvar alterações do filme selecionado");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setToolTipText("Excluir filme selecionado");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnLimpar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpar.setText("Limpar Campos");
        btnLimpar.setToolTipText("Limpar formulário e seleção");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        btnCadastrarNovo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrarNovo.setText("Cadastrar Novo Filme");
        btnCadastrarNovo.setToolTipText("Abrir tela de cadastro de filmes");
        btnCadastrarNovo.addActionListener(this::btnCadastrarNovoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneTabela)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filtroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblBuscaTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFiltroTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addComponent(pnlEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnCadastrarNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblSubtitulo)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltro)
                    .addComponent(filtroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscaTexto)
                    .addComponent(txtFiltroTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pnlEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrarNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void filtroCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroCategoriaActionPerformed
        if (!atualizandoCategorias) {
            txtFiltroTexto.setText("");
            limparCampos();
            carregarFilmes();
        }
    }//GEN-LAST:event_filtroCategoriaActionPerformed

    private void txtFiltroTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroTextoKeyReleased
        limparCampos();
        carregarFilmes();
    }//GEN-LAST:event_txtFiltroTextoKeyReleased

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        selecionarFilmeDaTabela();
    }//GEN-LAST:event_tabelaMouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        atualizarFilme();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirFilme();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnCadastrarNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarNovoActionPerformed
        new cenaflix.TelaInicial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarNovoActionPerformed

    /**
     * Ponto de entrada para execução isolada da tela de consulta.
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
            java.util.logging.Logger.getLogger(FormConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FormConsulta().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrarNovo;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JTextField campoCategoria;
    private javax.swing.JTextField campoData;
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField campoNome;
    private javax.swing.JComboBox<String> filtroCategoria;
    private javax.swing.JScrollPane jScrollPaneTabela;
    private javax.swing.JLabel lblBuscaTexto;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlEdicao;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtFiltroTexto;
    // End of variables declaration//GEN-END:variables
}
