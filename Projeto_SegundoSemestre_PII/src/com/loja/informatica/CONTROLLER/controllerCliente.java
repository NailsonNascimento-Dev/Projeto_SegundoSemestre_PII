package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.ClienteDAO;
import com.loja.informatica.MODEL.cliente;
import java.util.ArrayList;

/**
 *
 * @author Juliano
 */
public class controllerCliente {

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
    
    public static boolean ExcluirRegistro(int cpf) {

        return ClienteDAO.excluirRegistro(cpf);
    }

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
