/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.UTILS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marcos Vinícius Santos Souza
 * Classe para abrir a conexão com o banco de dados
 */
public class ConexaoMysql {

    public static String STATUS = "Não Conectado!";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static String SERVER = "localhost";
    public static String DATABASE = "banco_pi2";

    public static String LOGIN = "root";
    public static String SENHA = "";

    public static String URL = "";

    public static Connection CONEXAO = null;

    /**
     * Metodo desenvolvido para abrir a conexão com o banco de dados
    @throws Pode gerar erro de conexão com o banco de dados, o erro pode ser pelo login ou a senha estarem incorretos ou o banco de dados estar desconectado
    * @return retorna uma Connection,que vai ser se a conexão está aberta ou fechada
    */
    public static Connection abrirConexao() throws SQLException {

        URL = "jdbc:mysql://" + SERVER + ":3306/" + DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

        if (CONEXAO == null) {

            try {

                Class.forName(DRIVER);
                CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);

                if (CONEXAO != null) {
                    STATUS = "Conexão realizada com sucesso!";
                } else {
                    STATUS = "Problemas ao realizar conexão com o banco de dados!";
                }

            } catch (ClassNotFoundException e) {
                throw new ClassCastException("O Driver de conexão com o banco de dados não foi encontrado!");
            } catch (SQLException e) {
                throw new SQLException("Erro ao realizar conexão (Ex: login ou senha do banco de dados errados ou banco de dados Desconectado).");
            }

        }else{
           try {
               
                if(CONEXAO.isClosed())
                    CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conexão.");
            }
        }

        return CONEXAO;
    }
    /**
     * Metodo desenvolvido para saber o status da conexão
     * @return retorna uma String que seria o status da conexao
     */
    public static String getStatusConexao(){
        return STATUS;
    }
    /**
     * 
     * Metodo Desenvolvido apenas para fechar a conexão com o banco de dados caso ela esteja aberta
     * @return boolean, se o status estiver "Não conectado" retorna true e caso tenha dado algum problema para desconectar retorna false
     */
    public static boolean fecharConexao(){
        boolean retorno = false;
        
      try {
            if(CONEXAO!=null){
                if(!CONEXAO.isClosed())
                    CONEXAO.close();
            }
            
            STATUS = "Não conectado";
            retorno = true;
            
         } catch (SQLException e) {
            retorno = false;
        }
        
        return retorno;
    
    }

}
