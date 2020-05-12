package com.loja.informatica.UTILS;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class conexaoBancoDeDados {

    public Statement stm;

    public ResultSet rs;

    public Connection ctm;

    public String Driver = "org.postgresql.Driver";
    public String caminho = "jdbc:mysql://localhost:3306/Loja";
    public String usuario = "root";
    public String senha = "";

    public void conexao() {

        System.setProperty("jdbc.Drivers", Driver);

        try {

            ctm = DriverManager.getConnection(caminho, usuario, senha);
             //JOptionPane.showMessageDialog(null, "Conexão realizada");
             System.out.println("Conexão relizada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Conexão não realizada");
            System.out.println(ex);
        }

    }

    public void executaSQl(String sql) {

        try {
            stm = ctm.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar");
            System.out.println(ex);
        }

    }

    public void desconecta() {
        try {
            ctm.close();
            //JOptionPane.showMessageDialog(null, "Conexão encerrada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar");
            System.out.println(ex);
        }
    }

}
