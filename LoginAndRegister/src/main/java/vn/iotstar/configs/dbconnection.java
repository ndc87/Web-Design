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
//		try {
//            // Test kết nối với Windows Authentication
//            dbconnection dbConn = new dbconnection();
//            Connection conn = dbConn.getConnectionW();
//
//            // Create statement
//            Statement stmt = conn.createStatement();
//
//            // Insert 'GiaoVien'
//            stmt.executeUpdate("INSERT INTO Users(id, name, diachi) VALUES (2, 'Trung', 'HCM')");
//
//            // Get data from table 'Users'
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
//
//            // Show data
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("diachi"));
//            }
//
//            conn.close(); // close connection
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }




		String sqlInsert = "INSERT INTO testbang VALUES(?,?,?)";
		String selectAll = "SELECT * FROM testbang";
		try {

            dbconnection dbConn = new dbconnection();
            Connection conn = dbConn.getConnectionW();

            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setInt(1, 4);
            stmt.setString(2, "Trung");
            stmt.setString(3, "HCM");
            stmt.execute();
            // select all GiaoVien
            stmt = conn.prepareStatement(selectAll);
            // get data from table GiaoVien
            ResultSet rs = stmt.executeQuery();
            // show data
            while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

	}
}

