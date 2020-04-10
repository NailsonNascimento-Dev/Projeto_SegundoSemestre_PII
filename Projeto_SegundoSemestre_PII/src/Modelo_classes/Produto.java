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
    
    private String descricao;
    private String codigoFabricante;
    private String marca;
    private String[] tipo;
    private int quantidade;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(String codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String[] getTipo() {
        return tipo;
    }

    public void setTipo(String[] tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
    
    
    
    
    
    
    
    
    public String toString(){
        return(
                "Nome: "+ descricao+
                "codigo: "+ codigoFabricante+
                "Marca: "+ marca+
                "tipo: "+ tipo[0] +
                "Quantiade em Estoque: " + quantidade);
    }  
    
    
}
