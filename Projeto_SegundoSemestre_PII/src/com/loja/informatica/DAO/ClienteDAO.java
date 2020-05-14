package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.cliente;
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
public class ClienteDAO {

    public static boolean CadastrarCliente(cliente cliente) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into cliente (nome,nascimento,cpf,sexo,estado_civil,rua,cep,numero_casa,bairro,email,fone) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)");

            instrucaoSQL.setString(1, cliente.getNome());
            instrucaoSQL.setString(2, cliente.getData());
            instrucaoSQL.setString(3, cliente.getCpf());
            instrucaoSQL.setString(4, cliente.getSexo());
            instrucaoSQL.setString(5, cliente.getEstadoCivil());
            instrucaoSQL.setString(6, cliente.getRua());
            instrucaoSQL.setString(7, cliente.getCep());
            instrucaoSQL.setInt(8, cliente.getNumeroCasa());
            instrucaoSQL.setString(9, cliente.getBairro());
            instrucaoSQL.setString(10, cliente.getEmail());
            instrucaoSQL.setString(11, cliente.getTelefone());

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

    public static boolean atualizarRegistro(cliente cliente) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("update cliente set nome=?,nascimento=?,sexo=?,estado_civil=?,rua=?,"
                    + "cep=?,numero_casa=?,bairro=?,email=?,fone=? where cpf=?");

            instrucaoSQL.setString(1, cliente.getNome());
            instrucaoSQL.setString(2, cliente.getData());
            instrucaoSQL.setString(3, cliente.getSexo());
            instrucaoSQL.setString(4, cliente.getEstadoCivil());
            instrucaoSQL.setString(5, cliente.getRua());
            instrucaoSQL.setString(6, cliente.getCep());
            instrucaoSQL.setInt(7, cliente.getNumeroCasa());
            instrucaoSQL.setString(8, cliente.getBairro());
            instrucaoSQL.setString(9, cliente.getEmail());
            instrucaoSQL.setString(10, cliente.getTelefone());
            instrucaoSQL.setString(11, cliente.getCpf());
            
            

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
    
    public static boolean excluirRegistro(int cpf) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("delete from cliente where id_cli = ?");

            instrucaoSQL.setInt(1, cpf);

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

        public static ArrayList<cliente> buscarRegistros(String busca) {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<cliente> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("select * from cliente where cpf = ?");

            instrucaoSQL.setString(1, busca);

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                cliente cliente = new cliente();

                cliente.setId(resultado.getInt("id_cli"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setData(resultado.getString("nascimento"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setSexo(resultado.getString("sexo"));
                cliente.setEstadoCivil(resultado.getString("estado_civil"));
                cliente.setRua(resultado.getString("rua"));
                cliente.setCep(resultado.getString("cep"));
                cliente.setNumeroCasa(resultado.getInt("numero_casa"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("fone"));


                listarRegistros.add(cliente);

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
        
         public static ArrayList<cliente> carregarRegistros() {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<cliente> listarRegistros = new ArrayList<>();

        try {

            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("select * from cliente");

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                cliente cliente = new cliente();

                cliente.setId(resultado.getInt("id_cli"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setData(resultado.getString("nascimento"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setSexo(resultado.getString("sexo"));
                cliente.setEstadoCivil(resultado.getString("estado_civil"));
                cliente.setRua(resultado.getString("rua"));
                cliente.setCep(resultado.getString("cep"));
                cliente.setNumeroCasa(resultado.getInt("numero_casa"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setTelefone(resultado.getString("fone"));


                listarRegistros.add(cliente);

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
