package org.serratec.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.entidade.Funcionario;
import org.serratec.entidade.Parentesco;
import org.serratec.dbconnection.ConnectionFac;

import org.serratec.entidade.Dependente;
public class TesteDao {
	
	public static void main(String[] args) {
		ConnectionFac factory = new ConnectionFac();
		
		Connection connection = factory.getConnection();
		Dependente dependente;
		DependenteDAO dd = new DependenteDAO(connection);
		
		//int id_dependente, String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco,int id_funcionario
		try {
			dd.mostrarDependentes();
			dependente = dd.getById(2);
			//dd.excluir(dependente.getId_dependente());
		} catch (SQLException e) {
			System.out.println("erro");
			e.getStackTrace();
		}
		
							
		}
	}


