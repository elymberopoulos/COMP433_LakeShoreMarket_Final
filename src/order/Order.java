package order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.*;

import products.Book;

public class Order {
	private int orderID;
	private String sqlDate;
	private String sqlExpectedShippingDate;
	private boolean isShipped;
	private String status;
	private List<Book> book;
	private List<Book> orderProducts;
	
	public Order(int orderID, List<Book> orderProducts){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		this.sqlDate = format.format(date);
		this.sqlExpectedShippingDate = format.format((date.getTime() + 86400000));
		this.orderProducts = orderProducts;
		this.orderID = OrderNumberGenerator.getNewID();
		this.isShipped = false;
		this.status = "Pending";

		for(Book x: orderProducts){x.setOrderID(orderID);}
	}
	public Order() {}
	
	public Order(List<Book> orderProducts){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		this.sqlDate = format.format(date);
		this.sqlExpectedShippingDate = format.format((date.getTime() + 86400000));
		this.orderProducts = orderProducts;
		this.orderID = OrderNumberGenerator.getNewID();
		this.isShipped = false;
		this.status = "Pending";
		for(Book x: orderProducts){x.setOrderID(orderID);}
	}
	
	public Order(int orderID, List<Book> orderProducts, String sqlDate, String sqlExpectedShippingDate){
		this.orderID = orderID;
		this.orderProducts = orderProducts;
		this.sqlDate = sqlDate;
		this.sqlExpectedShippingDate = sqlExpectedShippingDate;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public boolean isShipped() {
		return isShipped;
	}

	public void setShipped(boolean isShipped) {
		this.isShipped = isShipped;
	}

	public String getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(String sqlDate) {
		this.sqlDate = sqlDate;
	}

	public String getSqlExpectedShippingDate() {
		return sqlExpectedShippingDate;
	}

	public void setSqlExpectedShippingDate(String sqlExpectedShippingDate) {
		this.sqlExpectedShippingDate = sqlExpectedShippingDate;
	}

	public List<Book> getBook() {
		return orderProducts;
	}

	public void setBook(List<Book> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

/*
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getExpectedShippingDate() {
		return expectedShippingDate;
	}

	public void setExpectedShippingDate(String expectedShippingDate) {
		this.expectedShippingDate = expectedShippingDate;
	}
*/
}
