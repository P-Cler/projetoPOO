package org.serratec.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.dao.DependenteDAO;
import org.serratec.dao.FolhaPagamentoDAO;
import org.serratec.dao.FuncionarioDAO;
import org.serratec.dbconnection.ConnectionFac;
import org.serratec.entidade.Dependente;
import org.serratec.entidade.FolhaPagamento;
import org.serratec.entidade.Funcionario;
import org.serratec.file.LeituraArquivo;
import org.serratec.file.SaidaFolhaDePagamento;

public class ProjetoAPP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ConnectionFac cf = new ConnectionFac();
		Connection connection = cf.getConnection();
		FuncionarioDAO funcDAO = new FuncionarioDAO(connection);
		DependenteDAO dependDAO = new DependenteDAO(connection);
		FolhaPagamentoDAO folhaPagamentoDAO = new FolhaPagamentoDAO(connection);

		try {

			System.out.println("Insira o nome e endereço do arquivo .csv: ");
			String arquivo1 = sc.nextLine();
			System.out.println("Insira o nome do arquivo desejado para o arquivo de Folha de Pagamento .csv:");
			String arquivo2 = sc.nextLine();
			System.out.println("Insira o nome do arquivo desejado para o arquivo de Rejeitados .csv:");
			String arquivo3 = sc.nextLine();

			List<FolhaPagamento> folhaPagamentos = new ArrayList<>();
			List<Funcionario> funcionarios = new ArrayList<>();
			funcionarios = LeituraArquivo.lerArquivoEntrada(arquivo1);

			for (Funcionario funcionario : funcionarios) {
				funcDAO.inserir(funcionario);
				for (Dependente dependente : funcionario.getDependentes()) {
					dependente.setId_funcionario(funcionario.getId_funcionario());
					dependDAO.inserir(dependente);
				}
			}
			funcionarios = funcDAO.getFuncionarios();

			SaidaFolhaDePagamento.saidaRejeitados(arquivo3);
			SaidaFolhaDePagamento.saidaFolhaPagamento(funcionarios, arquivo2);
			folhaPagamentos = SaidaFolhaDePagamento.getFolhaPagamentos();

			for (FolhaPagamento folhaPag : folhaPagamentos) {
				folhaPagamentoDAO.inserir(folhaPag);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao interagir com o banco de dados. ");
			e.printStackTrace();
		} finally {
			sc.close();
			cf.closeConnection();
		}

	}

}
