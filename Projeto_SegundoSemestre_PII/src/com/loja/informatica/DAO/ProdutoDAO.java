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
 * @author Nailson Nascimento <nailsonbr@gmail.com>
 */
public class ProdutoDAO {
    /**
     * função para cadastro de produtos no banco de dados
     * @param produto - instaciação do produto criado na classe controllerProduto
     * @return - retorno de para classe controllerProduto
     */
    public static boolean cadastrarProduto(Produto produto) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            //cria a conexao com o banco 
            conexao = ConexaoMysql.abrirConexao();
            
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("INSERT INTO produto (descricao, modelo_codigo, marca, id_tipo, quantidade, preco, observacao) VALUES (?, ?, ?, ?, ?, ?, ?)");

            instrucaoSQL.setString(1, produto.getDescricao());
            instrucaoSQL.setString(2, produto.getModelo_codigo());
            instrucaoSQL.setString(3, produto.getMarca());
            instrucaoSQL.setInt(4, produto.getTipo().getId_tipo());
            instrucaoSQL.setInt(5, produto.getQuantidade());
            instrucaoSQL.setDouble(6, produto.getPreco());
            instrucaoSQL.setString(7, produto.getObservacao());

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
     * função para caregar/buscar os produtos no banco de dados
     * @return - retorna um ArrayList com os produtos cadastrados no banco, para classe controllerProduto 
     */
    public static ArrayList<Produto> carregarRegistros() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        //criação de um ArrayList do tipo produto
        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {
            //cria a conexao com o banco 
            conexao = ConexaoMysql.abrirConexao();
            
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("select  p.descricao as descricaoProduto, p.modelo_codigo as codigoFabricante,"
                    + "  p.id_tipo as idTipo, marca, quantidade, preco, observacao,"
                    + " c.id_tipo as idTipo, c.modelo_tipo as descricaoTipo from produto p inner join tipo_produto"
                    + " c on c.id_tipo = p.id_tipo;");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setDescricao(resultado.getString("descricaoProduto"));
                produto.setModelo_codigo(resultado.getString("codigoFabricante"));

                TipoProduto tipo = new TipoProduto();
                tipo.setId_produto(resultado.getInt("idTipo"));
                tipo.setModelo_tipo(resultado.getString("descricaoTipo"));
                produto.setTipo(tipo);

                produto.setMarca(resultado.getString("marca"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setObservacao(resultado.getString("observacao"));

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
     * função para buscar produtos cadastrados através do codigo do fabricante
     * @param busca - recebe uma String de busca 
     * @return - retorna o produto com o codigo digitado 
     */
    public static ArrayList<Produto> buscarRegistrosCodigoFabricante(String busca) {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        //criação de um ArrayList do tipo produto
        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {
             //cria a conexao com o banco 
            conexao = ConexaoMysql.abrirConexao();
            
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("select produto.descricao,produto.modelo_codigo, tipo_produto.modelo_tipo,"
                    + "produto.quantidade, produto.marca,  produto.preco, produto.observacao from tipo_produto"
                    + " inner join produto on tipo_produto.id_tipo = produto.Id_tipo where produto.modelo_codigo = ?");

            instrucaoSQL.setString(1, busca);

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setDescricao(resultado.getString("descricao"));
                produto.setModelo_codigo(resultado.getString("modelo_codigo"));
                produto.setMarca(resultado.getString("marca"));

                TipoProduto tipo = new TipoProduto();

                tipo.setModelo_tipo(resultado.getString("modelo_tipo"));

                produto.setTipo(tipo);
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setObservacao(resultado.getString("observacao"));

                listarRegistros.add(produto);

                produto.toString();

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
     * função para buscar produtos cadastrados através da descrição
     * @param busca - recebe uma String de busca 
     * @return - retorna o produto com a descrição degigitada
     */
    public static ArrayList<Produto> buscarRegistrosDescricao(String busca) {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        //criação de um ArrayList do tipo produto
        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {
            //cria a conexao com o banco
            conexao = ConexaoMysql.abrirConexao();
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("select produto.descricao,produto.modelo_codigo, tipo_produto.modelo_tipo,"
                    + "produto.quantidade, produto.marca,  produto.preco from tipo_produto"
                    + " inner join produto on tipo_produto.id_tipo = produto.Id_tipo where produto.descricao = ?");

            instrucaoSQL.setString(1, busca);

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setDescricao(resultado.getString("descricao"));
                produto.setModelo_codigo(resultado.getString("modelo_codigo"));
                produto.setMarca(resultado.getString("marca"));

                TipoProduto tipo = new TipoProduto();
              
                tipo.setModelo_tipo(resultado.getString("modelo_tipo"));

                produto.setTipo(tipo);
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setObservacao(resultado.getString("observacao"));

                listarRegistros.add(produto);

                produto.toString();

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
     * função para buscar produtos cadastrados através do tipo
     * @param busca - recebe uma String de busca 
     * @return - retorna o produto os produtos do tipo desejado
     */
    public static ArrayList<Produto> buscarRegistrosTipo(String busca) {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        //criação de um ArrayList do tipo produto
        ArrayList<Produto> listarRegistros = new ArrayList<>();

        try {
            //cria a conexao com o banco
            conexao = ConexaoMysql.abrirConexao();
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("select produto.descricao,produto.modelo_codigo, tipo_produto.modelo_tipo,"
                    + "produto.quantidade, produto.marca,  produto.preco from tipo_produto"
                    + " inner join produto on tipo_produto.id_tipo = produto.Id_tipo where tipo_produto.modelo_tipo = ?");

            instrucaoSQL.setString(1, busca);
            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();

                produto.setDescricao(resultado.getString("descricao"));
                produto.setModelo_codigo(resultado.getString("modelo_codigo"));
                produto.setMarca(resultado.getString("marca"));

                TipoProduto tipo = new TipoProduto();
                //tipo.setId_produto(resultado.getInt("id_tipo"));
                tipo.setModelo_tipo(resultado.getString("modelo_tipo"));

                produto.setTipo(tipo);
                produto.setQuantidade(resultado.getInt("quantidade"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setObservacao(resultado.getString("observacao"));

                listarRegistros.add(produto);

                produto.toString();

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
     * função para editar/alterar o produto selecionado
     * @param produto- recebe o produto
     * @return - retorna o status como um booleano "true" para atualizado com sucesso 
     * e "false" quando o produto não for alterado.
     */
    public static boolean atualizarProduto(Produto produto) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            //cria a conexao com o banco
            conexao = ConexaoMysql.abrirConexao();
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("update produto set descricao=?, marca=?, id_tipo=?, quantidade=?, "
                    + "preco=?, observacao=? where modelo_codigo=?");

            instrucaoSQL.setString(1, produto.getDescricao());
            instrucaoSQL.setString(2, produto.getMarca());
            instrucaoSQL.setInt(3, produto.getTipo().getId_tipo());
            instrucaoSQL.setInt(4, produto.getQuantidade());
            instrucaoSQL.setDouble(5, produto.getPreco());
            instrucaoSQL.setString(6, produto.getObservacao());
            instrucaoSQL.setString(7, produto.getModelo_codigo());
           
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
     * função para excluir produto através do codigo do fabricante 
     * @param codigoFabricante - recebe um string com o codigo a ser excluido 
     * @return - retorna um boolean se ocorreu a exlusão
     */
    public static boolean excluirProduto(String codigoFabricante) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            //cria a conexao com o banco
            conexao = ConexaoMysql.abrirConexao();
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("DELETE FROM produto WHERE modelo_codigo = ?");

            instrucaoSQL.setString(1, codigoFabricante);

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
     * função para dar entradada ao produtos ao estoque
     * observação o produto ja deve estar cadastrado 
     * @param codigoFabricante - recebe o codigo do fabricante 
     * @param quantidade - recebe a quantidade a ser adicionada
     * @return - retorna boolean se foi ou não adicionado ao estoque
     */
    public static boolean adicionarProduto(String codigoFabricante, int quantidade) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            //cria a conexao com o banco
            conexao = ConexaoMysql.abrirConexao();
            //prepara a string para enviar ao banco de dados.
            instrucaoSQL = conexao.prepareStatement("update produto set quantidade = quantidade + ? where modelo_codigo = ?;");

            
            instrucaoSQL.setInt(1, quantidade);
            instrucaoSQL.setString(2, codigoFabricante);
            

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


}
