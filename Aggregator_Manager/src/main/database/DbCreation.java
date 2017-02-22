package main.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbCreation {
	private static DbCreation instance = null;
	private String url;
	private String driver;
	private String userName;
	private String password;
	private String dbName;

	private DbCreation() {
		LoadProperties();
	}

	public static synchronized DbCreation getInstance() {
		if (instance == null)
			instance = new DbCreation();
		return instance;
	}

	private void LoadProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("properties/db_properties.properties");
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.dbName = prop.getProperty("DBNAME");
		// System.out.println(this.dbName);
		this.driver = prop.getProperty("DRIVER");
		// System.out.println(this.driver);
		this.password = prop.getProperty("PASSWORD");
		// System.out.println(this.password);
		this.url = prop.getProperty("URL");
		// System.out.println(this.url);
		this.userName = prop.getProperty("USERNAME");
		// System.out.println(this.userName);
	}

	public Connection connect() {
		Connection conn;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			System.out.println("Cannot connect with database");
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}
	// TODO function for db creation

}
