/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.MODEL;

/**
 *
 * @author Nailson Nascimento <nailsonbr@gmail.com>
 */
public class TipoProduto {
    private int id_tipo;
    private String modelo_tipo;

    public TipoProduto(){
        
    }
    
    public TipoProduto(int id_tipo, String modelo_tipo){
        this.id_tipo = id_tipo;
        this.modelo_tipo = modelo_tipo;
               
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_produto(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getModelo_tipo() {
        return modelo_tipo;
    }

    public void setModelo_tipo(String modelo_tipo) {
        this.modelo_tipo = modelo_tipo;
    }

    

    @Override
    public String toString() {
     return getModelo_tipo();
    }


    
    
    
    
    
    
    
}
