package org.serratec.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.entidade.Funcionario;
import org.serratec.enums.Parentesco;
import org.serratec.entidade.Dependente;

public class LeituraArquivo {
	public static List<Funcionario> lerArquivoEntrada(String diretorio) {
		List<Funcionario> funcionarios = new ArrayList<>();

		try {
			FileReader file = new FileReader(diretorio);
			Scanner sc2 = new Scanner(file);

			Funcionario funcionarioAtual = null;

			while (sc2.hasNextLine()) {
				String linha = sc2.nextLine().trim();

				if (linha.isEmpty()) {
					continue;
				}

				String[] dados = linha.split(";");
				if (dados.length == 4) {
					String nome = dados[0];
					String cpf = dados[1];
					LocalDate dataNascimento = LocalDate.parse(dados[2], DateTimeFormatter.ofPattern("yyyyMMdd"));

					if (isSalario(dados[3])) {
						if (funcionarioAtual != null) {
							funcionarios.add(funcionarioAtual);
						}

						double salario = Double.parseDouble(dados[3]);
						funcionarioAtual = new Funcionario(nome, cpf, dataNascimento, salario);
					} else {
						try {
							Parentesco parentesco = Parentesco.valueOf(dados[3].toUpperCase());

							Dependente dependente = new Dependente(nome, cpf, dataNascimento, parentesco,
									funcionarioAtual.getId_funcionario());

							funcionarioAtual.adicionarDependente(dependente);

						} catch (Exception e) {
							System.err.println("Erro com dependente: " + nome + " - " + e.getMessage());
						}
					}
				}
			}

			if (funcionarioAtual != null) {
				funcionarios.add(funcionarioAtual);
			}

			sc2.close();

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