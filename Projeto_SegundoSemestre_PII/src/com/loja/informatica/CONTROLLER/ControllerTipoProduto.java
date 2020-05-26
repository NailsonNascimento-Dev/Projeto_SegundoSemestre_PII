/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.TipoProdutoDAO;
import com.loja.informatica.MODEL.TipoProduto;
import java.util.ArrayList;

/**
 *
 * @author Nailson Nascimento <nailsonbr@gmail.com>
 */
public class ControllerTipoProduto {
    
        public static ArrayList<TipoProduto> CarregaTipoProduto() {

        ArrayList<TipoProduto> listarTipoProduto = new ArrayList<>();

        ArrayList<TipoProduto> retornoEnvio = new ArrayList<>();

        listarTipoProduto = TipoProdutoDAO.CarregaTipoProduto();

        for (TipoProduto tipos : listarTipoProduto) {
            retornoEnvio.add(tipos);            
            //System.out.println("ID:"+ tipos.getId_tipo() + "Descrição"  + tipos.getModelo_tipo());
        }

        return retornoEnvio;
    }
    
}
