package databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	public static Connection getDatabaseConnection() {
		return openConnection();
	}
	
	private static Connection openConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://root.ctynnv0opzha.us-east-2.rds.amazonaws.com:3306/Lake_Shore_Market", 
            		"fsayeed", "Admin123");
			
		}catch(SQLException exception) {
			exception.printStackTrace();
		}catch(ClassNotFoundException cnException) {
			cnException.printStackTrace();
		}
		
		return connection;
	}

}
