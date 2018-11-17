package representations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import products.Book;

public class OrderRepresentation extends AbstractRepresentation{
	
	private int orderID;
	private String sqlDate;
	private String sqlExpectedShippingDate;
	private boolean isShipped;
	private List<Book> book;
	private List<Book> orderProducts;
	private String status;
	
	public OrderRepresentation(){}

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
*/////

}
