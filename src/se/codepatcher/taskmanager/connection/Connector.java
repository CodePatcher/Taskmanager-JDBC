package se.codepatcher.taskmanager.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost/JDBC?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	private final Connection connection;

	public Connector() throws SQLException {
		this.connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD); 
		}

	public Connection getConnection() {
		return connection;
	}

}