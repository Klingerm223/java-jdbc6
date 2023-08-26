package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection () {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				String username = props.getProperty("user");
				String password = props.getProperty("password");
				/*String useSSL = props.getProperty("useSSL");
				System.out.println(props);*/
				
				conn = DriverManager.getConnection (url,username,password);
				System.out.println("Conexao com DB ok!!");
		 }
		catch (SQLException e ) {
			throw new DbException("Falha conexao /" + e.getMessage());
    		}
		}
		return conn;
	}
	
	public static void closeConnection () {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Banco Fechado");
			}
			catch (SQLException e ) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	private static Properties loadProperties () {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties ();
				props.load(fs);
				return props;
				
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}


	public static void closeResultSet(PreparedStatement st) {
		if (st != null) {
			try {
				st.close();
				}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
}
