package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Backup.
 */
public class Backup {

	private final String url;
	private final String username;
	private final String password;
	private Connection conn;
	private Statement stmnt;

	/**
	 * Instantiates a new backup.
	 */
	public Backup() {
		url = "jdbc:mysql://localhost:3306/travel?useSSL=false";
		username = "root";
		password = "student";
	}

	/**
	 * Fetch table names.
	 *
	 * @return the string array list
	 */
	public ArrayList<String> fetchTable(String tableName) {
		ArrayList<String> tableNames = new ArrayList<String>();
		String SHOW_QUERY = "";
		
		if (tableName == null) {
			SHOW_QUERY = "SHOW TABLES";
		} else {
			SHOW_QUERY = "SHOW CREATE TABLE " + tableName;
		}

		try {
			stmnt = conn.createStatement();
		} catch (SQLException e) {
			try {
				// throw dlexception and pass error info
				String[] errorInfo = { String.valueOf(e.getStackTrace()) };
				throw new DLException(e, errorInfo);
			} catch (DLException e1) {
				System.out.println("There was an error completing an operation.");
			}
		}

		try {
			// execute string query
			final ResultSet rs = stmnt.executeQuery(SHOW_QUERY);
			while (rs.next()) {
				tableNames.add(rs.getString(1));
				if (tableName != null) {
					tableNames.add(rs.getString(2));
				}
			}
		} catch (SQLException e) {
			try {
				// throw dlexception and pass error info
				String[] errorInfo = { String.valueOf(e.getStackTrace()) };
				throw new DLException(e, errorInfo);
			} catch (DLException e1) {
				System.out.println("There was an error completing an operation.");
			}
		}
		return tableNames;
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
