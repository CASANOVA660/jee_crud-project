package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbc {

	public static Connection createConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/user";
		String username = "root";
		String password = "";
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("okkkk");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Post establishing a DB connection - " + con);
			System.out.println("OKKKK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}