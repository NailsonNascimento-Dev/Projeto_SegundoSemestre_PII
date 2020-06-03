/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.VIEWS;

import com.loja.informatica.CONTROLLER.ControllerFuncionarios;
import com.loja.informatica.CONTROLLER.ControllerProduto;
import com.loja.informatica.CONTROLLER.ControllerRelatorios;
import com.loja.informatica.CONTROLLER.ControllerTipoProduto;
import com.loja.informatica.MODEL.Funcionarios;
import com.loja.informatica.MODEL.Produto;
import com.loja.informatica.MODEL.TipoProduto;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nailson, Marcos
 */
public class telaAdministrador extends javax.swing.JFrame {

    boolean buscaAtivada = false;
    int indexLinha = 0;

    /**
     * Creates new form telaAdministrador
     */
    public telaAdministrador() {
        initComponents();
        btnBusca.setEnabled(false);
        btnEditarProduto.setEnabled(false);
        btnEntradaProdutoEstoque.setEnabled(false);
        btnDeletarProduto.setEnabled(false);

        buttonAlterar.setEnabled(false);
        buttonExcluir.setEnabled(false);
        buttonCancelar.setVisible(false);

        carregarRegistrosFuncionarios();
        carregarTipoProdutos();
        carregarRegistrosProduto();
    }

    /**
     * Metodo desenvolvido para buscar um registro em especifico pelo seu ID,
     * preenche as colunas da tabela passando as informações armazenadas em um
     * arrayList para ela.
     */
    public void buscarRegistro() {
        ArrayList<String[]> listarRegistros = ControllerFuncionarios.BuscarRegistro(fieldBuscarFuncionarios.getText());
        DefaultTableModel tabelaRegistros = new DefaultTableModel();

        tabelaRegistros.addColumn("ID");
        tabelaRegistros.addColumn("Nome");
        tabelaRegistros.addColumn("Sexo");
        tabelaRegistros.addColumn("Data Nascimento");
        tabelaRegistros.addColumn("CPF");
        tabelaRegistros.addColumn("Cargo");
        tabelaRegistros.addColumn("Rua");
        tabelaRegistros.addColumn("CEP");
        tabelaRegistros.addColumn("N° Casa");
        tabelaRegistros.addColumn("Bairro");
        tabelaRegistros.addColumn("Email");
        tabelaRegistros.addColumn("Telefone");
        tabelaRegistros.addColumn("Senha");

        tabelaFuncionarios.setModel(tabelaRegistros);

        for (String[] percorrerRegistros : listarRegistros) {
            tabelaRegistros.addRow(new String[]{
                percorrerRegistros[0],
                percorrerRegistros[1],
                percorrerRegistros[2],
                percorrerRegistros[3],
                percorrerRegistros[4],
                percorrerRegistros[5],
                percorrerRegistros[6],
                percorrerRegistros[7],
                percorrerRegistros[8],
                percorrerRegistros[9],
                percorrerRegistros[10],
                percorrerRegistros[11],
                percorrerRegistros[12]

            });
        }

    }

