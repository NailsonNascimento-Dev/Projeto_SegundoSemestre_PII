/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.TipoProduto;
import com.loja.informatica.UTILS.ConexaoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nailson Nascimento <nailsonbr@gmail.com>
 */
public class TipoProdutoDAO {
    
    public static ArrayList<TipoProduto> CarregaTipoProduto() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<TipoProduto> listaTipoProduto = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("SELECT * FROM tipo_produto");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                TipoProduto tipos= new TipoProduto();

                tipos.setId_produto(resultado.getInt("id_tipo"));
                tipos.setModelo_tipo(resultado.getString("modelo_tipo"));
               

                listaTipoProduto.add(tipos);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            listaTipoProduto = null;
        } finally {

            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listaTipoProduto;
    }
    
    
    
    
    
}
