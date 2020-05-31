/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loja.informatica.DAO;

import com.loja.informatica.MODEL.Relatorios;
import com.loja.informatica.UTILS.ConexaoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Azazel
 */
public class RelatoriosDAO {

    public static ArrayList<Relatorios> gerarRelatorio(String dataInicio, String dataFim){
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        ArrayList<Relatorios> listarRegistros = new ArrayList<>();
        
        try {
            conexao = ConexaoMysql.abrirConexao();
            
            instrucaoSQL = conexao.prepareStatement("Select v.id_venda, c.nome, v.cpf, v.data_hoje, v.valor_total from venda as v inner join cliente as c on c.cpf = v.cpf where date(data_hoje) >= ? and date(data_hoje) <= ?;");
            
            instrucaoSQL.setString(1, dataInicio);
            instrucaoSQL.setString(2, dataFim);
            
            resultado = instrucaoSQL.executeQuery();
            
            while(resultado.next()){
                Relatorios relatorios = new Relatorios();
                
                relatorios.setIdVenda(resultado.getInt("id_venda"));
                relatorios.setNome(resultado.getString("nome"));
                relatorios.setCpf(resultado.getString("cpf"));
                relatorios.setData(resultado.getDate("data_hoje"));
                relatorios.setValorTotal(resultado.getDouble("valor_total"));
                
                listarRegistros.add(relatorios);
                
                
                
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
