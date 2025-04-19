package org.serratec.dbconnection;

import java.sql.Connection;

public class TesteConexao {
public static void main(String[] args) {
	Connection connection = new ConnectionFac().getConnection();
}
}
