package org.serratec.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.entidade.FolhaPagamento;
import org.serratec.entidade.Funcionario;
import org.serratec.entidade.Pessoa;

public class SaidaFolhaDePagamento {
	static List<FolhaPagamento> folhaPagamentos = new ArrayList<>();
	public static List<Pessoa> rejeitados = new ArrayList<>();
	

	public static void saidaFolhaPagamento(List<Funcionario> func) {
		BufferedWriter lancamento = null;
		
		try {
			FolhaPagamento folhaPagamento = new FolhaPagamento();
			
			lancamento = new BufferedWriter(
					new FileWriter(System.getProperty("user.dir") + "/projetoFinal/src/FolhaDePagamento.csv"));
			
			for (Funcionario funcionario : func) {
				lancamento.append(funcionario.getNome() + ";");
				lancamento.append(funcionario.getCpf() + ";");
				lancamento.append(String.valueOf(folhaPagamento.descontoInss(funcionario)) + ";");
				lancamento.append(String.valueOf(folhaPagamento.descontoIR(funcionario)) + ";");
				lancamento.append(String.valueOf(folhaPagamento.salarioLiq(funcionario)) + ";\n");
				folhaPagamentos.add(new FolhaPagamento(funcionario, LocalDate.now(),
						folhaPagamento.descontoInss(funcionario), folhaPagamento.descontoIR(funcionario),
						folhaPagamento.salarioLiq(funcionario)));
				
			}
			lancamento.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar arquivo!");
			e.printStackTrace();
		}
	}
	
// ######################### NÃO TERMINADO
	public static void saidaRejeitados(List<Pessoa> pessoas) {
		BufferedWriter lancamento = null;
		
		try {
			
			lancamento = new BufferedWriter(
					new FileWriter(System.getProperty("user.dir") + "/projetoFinal/src/Rejeitados.csv"));
			
			for (Pessoa pessoa : pessoas) {
				lancamento.append(pessoa.getNome() + ";");
				lancamento.append(pessoa.getCpf() + ";");
				lancamento.append(pessoa.getDataNascimento() + ";\n");
				
				
				lancamento.append(pessoa.getDataNascimento() + ";");
			}
			lancamento.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar arquivo!");
			e.printStackTrace();
		}
	}

	
	public static List<FolhaPagamento> getFolhaPagamentos() {
		return folhaPagamentos;
	}
	
	

}
