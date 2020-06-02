/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.RelatoriosDAO;
import com.loja.informatica.MODEL.Relatorios;
import java.util.ArrayList;


/**
 *
 * @author Marcos Vinícius Santos Souza
 */
public class ControllerRelatorios {

    public static ArrayList<String[]> BuscarRegistros(String dataInicio, String dataFim) {

        ArrayList<Relatorios> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = RelatoriosDAO.gerarRelatorio(dataInicio, dataFim);

        for (Relatorios relatorios : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(relatorios.getIdVenda()),
                String.valueOf(relatorios.getNome()),
                String.valueOf(relatorios.getCpf()),
                String.valueOf(relatorios.getData()),
                String.valueOf(relatorios.getValorTotal())

            });
        }

        return retorno;

    }
    
    public static ArrayList<String[]> buscarRegistroDetalhe(int idVenda){
        
        ArrayList<Relatorios> listarRegistros = new ArrayList<>();
        
        ArrayList<String[]> retorno = new ArrayList<>();
        
        listarRegistros = RelatoriosDAO.gerarRelatorio2(idVenda);
        
        for (Relatorios relatorios : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(relatorios.getIdVenda()),
                String.valueOf(relatorios.getTipoModelo()),
                String.valueOf(relatorios.getDescricao()),
                String.valueOf(relatorios.getQuantidade()),
                String.valueOf(relatorios.getValorUnitario())
            });
        }
        
        return retorno;
    }
}
