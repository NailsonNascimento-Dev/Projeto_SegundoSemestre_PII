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
public class Produto {
    
    private String descricao;
    private String modelo_codigo;
    private String marca;
    private TipoProduto tipo;
    private int quantidade;
    private double preco;
    private String observacao;
    
    
    
    public Produto(){
        
    }

    public Produto(String descricao, String modelo_codigo, String marca, TipoProduto tipo, int quantidade, double preco, String observacao) {
        this.descricao = descricao;
        this.modelo_codigo = modelo_codigo;
        this.marca = marca;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
        this.observacao = observacao;
    }
    
    
    
    
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModelo_codigo() {
        return modelo_codigo;
    }

    public void setModelo_codigo(String modelo_codigo) {
        this.modelo_codigo = modelo_codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

   
    
}
