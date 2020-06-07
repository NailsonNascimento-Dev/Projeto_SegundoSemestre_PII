/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.Funcionarios;
import com.loja.informatica.VIEWS.telaVendedor;
import com.loja.informatica.UTILS.ConexaoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Santos Souza
 */
public class FuncionariosDAO {

    /**
     * Metodo desenvolvido para inserir um funcionario no banco de dados atraves dos dados enviados da classe ControllerFuncionarios
     * @param funcionarios
     * @return retorna true caso os dados passados consigam inserir um novo registro no banco de dados.
     */
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
/**
 * Metodo desenvolvido para atualizar um registro em especifico no banco de dados
 * @param funcionarios
 * @return retorna true caso consiga atualizar os dados desse registro
 */
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
/**
 * Metodo desenvolvido para excluir um registro em especifico pelo seu id no banco de dados
 * @param id
 * @return retona true caso consiga excluir um registro em especifico do banco de dados
 */
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
/**
 * Metodo desenvolvido para buscar um registro especifico ou uma categoria de registros em espefico no banco de dados
 * @param busca
 * @return retorna uma lista do objeto funcionario contendo os registros em questão
 */
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
/**
 * Metodo desenvolvido para trazer todos os registros da tabela funcionarios.
 * @return retorna uma lista do objeto funcionario contendo todos os registros da tabela funcionario
 */
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
/**
 * Metodo desenvolvido para trazer o cargo em espeficio do funcionario baseado em seu login e senha
 * @param usuario
 * @return retona uma string contendo o seu cargo assim podendo fazer com que ele se conecte na tela correta na camada view
 */
    public static String telaLogin(Funcionarios usuario) {
        String cargo = "";
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ResultSet resultado = null;
        int id = 0;

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("select * from funcionario where id=? and senha=?");

            instrucaoSQL.setInt(1, usuario.getId());
            instrucaoSQL.setString(2, usuario.getSenha1());

            //int linhasAfetadas = instrucaoSQL.executeQuery();
            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                usuario.setCargo(resultado.getString("cargo"));
                cargo = resultado.getString("cargo");
                id = resultado.getInt("id");
            }
            telaVendedor.pegaId(id);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return cargo;
    }

}
