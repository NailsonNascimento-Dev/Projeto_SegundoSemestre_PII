/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;
import com.loja.informatica.DAO.VendaDAO;
import com.loja.informatica.MODEL.Produto;
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
                String.valueOf(produto.getTipo()),
                String.valueOf(produto.getCodigoFabricante()),
                String.valueOf(produto.getMarca()),
                String.valueOf(produto.getQuantidade()),
                String.valueOf(produto.getPreco())        
            });
        }
       

        return retorno;

    }
    
}
