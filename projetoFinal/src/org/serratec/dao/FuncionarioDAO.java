package org.serratec.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.serratec.entidade.Funcionario;

public class FuncionarioDAO implements CrudDAO<Funcionario> {
	Connection connection;
	List<Funcionario> funcionarios = new ArrayList<>();
	final String table = "funcionario";

	public FuncionarioDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void inserir(Funcionario funcionario) throws SQLException {
		String sql = "INSERT INTO " + this.table + " (nome, cpf, data_nascimento, salario_bruto) VALUES (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
			stmt.setDouble(4, funcionario.getSalario_bruto());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				funcionario.setId_funcionario(idGerado);
				this.funcionarios.add(funcionario);
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
	public void atualizar(Funcionario funcionario) throws SQLException {
		String sql = "UPDATE funcionario SET nome = ?, cpf = ?, data_nascimento = ?, salario_bruto = ? WHERE id_funcionario = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		try {
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
			stmt.setDouble(4, funcionario.getSalario_bruto());
			stmt.setInt(5, funcionario.getId_funcionario());
			stmt.execute();
			System.out.println("Atualização concluída com sucesso!");
		} catch (SQLException e) {
			System.err.println("Atualização não concluída.");
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		try {
			stmt.setInt(1, id);
			stmt.execute();
			funcionarios.removeIf(f -> f.getId_funcionario() == id);
			System.out.println("Funcionário deletado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao deletar funcionário.");
			e.getStackTrace();
		}
	}

	@Override
	public void getAll() throws SQLException {
		String sql = "SELECT * FROM funcionario";
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				funcionarios.add(new Funcionario(rs.getInt("id_funcionario"), rs.getString("nome"), rs.getString("cpf"),
						rs.getDate("data_nascimento").toLocalDate(), rs.getDouble("salario_bruto")));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao carregar lista de funcionários: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Funcionario getById(Integer id) throws SQLException {
		String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
		System.out.println("Executando: " + sql + id);
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		Funcionario funcionario = null;
		if (rs.next()) {
			funcionario = new Funcionario(rs.getInt("id_funcionario"), rs.getString("nome"), rs.getString("cpf"),
					rs.getDate("data_nascimento").toLocalDate(), rs.getDouble("salario_bruto"));
		}

		return funcionario;
	}

	public void mostrarFuncionarios() {
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
}
