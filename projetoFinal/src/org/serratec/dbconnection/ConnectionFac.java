package org.serratec.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import org.DotEnvLoader;


public class ConnectionFac {
    private String url = "jdbc:postgresql://localhost:5432/salariomanager_db";
    private Connection connection = null;
    public Connection getConnection() {
    	String filePath="C:/Users/ayahk/Desktop/New folder/projetoPOO/projetoFinal/src/org/.env";
        Map<String, String> envs = DotEnvLoader.loadEnv(System.getProperty("user.dir")+"/src/org/.env");
        String username = envs.get("username");
        String password = envs.get("password");
        
        try {
            System.out.println("Fazendo conexão...");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Conexão não efetuada!");
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    
    public void closeConnection() {
    	try {
    		this.connection.close();
    		System.out.println("Conexão fechada.");
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão."+e.getMessage());
		}
    }
}
