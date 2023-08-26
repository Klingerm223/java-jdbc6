package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class app {

	public static void main(String[] args) {
	
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = 2090 "
					+ "WHERE "
				    + "DepartmentId = 1");
							
					//st.setInt(1, 2);
			
			
			
			int rows1 = st.executeUpdate();
			
					st = conn.prepareStatement(
							"UPDATE seller "
							+ "SET BaseSalary = 2090 "
							+ "WHERE "
						    + "DepartmentId = 2");
			
//			int x = 1;
//					
//				if (x < 2) {
//					throw new SQLException("Fake error");
//				}
							
					
					
					
			int rows2 = st.executeUpdate();
			
			conn.commit();
			System.out.println("rows1" + rows1);
			System.out.println("rows2" + rows2);
			
		  }
			
			catch (SQLException e) {
				try {
					conn.rollback();
					throw new DbIntegrityException(e.getMessage());
				}
				catch (SQLException e1) {
					throw new DbException("Erro trying to roolback caused :  "+ e1.getMessage());
				}
			}
			finally {
				DB.closeStatement(st);
				DB.closeConnection();
		}
	  }
}

