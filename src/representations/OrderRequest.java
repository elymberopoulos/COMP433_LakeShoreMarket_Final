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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	
	private int orderID;
	private String sqlDate;
	private String sqlExpectedShippingDate;
	//private Date orderDate;
	//private Date expectedShippingDate;
	private boolean shipped;
	private List<BookRequest> orderProducts;
	private String status;
	//private List<Book> bookList;//TODO Change this book list to an arrayList with List for static type for more abstraction.
	
	public OrderRequest() {}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
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

//	public boolean isShipped() {
//		return isShipped;
//	}
//
//	public void setShipped(boolean isShipped) {
//		this.isShipped = isShipped;
//	}


	public List<BookRequest> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<BookRequest> orderProducts) {
		this.orderProducts = orderProducts;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isShipped() {
		return shipped;
	}

	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}
	
/*	
	public OrderRequest(int orderID, List<Book> orderProducts){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		this.sqlDate = format.format(date);
		this.sqlExpectedShippingDate = format.format((date.getTime() + 86400000));
		//OLD ONE  this.sqlExpectedShippingDate = format.format(date);//CURRENT DATE IN MS PLUS 1 DAY IN MS
		this.orderProducts = orderProducts;
		this.orderID = orderID;
		//this.orderDate = new Date();
		//this.expectedShippingDate = new Date();
		this.isShipped = false;
		for(Book x: orderProducts){x.setOrderID(orderID);}
	
	}
	public OrderRequest(int orderID, List<Book> orderProducts, String sqlDate, String sqlExpectedShippingDate){
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
*/
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
*////
}

