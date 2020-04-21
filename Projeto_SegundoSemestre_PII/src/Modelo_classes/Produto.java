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
    
    //atributos da classe
    private String descricao;
    private String codigoFabricante;
    private String marca;
    private String tipo;
    private String observacao;
    private int quantidade;
    private double preco;
    

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getObservacao(){
        return observacao;
    }
    
    public void setObservacao(String observacao){
        this.observacao = observacao;
    }
    
    
    
    public String toString(){
        return(
                "Nome: "+ descricao+
                "\ncodigo: "+ codigoFabricante+
                "\nMarca: "+ marca+
                "\ntipo: "+ tipo +
                "\nQuantiade em Estoque: " + quantidade +
                "\nobsevação: " + observacao +
                "\npreço: " + preco);
    }  
    
    
}
