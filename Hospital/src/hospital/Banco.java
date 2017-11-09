/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author caue
 */
public class Banco {

    private Connection conexão;

    public void ativarConexao(String usuario, String senha) throws Exception {

        try {
            System.out.println("Estabelencedo conexão... Aguarde");
            Class.forName("com.mysql.jdbc.Driver");
            conexão = DriverManager.getConnection("jdbc:mysql://localhost/hospital", usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");
            //throw new Excecoes("001");
        } catch (Exception e) {
            if (e.getMessage().equals("001")) {
            System.out.println("001 - Impossivel estabelecer uma conexao com o Banco de Dados");
            }
        }
    }

    public void inserirNoBanco(int codigo, String nome, String data, String endereco,
            String telefone, String email, String sexo, boolean deficiente, boolean diabetes,
            boolean hiv, boolean hepatitec, boolean fumante, boolean alergia, String historico)
            throws Exception {

        try {
            Statement comando = (Statement) conexão.createStatement();
            System.out.println("INSERINDO REGISTROS NO BANCO");
            System.out.println("...");
            System.out.println("...");
            System.out.println("...");
            String sql;
            sql = "INSERT INTO pacientes VALUES (" + codigo + ",'" + nome + "','" + data + "','"
                    + endereco + "','" + telefone + "','" + email + "','" + sexo + "'," + deficiente
                    + "," + diabetes + "," + hiv + "," + hepatitec + "," + fumante + "," + alergia
                    + ",'" + historico + "');";

            comando.execute(sql);
            System.out.println("REGISTRO INSERIDO COM SUCESSO!");
            //throw new Excecoes("002");
        } catch (Exception e) {
            //if (e.getMessage().equals("002")) {
            //System.out.println(e.getMessage() + " - Impossivel inserir os dados no Banco de Dados");
            //}
        }
    }

    public void consultarNoBanco(String campo, String criterio) throws Exception {

        try {
            Statement comando = (Statement) conexão.createStatement();

            String sql;
            sql = "SELECT * FROM pacientes WHERE (" + campo + "=" + "'" + criterio + "');";
            ResultSet resultados = comando.executeQuery(sql);

            while (resultados.next()) {
                String dado = resultados.getString("nome");
                System.out.println(dado);
            }
            System.out.println("CONSULTA FEITA COM SUCESSO!");
            // throw new Excecoes("003");

        } catch (Exception e) {
            //if (e.getMessage().equals("003")) {
            //System.out.println(e.getMessage() + " - Falha ao consultar o Banco de Dados");
            //}
        }
    }

    public void deletarNoBanco(int codigo) {
        try {
            Statement comando = (Statement) conexão.createStatement();
            String sql;
            sql = "DELETE FROM pacientes WHERE (codigo = " + codigo + ");";
            comando.execute(sql);
            System.out.println("REGISTRO EXCLUIDO COM SUCESSO!");
            //throw new Excecoes("004");
        } catch (Exception e) {
            // if (e.getMessage().equals("004")) {
            //System.out.println(e.getMessage() + " - Falha ao realizar a exclusao do registro no Banco de Dados");
            //}
        }
    }

    public void consultaCodigoNoBanco(int codigo) throws Exception {
        Statement comando = null;
        String query = "SELECT * FROM pacientes WHERE codigo = " + codigo + " ";

        try {
            comando = (Statement) conexão.createStatement();

            ResultSet rs = comando.executeQuery(query);

            while (rs.next()) {
                String nome = rs.getString("nome");
                String data_nasc = rs.getString("data_nasc");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String sexo = rs.getString("sexo");

                System.out.println("Nome: " + nome + "\t" + "Data Nascimento:" + data_nasc
                        + "\t" + "Endereço:" + endereco + "\t" + "Telefone: " + telefone + "\t" + "Email:"
                        + email + "\t" + "Sexo:" + sexo);
            }
            System.out.println("CONSULTA FEITA COM SUCESSO!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateNoBanco(String campo, String novo_valor, int codigo) throws Exception {

        try {
            PreparedStatement ps = conexão.prepareStatement("UPDATE pacientes SET " + campo + " = ?  WHERE codigo = ?");

            ps.setString(1, novo_valor);
            ps.setInt(2, codigo);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
