/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.ClienteDAO;
import com.loja.informatica.DAO.VendaDAO;
import com.loja.informatica.MODEL.Funcionarios;
import com.loja.informatica.MODEL.Produto;
import com.loja.informatica.MODEL.cliente;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class ControllerVenda {

    public static ArrayList<String[]> CarregarProdutos() {

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = VendaDAO.carregarRegistros();

        for (Produto produto : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(produto.getGrupo()),
                String.valueOf(produto.getModelo_codigo()),
                String.valueOf(produto.getMarca()),
                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getPreco())
            });
        }

        return retorno;

    }

    public static boolean RealizarVenda(String cpf, int id, int quantidade, double preco) {

        cliente cliente = new cliente();
        Funcionarios funcionario = new Funcionarios();
        Produto produto = new Produto();

        cliente.setCpf(cpf);
        funcionario.setId(id);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);

        return VendaDAO.RealizarVenda(cliente, funcionario, produto);

    }

    public static int IdVenda() {
        System.out.print("");
        return VendaDAO.IdVenda();
    }

    public static boolean DetalhesVenda(int id, String modelo, int quantidade) {

        Produto produto = new Produto();

        produto.setId(id);
        produto.setModelo_codigo(modelo);
        produto.setQuantidade(quantidade);

        return VendaDAO.DetalhesVenda(produto);

    }

    public static ArrayList<String[]> CarregarHistorico() {

        ArrayList<Produto> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = VendaDAO.carregarHistorico();
        
        for (Produto produto : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(produto.getDescricao()),
                String.valueOf(produto.getModelo_codigo()),
                String.valueOf(produto.getId()),
                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getPreco()),
                String.valueOf(produto.getData()),});
        }
        return retorno;
    }
}
