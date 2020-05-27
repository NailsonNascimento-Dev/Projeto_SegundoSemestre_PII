/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.Produto;
import com.loja.informatica.MODEL.TipoProduto;
import com.loja.informatica.UTILS.ConexaoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class VendaDAO {

    public static ArrayList<Produto> carregarRegistros() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("select tipo_produto.modelo_tipo, produto.modelo_codigo, "
                    + "produto.marca, produto.quantidade, produto.preco from tipo_produto " +
                      "inner join produto on tipo_produto.id_tipo = produto.Id_tipo;");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setGrupo(resultado.getString("modelo_tipo"));
                produto.setModelo_codigo(resultado.getString("modelo_codigo"));
                produto.setMarca(resultado.getString("marca"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("preco"));

                listarRegistros.add(produto);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            listarRegistros = null;
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

        return listarRegistros;

    }

}
