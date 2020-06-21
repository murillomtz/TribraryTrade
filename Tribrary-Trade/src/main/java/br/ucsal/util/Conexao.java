package br.ucsal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	private static Connection con;
	static {
		String url = "jdbc:hsqldb:hsql://localhost/tribraryBd/banco";
		String user = "SA";
		String password = "";

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e1) {
			System.out.println("erro: ClassNotFoundException");
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erro: SQLException");
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){

		return con;
	}
}