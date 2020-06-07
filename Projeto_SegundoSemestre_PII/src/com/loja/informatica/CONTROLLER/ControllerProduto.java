/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.ProdutoDAO;
import com.loja.informatica.MODEL.Produto;
import com.loja.informatica.MODEL.TipoProduto;
import java.util.ArrayList;

/**
 *
 * @author Nailson Nascimento <nailsonbr@gmail.com>
 */

public class ControllerProduto {

    /**
     * função para criação do produto e envio para classe ProdutoDAO
     * @param descricao
     * @param modelo_codigo
     * @param marca
     * @param tipo
     * @param quantidade
     * @param preco
     * @param observacao
     * @return - retorna um boolean se foi ou não cadastrado
     */
    //descricao, modelo_codigo, marca, tipo, quantidade, preco, observacao
    public static boolean CadastrarProduto(String descricao, String modelo_codigo, String marca, TipoProduto tipo,
            int quantidade, double preco, String observacao) {

        Produto produto = new Produto();
        //System.out.println("Teste erro !");

        produto.setDescricao(descricao);
        produto.setModelo_codigo(modelo_codigo);
        produto.setMarca(marca);
        produto.setTipo(tipo);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setObservacao(observacao);

        //System.out.println("id: " + tipo.getId_tipo() + "Descricao: " + tipo.getModelo_tipo());
        return ProdutoDAO.cadastrarProduto(produto);
    }
    /**
     * função para buscar produtos cadastrados
     * @return - retorna um ArrayList com os produtos cadastrados
     */
    public static ArrayList<String[]> CarregarProduto() {

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ProdutoDAO.carregarRegistros();

        for (Produto produto : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(produto.getDescricao()),
                String.valueOf(produto.getModelo_codigo()),
                String.valueOf(produto.getTipo().getModelo_tipo()),
                //String.valueOf(produto.getTipo().getId_tipo()),

                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getMarca()),
                String.valueOf(produto.getPreco()),
                String.valueOf(produto.getObservacao())});
        }

        return retorno;

    }
    /**
     * função para buscar registros no banco 
     * @param busca
     * @return - retorna um ArrayList com o produto selecionado, através do codigo do fabricante
     */
    public static ArrayList<String[]> buscarRegistrosCodigoFabricante(String busca) {

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ProdutoDAO.buscarRegistrosCodigoFabricante(busca);

        for (Produto produto : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(produto.getDescricao()),
                String.valueOf(produto.getModelo_codigo()),
                String.valueOf(produto.getTipo().getModelo_tipo()),
                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getMarca()),
                String.valueOf(produto.getPreco()),
                String.valueOf(produto.getObservacao()) });

        }

        return retorno;

    }
    /**
     * função para buscar registros no banco 
     * @param busca
     * @return - retorna um ArrayList com o produto selecionado, através da Descrição
     */
    public static ArrayList<String[]> buscarRegistrosDescricao(String busca) {

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ProdutoDAO.buscarRegistrosDescricao(busca);

        for (Produto produto : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(produto.getDescricao()),
                String.valueOf(produto.getModelo_codigo()),
                String.valueOf(produto.getTipo().getModelo_tipo()),
                //String.valueOf(produto.getTipo().getId_tipo()),
                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getMarca()),
                String.valueOf(produto.getPreco()), //String.valueOf(produto.getObservacao()),
            });

        }

        return retorno;

    }
    /**
     * função para buscar registros no banco 
     * @param busca
     * @return - retorna um ArrayList com o produto selecionado, através do tipo do produto
     */
    public static ArrayList<String[]> buscarRegistrosTipo(String busca) {

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ProdutoDAO.buscarRegistrosTipo(busca);

        for (Produto produto : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(produto.getDescricao()),
                String.valueOf(produto.getModelo_codigo()),
                String.valueOf(produto.getTipo().getModelo_tipo()),
                //String.valueOf(produto.getTipo().getId_tipo()),
                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getMarca()),
                String.valueOf(produto.getPreco()), //String.valueOf(produto.getObservacao()),
            });

        }

        return retorno;

    }
    /**
     * função para alterar o produto
     * observação recebe os valores alterados para salvar no produto
     * @param descricao
     * @param modelo_codigo
     * @param marca
     * @param tipo
     * @param quantidade
     * @param preco
     * @param observacao
     * @return 
     */
    public static boolean AlterarProduto(String descricao, String modelo_codigo, String marca, TipoProduto tipo,
            int quantidade, double preco, String observacao) {

        Produto produto = new Produto();

        produto.setDescricao(descricao);
        produto.setModelo_codigo(modelo_codigo);
        produto.setMarca(marca);
        produto.setTipo(tipo);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setObservacao(observacao);

        return ProdutoDAO.atualizarProduto(produto);

    }
    /**
     * função para excluir um determinado produto através do seu codigo do fabricante 
     * @param codigoFabricante - recebe um string "codigo do fabricante" para realizarmos a exlcusão
     * @return - retorna um boolean "true" para iten exluido ou "false" para erro.
     */
    public static boolean ExcluirRegistro(String codigoFabricante) {

        return ProdutoDAO.excluirProduto(codigoFabricante);
    }
    /**
     * função para adicionar uma determinada quantidade de produtos ao estoque 
     * @param codigoFabricante - String para selecionar o produto atraves do seu codigo do fabricante
     * @param quantidade - Int com a quantiade de produtos a serem adicionados
     * @return - retorna um boolean "true" para produtos diconados com sucesso "false" se der erro
     */
    public static boolean adicionarProduto(String codigoFabricante, int quantidade) {

        return ProdutoDAO.adicionarProduto(codigoFabricante, quantidade);

    }
}