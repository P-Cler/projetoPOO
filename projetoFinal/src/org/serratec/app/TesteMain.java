package org.serratec.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.serratec.dao.DependenteDAO;
import org.serratec.dao.FolhaPagamentoDAO;
import org.serratec.dao.FuncionarioDAO;
import org.serratec.dbconnection.ConnectionFac;
import org.serratec.entidade.Dependente;
import org.serratec.entidade.FolhaPagamento;
import org.serratec.entidade.Funcionario;
import org.serratec.file.LeituraArquivo;
import org.serratec.file.SaidaFolhaDePagamento;

public class TesteMain {

	public static void main(String[] args) {
		
		try {
			ConnectionFac cf = new ConnectionFac();
			Connection connection = cf.getConnection();
			FuncionarioDAO funcDAO = new FuncionarioDAO(connection);
			DependenteDAO dependDAO = new DependenteDAO(connection);
			FolhaPagamentoDAO folhaPagamentoDAO = new FolhaPagamentoDAO(connection);
			
			
			List<FolhaPagamento> folhaPagamentos = new ArrayList<>();
			List<Funcionario> funcionarios = new ArrayList<>();
			funcionarios = LeituraArquivo.lerArquivoEntrada();
			
			
			for (Funcionario funcionario : funcionarios) {
				funcDAO.inserir(funcionario);
				for (Dependente dependente : funcionario.getDependentes()) {
					dependente.setId_funcionario(funcionario.getId_funcionario());
					dependDAO.inserir(dependente);
				}
			}
			funcionarios = funcDAO.getFuncionarios();
			for (Funcionario funcionario : funcionarios) {
				System.out.println(funcionario);
			}
			SaidaFolhaDePagamento.saidaRejeitados();
			SaidaFolhaDePagamento.saidaFolhaPagamento(funcionarios);
			folhaPagamentos = SaidaFolhaDePagamento.getFolhaPagamentos();
			
			for (FolhaPagamento folhaPagamento : folhaPagamentos) {
			System.out.println(folhaPagamento);
		}

			for (FolhaPagamento folhaPag : folhaPagamentos) {
				folhaPagamentoDAO.inserir(folhaPag);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao acessar o banco de dados: ");
			e.printStackTrace();
		}

	}

}
