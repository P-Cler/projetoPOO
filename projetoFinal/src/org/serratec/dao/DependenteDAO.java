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

import org.serratec.entidade.Dependente;
import org.serratec.enums.Parentesco;
import org.serratec.exception.DependenteException;
import org.serratec.file.SaidaFolhaDePagamento;

public class DependenteDAO implements CrudDAO<Dependente> {
	Connection connection;
	List<Dependente> dependentes = new ArrayList<>();
	final String table = "dependente";

	public DependenteDAO(Connection connection) {
		this.connection = connection;
		getAll();
	}

	@Override
	public void inserir(Dependente dependente) throws SQLException {
		String sql = "INSERT INTO " + this.table
				+ " (nome, cpf, data_nascimento, parentesco, id_funcionario) VALUES (?,?,?,?,?)";
		try {
			for (Dependente d : dependentes) {
				if (d.getCpf().equals(dependente.getCpf())) {
					SaidaFolhaDePagamento.rejeitados.add(dependente);
					throw new DependenteException("Dependente não inserido. CPF já existente!");
				}
			}

			if (dependente.getDataNascimento().plusYears(18).isBefore(LocalDate.now())) {
				SaidaFolhaDePagamento.rejeitados.add(dependente);
				throw new DependenteException("Dependente não inserido, maior de 18 anos.");
			}

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, dependente.getNome());
			stmt.setString(2, dependente.getCpf());
			stmt.setDate(3, Date.valueOf(dependente.getDataNascimento()));
			stmt.setString(4, dependente.getParentesco().toString());
			stmt.setInt(5, dependente.getId_funcionario());
			stmt.execute();

			this.dependentes.add(dependente);

		} catch (SQLException e) {
			System.err.println("Erro ao inserir dependente no banco.");
		} catch (DependenteException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void atualizar(Dependente dependente) throws SQLException {
		String sql = "UPDATE " + this.table
				+ " SET nome = ?, cpf = ?, data_nascimento = ?, parentesco = ? WHERE id_dependente = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		try {
			stmt.setString(1, dependente.getNome());
			stmt.setString(2, dependente.getCpf());
			stmt.setDate(3, Date.valueOf(dependente.getDataNascimento()));
			stmt.setString(4, dependente.getParentesco().toString());
			stmt.setInt(5, dependente.getId_dependente());
			stmt.execute();
			System.out.println("Atualização concluída com sucesso!");
		} catch (SQLException e) {
			System.err.println("Atualização não concluída.");
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "DELETE FROM " + this.table + " WHERE id_dependente = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		try {
			stmt.setInt(1, id);
			stmt.execute();
			dependentes.removeIf(d -> d.getId_dependente() == id);
			System.out.println("Dependente deletado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao deletar dependente.");
			e.getStackTrace();
		}
	}

	@Override
	public void getAll() {
		String sql = "SELECT * FROM " + table;
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				dependentes.add(new Dependente(rs.getInt("id_dependente"), rs.getString("nome"), rs.getString("cpf"),
						rs.getDate("data_nascimento").toLocalDate(), Parentesco.valueOf(rs.getString("parentesco")),
						rs.getInt("id_funcionario")));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao carregar lista de funcionários: " + e.getMessage());

		}
	}

	@Override
	public Dependente getById(Integer id) throws SQLException {
		String sql = "SELECT * FROM " + table + " WHERE id_dependente = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Dependente dependente = null;
		if (rs.next()) {
			dependente = new Dependente(rs.getInt("id_dependente"), rs.getString("nome"), rs.getString("cpf"),
					rs.getDate("data_nascimento").toLocalDate(),
					Parentesco.valueOf(rs.getString("parentesco").toUpperCase()), rs.getInt("id_funcionario"));
		}

		return dependente;
	}

	public void mostrarDependentes() {
		for (Dependente dependente : dependentes) {
			System.out.println(dependente);
		}
	}
}
