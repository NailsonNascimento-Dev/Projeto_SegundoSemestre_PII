/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.Funcionarios;
import com.loja.informatica.UTILS.ConexaoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Azazel
 */
public class FuncionariosDAO {

    public static boolean cadastrarFuncionario(Funcionarios funcionarios) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("INSERT INTO loja (nome, sexo, data_nascimento, cpf, cargo, rua, cep, numero_casa, bairro, email, telefone, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?)");
                    

            instrucaoSQL.setString(1, funcionarios.getNome());
            instrucaoSQL.setString(2, funcionarios.getSexo());
            instrucaoSQL.setString(3, funcionarios.getData());
            instrucaoSQL.setString(4, funcionarios.getCpf());
            instrucaoSQL.setString(5, funcionarios.getCargo());
            instrucaoSQL.setString(6, funcionarios.getRua());
            instrucaoSQL.setString(7, funcionarios.getCep());
            instrucaoSQL.setInt(8, funcionarios.getNumeroCasa());
            instrucaoSQL.setString(9, funcionarios.getBairro());
            instrucaoSQL.setString(10, funcionarios.getEmail());
            instrucaoSQL.setString(11, funcionarios.getTelefone());
            instrucaoSQL.setString(12, funcionarios.getSenha1());

            int linhasAfetadas = instrucaoSQL.executeUpdate();

            if (linhasAfetadas >= 1) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {

            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return retorno;

    }

}
