/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.FuncionariosDAO;
import com.loja.informatica.MODEL.Funcionarios;

/**
 *
 * @author Azazel
 */
public class ControllerFuncionarios {
    
    public static boolean CadastrarFuncionario(String nome, String sexo, String data, String cpf, String cargo, String rua, String cep, int numeroCasa, String bairro, String email, String telefone, String senha){
        
        Funcionarios funcionarios = new Funcionarios();
        
        funcionarios.setNome(nome);
        funcionarios.setSexo(sexo);
        funcionarios.setData(data);
        funcionarios.setCpf(cpf);
        funcionarios.setCargo(cargo);
        funcionarios.setRua(rua);
        funcionarios.setCep(cep);
        funcionarios.setNumeroCasa(numeroCasa);
        funcionarios.setBairro(bairro);
        funcionarios.setEmail(email);
        funcionarios.setTelefone(telefone);
        funcionarios.setSenha1(senha);
        
        return FuncionariosDAO.cadastrarFuncionario(funcionarios);
    }
}
