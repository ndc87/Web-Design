package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbconnection {

	private final String serverName = "localhost";
	private final String dbName = "JDBC_LoginDB";
	private final String portNumber = "1433";
	private final String instance = "";

	public Connection getConnectionW() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
				+ ";integratedSecurity=true;databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";integratedSecurity=true;databaseName="
					+ dbName + ";encrypt=true;trustServerCertificate=true;";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url);
	}

	public static void main(String[] args) {	

		String sqlInsert = "INSERT INTO Users(username, password, email, fullname, phone) VALUES(?,?,?,?,?)";
		String selectAll = "SELECT * FROM Users";
		try {

			dbconnection dbConn = new dbconnection();
			Connection conn = dbConn.getConnectionW();

			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1, "user_test_main");
			stmt.setString(2, "pass123");
			stmt.setString(3, "test@example.com");
			stmt.setString(4, "Người dùng main");
			stmt.setString(5, "0123456789");
			stmt.execute();

			stmt = conn.prepareStatement(selectAll);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + ", Username: " + rs.getString("username") + ", Email: " + rs.getString("email"));
			}
			
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}