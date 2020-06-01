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
 * @author Marcos Vinícius Santos Souza
 */
public class RelatoriosDAO {

    public static ArrayList<Relatorios> gerarRelatorio(String dataInicio, String dataFim) {
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

            while (resultado.next()) {
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

    public static ArrayList<Relatorios> gerarRelatorio2(int idVenda) {
        ResultSet resultado = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Relatorios> listarRegistros = new ArrayList<>();

        try {
            conexao = ConexaoMysql.abrirConexao();

            instrucaoSQL = conexao.prepareStatement(" select tp.modelo_tipo, p.descricao, d.quantidade, p.preco from tipo_produto as tp inner join produto as p on p.id_tipo = tp.id_tipo inner join detalhes as d on d.modelo_codigo = p.modelo_codigo inner join venda as v on v.id_venda = d.id_venda where v.id_venda = ?;");

            instrucaoSQL.setInt(1, idVenda);

            resultado = instrucaoSQL.executeQuery();

            while (resultado.next()) {
                Relatorios relatorios = new Relatorios();

                relatorios.setTipoModelo(resultado.getString("modelo_tipo"));
                relatorios.setDescricao(resultado.getString("descricao"));
                relatorios.setQuantidade(resultado.getInt("quantidade"));
                relatorios.setValorUnitario(resultado.getDouble("preco"));

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
