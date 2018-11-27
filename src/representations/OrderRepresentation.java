package representations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import products.Book;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation extends AbstractRepresentation{
	
	private int orderID;
	private String sqlDate;
	private String sqlExpectedShippingDate;
	private boolean isShipped;
	
	@XmlElement(name="books", namespace="")
	private List<BookRepresentation> orderProducts; //CREATE BOOK REPRESENTATION NOT BOOK
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

	public List<BookRepresentation> getBook() {
		return orderProducts;
	}

	public void setBook(List<BookRepresentation> orderProducts) {
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
