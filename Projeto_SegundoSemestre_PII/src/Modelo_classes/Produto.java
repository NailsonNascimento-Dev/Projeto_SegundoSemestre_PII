/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_classes;

/**
 *
 * @author Nailson Nascimento <nailsonbr@gmail.com>
 */
public class Produto {
    
    public String nomeDescricao;
    public String CodigoFabricante;
    public String marca;
    public String[] tipo;
    public int quantidade;
    
    public String toString(){
        return(
                "Nome: "+ nomeDescricao+
                "codigo: "+CodigoFabricante+
                "Marca: "+ marca+
                "tipo: "+ tipo[0] +
                "Quantiade em Estoque: " + quantidade);
    }  
    
    
}
