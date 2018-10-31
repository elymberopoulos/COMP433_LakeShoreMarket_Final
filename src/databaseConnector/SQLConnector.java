package databaseConnector;
import java.sql.*;
 
public class SQLConnector {
	
	private String query;
	
	public SQLConnector() {
		Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://root.ctynnv0opzha.us-east-2.rds.amazonaws.com:3306/Lake_Shore_Market", 
                		"fsayeed", "Admin123");

                        Statement myStatement = connection.createStatement();

                        ResultSet rs = myStatement.executeQuery("select * from test");
                        	while (rs.next()){
                        		System.out.println(rs.getString("studentName"));
                            }
                           } catch (Exception e) {
                                        
                                         e.printStackTrace();
                           }finally{
                                 //finally block used to close resources
                                 try{
                                   
                                 }catch(Exception se2){
                                 }// nothing we can do
                                 try{
                                    if(connection!=null)
                                          connection.close();
                                 }catch(SQLException se){
                                    se.printStackTrace();
                                 }//end finally try
                              }//end try
              }
	 
		public SQLConnector(String query) {
			Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
	                connection = DriverManager.getConnection("jdbc:mysql://root.ctynnv0opzha.us-east-2.rds.amazonaws.com:3306/Lake_Shore_Market", 
	                		"fsayeed", "Admin123");

	                        Statement myStatement = connection.createStatement();

	                        ResultSet rs = myStatement.executeQuery(query);
	                        	while (rs.next()){
	                        		System.out.println(rs.getString("studentName"));
	                            }
	                           } catch (Exception e) {
	                                        
	                                         e.printStackTrace();
	                           }finally{
	                                 //finally block used to close resources
	                                 try{
	                                   
	                                 }catch(Exception se2){
	                                 }// nothing we can do
	                                 try{
	                                    if(connection!=null)
	                                          connection.close();
	                                 }catch(SQLException se){
	                                    se.printStackTrace();
	                                 }//end finally try
	                              }//end try
	              }
 
}