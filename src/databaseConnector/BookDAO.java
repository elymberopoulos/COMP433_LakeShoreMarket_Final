package databaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import products.Book;

public class BookDAO {
	
	public static void post(Book book){
		
		String postQuery = "INSERT INTO books (bookID,bookName,bookAuthor,category,isbn,price,productReview,productOwner,orderID)" + 
		"VALUES ('" + book.getProductID() + "','" + book.getProductName() + "','" + book.getAuthor() + "','" + book.getCategory() + "','" + book.getIsbn() + 
		"','" + book.getProductPrice() + "','" + book.getProductReview() + "','" + book.getProductOwner() + "','" + book.getOrderID() + "');";
		
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
	
	public static void put(Book book){ //TODO Globally increment book productID
		String putQuery = "UPDATE books "
				+ "SET bookID ='"+book.getProductID()+ "',bookName ='"+ book.getProductName()+ 
				"',bookAuthor='"+book.getAuthor()+"',category='"+book.getCategory()+
				"',isbn='"+book.getIsbn()+"',price='"+book.getProductPrice()+
				"',productReview='"+book.getProductReview()+"',productOwner='"+book.getProductOwner()+ "',orderID='"+ book.getOrderID()+ "' WHERE bookID="+book.getProductID()+";";
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

	public static List<Book> get(){
		String getQuery = "select * from books;";
		Connection connection = DBConnect.getDatabaseConnection();
		List<Book> returnList = new ArrayList<Book>();
		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(getQuery);
			while(rs.next()){
				System.out.println(rs.getString("bookID") + ", " + rs.getString("bookName") + ", "
				+ rs.getString("bookAuthor") + ", " + rs.getString("category") + ", " + rs.getString("isbn") + ", " + 
						rs.getString("price") + ", " + rs.getString("productReview") + ", " + rs.getString("productOwner") + ", " + rs.getInt("orderID"));
				Book targetBook = new Book(rs.getString("bookName"), rs.getDouble("price"), rs.getString("productReview"), 
						rs.getString("productOwner"), rs.getInt("bookID"), rs.getInt("isbn"), rs.getString("bookAuthor"), rs.getString("category"));
				targetBook.setOrderID(rs.getInt("orderID"));
				returnList.add(targetBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;		
	}
	public static Book getMatchingBook(String id){
		
		String selectQuery = "SELECT * FROM books where bookName = " + "'" + id + "';";
		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement getStatement = connection.createStatement();
			ResultSet rs = getStatement.executeQuery(selectQuery);
			rs.next();
			Book targetBook = new Book(rs.getString("bookName"), rs.getDouble("price"), rs.getString("productReview"), 
					rs.getString("productOwner"), rs.getInt("bookID"), rs.getInt("isbn"), rs.getString("bookAuthor"), rs.getString("category"));
			targetBook.setOrderID(rs.getInt("orderID"));
			return targetBook;
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return null;
	}
	
	public static void delete(Book book){
		int bookID = book.getProductID();
		String deleteQuery = "DELETE FROM books where bookID = " + "'" + bookID + "';";
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
	public static void deleteIDMatch(int productID){
		int bookID = productID;
		String deleteQuery = "DELETE FROM books where bookID = " + "'" + bookID + "';";
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
