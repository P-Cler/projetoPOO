package org.serratec.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionFac {
    private String url = "jdbc:postgresql://localhost:5432/salariomanager_db";

    public Connection getConnection() {
        String username;
        String password;
        Connection connection = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Usuário:");
        username = sc.nextLine();
        System.out.println("Senha:");
        password = sc.nextLine();
        
        try {
            System.out.println("Fazendo conexão...");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Conexão não efetuada!");
            e.printStackTrace();
            return null;
        }
        return connection;
    }
}