    /**
     * Metodo desenvolvido para escrever os dados de uma tabela em um arquivo
     * txt.
     *
     * @param tabela recebe a tabela que será pego os dados em especifico.
     * @param file recebe o arquivo que será escrito os dados pego na tabela.
     * @throws IOException Pode gerar um erro ao ler e armazenar dados em um
     * arquivo de texto.
     * @throws NullPointerException pode gerar um erro caso o arquivo não
     * exista.
     */
    public void escreverArquivoExcel(JTable tabela, File file) throws IOException, NullPointerException {

        TableModel model = tabela.getModel();
        FileWriter out = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(out);
        for (int i = 0; i < model.getColumnCount(); i++) {
            bw.write(model.getColumnName(i) + "\t");
        }
        bw.write("\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                bw.write(model.getValueAt(i, j).toString() + "\t");
            }
            bw.write("\n");
        }
        bw.close();
        out.close();
    }

    /**
     * Metodo desenvolvido para escolher o caminho onde o arquivo excel deseja
     * ser salvo além de fazer a sua conversao de texto para excel.
     *
     * @param tabela recebe como parametro a tabela que contem os registros.
     */
    public void caminhoArquivoConversao(JTable tabela) {
        JFileChooser f = null;
        String path = null;
        try {
            f = new JFileChooser();
            f.showSaveDialog(this);
            path = f.getSelectedFile().getPath();

            escreverArquivoExcel(tabela, new File(path + ".xls"));
            JOptionPane.showMessageDialog(this, "Exportado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cancelado!");
        }

        try {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo desenvolvido apenas para limpar o campo da tela do funcionario
     * quando chamado em alguma ação.
     */
    public void limparCampos() {
        fieldBairro.setText("");
        fieldBuscarFuncionarios.setText("");
        fieldCPF.setText("");
        fieldCep.setText("");
        fieldDataNacimento.setText("");
        fieldEmail.setText("");
        fieldNome.setText("");
        fieldNumeroCasa.setText("");
        fieldPW_1.setText("");
        fieldPW_2.setText("");
        fieldRua.setText("");
        fieldTelefone.setText("");
        txtID.setText("#ID: ");

        carregarRegistrosFuncionarios();
        buttonCancelar.setVisible(false);
        buttonCadastrar.setEnabled(true);
        fieldBuscarFuncionarios.setEnabled(true);
        buttonBuscar.setEnabled(true);
        buttonEditar.setEnabled(true);
        buttonAlterar.setEnabled(false);
        buttonExcluir.setEnabled(false);
    }

    /**
     * Metodo desenvolvido para carregar todos os registros da tabela dos
     * funcionarios quando chamado.
     */
    public void carregarRegistrosFuncionarios() {
        ArrayList<String[]> listarRegistros = ControllerFuncionarios.CarregarRegistros();
        DefaultTableModel tabelaRegistros = new DefaultTableModel();

        tabelaRegistros.addColumn("ID");
        tabelaRegistros.addColumn("Nome");
        tabelaRegistros.addColumn("Sexo");
        tabelaRegistros.addColumn("Data Nascimento");
        tabelaRegistros.addColumn("CPF");
        tabelaRegistros.addColumn("Cargo");
        tabelaRegistros.addColumn("Rua");
        tabelaRegistros.addColumn("CEP");
        tabelaRegistros.addColumn("N° Casa");
        tabelaRegistros.addColumn("Bairro");
        tabelaRegistros.addColumn("Email");
        tabelaRegistros.addColumn("Telefone");
        tabelaRegistros.addColumn("Senha");

        tabelaFuncionarios.setModel(tabelaRegistros);

        for (String[] percorrerRegistros : listarRegistros) {
            tabelaRegistros.addRow(new String[]{
                percorrerRegistros[0],
                percorrerRegistros[1],
                percorrerRegistros[2],
                percorrerRegistros[3],
                percorrerRegistros[4],
                percorrerRegistros[5],
                percorrerRegistros[6],
                percorrerRegistros[7],
                percorrerRegistros[8],
                percorrerRegistros[9],
                percorrerRegistros[10],
                percorrerRegistros[11],
                percorrerRegistros[12]

            });
        }

    }

    /**
     * Metodo desenvolvido para validar se os campos estão corretos recebendo
     * como paramero cada campo a ser validado.
     *
     * @param nome
     * @param cpf
     * @param data
     * @param rua
     * @param bairro
     * @param email
     * @param telefone
     * @param senha1
     * @param senha2
     * @return retorna true caso os campos estejam todos corretos e false caso
     * tenha algum problema dando uma mensagem na tela onde está ocorrendo o
     * erro ou os erros.
     */
    public boolean validarCampos(
            //int cep,
            //int numeroCasa,
            String nome,
            String cpf,
            String data,
            String rua,
            String bairro,
            String email,
            String telefone,
            String senha1,
            String senha2) {

        boolean camposOK1 = false;
        boolean camposOK2 = false;
        boolean camposOK3 = false;
        boolean camposOK4 = false;
        boolean camposOK5 = false;
        boolean camposOK6 = false;
        boolean camposOK7 = false;
        boolean camposOK8 = false;
        boolean camposOK9 = false;
        boolean camposOK10 = false;

        boolean camposOK = false;

        if (nome.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo de Nome vazio");
            camposOK1 = false;
        } else {
            camposOK1 = true;
        }
        if (data.equals("  /  /    ")) {
            JOptionPane.showMessageDialog(this, "Campo de Data vazio");
            camposOK2 = false;
        } else {
            camposOK2 = true;
        }
        if (cpf.equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(this, "Campo de CPF vazio");
            camposOK3 = false;
        } else {
            camposOK3 = true;
        }

        if (rua.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo de Rua vazio");
            camposOK4 = false;
        } else {
            camposOK4 = true;
        }

        if (fieldCep.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo de Cep vazio");
            camposOK5 = false;
        } else {
            camposOK5 = true;
        }
        if (fieldNumeroCasa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo de Nº vazio");
            camposOK6 = false;
        } else {
            camposOK6 = true;
        }

        if (bairro.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo de Bairro vazio");
            camposOK7 = false;
        } else {
            camposOK7 = true;
        }
        if (email.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo de E-mail vazio");
            camposOK8 = false;
        } else {
            camposOK8 = true;
        }
        if (telefone.equals("(  )     -    ")) {
            JOptionPane.showMessageDialog(this, "Campo de Telefone vazio");
            camposOK9 = false;
        } else {
            camposOK9 = true;
        }
        if ((senha1.trim().equals("")) || (senha2.trim().equals(""))) {
            JOptionPane.showMessageDialog(this, "Campo de senha vazio");
            camposOK10 = false;
        } else if (!senha1.equals(senha2)) {
            JOptionPane.showMessageDialog(this, "A senha está errada em algum dos campos");
            camposOK10 = false;
        } else {
            camposOK10 = true;
        }
        if (camposOK1 == true && camposOK2 == true && camposOK3 == true && camposOK4 == true && camposOK5 == true
                && camposOK6 == true && camposOK7 == true && camposOK8 == true && camposOK9 == true && camposOK10 == true) {
            camposOK = true;
        }

        return camposOK;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPFuncionario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fieldNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ComboBoxCargo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        fieldCPF = new javax.swing.JFormattedTextField();
        fieldDataNacimento = new javax.swing.JFormattedTextField();
        txtID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        comboBoxSexo = new javax.swing.JComboBox<>();
        buttonRecarregarRegistros = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        fieldBairro = new javax.swing.JTextField();
        fieldRua = new javax.swing.JTextField();
        fieldNumeroCasa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldCep = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        fieldEmail = new javax.swing.JFormattedTextField();
        fieldTelefone = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        fieldBuscarFuncionarios = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buttonEditar = new javax.swing.JButton();
        buttonBuscar = new javax.swing.JButton();
        buttonAlterar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        buttonCadastrar = new javax.swing.JToggleButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        fieldPW_1 = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        fieldPW_2 = new javax.swing.JPasswordField();
        jPRelatorios = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaRelatorios = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        buttonGerarRelatorio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        fieldDataFim = new com.toedter.calendar.JDateChooser();
        fieldDataInicio = new com.toedter.calendar.JDateChooser();
        buttonExportarExcel = new javax.swing.JButton();
        buttonLimparTabelas = new javax.swing.JButton();
        buttonExportarExcel2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaRelatorios2 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPEstoque = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cboTipo = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtCodFabricante = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        txtQuantidade = new javax.swing.JTextField();
        btnCriar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        btnBusca = new javax.swing.JButton();
        btnEditarProduto = new javax.swing.JButton();
        btnEntradaProdutoEstoque = new javax.swing.JButton();
        cboTipoBusca = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        btnDeletarProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrador");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(800, 730));

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(64, 74));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 630));

        jPFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPFuncionario.setAutoscrolls(true);
        jPFuncionario.setName(""); // NOI18N
        jPFuncionario.setPreferredSize(new java.awt.Dimension(722, 592));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));

        jLabel2.setText("*Data de Nascimento");

        fieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNomeActionPerformed(evt);
            }
        });
        fieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldNomeKeyTyped(evt);
            }
        });

        jLabel3.setText("*Nome Completo");

        ComboBoxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor" }));

        jLabel4.setText("*Cpf");

        try {
            fieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCPFActionPerformed(evt);
            }
        });

        try {
            fieldDataNacimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldDataNacimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDataNacimentoActionPerformed(evt);
            }
        });

        txtID.setText("#ID:");

        jLabel5.setText("*Cargo");

        jLabel33.setText("*Sexo:");

        comboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Homem", "Mulher", "Gay", "Lesbica", "Trans", "Outros(a)", "Não Informar" }));

        buttonRecarregarRegistros.setText("Recarregar Registros");
        buttonRecarregarRegistros.setMaximumSize(new java.awt.Dimension(63, 32));
        buttonRecarregarRegistros.setMinimumSize(new java.awt.Dimension(63, 32));
        buttonRecarregarRegistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRecarregarRegistrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldDataNacimento, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(13, 13, 13)
                        .addComponent(fieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRecarregarRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtID)
                    .addComponent(jLabel33)
                    .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRecarregarRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(ComboBoxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldDataNacimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        fieldBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldBairroKeyTyped(evt);
            }
        });

        fieldRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldRuaKeyTyped(evt);
            }
        });

        fieldNumeroCasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldNumeroCasaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldNumeroCasaKeyTyped(evt);
            }
        });

        jLabel8.setText("*Nº");

        jLabel6.setText("*Rua");

        jLabel9.setText("*Bairro");

        try {
            fieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fieldCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldCepKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldCepKeyTyped(evt);
            }
        });

        jLabel10.setText("*Cep");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(fieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldBairro))
                    .addComponent(fieldRua))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldCep)
                        .addComponent(jLabel10))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(fieldNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));

        try {
            fieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel11.setText("*E-mail");

        jLabel12.setText("*Fone");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldEmail)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(13, 13, 13)
                .addComponent(fieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(fieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Funcionários Cadastrados"));

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Cargo", "E-mail"
            }
        ));
        jScrollPane1.setViewportView(tabelaFuncionarios);

        jLabel7.setText("Buscar Funcionarios");

        buttonEditar.setText("Editar");
        buttonEditar.setPreferredSize(new java.awt.Dimension(85, 32));
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        buttonBuscar.setText("Buscar");
        buttonBuscar.setPreferredSize(new java.awt.Dimension(85, 32));
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        buttonAlterar.setText("Alterar");
        buttonAlterar.setMaximumSize(new java.awt.Dimension(63, 32));
        buttonAlterar.setMinimumSize(new java.awt.Dimension(63, 32));
        buttonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlterarActionPerformed(evt);
            }
        });

        buttonExcluir.setText("Excluir");
        buttonExcluir.setMaximumSize(new java.awt.Dimension(63, 32));
        buttonExcluir.setMinimumSize(new java.awt.Dimension(63, 32));
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.setMaximumSize(new java.awt.Dimension(63, 32));
        buttonCancelar.setMinimumSize(new java.awt.Dimension(63, 32));
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldBuscarFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldBuscarFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        buttonCadastrar.setText("Cadastrar");
        buttonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha"));

        jLabel24.setText("*Digite sua senha:");

        jLabel25.setText("*Confirme a sua senha:");

        fieldPW_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPW_2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPW_1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPW_2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(fieldPW_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addComponent(fieldPW_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPFuncionarioLayout = new javax.swing.GroupLayout(jPFuncionario);
        jPFuncionario.setLayout(jPFuncionarioLayout);
        jPFuncionarioLayout.setHorizontalGroup(
            jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFuncionarioLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(buttonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPFuncionarioLayout.setVerticalGroup(
            jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Funcionario", jPFuncionario);

        jPRelatorios.setToolTipText("1");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Impressão de Relatórios"));

        tabelaRelatorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Venda", "Nome", "Cpf", "Data", "Valor"
            }
        ));
        tabelaRelatorios.getTableHeader().setReorderingAllowed(false);
        tabelaRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaRelatoriosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelaRelatorios);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione o período"));

        buttonGerarRelatorio.setText("Gerar relatório");
        buttonGerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGerarRelatorioActionPerformed(evt);
            }
        });

        jLabel1.setText("Data incial:");

        jLabel17.setText("Data Final:");

        buttonExportarExcel.setText("Exportar  Excel Tabela 1");
        buttonExportarExcel.setMaximumSize(new java.awt.Dimension(113, 32));
        buttonExportarExcel.setMinimumSize(new java.awt.Dimension(113, 32));
        buttonExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportarExcelActionPerformed(evt);
            }
        });

        buttonLimparTabelas.setText("Limpar Tabelas");
        buttonLimparTabelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimparTabelasActionPerformed(evt);
            }
        });

        buttonExportarExcel2.setText("Exportar Excel Tabela 2");
        buttonExportarExcel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportarExcel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(fieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(buttonGerarRelatorio)
                        .addGap(18, 18, 18)
                        .addComponent(buttonExportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonExportarExcel2)
                        .addGap(18, 18, 18)
                        .addComponent(buttonLimparTabelas))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonGerarRelatorio)
                        .addComponent(buttonExportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonLimparTabelas)
                        .addComponent(buttonExportarExcel2)))
                .addContainerGap())
        );

        tabelaRelatorios2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo / Modelo", "Descrição", "Quantidade", "Valor Unitário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaRelatorios2.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tabelaRelatorios2);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPRelatoriosLayout = new javax.swing.GroupLayout(jPRelatorios);
        jPRelatorios.setLayout(jPRelatoriosLayout);
        jPRelatoriosLayout.setHorizontalGroup(
            jPRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPRelatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPRelatoriosLayout.setVerticalGroup(
            jPRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPRelatoriosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Relatórios", jPRelatorios);

        jPEstoque.setToolTipText("");
        jPEstoque.setPreferredSize(new java.awt.Dimension(800, 730));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de produto"));

        cboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoActionPerformed(evt);
            }
        });

        jLabel19.setText("Observação");

        jLabel16.setText("Código Fabricante *");

        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        txtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescricaoKeyTyped(evt);
            }
        });

        txtCodFabricante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodFabricanteKeyTyped(evt);
            }
        });

        jLabel14.setText("Marca *");

        jLabel15.setText("Quantidade *");

        jLabel18.setText("Tipo / Grupo *");

        jLabel13.setText("Descrição *");

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane2.setViewportView(txtObservacao);

        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyTyped(evt);
            }
        });

        btnCriar.setText("Cadastrar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel26.setText("Preço  *        R$ ");

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });
        txtPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(txtMarca))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDescricao)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodFabricante)
                            .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 737, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPreco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26)))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCriar)
                    .addComponent(btnNovo))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos Cadastrados"));

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscaKeyTyped(evt);
            }
        });

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Código Fabricante", "Tipo / Grupo", "Quantidade", "Marca", "Preço R$"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProdutos);

        jLabel20.setText("Buscar Produto");

        btnBusca.setText("Buscar");
        btnBusca.setPreferredSize(new java.awt.Dimension(85, 32));
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        btnEditarProduto.setText("Editar");
        btnEditarProduto.setPreferredSize(new java.awt.Dimension(85, 32));
        btnEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProdutoActionPerformed(evt);
            }
        });

        btnEntradaProdutoEstoque.setText("Ent. Estoque");
        btnEntradaProdutoEstoque.setMaximumSize(new java.awt.Dimension(85, 32));
        btnEntradaProdutoEstoque.setMinimumSize(new java.awt.Dimension(85, 32));
        btnEntradaProdutoEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaProdutoEstoqueActionPerformed(evt);
            }
        });

        cboTipoBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código Fabricante", "Descrição", "Tipo / Grupo", " " }));

        jLabel23.setText("Tipo de Busca");

        btnDeletarProduto.setText("Deleta Produto");
        btnDeletarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBusca)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboTipoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 287, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(btnBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEntradaProdutoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnDeletarProduto)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTipoBusca)
                        .addComponent(btnDeletarProduto)
                        .addComponent(btnEditarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEntradaProdutoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPEstoqueLayout = new javax.swing.GroupLayout(jPEstoque);
        jPEstoque.setLayout(jPEstoqueLayout);
        jPEstoqueLayout.setHorizontalGroup(
            jPEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPEstoqueLayout.setVerticalGroup(
            jPEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEstoqueLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Estoque", jPEstoque);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeletarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarProdutoActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o produto?", "Aviso!", JOptionPane.INFORMATION_MESSAGE);

        if (resposta == 0) {
            try {
                String codigoFabricante = txtBusca.getText();

                boolean retorno = ControllerProduto.ExcluirRegistro(codigoFabricante);

                if (retorno == true) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com sucesso!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao Excluir registro!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            } catch (Exception e) {
                System.out.print("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operação Cancelada!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeletarProdutoActionPerformed

    private void btnEntradaProdutoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaProdutoEstoqueActionPerformed
        // TODO add your handling code here:

        String quandidaEntrada = JOptionPane.showInputDialog("Quantos itens do produto deseja incluir: ");
        int quandidaEntradaInt = Integer.parseInt(quandidaEntrada);

        boolean status = ControllerProduto.adicionarProduto(txtCodFabricante.getText(), quandidaEntradaInt);
        
        if(status == true){
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso !!");
        }else{
            JOptionPane.showMessageDialog(null, "Valor não adicionado!!");
        }

    }//GEN-LAST:event_btnEntradaProdutoEstoqueActionPerformed

    private void btnEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProdutoActionPerformed
        // TODO add your handling code here:

        //Abilita campos para nova edição
        txtDescricao.setEnabled(true);
        txtCodFabricante.setEnabled(true);
        txtMarca.setEnabled(true);
        cboTipo.setEnabled(true);
        txtObservacao.setEnabled(true);
        txtPreco.setEnabled(true);
        btnCriar.setEnabled(true);
        btnCriar.setText("Alterar");

    }//GEN-LAST:event_btnEditarProdutoActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed

        buscaAtivada = true;

        int tipoBusca = cboTipoBusca.getSelectedIndex();
        String busca = txtBusca.getText();

        //exibo os dados do produto buscado porém desabilita a edição.
        btnCriar.setEnabled(false);
        txtDescricao.setEnabled(false);
        txtCodFabricante.setEnabled(false);
        txtMarca.setEnabled(false);
        txtQuantidade.setEnabled(false);
        txtObservacao.setEnabled(false);
        cboTipo.setEnabled(false);
        txtPreco.setEnabled(false);

        boolean valorEncontrado = false;
        switch (tipoBusca) {
            case 0:
                //verifica se existe itens na tabela
                if (busca.length() > 0) {
                    System.out.println("Busca Código Fabricante ");
                    for (int i = 0; i < tblProdutos.getRowCount(); i++) {
                        //pega linha da tabela
                        if (busca.equals(tblProdutos.getModel().getValueAt(i, 1))) {

                            btnEditarProduto.setEnabled(true);
                            btnEntradaProdutoEstoque.setEnabled(true);
                            btnDeletarProduto.setEnabled(true);
                            btnNovo.setEnabled(true);

                            System.out.println("AQui 1");

                            System.out.println("AQUI 2");
                            ArrayList<String[]> listarProdutos = ControllerProduto.buscarRegistrosCodigoFabricante(busca);
                            System.out.println("AQUI 3");
                            DefaultTableModel tabelaRegistros = new DefaultTableModel();

                            System.out.println("AQUI 4");

                            tabelaRegistros.addColumn("Descrição");
                            tabelaRegistros.addColumn("Código Fabricante");
                            tabelaRegistros.addColumn("Tipo / Grupo");
                            tabelaRegistros.addColumn("Quantidade");
                            tabelaRegistros.addColumn("Marca");
                            tabelaRegistros.addColumn("Preco R$");

                            tblProdutos.setModel(tabelaRegistros);

                            for (String[] percorrerRegistros : listarProdutos) {
                                tabelaRegistros.addRow(new String[]{
                                    percorrerRegistros[0],
                                    percorrerRegistros[1],
                                    percorrerRegistros[2],
                                    percorrerRegistros[3],
                                    percorrerRegistros[4],
                                    percorrerRegistros[5]

                                });

                                valorEncontrado = true;

                            }
                        }
                    }

                    if (!valorEncontrado) {
                        JOptionPane.showMessageDialog(null, "Valor não encontrado! ");
                    }

                }
                break;

            case 1:
                if (busca.length() > 0) {
                    System.out.println("Busca Descrição");
                    for (int i = 0; i < tblProdutos.getRowCount(); i++) {
                        //pega linha da tabela
                        if (busca.equals(tblProdutos.getValueAt(i, 0))) {

                            btnEditarProduto.setEnabled(true);
                            btnEntradaProdutoEstoque.setEnabled(true);
                            btnDeletarProduto.setEnabled(true);
                            btnNovo.setEnabled(true);

                            //pega valor do index da linha da tabela para podemos auterar os parametros...
                            //indexLinha = i;
                            System.out.println("AQUI 2");
                            ArrayList<String[]> listarProdutos = ControllerProduto.buscarRegistrosDescricao(busca);
                            System.out.println("AQUI 3");
                            DefaultTableModel tabelaRegistros = new DefaultTableModel();

                            System.out.println("AQUI 4");

                            tabelaRegistros.addColumn("Descrição");
                            tabelaRegistros.addColumn("Código Fabricante");
                            tabelaRegistros.addColumn("Tipo / Grupo");
                            tabelaRegistros.addColumn("Quantidade");
                            tabelaRegistros.addColumn("Marca");
                            tabelaRegistros.addColumn("Preco R$");

                            tblProdutos.setModel(tabelaRegistros);

                            for (String[] percorrerRegistros : listarProdutos) {
                                tabelaRegistros.addRow(new String[]{
                                    percorrerRegistros[0],
                                    percorrerRegistros[1],
                                    percorrerRegistros[2],
                                    percorrerRegistros[3],
                                    percorrerRegistros[4],
                                    percorrerRegistros[5]

                                });

                                valorEncontrado = true;

                            }

                        }
                    }

                    if (!valorEncontrado) {
                        JOptionPane.showMessageDialog(null, "Valor não encontrado! ");
                    }
                }
                break;
            case 2:
                if (busca.length() > 0) {
                    System.out.println("Busca tipo/Grupo");
                    for (int i = 0; i < tblProdutos.getRowCount(); i++) {
                        //pega linha da tabela
                        if (busca.equals(tblProdutos.getValueAt(i, 2))) {

                            btnEditarProduto.setEnabled(true);
                            btnEntradaProdutoEstoque.setEnabled(true);
                            btnDeletarProduto.setEnabled(true);
                            btnNovo.setEnabled(true);

                            //pega valor do index da linha da tabela para podemos auterar os parametros...
                            indexLinha = i;

                            System.out.println("AQUI 2");
                            ArrayList<String[]> listarProdutos = ControllerProduto.buscarRegistrosTipo(busca);
                            System.out.println("AQUI 3");
                            DefaultTableModel tabelaRegistros = new DefaultTableModel();

                            System.out.println("AQUI 4");

                            tabelaRegistros.addColumn("Descrição");
                            tabelaRegistros.addColumn("Código Fabricante");
                            tabelaRegistros.addColumn("Tipo / Grupo");
                            tabelaRegistros.addColumn("Quantidade");
                            tabelaRegistros.addColumn("Marca");
                            tabelaRegistros.addColumn("Preco R$");

                            tblProdutos.setModel(tabelaRegistros);

                            for (String[] percorrerRegistros : listarProdutos) {
                                tabelaRegistros.addRow(new String[]{
                                    percorrerRegistros[0],
                                    percorrerRegistros[1],
                                    percorrerRegistros[2],
                                    percorrerRegistros[3],
                                    percorrerRegistros[4],
                                    percorrerRegistros[5]

                                });

                                valorEncontrado = true;

                            }

                        }
                    }

                    if (!valorEncontrado) {
                        JOptionPane.showMessageDialog(null, "Valor não encontrado! ");
                    }
                }
                break;
        }

    }//GEN-LAST:event_btnBuscaActionPerformed

    private void txtPrecoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecoKeyTyped
        validacaoCaracterNumero(evt);
    }//GEN-LAST:event_txtPrecoKeyTyped

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:

        buscaAtivada = false;

        btnCriar.setEnabled(true);
        btnEditarProduto.setEnabled(false);
        btnDeletarProduto.setEnabled(false);
        btnEntradaProdutoEstoque.setEnabled(false);

        txtDescricao.setText("");
        txtCodFabricante.setText("");
        txtMarca.setText("");
        txtQuantidade.setText("");
        txtObservacao.setText("");
        txtPreco.setText("");
        btnCriar.setText("Cadastrar");
        txtBusca.setText("");
        carregarRegistrosProduto();

        txtDescricao.setEnabled(true);
        txtCodFabricante.setEnabled(true);
        txtMarca.setEnabled(true);
        txtQuantidade.setEnabled(true);
        txtObservacao.setEnabled(true);
        cboTipo.setEnabled(true);
        txtPreco.setEnabled(true);

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed

        try {

            Produto produto = new Produto();

            if (buscaAtivada == true) {

                String descricao = txtDescricao.getText();
                String modelo_codigo = txtCodFabricante.getText();
                String marca = txtMarca.getText();
                TipoProduto tipo = ((TipoProduto) cboTipo.getSelectedItem());
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                double preco = Double.parseDouble(txtPreco.getText());
                String observacao = txtObservacao.getText();

                System.out.println("teste");

                boolean statusAtualizacao = ControllerProduto.AlterarProduto(descricao, modelo_codigo, marca, tipo, quantidade, preco, observacao);

                if (statusAtualizacao == true) {

                    //limpa campos para novo cadastro de produtos
                    txtDescricao.setText("");
                    txtCodFabricante.setText("");
                    txtMarca.setText("");

                    txtQuantidade.setText("");
                    txtObservacao.setText("");
                    txtPreco.setText("");

                    //Atualiza a tabela de produtos
                    carregarRegistrosProduto();

                    indexLinha = -1;
                    JOptionPane.showMessageDialog(null, "Alterado com Sucesso !");
                } else {
                    JOptionPane.showMessageDialog(null, "O Produto não foi alterado !");
                }

            } else {

                if (txtDescricao.getText().trim().equals("")) {
                    txtDescricao.requestFocus();
                    JOptionPane.showMessageDialog(null, "Campo 'Descrição'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (txtCodFabricante.getText().trim().equals("")) {
                    txtCodFabricante.requestFocus();
                    JOptionPane.showMessageDialog(null, "Campo 'Código Fabricante'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (txtMarca.getText().trim().equals("")) {
                    txtMarca.requestFocus();
                    JOptionPane.showMessageDialog(null, "Campo 'Marca'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (txtQuantidade.getText().trim().equals("")) {
                    txtQuantidade.requestFocus();
                    JOptionPane.showMessageDialog(null, "Campo 'Quantidade'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (txtPreco.getText().trim().equals("")) {
                    txtPreco.requestFocus();
                    JOptionPane.showMessageDialog(null, "Campo 'Preço'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String descricao = txtDescricao.getText();
                String modelo_codigo = txtCodFabricante.getText();
                String marca = txtMarca.getText();
                TipoProduto tipo = ((TipoProduto) cboTipo.getSelectedItem());
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                double preco = Double.parseDouble(txtPreco.getText());
                String observacao = txtObservacao.getText();

                boolean statusCadastro = ControllerProduto.CadastrarProduto(descricao, modelo_codigo, marca, tipo, quantidade, preco, observacao);
                if (statusCadastro) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado !");

                    //limpa campos para novo cadastro de produtos
                    txtDescricao.setText("");
                    txtCodFabricante.setText("");
                    txtMarca.setText("");
                    txtQuantidade.setText("");
                    txtObservacao.setText("");
                    txtPreco.setText("");

                    //Atualiza a tabela de produtos
                    carregarRegistrosProduto();
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não cadastrado !");

                }

                if (txtQuantidade.getText().length() == 0) {
                    System.out.println(" o campo quantiade possui");

                }

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Erro no cadastro");
        }
    }//GEN-LAST:event_btnCriarActionPerformed

    public void carregarRegistrosProduto() {

        ArrayList<String[]> listarRegistros = ControllerProduto.CarregarProduto();
        DefaultTableModel tabelaRegistros = new DefaultTableModel();

        tabelaRegistros.addColumn("descricao");
        tabelaRegistros.addColumn("Código Fabricante");
        tabelaRegistros.addColumn("Tipo / Grupo");
        tabelaRegistros.addColumn("Quantidade");
        tabelaRegistros.addColumn("Marca");
        tabelaRegistros.addColumn("Preço R$");

        tblProdutos.setModel(tabelaRegistros);

        for (String[] percorrerRegistros : listarRegistros) {
            tabelaRegistros.addRow(new String[]{
                percorrerRegistros[0],
                percorrerRegistros[1],
                percorrerRegistros[2],
                percorrerRegistros[3],
                percorrerRegistros[4],
                percorrerRegistros[5],});
        }
        tblProdutos.setDefaultEditor(Object.class, null);
    }

    public void carregarTipoProdutos() {
        ArrayList<TipoProduto> listartipoProdutos = ControllerTipoProduto.CarregaTipoProduto();

        for (TipoProduto tipos : listartipoProdutos) {
            cboTipo.addItem(tipos);

        }

    }

    private void txtQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyTyped
        validacaoQuantidadeEstoque(evt);
    }//GEN-LAST:event_txtQuantidadeKeyTyped

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        validacaoCaracter(evt);
    }//GEN-LAST:event_txtMarcaKeyTyped

    private void txtCodFabricanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFabricanteKeyTyped
        validacaoCaracter(evt);
    }//GEN-LAST:event_txtCodFabricanteKeyTyped

    private void txtDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescricaoKeyTyped
        validacaoCaracter(evt);
    }//GEN-LAST:event_txtDescricaoKeyTyped

    /**
     * Metodo desenvolvido para o botão de cadastrar o funcionario pegando os
     * seus dados que estão nos campos de texto passando para os atributos da
     * classe funcionarios e depois enviando para o metodo de validar os campos
     * e caso esteja tudo certo ele da sequencia enviando para a classe
     * controller do funcionario e vai exibir a mensagem informando se o
     * registro foi cadastrado com sucesso ou não.
     *
     * @param evt
     */

    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarActionPerformed
        boolean conversao = false;

        try {

            Funcionarios funcionarios = new Funcionarios();
            funcionarios.setNome(fieldNome.getText());
            funcionarios.setSexo(comboBoxSexo.getSelectedItem().toString());
            funcionarios.setData(fieldDataNacimento.getText());
            funcionarios.setCpf(fieldCPF.getText());
            funcionarios.setCargo(ComboBoxCargo.getSelectedItem().toString());
            funcionarios.setRua(fieldRua.getText());
            funcionarios.setCep(fieldCep.getText());
            funcionarios.setBairro(fieldBairro.getText());
            funcionarios.setEmail(fieldEmail.getText());
            funcionarios.setTelefone(fieldTelefone.getText());
            funcionarios.setSenha1(String.valueOf(fieldPW_1.getPassword()));
            funcionarios.setSenha2(String.valueOf(fieldPW_2.getPassword()));

            boolean campoValidado = validarCampos(funcionarios.getNome(), funcionarios.getCpf(), funcionarios.getData(),
                    funcionarios.getRua(), funcionarios.getBairro(), funcionarios.getEmail(),
                    funcionarios.getTelefone(), funcionarios.getSenha1(), funcionarios.getSenha2());

            funcionarios.setNumeroCasa(Integer.parseInt(fieldNumeroCasa.getText()));

            conversao = true;

            if (campoValidado == true && conversao == true) {
                boolean retorno = ControllerFuncionarios.CadastrarFuncionario(funcionarios.getNome(), funcionarios.getSexo(), funcionarios.getData(),
                        funcionarios.getCpf(), funcionarios.getCargo(), funcionarios.getRua(), funcionarios.getCep(),
                        funcionarios.getNumeroCasa(), funcionarios.getBairro(), funcionarios.getEmail(), funcionarios.getTelefone(), funcionarios.getSenha1());
                if (retorno == true) {
                    JOptionPane.showMessageDialog(this, "Cadastro Efetuado com Sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                    carregarRegistrosFuncionarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao efetuar cadastro!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Campo de N° está vazio ou com caracteries invalidos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_buttonCadastrarActionPerformed


    private void fieldCepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCepKeyTyped

    }//GEN-LAST:event_fieldCepKeyTyped
    /**
     * Evento desenvolvido para aceitar apenas valores numéricos.
     *
     * @param evt
     */
    private void fieldCepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCepKeyReleased
        if (fieldCep.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Apenas valores numéricos!");
        }
    }//GEN-LAST:event_fieldCepKeyReleased
    /**
     * Evento desenvolvido para aceitar apenas numeros.
     *
     * @param evt
     */
    private void fieldNumeroCasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNumeroCasaKeyTyped
        char c = evt.getKeyChar(); //recebe o evento
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldNumeroCasaKeyTyped
    /**
     * Evento desenvolvido para aceitar apenas numeros.
     *
     * @param evt
     */
    private void fieldNumeroCasaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNumeroCasaKeyReleased
        if (fieldNumeroCasa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Apenas valores numeros!");
        }
    }//GEN-LAST:event_fieldNumeroCasaKeyReleased
    /**
     * Evento desenvolvido apenas para aceitar letras validas.
     *
     * @param evt
     */
    private void fieldRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldRuaKeyTyped
        String caracteres = "0987654321!@#$%¨&*('-)+=,./:;?~{}[]|_ºª°§";// lista de caracters que não devem ser aceitos
        if (caracteres.contains(evt.getKeyChar() + "")) {// se o character que gerou o evento estiver na lista
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fieldRuaKeyTyped
    /**
     * Evento desenvolvido para aceitar apenas letras validas.
     *
     * @param evt
     */
    private void fieldBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldBairroKeyTyped
        String caracteres = "0987654321!@#$%¨&*('-)+=,./:;?~{}[]|_ºª°§";// lista de caracters que não devem ser aceitos
        if (caracteres.contains(evt.getKeyChar() + "")) {// se o character que gerou o evento estiver na lista
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fieldBairroKeyTyped

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed

    }//GEN-LAST:event_txtPrecoActionPerformed

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyTyped
        validacaoCaracter(evt);
        btnBusca.setEnabled(true);

        if (txtBusca.getText().equals("")) {
            carregarRegistrosProduto();
        }


    }//GEN-LAST:event_txtBuscaKeyTyped

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void cboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoActionPerformed

    private void fieldDataNacimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDataNacimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDataNacimentoActionPerformed

    /**
     * Evento desenvolvido para aceitar apenas letras validas.
     *
     * @param evt
     */
    private void fieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNomeKeyTyped
        String caracteres = "0987654321!@#$%¨&*('-)+=,./:;?~{}[]|_ºª°§";// lista de caracters que não devem ser aceitos
        if (caracteres.contains(evt.getKeyChar() + "")) {// se o character que gerou o evento estiver na lista
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fieldNomeKeyTyped

    private void fieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNomeActionPerformed

    private void fieldCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldCPFActionPerformed

    private void fieldPW_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPW_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPW_2ActionPerformed

    private void buttonRecarregarRegistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRecarregarRegistrosActionPerformed
        carregarRegistrosFuncionarios();
        fieldBuscarFuncionarios.setText("");

    }//GEN-LAST:event_buttonRecarregarRegistrosActionPerformed
    /**
     * Botão para buscar um registro em especifico chamando a função de buscar
     * registro
     *
     * @param evt
     */
    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        buscarRegistro();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    /**
     * Botão de editar, quando se clica nele, ele pega os dados do registro em
     * especifico que se quer editar que está na tabela e coloca seus dados nos
     * respectivos campos para serem editados.
     *
     * @param evt
     */
    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed

        try {
            buscarRegistro();
            int editar = Integer.parseInt(fieldBuscarFuncionarios.getText());
            JOptionPane.showMessageDialog(this, "Modo de Edição!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            buttonAlterar.setEnabled(true);
            buttonExcluir.setEnabled(true);
            fieldBuscarFuncionarios.setEnabled(false);
            buttonBuscar.setEnabled(false);
            buttonEditar.setEnabled(false);
            buttonCancelar.setVisible(true);
            buttonCadastrar.setEnabled(false);

            if (editar >= 1) {
                for (int i = 0; i < tabelaFuncionarios.getColumnCount(); i++) {

                    txtID.setText("#ID: " + tabelaFuncionarios.getValueAt(i, 0).toString());
                    fieldNome.setText(tabelaFuncionarios.getValueAt(i, 1).toString());
                    fieldDataNacimento.setText(tabelaFuncionarios.getValueAt(i, 3).toString());
                    fieldCPF.setText(tabelaFuncionarios.getValueAt(i, 4).toString());
                    fieldRua.setText(tabelaFuncionarios.getValueAt(i, 6).toString());
                    fieldCep.setText(tabelaFuncionarios.getValueAt(i, 7).toString());
                    fieldNumeroCasa.setText(tabelaFuncionarios.getValueAt(i, 8).toString());
                    fieldBairro.setText(tabelaFuncionarios.getValueAt(i, 9).toString());
                    fieldEmail.setText(tabelaFuncionarios.getValueAt(i, 10).toString());
                    fieldTelefone.setText(tabelaFuncionarios.getValueAt(i, 11).toString());
                    fieldPW_1.setText(tabelaFuncionarios.getValueAt(i, 12).toString());
                    fieldPW_2.setText(tabelaFuncionarios.getValueAt(i, 12).toString());

                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Você precisa informar um ID para editar!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("");
        }
    }//GEN-LAST:event_buttonEditarActionPerformed
    /**
     * Botão desenvolvido para cancelar o modo de edição retornando ao modo
     * normal do programa
     *
     * @param evt
     */
    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja sair do modo de Edição?", "Aviso!", JOptionPane.INFORMATION_MESSAGE);

        if (resposta == 0) {
            JOptionPane.showMessageDialog(this, "Voltando ao modo normal!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } else {
            JOptionPane.showMessageDialog(this, "Operação cancelada!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonCancelarActionPerformed

    /**
     * Botão de alterar pega as informações contidas nos campos e envia para a
     * função de validar os campos e casa esteja tudo certo da sequencia
     * enviando para a classe controler então a partir disso vai mostrar caso a
     * alteração do registro tenha sido efetuada com sucesso ou não.
     *
     * @param evt
     */
    private void buttonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlterarActionPerformed
        boolean conversao = false;
        Funcionarios funcionarios = new Funcionarios();

        try {
            funcionarios.setId(Integer.parseInt(fieldBuscarFuncionarios.getText()));
            funcionarios.setNumeroCasa(Integer.parseInt(fieldNumeroCasa.getText()));

            funcionarios.setNome(fieldNome.getText());
            funcionarios.setSexo(comboBoxSexo.getSelectedItem().toString());
            funcionarios.setData(fieldDataNacimento.getText());
            funcionarios.setCpf(fieldCPF.getText());
            funcionarios.setCargo(ComboBoxCargo.getSelectedItem().toString());
            funcionarios.setRua(fieldRua.getText());
            funcionarios.setCep(fieldCep.getText());
            funcionarios.setBairro(fieldBairro.getText());
            funcionarios.setEmail(fieldEmail.getText());
            funcionarios.setTelefone(fieldTelefone.getText());
            funcionarios.setSenha1(String.valueOf(fieldPW_1.getPassword()));
            funcionarios.setSenha2(String.valueOf(fieldPW_2.getPassword()));

            conversao = true;

            boolean campoValidado = validarCampos(funcionarios.getNome(), funcionarios.getCpf(), funcionarios.getData(),
                    funcionarios.getRua(), funcionarios.getBairro(), funcionarios.getEmail(),
                    funcionarios.getTelefone(), funcionarios.getSenha1(), funcionarios.getSenha2());

            if (campoValidado == true && conversao == true) {

                boolean retorno = ControllerFuncionarios.AlterarRegistro(funcionarios.getId(), funcionarios.getNome(), funcionarios.getSexo(), funcionarios.getData(),
                        funcionarios.getCpf(), funcionarios.getCargo(), funcionarios.getRua(), funcionarios.getCep(),
                        funcionarios.getNumeroCasa(), funcionarios.getBairro(), funcionarios.getEmail(), funcionarios.getTelefone(), funcionarios.getSenha1());

                if (retorno == true) {
                    JOptionPane.showMessageDialog(this, "Registro alterado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao alterar Registro!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            }

        } catch (NumberFormatException e) {
            System.out.print("");
        }


    }//GEN-LAST:event_buttonAlterarActionPerformed

    /**
     * Botão para excluir um registro do banco de dados, emite uma caixa de
     * dialogo para confirmar e caso sim envia a requisição para a classe
     * controller junto ao id especifico do funcionario que deseja ser excluido
     * e a partir disso vai mostrar para o usuario caso tenha excluido o
     * registro com sucesso ou não.
     *
     * @param evt
     */
    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o registro?", "Aviso!", JOptionPane.INFORMATION_MESSAGE);

        if (resposta == 0) {
            try {
                int id = Integer.parseInt(fieldBuscarFuncionarios.getText());

                boolean retorno = ControllerFuncionarios.ExcluirRegistro(id);

                if (retorno == true) {
                    JOptionPane.showMessageDialog(this, "Registro Excluido com sucesso!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao Excluir registro!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            } catch (Exception e) {
                System.out.print("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operação Cancelada!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_buttonExcluirActionPerformed

    /**
     * Botão para exportar para excel , ele cria um arquivo de texto vazio e
     * chama duas funcões que são parte de um todo para poder escrever os dados
     * da tabela em um arquivo de texto e em seguida converter esse arquivo para
     * excel escolhendo onde o arquivo deseja ser salvo.
     *
     * @param evt
     */
    private void buttonExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportarExcelActionPerformed
        File arquivo = new File("Excel.txt");

        try {
            escreverArquivoExcel(tabelaRelatorios, arquivo);
            caminhoArquivoConversao(tabelaRelatorios);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar para excel!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_buttonExportarExcelActionPerformed

    private void buttonGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGerarRelatorioActionPerformed

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dataFormatadaInicio = simpleDateFormat.format(fieldDataInicio.getDate());
        String dataFormatadaFim = simpleDateFormat.format(fieldDataFim.getDate());

        ArrayList<String[]> listarVendas = ControllerRelatorios.BuscarRegistros(dataFormatadaInicio, dataFormatadaFim);
        DefaultTableModel tabelaRelatorioDft1 = new DefaultTableModel();

        tabelaRelatorioDft1.addColumn("ID Venda");
        tabelaRelatorioDft1.addColumn("Nome");
        tabelaRelatorioDft1.addColumn("CPF");
        tabelaRelatorioDft1.addColumn("Datas");
        tabelaRelatorioDft1.addColumn("Valor da Venda");

        tabelaRelatorios.setModel(tabelaRelatorioDft1);

        for (String[] percorrerDados : listarVendas) {
            tabelaRelatorioDft1.addRow(new String[]{
                percorrerDados[0],
                percorrerDados[1],
                percorrerDados[2],
                percorrerDados[3],
                percorrerDados[4]
            });
        }

        tabelaRelatorios.setDefaultEditor(Object.class, null);

    }//GEN-LAST:event_buttonGerarRelatorioActionPerformed

    private void tabelaRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaRelatoriosMouseClicked
        int idVenda = Integer.parseInt(tabelaRelatorios.getValueAt(tabelaRelatorios.getSelectedRow(), 0).toString());

        ArrayList<String[]> listarVendaDescricao = ControllerRelatorios.buscarRegistroDetalhe(idVenda);

        DefaultTableModel tabelaRelatorioDft2 = new DefaultTableModel();

        tabelaRelatorioDft2.addColumn("ID Venda");
        tabelaRelatorioDft2.addColumn("Tipo / Modelo");
        tabelaRelatorioDft2.addColumn("Descrição");
        tabelaRelatorioDft2.addColumn("Quantidade");
        tabelaRelatorioDft2.addColumn("Valor Unitário");

        tabelaRelatorios2.setModel(tabelaRelatorioDft2);

        for (String[] percorrerDados : listarVendaDescricao) {
            tabelaRelatorioDft2.addRow(new String[]{
                percorrerDados[0],
                percorrerDados[1],
                percorrerDados[2],
                percorrerDados[3],
                percorrerDados[4]

            });

        }

        tabelaRelatorios2.setDefaultEditor(Object.class, null);
    }//GEN-LAST:event_tabelaRelatoriosMouseClicked

    private void buttonLimparTabelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparTabelasActionPerformed
        DefaultTableModel tabelaRelatorioDft1 = (DefaultTableModel) tabelaRelatorios.getModel();
        tabelaRelatorioDft1.setNumRows(0);
        DefaultTableModel tabelaRelatorioDft2 = (DefaultTableModel) tabelaRelatorios2.getModel();
        tabelaRelatorioDft2.setNumRows(0);
    }//GEN-LAST:event_buttonLimparTabelasActionPerformed

    private void buttonExportarExcel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportarExcel2ActionPerformed
        File arquivo = new File("Excel2.txt");

        try {
            escreverArquivoExcel(tabelaRelatorios2, arquivo);
            caminhoArquivoConversao(tabelaRelatorios2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar para excel!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_buttonExportarExcel2ActionPerformed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        txtDescricao.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0).toString());
        txtCodFabricante.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 1).toString());
        cboTipo.setSelectedItem(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 2).toString());
        txtQuantidade.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 3).toString());
        txtMarca.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 4).toString());
        txtPreco.setText(tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tblProdutosMouseClicked

    /**
     * Evento desenvolvido para validação de caracteries
     *
     * @param evt
     */
    private void validacaoCaracter(java.awt.event.KeyEvent evt) {

        String naoPermitidos = "!@#$%¨&*('-){}[]+=,./:;?|_ºª°§ ";//caracteres que não serão aceitos (Resolver " e \)
        if (naoPermitidos.contains(evt.getKeyChar() + "")) {// se o campo que ativa o evento 'evt.getKeyChar' conter o caracter 
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Evento desenvolvido para permitir apenas valores numéricos
     *
     * @param evt
     */
    private void validacaoCaracterNumero(java.awt.event.KeyEvent evt) {

        String naoPermitidos = "abcdefghijklmnopqrstuvwxyz!@#$%¨&*('-){}[]+=/:;,?|_ºª°§ABCDEFGHIJKLMNOPQRSTUVWXYZ";//caracteres que não serão aceitos (Resolver " e \)
        if (naoPermitidos.contains(evt.getKeyChar() + "")) {// se o campo que ativa o evento 'evt.getKeyChar' conter o caracter 
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Evento desenvolvido para permitir apenas valores numéricos
     *
     * @param evt
     */
    private void validacaoQuantidadeEstoque(java.awt.event.KeyEvent evt) {

        String naoPermitidos = "abcdefghijklmnopqrstuvwxyz.!@#$%¨&*('-){}[]+=,./:;?|_ºª°§ABCDEFGHIJKLMNOPQRSTUVWXYZ";//caracteres que não serão aceitos (Resolver " e \)
        if (naoPermitidos.contains(evt.getKeyChar() + "")) {// se o campo que ativa o evento 'evt.getKeyChar' conter o caracter 
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCargo;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnDeletarProduto;
    private javax.swing.JButton btnEditarProduto;
    private javax.swing.JButton btnEntradaProdutoEstoque;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton buttonAlterar;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JToggleButton buttonCadastrar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonExportarExcel;
    private javax.swing.JButton buttonExportarExcel2;
    private javax.swing.JButton buttonGerarRelatorio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton buttonLimparTabelas;
    private javax.swing.JButton buttonRecarregarRegistros;
    private javax.swing.JComboBox<Object> cboTipo;
    private javax.swing.JComboBox<String> cboTipoBusca;
    private javax.swing.JComboBox<String> comboBoxSexo;
    private javax.swing.JTextField fieldBairro;
    private javax.swing.JTextField fieldBuscarFuncionarios;
    private javax.swing.JFormattedTextField fieldCPF;
    private javax.swing.JFormattedTextField fieldCep;
    private com.toedter.calendar.JDateChooser fieldDataFim;
    private com.toedter.calendar.JDateChooser fieldDataInicio;
    private javax.swing.JFormattedTextField fieldDataNacimento;
    private javax.swing.JFormattedTextField fieldEmail;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JTextField fieldNumeroCasa;
    private javax.swing.JPasswordField fieldPW_1;
    private javax.swing.JPasswordField fieldPW_2;
    private javax.swing.JTextField fieldRua;
    private javax.swing.JFormattedTextField fieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPEstoque;
    private javax.swing.JPanel jPFuncionario;
    private javax.swing.JPanel jPRelatorios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabelaFuncionarios;
    private javax.swing.JTable tabelaRelatorios;
    private javax.swing.JTable tabelaRelatorios2;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodFabricante;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JFormattedTextField txtPreco;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
