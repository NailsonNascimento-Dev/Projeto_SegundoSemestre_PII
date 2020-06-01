/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.FuncionariosDAO;
import com.loja.informatica.MODEL.Funcionarios;
import java.util.ArrayList;

/**
 *
 * @author Azazel
 */
public class ControllerFuncionarios {

    public static boolean CadastrarFuncionario(String nome, String sexo, String data, String cpf, String cargo, String rua,
            String cep, int numeroCasa, String bairro, String email, String telefone, String senha) {

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

    public static boolean AlterarRegistro(int id, String nome, String sexo, String data, String cpf, String cargo, String rua,
            String cep, int numeroCasa, String bairro, String email, String telefone, String senha) {

        Funcionarios funcionarios = new Funcionarios();
        funcionarios.setId(id);
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

        return FuncionariosDAO.atualizarRegistro(funcionarios);
    }

    public static boolean ExcluirRegistro(int id) {

        return FuncionariosDAO.excluirRegistro(id);
    }

    public static ArrayList<String[]> BuscarRegistro(String busca) {
        ArrayList<Funcionarios> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = FuncionariosDAO.buscarRegistros(busca);

        for (Funcionarios funcionarios : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(funcionarios.getId()),
                String.valueOf(funcionarios.getNome()),
                String.valueOf(funcionarios.getSexo()),
                String.valueOf(funcionarios.getData()),
                String.valueOf(funcionarios.getCpf()),
                String.valueOf(funcionarios.getCargo()),
                String.valueOf(funcionarios.getRua()),
                String.valueOf(funcionarios.getCep()),
                String.valueOf(funcionarios.getNumeroCasa()),
                String.valueOf(funcionarios.getBairro()),
                String.valueOf(funcionarios.getEmail()),
                String.valueOf(funcionarios.getTelefone()),
                String.valueOf(funcionarios.getSenha1())
            });
        }

        return retorno;

    }

    public static ArrayList<String[]> CarregarRegistros() {

        ArrayList<Funcionarios> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = FuncionariosDAO.carregarRegistros();

        for (Funcionarios funcionarios : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(funcionarios.getId()),
                String.valueOf(funcionarios.getNome()),
                String.valueOf(funcionarios.getSexo()),
                String.valueOf(funcionarios.getData()),
                String.valueOf(funcionarios.getCpf()),
                String.valueOf(funcionarios.getCargo()),
                String.valueOf(funcionarios.getRua()),
                String.valueOf(funcionarios.getCep()),
                String.valueOf(funcionarios.getNumeroCasa()),
                String.valueOf(funcionarios.getBairro()),
                String.valueOf(funcionarios.getEmail()),
                String.valueOf(funcionarios.getTelefone()),
                String.valueOf(funcionarios.getSenha1())
            });
        }

        return retorno;
    }
    
    
    
    public static String login (String senha1, int id){
        
        String cargo;
        
        Funcionarios usuario = new Funcionarios();
        
       usuario.setSenha1(senha1);
       usuario.setId(id);
       cargo = FuncionariosDAO.telaLogin(usuario);
      return cargo;
    }
}
