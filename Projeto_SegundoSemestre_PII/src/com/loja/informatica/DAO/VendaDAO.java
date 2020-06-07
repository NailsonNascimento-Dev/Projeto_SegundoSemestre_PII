/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.Produto;
import com.loja.informatica.MODEL.cliente;
import com.loja.informatica.MODEL.Funcionarios;
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

    /**
     * metodo para carregar os produtos
     *
     * @return retorna um array list com os dados do produdo
     */
    public static ArrayList<Produto> carregarRegistros() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("select tipo_produto.modelo_tipo, produto.modelo_codigo, "
                    + "produto.marca, produto.quantidade, produto.preco from tipo_produto "
                    + "inner join produto on tipo_produto.id_tipo = produto.Id_tipo;");

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

    /**
     * Metodo para realizar venda
     *
     * @param cliente
     * @param funcionario
     * @param produto
     * @return retorna um boolean true caso finalize a venda e false caso não
     */
    public static boolean RealizarVenda(cliente cliente, Funcionarios funcionario, Produto produto) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into venda (cpf, id_vendedor, quantidade, valor_total) "
                    + "values (?,?,?,?)");

            instrucaoSQL.setString(1, cliente.getCpf());
            instrucaoSQL.setInt(2, funcionario.getId());
            instrucaoSQL.setInt(3, produto.getQuantidade());
            instrucaoSQL.setDouble(4, produto.getPreco());

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas >= 1) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    /**
     * metodo para buscar o id da venda
     *
     * @return retorna um int com o numero da venda
     */
    public static int IdVenda() {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ResultSet resultado = null;
        int id = 0;

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("Select Max(id_venda) as Maior from venda;");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getInt("Maior"));
                id = resultado.getInt("Maior");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return id;

    }

    /**
     * metodo para buscar um produto
     *
     * @param produto
     * @return retorna um boolean true caso ache e false caso não
     */
    public static boolean DetalhesVenda(Produto produto) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into detalhes(id_venda, modelo_codigo, quantidade)"
                    + "values (?,?,?)");

            instrucaoSQL.setInt(1, produto.getId());
            instrucaoSQL.setString(2, produto.getModelo_codigo());
            instrucaoSQL.setInt(3, produto.getQuantidade());

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas >= 1) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;
    }

    /**
     * Metodo para carregar o historico de venda
     *
     * @return retorna um array list com os dados
     */

    public static ArrayList<Produto> carregarHistorico() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("select cliente.nome, cliente.cpf, venda.id_Venda, venda.quantidade, "
                    + "venda.valor_total, venda.data_hoje from venda "
                    + "inner join cliente on venda.cpf = cliente.cpf; ");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setDescricao(resultado.getString("nome"));
                produto.setModelo_codigo(resultado.getString("cpf"));
                produto.setId(resultado.getInt("id_venda"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("valor_total"));
                produto.setData(resultado.getString("data_hoje"));

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
