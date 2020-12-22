package br.ucsal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection con;

    static {
        String url = "jdbc:mysql://localhost/tribary?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public static Connection getConnection() {

        return con;
    }

    public static Boolean isConnectionValid() {

        if (getConnection() != null) {

            return true;
        } else {

            return false;
        }

    }

}