/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Azazel
 */
public class Funcionarios extends pessoa{
    
    private String cargo;
    private String senha1;
    private String senha2;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

}
