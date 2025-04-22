package org.serratec.file;

import java.util.ArrayList;
import java.util.List;

import org.serratec.entidade.Dependente;
import org.serratec.entidade.Funcionario;
import org.serratec.dao.DependenteDAO;
import org.serratec.dao.FuncionarioDAO;
import org.serratec.dbconnection.ConnectionFac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


	public class TesteFile {
	    public static void main(String[] args) {

	        Connection connection = new ConnectionFac().getConnection();
	        LeituraArquivo la = new LeituraArquivo();
	        List<Funcionario> funcionarios = la.lerArquivoEntrada();
	        FuncionarioDAO fd = new FuncionarioDAO(connection);
	        DependenteDAO dp = new DependenteDAO(connection);

	        try {
	            for (Funcionario funcionario : funcionarios) {
	                fd.inserir(funcionario); // aqui já atribui o ID

	                for (Dependente dependente : funcionario.getDependentes()) {
	                    dependente.setId_funcionario(funcionario.getId_funcionario()); // usa o ID já preenchido
	                    dp.inserir(dependente);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Não foi possível cadastrar o funcionario: " + e.getMessage());
	        }

	        System.out.println("oi");
	    }
	}
