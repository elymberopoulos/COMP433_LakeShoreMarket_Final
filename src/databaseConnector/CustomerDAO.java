package databaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import serviceUsers.Customer;

public class CustomerDAO {
	
	public static void post(Customer customer){
		String postQuery = "insert into customer "
				+ "(customerID,customerfirstName,customerlastName,companyName,customerAddress,"
				+ "customerNumber,customerEmail,customerOrder,creditcardNumber,customerPassword) "
				+ "values('" + customer.getUserID() + "','" + customer.getFirstName() + "', '" + customer.getLastName() + "','" + customer.getCompanyName() + "','" + 
				customer.getAddress() + "','" + customer.getPhoneNumber() + "','" + customer.getEmail() + "','" + 
				customer.getNumberOfOrders() + "', '" + customer.getCreditCardNumber() + "', '" + customer.getPassword() + "');";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(postQuery);
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		
	}
	public static void put(Customer customer){
		String putQuery = "UPDATE customer "
				+ "SET customerID ='"+customer.getUserID()+ "',customerfirstName ='"+ customer.getFirstName()+ 
				"',customerlastName='"+customer.getLastName()+"',companyName='"+customer.getCompanyName()+
				"',customerAddress='"+customer.getAddress()+"',customerNumber='"+customer.getPhoneNumber()+
				"',customerEmail='"+customer.getEmail()+"',customerOrder='"+customer.getNumberOfOrders()+"',customerPassword='"+customer.getPassword()+
				"' WHERE customerID='"+customer.getUserID()+"';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(putQuery);
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
	public static List<Customer> get(){
		String getQuery = "select * from customer;";
		Connection connection = DBConnect.getDatabaseConnection();
		List<Customer> returnList = new ArrayList<Customer>();

		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(getQuery);
			while(rs.next()){
				System.out.println(rs.getString("customerID") + ", " + rs.getString("customerfirstName") + ", " + rs.getString("customerlastName") +", "
				+ rs.getString("customerAddress") + ", " + rs.getString("customerNumber") + ", " + rs.getString("customerEmail")
				+ ", " + rs.getString("customerOrder") + ", " + rs.getString("companyName") + ", " + rs.getString("creditcardNumber"));
				Customer targetCustomer = new Customer(rs.getString("customerfirstName"), rs.getString("customerlastName"), rs.getString("customerID"), rs.getString("companyName"),
						rs.getString("customerAddress"), rs.getInt("customerNumber"), rs.getString("customerEmail"), rs.getInt("customerOrder"), rs.getInt("creditcardNumber"), rs.getString("customerPassword"));
				returnList.add(targetCustomer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}
	public static void delete(Customer customer){
		String userID = customer.getUserID();
		String deleteQuery = "delete from customer where customerID =" + "'" + userID + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement deleteStatement = connection.createStatement();
			deleteStatement.executeUpdate(deleteQuery);				
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public static void deleteIDMatch(String userID){
		String deleteQuery = "delete from customer where customerID =" + "'" + userID + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement deleteStatement = connection.createStatement();
			deleteStatement.executeUpdate(deleteQuery);				
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
	}

}
