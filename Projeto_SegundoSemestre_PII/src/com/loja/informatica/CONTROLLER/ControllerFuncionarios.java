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
 * @author Marcos Vinícius Santos Souza
 *
 */
public class ControllerFuncionarios {

    /**
     * Metodo desenvolvido para passar a requisição da view para a DAO pondendo
     * cadastrar um registro
     *
     * @param nome
     * @param sexo
     * @param data
     * @param cpf
     * @param cargo
     * @param rua
     * @param cep
     * @param numeroCasa
     * @param bairro
     * @param email
     * @param telefone
     * @param senha
     * @return retorna true caso a os dados passados consigam ser inseridos no
     * banco de dados atraves da classe funcionarioDAo
     */
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

    /**
     * Metodo desenvolvido para alterar um registro enviando a requisisão da
     * view para a classe DAO
     *
     * @param id
     * @param nome
     * @param sexo
     * @param data
     * @param cpf
     * @param cargo
     * @param rua
     * @param cep
     * @param numeroCasa
     * @param bairro
     * @param email
     * @param telefone
     * @param senha
     * @return retorna true caso os dados passados para a classe funcionarioDAO
     * consigam consigam atualizar determinado registro no banco de dados
     */
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

    /**
     * Metodo desenvolvido para enviar a exclusão de um registro pelo seu id
     * para a classe DAO
     *
     * @param id
     * @return retorna true caso consiga excluir um registro atraves da classe
     * DAO
     */
    public static boolean ExcluirRegistro(int id) {

        return FuncionariosDAO.excluirRegistro(id);
    }

    /**
     * Metodo desenvolvido para enviar a requisição da view para a DAO para
     * buscar um registro em especifico
     *
     * @param busca
     * @return retorna um array de string contendo os dados do rigistro em
     * especifico a ser buscado
     */
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

    /**
     * Metodo desenvolvido para enviar a requisição da viw para a classe DAO
     * trazendo todos os registros da tabela
     *
     * @return retorna um array de string contendo todos os dados da tabela do
     * banco de dados
     */
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
/**
 * Metodo desenvolvido para enviar a requisição de login da view para classe DAO para o usuario poder se logar no sistema
 * @param senha1
 * @param id
 * @return retorna uma string contendo o cargo em especifico 
 */
    public static String login(String senha1, int id) {

        String cargo;
        Funcionarios usuario = new Funcionarios();
        usuario.setSenha1(senha1);
        usuario.setId(id);
        cargo = FuncionariosDAO.telaLogin(usuario);
        return cargo;
    }
}
