/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto_Views;

import Atributos.Funcionarios;
import Modelo_classes.Produto;
import ValidacaoDeCampos.soCaracteres;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



/**
 *
 * @author nailson
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
        
    }

    public void exporTabela(JTable tabela, File file) throws IOException, NullPointerException {

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

    public void caminhoArquivo(JTable tabela) {
        JFileChooser f = null;
        String path = null;
        try {
            f = new JFileChooser();
            f.showSaveDialog(this);
            path = f.getSelectedFile().getPath();

            exporTabela(tabela, new File(path + ".xls"));
            JOptionPane.showMessageDialog(this, "Exportado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cancelado!");
        }

        try {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

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
    }

    public boolean validarCampos(
            //int cep,
            //int numeroCasa,
            String nome,
            String cpf,
            String data,
            String cargo,
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
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
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
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        fieldPW_1 = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        fieldPW_2 = new javax.swing.JPasswordField();
        jPRelatorios = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton4 = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableMelhoresVendedores = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMaisVendidos = new javax.swing.JTable();
        exportarExcel1 = new javax.swing.JButton();
        exportarExcel2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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

        jLabel2.setText("Data de Nascimento");

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

        jLabel3.setText("Nome Completo");

        ComboBoxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendedor", "Estoquista", "Repositor", "Administrador" }));

        jLabel4.setText("Cpf");

        try {
            fieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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

        jLabel1.setText("#ID");

        jLabel5.setText("Cargo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldDataNacimento, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxCargo, 0, 252, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldNome)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fieldDataNacimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(fieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

        jLabel8.setText("Nº");

        jLabel6.setText("Rua");

        jLabel9.setText("Bairro");

        fieldCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldCepKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldCepKeyTyped(evt);
            }
        });

        jLabel10.setText("Cep");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(fieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(fieldNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));

        try {
            fieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel11.setText("E-mail");

        jLabel12.setText("Fone");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldEmail)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setText("Buscar Funcionarios");

        jButton3.setText("Editar");
        jButton3.setPreferredSize(new java.awt.Dimension(85, 32));

        jButton10.setText("Buscar");
        jButton10.setPreferredSize(new java.awt.Dimension(85, 32));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldBuscarFuncionarios, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldBuscarFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jToggleButton1.setText("Cadastrar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Senha"));

        jLabel24.setText("Digite sua senha:");

        jLabel25.setText("Confirme a sua senha:");

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
                .addComponent(fieldPW_2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(fieldPW_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(fieldPW_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Funcionario", jPFuncionario);

        jPRelatorios.setToolTipText("1");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Impressão de Relatórios"));

        jToggleButton4.setText("Imprimir");
        jToggleButton4.setPreferredSize(new java.awt.Dimension(85, 32));

        tableMelhoresVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Madara", "M", "100"},
                {"2", "Sasuke", "M", "19"},
                {"3", "Itachi", "M", "15"}
            },
            new String [] {
                "id", "nome", "sexo", "idade"
            }
        ));
        jScrollPane5.setViewportView(tableMelhoresVendedores);

        jLabel22.setText("Melhores Vendedores");

        jLabel21.setText("Produtos Mais Vendidos");

        tableMaisVendidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Madara", "100", "I9"},
                {"2", "Sasuke", "50", "Placa de video"},
                {"3", "Itachi", "70", "Pc gamer"},
                {"4", "Naruto", "32", "Gabinete"},
                {"5", "Sakura", "51", "Notebook"},
                {"6", "Tsunade", "61", "Mouses"},
                {"7", "Ino", "47", "Teclados"},
                {"8", "Hinata", "71", "Cadeiras gamers"}
            },
            new String [] {
                "id", "nome", "quantidade", "produto"
            }
        ));
        jScrollPane4.setViewportView(tableMaisVendidos);

        exportarExcel1.setText("Exportar");
        exportarExcel1.setPreferredSize(new java.awt.Dimension(85, 32));
        exportarExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarExcel1ActionPerformed(evt);
            }
        });

        exportarExcel2.setText("Exportar");
        exportarExcel2.setPreferredSize(new java.awt.Dimension(85, 32));
        exportarExcel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarExcel2ActionPerformed(evt);
            }
        });

        jButton9.setText("Imprimir");
        jButton9.setPreferredSize(new java.awt.Dimension(85, 32));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel22)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exportarExcel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(exportarExcel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exportarExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exportarExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Graficos"));

        jButton2.setText("Representação Vendedor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Representação Produto");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPRelatoriosLayout = new javax.swing.GroupLayout(jPRelatorios);
        jPRelatorios.setLayout(jPRelatoriosLayout);
        jPRelatoriosLayout.setHorizontalGroup(
            jPRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPRelatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPRelatoriosLayout.setVerticalGroup(
            jPRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPRelatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Relatórios", jPRelatorios);

        jPEstoque.setToolTipText("");
        jPEstoque.setPreferredSize(new java.awt.Dimension(800, 730));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de produto"));

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Placa de Vídeo", "Placa de Capitura", "Placa Mãe", "Fonte", "Hard Disk (HD) / SSD", "Memória", "Gabinete", " ", " " }));

        jLabel19.setText("Observação");

        jLabel16.setText("Código Fabricante");

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

        jLabel14.setText("Marca");

        jLabel15.setText("Quantidade");

        jLabel18.setText("Tipo / Grupo");

        jLabel13.setText("Descrição");

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

        jLabel26.setText("Preço - R$");

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
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
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
                            .addComponent(txtPreco)
                            .addComponent(txtCodFabricante)
                            .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 539, Short.MAX_VALUE)
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
                                .addGap(0, 89, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeletarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarProdutoActionPerformed
        // TODO add your handling code here:

        if (indexLinha >= 0) {

            //resgata o modelo da tabela e atribui a uma variavel do tipo DefaultTableModel
            DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
            model.removeRow(indexLinha);

        }
    }//GEN-LAST:event_btnDeletarProdutoActionPerformed

    private void btnEntradaProdutoEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaProdutoEstoqueActionPerformed
        // TODO add your handling code here:

        String quandidaEntrada = JOptionPane.showInputDialog("Quantos itens do produto deseja incluir: ");
        int quandidaEntradaInt = Integer.parseInt(quandidaEntrada);
        
        
        String valorAtualEstoque = tblProdutos.getValueAt(indexLinha, 3).toString();
        int valorEstoqueInt = Integer.parseInt(valorAtualEstoque );
        
        tblProdutos.setValueAt((quandidaEntradaInt + valorEstoqueInt), indexLinha, 3);
        
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
                //veriiica se existe itens na tabela
                if (busca.length() > 0) {
                    System.out.println("Busca Código Fabricante ");
                    for (int i = 0; i < tblProdutos.getRowCount(); i++) {
                        //pega linha da tabela
                        if (busca.equals(tblProdutos.getValueAt(i, 1))) {
                            

                            btnEditarProduto.setEnabled(true);
                            btnEntradaProdutoEstoque.setEnabled(true);
                            btnDeletarProduto.setEnabled(true);

                            //pega valor do index da linha da tabela para podemos auterar os parametros...
                            indexLinha = i;

                            txtDescricao.setText(tblProdutos.getValueAt(i, 0).toString());
                            txtCodFabricante.setText(tblProdutos.getValueAt(i, 1).toString());
                            cboTipo.setSelectedItem(tblProdutos.getValueAt(i, 2).toString());
                            txtQuantidade.setText(tblProdutos.getValueAt(i, 3).toString());
                            txtMarca.setText(tblProdutos.getValueAt(i, 4).toString());
                            txtPreco.setText(tblProdutos.getValueAt(i, 5).toString());
                            valorEncontrado = true;
                            
                        }
                    }
                    
                    if(!valorEncontrado){
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

                            //pega valor do index da linha da tabela para podemos auterar os parametros...
                            indexLinha = i;

                            txtDescricao.setText(tblProdutos.getValueAt(i, 0).toString());
                            txtCodFabricante.setText(tblProdutos.getValueAt(i, 1).toString());
                            cboTipo.setSelectedItem(tblProdutos.getValueAt(i, 2).toString());
                            txtQuantidade.setText(tblProdutos.getValueAt(i, 3).toString());
                            txtMarca.setText(tblProdutos.getValueAt(i, 4).toString());
                            txtPreco.setText(tblProdutos.getValueAt(i, 5).toString());
                            valorEncontrado = true;
                            
                        }
                    }
                    
                    if(!valorEncontrado){
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

                            //pega valor do index da linha da tabela para podemos auterar os parametros...
                            indexLinha = i;

                            txtDescricao.setText(tblProdutos.getValueAt(i, 0).toString());
                            txtCodFabricante.setText(tblProdutos.getValueAt(i, 1).toString());
                            cboTipo.setSelectedItem(tblProdutos.getValueAt(i, 2).toString());
                            txtQuantidade.setText(tblProdutos.getValueAt(i, 3).toString());
                            txtMarca.setText(tblProdutos.getValueAt(i, 4).toString());
                            txtPreco.setText(tblProdutos.getValueAt(i, 5).toString());
                            valorEncontrado = true;
                            
                        }
                    }
                    
                    if(!valorEncontrado){
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
        txtDescricao.setText("");
        txtCodFabricante.setText("");
        txtMarca.setText("");
        txtQuantidade.setText("");
        txtObservacao.setText("");
        txtPreco.setText("");

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
                System.out.println("busca realizada");
                System.out.println("linha: " + indexLinha);

                tblProdutos.setValueAt(txtDescricao.getText(), indexLinha, 0);
                tblProdutos.setValueAt(txtCodFabricante.getText(), indexLinha, 1);
                tblProdutos.setValueAt(cboTipo.getSelectedItem(), indexLinha, 2);
                tblProdutos.setValueAt(txtMarca.getText(), indexLinha, 4);
                tblProdutos.setValueAt(txtPreco.getText(), indexLinha, 5);

                indexLinha = -1;
        } else {
                
                if(txtDescricao.getText().trim().equals("")){
                    txtDescricao.requestFocus();
                    JOptionPane.showMessageDialog(null,"Campo 'Descrição'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(txtCodFabricante.getText().trim().equals("")){
                    txtCodFabricante.requestFocus();
                    JOptionPane.showMessageDialog(null,"Campo 'Código Fabricante'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(txtMarca.getText().trim().equals("")){
                    txtMarca.requestFocus();
                    JOptionPane.showMessageDialog(null,"Campo 'Marca'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(txtQuantidade.getText().trim().equals("")){
                    txtQuantidade.requestFocus();
                    JOptionPane.showMessageDialog(null,"Campo 'Quantidade'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(txtPreco.getText().trim().equals("")){
                    txtPreco.requestFocus();
                    JOptionPane.showMessageDialog(null,"Campo 'Preço'  incorreto", "Aviso !", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                
                
            
                produto.setDescricao(txtDescricao.getText());
                produto.setCodigoFabricante(txtCodFabricante.getText());
                produto.setTipo(cboTipo.getSelectedItem().toString());
                produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                produto.setMarca(txtMarca.getText());
                produto.setPreco(Double.parseDouble(txtPreco.getText()));
                produto.setObservacao(txtObservacao.getText());

                System.out.println(produto.toString());

                //resgata o modelo da tabela e atribui a uma variavel do tipo DefaultTableModel
                DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();

                //adiciona os valores do objeto "produto" a linha da tabela
                model.addRow(new Object[]{
                    produto.getDescricao(),
                    produto.getCodigoFabricante(),
                    produto.getTipo(),
                    produto.getQuantidade(),
                    produto.getMarca(),
                    produto.getPreco()

            });

            //limpa campos para novo cadastro de produtos
            txtDescricao.setText("");
            txtCodFabricante.setText("");
            txtMarca.setText("");
            txtQuantidade.setText("");
            txtObservacao.setText("");
            txtPreco.setText("");

            if (txtQuantidade.getText().length() == 0) {
                System.out.println(" o campo quantiade possui");
                
            }

        }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this,"Erro no cadastro");
        }
    }//GEN-LAST:event_btnCriarActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void exportarExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarExcel1ActionPerformed
        File arquivo1 = new File("Excel1.txt");

        try {
            exporTabela(tableMaisVendidos, arquivo1);
            caminhoArquivo(tableMaisVendidos);
        } catch (IOException ex) {
            Logger.getLogger(telaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportarExcel1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        boolean campoValidado = false;
        boolean conversao = false;

        Funcionarios funcionarios = new Funcionarios();
        funcionarios.setNome(fieldNome.getText());
        funcionarios.setCpf(fieldCPF.getText());
        funcionarios.setRua(fieldRua.getText());
        funcionarios.setBairro(fieldBairro.getText());
        funcionarios.setEmail(fieldEmail.getText());
        funcionarios.setTelefone(fieldTelefone.getText());
        funcionarios.setCargo(ComboBoxCargo.getSelectedItem().toString());
        funcionarios.setSenha1(String.valueOf(fieldPW_1.getPassword()));
        funcionarios.setSenha2(String.valueOf(fieldPW_2.getPassword()));
        funcionarios.setData(fieldDataNacimento.getText());

        campoValidado = validarCampos(funcionarios.getNome(), funcionarios.getCpf(), funcionarios.getData(),
                funcionarios.getCargo(), funcionarios.getRua(), funcionarios.getBairro(), funcionarios.getEmail(),
                funcionarios.getTelefone(), funcionarios.getSenha1(), funcionarios.getSenha2());

        try {

            funcionarios.setCep(Integer.parseInt(fieldCep.getText()));
            funcionarios.setNumeroCasa(Integer.parseInt(fieldNumeroCasa.getText()));

            conversao = true;

        } catch (NumberFormatException e) {

            conversao = false;

        }

        if (campoValidado == true && conversao == true) {
            JOptionPane.showMessageDialog(this, "Cadastro Efetuado com Sucesso!");
            limparCampos();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void fieldCepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCepKeyTyped
        char c = evt.getKeyChar(); //recebe o evento
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldCepKeyTyped

    private void fieldCepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCepKeyReleased
        if (fieldCep.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Apenas valores numeros!");
        }
    }//GEN-LAST:event_fieldCepKeyReleased

    private void fieldNumeroCasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNumeroCasaKeyTyped
        char c = evt.getKeyChar(); //recebe o evento
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldNumeroCasaKeyTyped

    private void fieldNumeroCasaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNumeroCasaKeyReleased
        if (fieldNumeroCasa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Apenas valores numeros!");
        }
    }//GEN-LAST:event_fieldNumeroCasaKeyReleased

    private void fieldRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldRuaKeyTyped
        String caracteres = "0987654321!@#$%¨&*('-)+=,./:;?~ç{}[]|_ºª°§";// lista de caracters que não devem ser aceitos
        if (caracteres.contains(evt.getKeyChar() + "")) {// se o character que gerou o evento estiver na lista
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fieldRuaKeyTyped

    private void fieldBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldBairroKeyTyped
        String caracteres = "0987654321!@#$%¨&*('-)+=,./:;?~ç{}[]|_ºª°§";// lista de caracters que não devem ser aceitos
        if (caracteres.contains(evt.getKeyChar() + "")) {// se o character que gerou o evento estiver na lista
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fieldBairroKeyTyped

    private void fieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNomeKeyTyped
        String caracteres = "0987654321!@#$%¨&*('-)+=,./:;?~ç{}[]|_ºª°§";// lista de caracters que não devem ser aceitos
        if (caracteres.contains(evt.getKeyChar() + "")) {// se o character que gerou o evento estiver na lista
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_fieldNomeKeyTyped

    private void fieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNomeActionPerformed

    private void exportarExcel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarExcel2ActionPerformed
        File arquivo2 = new File("Excel2.txt");

        try {
            exporTabela(tableMelhoresVendedores, arquivo2);
            caminhoArquivo(tableMelhoresVendedores);
        } catch (IOException ex) {
            Logger.getLogger(telaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_exportarExcel2ActionPerformed

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        
    }//GEN-LAST:event_txtPrecoActionPerformed

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void fieldDataNacimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDataNacimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDataNacimentoActionPerformed

    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyTyped
        validacaoCaracter(evt);
        btnBusca.setEnabled(true);
    }//GEN-LAST:event_txtBuscaKeyTyped

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    //função para validar a utilização de caracters
    private void validacaoCaracter(java.awt.event.KeyEvent evt) {

        String naoPermitidos = "!@#$%¨&*('-){}[]+=,./:;?|_ºª°§ ";//caracteres que não serão aceitos (Resolver " e \)
        if (naoPermitidos.contains(evt.getKeyChar() + "")) {// se o campo que ativa o evento 'evt.getKeyChar' conter o caracter 
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //função para validar a utilização de caracters e numeros
    private void validacaoCaracterNumero(java.awt.event.KeyEvent evt) {

        String naoPermitidos = "abcdefghijklmnopqrstuvwxyz!@#$%¨&*('-){}[]+=/:;,?|_ºª°§ABCDEFGHIJKLMNOPQRSTUVWXYZ";//caracteres que não serão aceitos (Resolver " e \)
        if (naoPermitidos.contains(evt.getKeyChar() + "")) {// se o campo que ativa o evento 'evt.getKeyChar' conter o caracter 
            evt.consume();//aciona esse propriedade para eliminar a ação do evento
            JOptionPane.showMessageDialog(null, "Caractere Invalido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //função para validar a utilização de caracters e numeros
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JComboBox<String> cboTipoBusca;
    private javax.swing.JButton exportarExcel1;
    private javax.swing.JButton exportarExcel2;
    private javax.swing.JTextField fieldBairro;
    private javax.swing.JTextField fieldBuscarFuncionarios;
    private javax.swing.JFormattedTextField fieldCPF;
    private javax.swing.JFormattedTextField fieldCep;
    private javax.swing.JFormattedTextField fieldDataNacimento;
    private javax.swing.JFormattedTextField fieldEmail;
    private javax.swing.JTextField fieldNome;
    private javax.swing.JTextField fieldNumeroCasa;
    private javax.swing.JPasswordField fieldPW_1;
    private javax.swing.JPasswordField fieldPW_2;
    private javax.swing.JTextField fieldRua;
    private javax.swing.JFormattedTextField fieldTelefone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JTable tableMaisVendidos;
    private javax.swing.JTable tableMelhoresVendedores;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodFabricante;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JFormattedTextField txtPreco;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
