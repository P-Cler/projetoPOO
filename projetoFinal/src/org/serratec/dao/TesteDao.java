package org.serratec.dao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.entidade.Funcionario;

public class TesteDao {
public static void main(String[] args) {
	
	FuncionarioDAO fd = new FuncionarioDAO();
	Funcionario funcionario;
	int opcao = 0;
	try {
		fd.getAll();
	} catch (Exception e) {
		System.out.println("Não foi possível abrir!");
	}
	
	while(opcao != -1) {
	try {
		fd.mostrarFuncionarios();
		System.out.println("Opção:");
		Scanner sc = new Scanner(System.in);
		opcao = sc.nextInt();
		switch (opcao) {
		case 1:
				  sc.nextLine(); 
			    System.out.println("Nome:");
				    String nome = sc.nextLine();

				    System.out.println("CPF:");
				    String cpf = sc.nextLine();

				    System.out.println("Data de nascimento (yyyy-MM-dd):");
				    String dataNascStr = sc.nextLine();
				    LocalDate dataNasc = LocalDate.parse(dataNascStr);

				    System.out.println("Salário bruto:");
				    double salario = sc.nextDouble();

				    fd.inserir(new Funcionario(nome, cpf, dataNasc, salario));
			break;
		case 2:
				System.out.println("Digite o ID do funcionário que deseja atualizar:");
				Integer id = sc.nextInt();
				sc.nextLine();
				
				 funcionario = fd.getById(id);
				 System.out.println(funcionario);
			 
			 System.out.println("Nome: " + funcionario.getNome()+ "  Digite o novo nome ou pressione enter.");
			String entrada = sc.nextLine();
			 if(entrada != "" && entrada != funcionario.getNome()) {
				 funcionario.setNome(entrada);
			 } 
			 
			 System.out.println("CPF: " + funcionario.getCpf()+ "  Digite o novo CPF ou pressione enter.");
			  entrada = sc.nextLine();
			 if(entrada != "" && entrada != funcionario.getCpf()) {
				 funcionario.setCpf(entrada);
			 }
			 
			 System.out.println("Data de Nascimento: " + funcionario.getDataNascimento()+ "\nDigite a nova data de nascimento ou pressione enter.");
			  entrada = sc.nextLine();
			 
			 if(entrada != "" ) {
				 LocalDate dataEntrada = LocalDate.parse(entrada);
				 if (dataEntrada != funcionario.getDataNascimento()){
				 
					 funcionario.setDataNascimento(dataEntrada);
				 }
			 }
		
			 System.out.println("Salário bruto: " + funcionario.getSalario_bruto()+ "\nDigite o novo salário bruto ou pressione enter.");
			 Double salarioBruto = sc.nextDouble();
			 
			 if(salarioBruto != null ) {
				 
				 if(salarioBruto != funcionario.getSalario_bruto())
				 funcionario.setSalario_bruto(salarioBruto);
				 System.out.println("Depois");
			 }
			 
			 System.out.println("Antes de atualizar");
			 fd.atualizar(funcionario);
			 System.out.println("Depois de atualizar");
			
		break;
	case 3:
		System.out.println("Digite o funcionário que deseja excluir:");
		fd.excluir(sc.nextInt());
		break;
	default:
	}
	System.out.println("Deu certo o teste");
	}catch (SQLException e) {
	System.out.println("DEU ERRADO" + e.getMessage());
	e.getStackTrace();

}
	}
	}
}
