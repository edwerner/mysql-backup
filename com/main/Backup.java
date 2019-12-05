package com.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Backup {
	
	private final String url;
	private final String username;
	private final String password;
	private Connection conn;

	// backup constructor
	public Backup() {
		url = "jdbc:mysql://localhost:3306/travel?useSSL=false";
		username = "root";
		password = "Gv3rn1ca";
	}

	/**
	 * Connect to mysql driver.
	 */
	public void connect() {
		try {
			// create connection with attributes
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			try {
				// throw dlexception and pass error info
				String[] errorInfo = { String.valueOf(e.getStackTrace()) };
				throw new DLException(e, errorInfo);
			} catch (DLException e1) {
				System.out.println("There was an error completing an operation.");
			}
		}
	}

	/**
	 * Close mysql database connection.
	 */
	public void close() {
		try {
			// close connection
			conn.close();
		} catch (SQLException e) {
			try {
				// throw dlexception and pass error info
				String[] errorInfo = { String.valueOf(e.getStackTrace()) };
				throw new DLException(e, errorInfo);
			} catch (DLException e1) {
				System.out.println("There was an error completing an operation.");
			}
		}
	}
}