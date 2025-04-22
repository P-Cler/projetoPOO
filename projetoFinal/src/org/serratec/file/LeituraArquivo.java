package org.serratec.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.entidade.Funcionario;
import org.serratec.enums.Parentesco;
import org.serratec.entidade.Dependente;
import org.serratec.entidade.FolhaPagamento;

public class LeituraArquivo {
	public static List<Funcionario> lerArquivoEntrada() {
	    List<Funcionario> funcionarios = new ArrayList<>();
	    
	    

	    try {
//	    	System.out.println("Insira o nome e endereço do arquivo .csv: ");
//	    	Scanner sc = new Scanner(System.in);
//	    	String diretorio = sc.nextLine();
//	    	FileReader file = new FileReader(diretorio);
//	    	sc.close();
	        FileReader file = new FileReader("D:/Usuários/HugoB/Área de Trabalho/arquivoEntrada/entrada.csv");
	        Scanner sc2 = new Scanner(file);

	        Funcionario funcionarioAtual = null;

	        while (sc2.hasNextLine()) {
	            String linha = sc2.nextLine().trim();

	            if (linha.isEmpty()) {
	                // Ignora linhas em branco
	                continue;
	            }

	            String[] dados = linha.split(";");
	            if (dados.length == 4) {
	                String nome = dados[0];
	                String cpf = dados[1];
	                LocalDate dataNascimento = LocalDate.parse(dados[2], DateTimeFormatter.ofPattern("yyyyMMdd"));

	                if (isSalario(dados[3])) {
	                    // Antes de criar um novo funcionário, salva o anterior
	                    if (funcionarioAtual != null) {
	                        funcionarios.add(funcionarioAtual);
	                    }

	                    // Cria novo funcionário
	                    double salario = Double.parseDouble(dados[3]);
	                    funcionarioAtual = new Funcionario(nome, cpf, dataNascimento, salario);
	                } else {
	                    // É um dependente
	                    try {
	                        Parentesco parentesco = Parentesco.valueOf(dados[3].toUpperCase());

	                        Dependente dependente = new Dependente(
	                            nome,
	                            cpf,
	                            dataNascimento,
	                            parentesco,
	                            funcionarioAtual.getId_funcionario()
	                        );

	                        funcionarioAtual.adicionarDependente(dependente);

	                    } catch ( Exception e) {
	                        System.err.println("Erro com dependente: " + nome + " - " + e.getMessage());
	                    }
	                }
	            }
	        }

	        // Adiciona o último funcionário lido
	        if (funcionarioAtual != null) {
	            funcionarios.add(funcionarioAtual);
	        }

	        sc2.close();
	        System.out.println("******** Dados importados do Arquivo ********");
	        for (Funcionario funcionario : funcionarios) {
				System.out.println("Funcionário: "+funcionario);
				System.out.println("Dependentendes:");
				for (Dependente dependente : funcionario.getDependentes()) {
					System.out.println(dependente);
				}
			}
	       

	    } catch (FileNotFoundException e) {
	        System.err.println("Arquivo não encontrado!");
	    } catch (Exception e) {
	        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
	    }

	    return funcionarios;
	}
	
	private static boolean isSalario(String valor) {
	    try {
	        Double.parseDouble(valor);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	

}
