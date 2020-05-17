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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            instrucaoSQL = conexao.prepareStatement("INSERT INTO funcionario (nome, sexo, data_nascimento, cpf, cargo, rua, cep, numero_casa, bairro, email, telefone, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?)");

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

    public static boolean atualizarRegistro(Funcionarios funcionarios) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE funcionario SET nome = ?, sexo = ?, data_nascimento = ?, cpf = ?, cargo = ?, rua = ?, cep = ?, numero_casa = ?, bairro = ?, email = ?, telefone = ?, senha = ? WHERE id = ?");

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
            instrucaoSQL.setInt(13, funcionarios.getId());

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

    public static boolean excluirRegistro(int id) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("DELETE FROM funcionario WHERE id = ?");

            instrucaoSQL.setInt(1, id);

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

    public static ArrayList<Funcionarios> buscarRegistros(String busca) {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Funcionarios> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE id = ? OR nome LIKE ? OR cargo LIKE ?;");

            instrucaoSQL.setString(1, busca);
            instrucaoSQL.setString(2, "%" + busca + '%');
            instrucaoSQL.setString(3, "%" + busca + '%');

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Funcionarios funcionarios = new Funcionarios();

                funcionarios.setId(resultado.getInt("id"));
                funcionarios.setNome(resultado.getString("nome"));
                funcionarios.setSexo(resultado.getString("sexo"));
                funcionarios.setData(resultado.getString("data_nascimento"));
                funcionarios.setCpf(resultado.getString("cpf"));
                funcionarios.setCargo(resultado.getString("cargo"));
                funcionarios.setRua(resultado.getString("rua"));
                funcionarios.setCep(resultado.getString("cep"));
                funcionarios.setNumeroCasa(resultado.getInt("numero_casa"));
                funcionarios.setBairro(resultado.getString("bairro"));
                funcionarios.setEmail(resultado.getString("email"));
                funcionarios.setTelefone(resultado.getString("telefone"));
                funcionarios.setSenha1(resultado.getString("senha"));

                listarRegistros.add(funcionarios);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            listarRegistros = null;
        } finally {

            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listarRegistros;

    }

    public static ArrayList<Funcionarios> carregarRegistros() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Funcionarios> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("SELECT * FROM funcionario");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Funcionarios funcionarios = new Funcionarios();

                funcionarios.setId(resultado.getInt("id"));
                funcionarios.setNome(resultado.getString("nome"));
                funcionarios.setSexo(resultado.getString("sexo"));
                funcionarios.setData(resultado.getString("data_nascimento"));
                funcionarios.setCpf(resultado.getString("cpf"));
                funcionarios.setCargo(resultado.getString("cargo"));
                funcionarios.setRua(resultado.getString("rua"));
                funcionarios.setCep(resultado.getString("cep"));
                funcionarios.setNumeroCasa(resultado.getInt("numero_casa"));
                funcionarios.setBairro(resultado.getString("bairro"));
                funcionarios.setEmail(resultado.getString("email"));
                funcionarios.setTelefone(resultado.getString("telefone"));
                funcionarios.setSenha1(resultado.getString("senha"));

                listarRegistros.add(funcionarios);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            listarRegistros = null;
        } finally {

            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoMysql.fecharConexao();

            } catch (SQLException ex) {
            }
        }

        return listarRegistros;
    }

}
