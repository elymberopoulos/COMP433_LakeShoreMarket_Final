package databaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import order.Order;
import products.Book;
import serviceUsers.Partner;

public class OrderDAO {
	
	public static void post(Order order){
		String postQuery = "INSERT INTO placedOrder (orderID,orderDate,expectedShippingDate,isShipped,orderCurrentStatus) "
				+ "VALUES ("+order.getOrderID()+", "+order.getSqlDate()+", "+order.getSqlExpectedShippingDate()+", " +
				order.isShipped()+ ", '" + order.getStatus() +"');";
		for(Book book: order.getBook()) {BookDAO.post(book);}

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
		
	public static List<Order> get(){
		String getQuery = "select * from placedOrder";
		Connection connection = DBConnect.getDatabaseConnection();
		List<Book> orderProducts = new ArrayList<Book>();
		List<Order> returnList = new ArrayList<Order>();
		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(getQuery);
			while(rs.next()){
				/*REDUNDANT ALREADY IN MAGAGER FACADE
				for(Book x : BookDAO.get()){
					if(x.getOrderID() == rs.getInt("orderID")){
						orderProducts.add(x);
					}
				}
				*/
				System.out.println(rs.getString("orderID") + ", " + rs.getString("orderDate") + ", "
						+ rs.getString("expectedShippingDate") + ", " + rs.getString("isShipped") + ", " + rs.getString("orderCurrentStatus"));
				Order targetOrder = new Order(rs.getInt("orderID"), orderProducts, rs.getString("orderDate"), rs.getString("expectedShippingDate"));
				targetOrder.setStatus(rs.getString("orderCurrentStatus"));
				returnList.add(targetOrder);
			}
			
		}catch(SQLException se) {
			se.printStackTrace();

		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return returnList;	
	}
	
	public static Order getOrderByNumber(int orderID){
		String getQuery = "SELECT * FROM placedOrder where orderID = " + "'" + orderID + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		List<Book> orderProducts = BookDAO.getBooksByOrderID(orderID);
		
		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(getQuery);
			rs.next();
			Order targetOrder = new Order(rs.getInt("orderID"), orderProducts, rs.getString("orderDate"), rs.getString("expectedShippingDate"));
			targetOrder.setStatus(rs.getString("orderCurrentStatus"));
			return targetOrder;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static void put(Order order){
		String putQuery = "UPDATE placedOrder "
				+ "SET orderID ='"+order.getOrderID()+ "',orderDate ='"+ order.getSqlDate()+ 
				"',expectedShippingDate='"+order.getSqlExpectedShippingDate()+"',isShipped="+order.isShipped()+
				" WHERE orderID='"+order.getOrderID()+"';";
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
	public static void updateStatus(String newStatus, int orderID){
		String updateQuery = "UPDATE placedOrder SET orderCurrentStatus = '" + newStatus + "' WHERE orderID= " + "'" + orderID + "'";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(updateQuery);
			
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
	public static void delete(Order order){
		int orderID = order.getOrderID();
		String deleteQuery = "delete from placedOrder WHERE orderID=" + "'" + orderID + "'";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(deleteQuery);
			
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
	public static void deleteIDMatch(int orderID){
		String deleteQuery = "delete from placedOrder where orderID=" + "'" + orderID + "'";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement insertStatement = connection.createStatement();
			insertStatement.executeLargeUpdate(deleteQuery);
			
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
	
