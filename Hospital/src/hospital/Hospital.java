/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author caue
 */
public class Hospital {

    Banco bd = new Banco();

    /**
     * @param args the command line arguments
     * @return
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Hospital hospital = new Hospital();
        int opc = 0;
        Scanner teclado = new Scanner(System.in);

        // variaveis criadas para guardar os valores a serem inseridos
        int codigo;
        String nome;
        String data;
        String endereco;
        String telefone;
        String email;
        String sexo;
        boolean deficiente;
        boolean diabetes;
        boolean hiv;
        boolean hepatitec;
        boolean fumante;
        boolean alergia;
        String historico;

        // conexão com o banco, no meu caso, usuario/senha = root/root
        try {
            hospital.bd.ativarConexao("root", "");
            //throw new Excecoes("001");
        } catch (Exception e) {
            //if(e.getMessage().equals("005")){
            //System.out.println(e.getMessage() + " - Impossivel estabelecer uma conexao com o Banco de Dados");
            // }
        }

        do {
            // menu
            System.out.println("MENU - HOSPITAL\n\n");
            System.out.println("1.CADASTRAR PACIENTE");
            System.out.println("2.ALTERAR DADOS DO PACIENTE");
            System.out.println("3.EXCLUIR PACIENTE");
            System.out.println("4.CONSULTAR PACIENTE");
            System.out.println("0.SAIR");
            System.out.println("\n\n");
            System.out.println("Sua escolha:    ");
            opc = teclado.nextInt();

            switch (opc) {

                case 1: {
                    // INSERT
                    System.out.println("CODIGO: ");
                    codigo = teclado.nextInt();
                    System.out.println("NOME: ");
                    nome = teclado.next();
                    System.out.println("DATA NASCIMENTO: ");
                    data = teclado.next();
                    //SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("ENDEREÇO:  ");
                    endereco = teclado.nextLine();
                    System.out.println("TELEFONE:  ");
                    telefone = teclado.next();
                    System.out.println("EMAIL:  ");
                    email = teclado.next();
                    System.out.println("SEXO:  ");
                    sexo = teclado.next();
                    System.out.println("DEFICIENTE:  ");
                    deficiente = teclado.nextBoolean();
                    System.out.println("DIABETES:  ");
                    diabetes = teclado.nextBoolean();
                    System.out.println("HIV:  ");
                    hiv = teclado.nextBoolean();
                    System.out.println("HEPATITE C:  ");
                    hepatitec = teclado.nextBoolean();
                    System.out.println("FUMANTE:  ");
                    fumante = teclado.nextBoolean();
                    System.out.println("ALERGIA:  ");
                    alergia = teclado.nextBoolean();
                    System.out.println("HISTORICO:\n");
                    historico = teclado.nextLine();

                    try {
                        hospital.bd.inserirNoBanco(codigo, nome, data, endereco, telefone, email, sexo,
                                deficiente, diabetes, hiv, hepatitec, fumante, alergia, historico);
                        //throw new Excecoes("002");
                    } catch (Exception e) {
                        //if(e.getMessage().equals("006")){
                        //System.out.println(e.getMessage() + " - Impossivel realizar a insercao no Banco de Dados");
                        //}
                    }
                    System.out.println("PRESSIONE UMA TECLA PARA CONTINUAR");
                }
                break;
                case 2: {
                    System.out.println("Digite o codigo que deseja alterar");
                    int codigo_a_consultar = teclado.nextInt();

                    try {
                        hospital.bd.consultaCodigoNoBanco(codigo_a_consultar);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                    System.out.println("Digite o campo que deseja alterar");
                    String campo = teclado.next();
                    System.out.println("Digite o novo valor para o campo");
                    String novo_valor = teclado.next();

                    //System.out.println(campo + "\n");
                    //System.out.println(novo_valor + "\n");
                    try {
                        hospital.bd.updateNoBanco(campo, novo_valor, codigo_a_consultar);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }
                break;
                case 3: {
                    System.out.println("FORNECA O CODIGO DO PACIENTE A SER EXCLUIDO");
                    int deletar = teclado.nextInt();

                    try {
                        hospital.bd.deletarNoBanco(deletar);
                        //throw new Excecoes("003");
                    } catch (Exception e) {
                        //if(e.getMessage().equals("007")){
                        //System.out.println(e.getMessage() + " - Impossivel realizar a exlcusao do  Banco de Dados");
                        //}
                    }

                }
                break;
                case 4: {

                    System.out.println("DIGITE O CAMPO E EM SEGUIDA O CRITERIO DE BUSCA");
                    System.out.println("CAMPO");
                    String campo = teclado.next();
                    System.out.println("CRITERIO:");
                    String criterio = teclado.next();

                    try {
                        hospital.bd.consultarNoBanco(campo, criterio);
                        //throw new Excecoes("004");
                    } catch (Exception e) {
                        //if(e.getMessage().equals("008")){
                        //System.out.println(e.getMessage() + " - Impossivel realizar a consulta ao Banco de Dados");
                        //}
                    }
                }
                break;

            }

        } while (opc != 0);

    }

}
