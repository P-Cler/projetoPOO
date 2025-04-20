package org.serratec.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.entidade.Funcionario;

public class TesteDao {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		FuncionarioDAO fd = new FuncionarioDAO();
//		//Funcionario funcionario = new Funcionario("Antônio", "17784932015", LocalDate.of(1927, 07, 11), 3500.0);
//		Funcionario funcionario;
//		int opcao = 0;
//		try {
//			fd.abrir();
//		} catch (Exception e) {
//			System.out.println("Não foi possível abrir!");
//		}
//		while(opcao != -1) {
//		try {
//			
//			fd.mostrarFuncionarios();
//			System.out.println("Opção:");
//			opcao = sc.nextInt();
//			switch (opcao) {
//			case 1:
////				  sc.nextLine(); 
////				    System.out.println("Nome:");
////				    String nome = sc.nextLine();
////
////				    System.out.println("CPF:");
////				    String cpf = sc.nextLine();
////
////				    System.out.println("Data de nascimento (yyyy-MM-dd):");
////				    String dataNascStr = sc.nextLine();
////				    LocalDate dataNasc = LocalDate.parse(dataNascStr);
////
////				    System.out.println("Salário bruto:");
////				    double salario = sc.nextDouble();
//
////				    fd.inserir(new Funcionario(nome, cpf, dataNasc, salario));
//				break;
//			case 2:
////				System.out.println("Digite o ID do funcionário que deseja atualizar:");
////				Integer id = sc.nextInt();
////				sc.nextLine();
////
////				System.out.println("Qual campo deseja atualizar?");
////				System.out.println("1 - Nome");
////				System.out.println("2 - CPF");
////				System.out.println("3 - Data de Nascimento");
////				System.out.println("4 - Salário");
////				 Integer idcampo = sc.nextInt();
////				switch(idcampo) {
////				case 1:
////					funcionario.setNome(sc.nextLine()); 
////					fd.atualizar(id, "nome", funcionario.getNome());
////					break;
//					String cpf = sc.nextLine();
//					break;
//				case 3:
//					String data = sc.nextLine();
//					break;
//				case 4:
//					Double salario = sc.nextDouble();
//					break;
//				}
//				//fd.atualizar(new Funcionario(nome, cpf, dataNasc, salario));
//				
//				break;
//			case 3:
//				System.out.println("Digite o funcionário que deseja excluir:");
//				fd.excluir(sc.nextInt());
//				break;
//			default:
//			}
//			System.out.println("Deu certo o teste");
//		} catch (SQLException e) {
//			System.out.println("DEU ERRADO" + e.getMessage());
//			e.getStackTrace();
//
//		}
//		}
//	}
}
