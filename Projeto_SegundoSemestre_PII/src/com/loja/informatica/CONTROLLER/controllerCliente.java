package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.ClienteDAO;
import com.loja.informatica.MODEL.cliente;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class controllerCliente {

    /**
     * Metodo para cadastrar cliente
     *
     * @param nome
     * @param sexo
     * @param data
     * @param cpf
     * @param estadoCivil
     * @param rua
     * @param cep
     * @param numeroCasa
     * @param bairro
     * @param email
     * @param telefone
     * 
     * @return retorna true caso cadastre um cliente e false caso não
     *
     */
    public static boolean CadastrarCliente(String nome, String data, String cpf, String sexo, String estadoCivil, String rua,
            String cep, int numeroCasa, String bairro, String email, String telefone) {

        cliente cliente = new cliente();
        cliente.setNome(nome);
        cliente.setData(data);
        cliente.setCpf(cpf);
        cliente.setSexo(sexo);
        cliente.setEstadoCivil(estadoCivil);
        cliente.setRua(rua);
        cliente.setCep(cep);
        cliente.setNumeroCasa(numeroCasa);
        cliente.setBairro(bairro);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        return ClienteDAO.CadastrarCliente(cliente);

    }
    
        /**
     * Metodo para alterar cliente
     *
     * @param nome
     * @param sexo
     * @param data
     * @param cpf
     * @param estadoCivil
     * @param rua
     * @param cep
     * @param numeroCasa
     * @param bairro
     * @param email
     * @param telefone
     * 
     * @return retorna true caso altere um cliente e false caso não
     *
     */

    public static boolean AlterarCliente(String nome, String data, String sexo, String estadoCivil, String rua,
            String cep, int numeroCasa, String bairro, String email, String telefone, String cpf) {

        cliente cliente = new cliente();
        cliente.setNome(nome);
        cliente.setData(data);
        cliente.setSexo(sexo);
        cliente.setEstadoCivil(estadoCivil);
        cliente.setRua(rua);
        cliente.setCep(cep);
        cliente.setNumeroCasa(numeroCasa);
        cliente.setBairro(bairro);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);

        return ClienteDAO.atualizarRegistro(cliente);

    }
    
    /**
     * *
     * @param cpf
     * @return retorna uma boolean true caso ache e false caso não 
     */

    public static boolean ExcluirRegistro(int cpf) {

        return ClienteDAO.excluirRegistro(cpf);
    }

    /**
     * metodo para buscar um cliente especifico nos registros
     * 
     * @param busca
     * 
     * @return retorna um array list com o registro buscado 
     */
    public static ArrayList<String[]> BuscarRegistro(String busca) {

        ArrayList<cliente> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ClienteDAO.buscarRegistros(busca);

        for (cliente cliente : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(cliente.getId()),
                String.valueOf(cliente.getNome()),
                String.valueOf(cliente.getData()),
                String.valueOf(cliente.getCpf()),
                String.valueOf(cliente.getSexo()),
                String.valueOf(cliente.getEstadoCivil()),
                String.valueOf(cliente.getRua()),
                String.valueOf(cliente.getCep()),
                String.valueOf(cliente.getNumeroCasa()),
                String.valueOf(cliente.getBairro()),
                String.valueOf(cliente.getEmail()),
                String.valueOf(cliente.getTelefone())

            });
        }

        return retorno;

    }
    
    /**
     * Metodo para carregar os registros dos cliente
     * 
     * @return retorna um array list com os registros buscados 
     */

    public static ArrayList<String[]> CarregarRegistros() {

        ArrayList<cliente> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ClienteDAO.carregarRegistros();

        for (cliente cliente : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(cliente.getId()),
                String.valueOf(cliente.getNome()),
                String.valueOf(cliente.getData()),
                String.valueOf(cliente.getCpf()),
                String.valueOf(cliente.getSexo()),
                String.valueOf(cliente.getEstadoCivil()),
                String.valueOf(cliente.getRua()),
                String.valueOf(cliente.getCep()),
                String.valueOf(cliente.getNumeroCasa()),
                String.valueOf(cliente.getBairro()),
                String.valueOf(cliente.getEmail()),
                String.valueOf(cliente.getTelefone())

            });
        }

        return retorno;

    }

}
