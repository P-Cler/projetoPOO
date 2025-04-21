package org.serratec.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.entidade.FolhaPagamento;

public class FolhaPagamentoDAO implements CrudDAO<FolhaPagamento> {
	Connection connection;
	List<FolhaPagamento> folhaPagamentos = new ArrayList<>();
	final String table = "folha_de_pagamento";

	public FolhaPagamentoDAO(Connection connection) {
		this.connection = connection;
		getAll();
	}

	@Override
	public void inserir(FolhaPagamento folhaPagamento) throws SQLException {
		String sql = "INSERT INTO " + this.table
				+ " (id_funcionario, data_pagamento, desconto_inss, desconto_ir, salario_liquido) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, folhaPagamento.getIdFuncionario());
			stmt.setDate(2, Date.valueOf(folhaPagamento.getDataPagamento()));
			stmt.setDouble(3, folhaPagamento.getDescontoINSS());
			stmt.setDouble(4, folhaPagamento.getDescontoIR());
			stmt.setDouble(5, folhaPagamento.getSalarioLiquido());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				folhaPagamento.setCodigo(idGerado);
				this.folhaPagamentos.add(folhaPagamento);
				System.out.println("Funcionário inserido com ID: " + idGerado);
			} else {
				System.out.println("Não foi possível obter o ID gerado.");
			}
			System.out.println("Funcionário cadastrado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Não foi possível inserir os dados!");
			e.getStackTrace();
		}
	}

	@Override
	public void atualizar(FolhaPagamento folhaPagamento) throws SQLException {
		String sql = "UPDATE " + this.table
				+ " SET id_funcionario, data_pagamento, desconto_inss, desconto_ir, salario_liquido WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		try {
			stmt.setInt(1, folhaPagamento.getIdFuncionario());
			stmt.setDate(2, Date.valueOf(folhaPagamento.getDataPagamento()));
			stmt.setDouble(3, folhaPagamento.getDescontoINSS());
			stmt.setDouble(4, folhaPagamento.getDescontoIR());
			stmt.setDouble(5, folhaPagamento.getSalarioLiquido());
			stmt.execute();
			System.out.println("Atualização concluída com sucesso!");
		} catch (SQLException e) {
			System.err.println("Atualização não concluída.");
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "DELETE FROM " + this.table + " WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		try {
			stmt.setInt(1, id);
			stmt.execute();
			folhaPagamentos.removeIf(tp -> tp.getCodigo() == id);
			System.out.println("Pagamento deletado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao deletar pagamento.");
			e.getStackTrace();
		}
	}

	@Override
	public void getAll() {
		String sql = "SELECT * FROM " + table;
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				folhaPagamentos.add(new FolhaPagamento(rs.getInt("id"), LocalDate.parse(rs.getString("data_pagamento")),
						rs.getDouble("desconto_inss"), rs.getDouble("desconto_ir"), rs.getDouble("salario_liquido")));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao carregar lista de pagamentos: " + e.getMessage());
		}
	}

	@Override
	public FolhaPagamento getById(Integer id) throws SQLException {
		String sql = "SELECT * FROM " + table + " WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		FolhaPagamento folhaPagamento = null;
		if (rs.next()) {
			folhaPagamento = new FolhaPagamento(rs.getInt("id"), LocalDate.parse(rs.getString("data_pagamento")),
					rs.getDouble("desconto_inss"), rs.getDouble("desconto_ir"), rs.getDouble("salario_liquido"));
		} else {
			System.out.println("Pagamento não encontrado.");
		}

		return folhaPagamento;
	}

	public void mostrarDependentes() {
		for (FolhaPagamento folhaDePagamento : folhaPagamentos) {
			System.out.println(folhaDePagamento);
		}
	}
}
