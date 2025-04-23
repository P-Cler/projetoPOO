package org.serratec.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.entidade.Dependente;
import org.serratec.entidade.FolhaPagamento;
import org.serratec.entidade.Funcionario;
import org.serratec.entidade.Pessoa;
import org.serratec.enums.Parentesco;

public class SaidaFolhaDePagamento {
	static List<FolhaPagamento> folhaPagamentos = new ArrayList<>();
	public static List<Pessoa> rejeitados = new ArrayList<>();

	public static void saidaFolhaPagamento(List<Funcionario> func) {
		BufferedWriter lancamento = null;

		try {
			FolhaPagamento folhaPagamento = new FolhaPagamento();
			Scanner sc = new Scanner(System.in);
			System.out.println("Insira o nome do arquivo desejado para o arquivo de Folha de Pagamento .csv:");
			String nomeArquivo = sc.nextLine();

			lancamento = new BufferedWriter(
					new FileWriter(System.getProperty("user.home") + "/Desktop/" + nomeArquivo + ".csv"));

			for (Funcionario funcionario : func) {
				lancamento.append(funcionario.getNome() + ";");
				lancamento.append(funcionario.getCpf() + ";");
				lancamento.append(String.valueOf(folhaPagamento.descontoInss(funcionario)) + ";");
				lancamento.append(String.valueOf(folhaPagamento.descontoIR(funcionario)) + ";");
				lancamento.append(String.valueOf(folhaPagamento.salarioLiq(funcionario)) + ";\n");
				folhaPagamentos
						.add(new FolhaPagamento(funcionario, LocalDate.now(), folhaPagamento.descontoInss(funcionario),
								folhaPagamento.descontoIR(funcionario), folhaPagamento.salarioLiq(funcionario)));

			}
			lancamento.close();
//			sc.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar arquivo!");
			e.printStackTrace();
		}
	}

	public static void saidaRejeitados() {
		BufferedWriter lancamento = null;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Insira o nome do arquivo desejado para o arquivo de Rejeitados .csv:\n");
			String nomeArquivo = sc.nextLine();

			lancamento = new BufferedWriter(
					new FileWriter(System.getProperty("user.home") + "/Desktop/" + nomeArquivo + ".csv"));

			for (Pessoa pessoa : rejeitados) {
				if (pessoa instanceof Funcionario funcionario) {
					lancamento.write(funcionario.getNome() + ";");
					lancamento.write(funcionario.getCpf() + ";");
					lancamento.write(funcionario.getDataNascimento() + ";");
					lancamento.write(String.valueOf(funcionario.getSalario_bruto()) + "\n");

				} else if (pessoa instanceof Dependente dependente) {
					lancamento.write(dependente.getNome() + ";");
					lancamento.write(dependente.getCpf() + ";");
					lancamento.write(dependente.getDataNascimento() + ";");
					lancamento.write(dependente.getParentesco().name() + "\n");
				}
				lancamento.write("\n");
			}
			lancamento.close();
//			sc.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar arquivo!");
			e.printStackTrace();
		}
	}

	public static List<FolhaPagamento> getFolhaPagamentos() {
		return folhaPagamentos;
	}

}