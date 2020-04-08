/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Atributos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Azazel
 */
public class Funcionarios {
    
    private int id;
    private String nome;
    private DateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
    private String cpf;
    private String cargo;
    private String rua;
    private String cep;
    private int numeroCasa;
    private String bairro;
    private String email;
    private String telefone;
}
