package controle;

import Modelo_classes.Cliente;
import controle.conexaoBancoDeDados;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano
 */
public class controleCliente {

    Cliente cli = new Cliente();

    conexaoBancoDeDados conectar = new conexaoBancoDeDados();

    public void cadastrar(Cliente mod) {//Metodo para cadastrar cliente

        conectar.conexao();

        try {
            PreparedStatement pst = conectar.ctm.prepareStatement("insert into cliente (nome,nascimento,cpf,sexo,estado_civil,rua,cep,numero_casa,bairro,email,fone) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getData());
            pst.setString(3, mod.getCpf());
            pst.setString(4, mod.getSexo());
            pst.setString(5, mod.getEstadoCivil());
            pst.setString(6, mod.getRua());
            pst.setString(7, mod.getCep());
            pst.setInt(8, mod.getNumeroCasa());
            pst.setString(9, mod.getBairro());
            pst.setString(10, mod.getEmail());
            pst.setString(11, mod.getTelefone());
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Dados inseridos");
            System.out.println("Dados inseridos");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Dados não inseridos");
            System.out.println(ex);
        }

        conectar.desconecta();

    }

    public void excluir(Cliente mod) {//Metodo para excluir cliente
        conectar.conexao();

        try {
            PreparedStatement pst = conectar.ctm.prepareStatement("delete from cliente where cpf = ?");
            pst.setString(1, mod.getCpf());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Cadastro deletado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Dados não encontrados");
            System.out.println(ex);
        }
        conectar.desconecta();

    }

    public Cliente buscarCampo(Cliente mod) {//Metodo para pesquisar cliente

        conectar.conexao();

        conectar.executaSQl("select * from cliente where cpf like'" + mod.getPesquisar() + "%'");
        try {
            conectar.rs.first();

            mod.setId(conectar.rs.getInt("id_cli"));
            mod.setNome(conectar.rs.getString("nome"));
            mod.setData(conectar.rs.getString("nascimento"));
            mod.setCpf(conectar.rs.getString("cpf"));
            mod.setSexo(conectar.rs.getString("sexo"));
            mod.setEstadoCivil(conectar.rs.getString("estado_civil"));
            mod.setRua(conectar.rs.getString("rua"));
            mod.setCep(conectar.rs.getString("cep"));
            mod.setNumeroCasa(conectar.rs.getInt("numero_casa"));
            mod.setBairro(conectar.rs.getString("bairro"));
            mod.setEmail(conectar.rs.getString("email"));
            mod.setTelefone(conectar.rs.getString("fone"));
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "dados nao encontrados");
            System.out.println(ex);
        }

        conectar.desconecta();

        return mod;

    }

    public void alterar(Cliente mod) {//Metodo para alterar cliente

        conectar.conexao();

        try {
            PreparedStatement pst = conectar.ctm.prepareStatement("update cliente set nome=?,nascimento=?,sexo=?,estado_civil=?,rua=?,"
                    + "cep=?,numero_casa=?,bairro=?,email=?,fone=? where cpf=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getData());
            pst.setString(3, mod.getSexo());
            pst.setString(4, mod.getEstadoCivil());
            pst.setString(5, mod.getRua());
            pst.setString(6, mod.getCep());
            pst.setInt(7, mod.getNumeroCasa());
            pst.setString(8, mod.getBairro());
            pst.setString(9, mod.getEmail());
            pst.setString(10, mod.getTelefone());
            pst.setString(11, mod.getCpf());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incerir codigo");
            System.out.println(ex);

        }

        conectar.desconecta();

    }

}
