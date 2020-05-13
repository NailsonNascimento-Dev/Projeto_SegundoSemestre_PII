package com.loja.informatica.CONTROLLER;

import com.loja.informatica.DAO.ClienteDAO;
import com.loja.informatica.MODEL.Cliente;
import java.util.ArrayList;


/**
 *
 * @author Juliano
 */
public class controllerCliente {

    public static boolean CadastrarCliente(String nome, String data, String cpf, String sexo, String estadoCivil, String rua,
            String cep, int numeroCasa, String bairro, String email, String telefone) {

        Cliente cliente = new Cliente();
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

    public static boolean AlterarCliente(String nome, String data, String cpf, String sexo, String estadoCivil, String rua,
            String cep, int numeroCasa, String bairro, String email, String telefone) {

        Cliente cliente = new Cliente();
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

    /*  
    public static ArrayList<String[]> BuscarRegistro(String busca) {
 
             ArrayList<Cliente> listarRegistros = new ArrayList<>();

        ArrayList<String[]> retorno = new ArrayList<>();

        listarRegistros = ClienteDAO.buscarRegistros(busca);

        for (Cliente cliente : listarRegistros) {
            retorno.add(new String[]{
                String.valueOf(cliente.getId()),
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
    
     */
    
   /* 
    public Cliente buscarCampo(Cliente cliente){
        
        
        
    }
        */
    
}
