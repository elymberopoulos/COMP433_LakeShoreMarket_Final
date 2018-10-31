package databaseConnector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import order.Order;
import products.Book;

public class OrderManagerFacade {
	
	public OrderManagerFacade() {}

	public List<Order> getOrders(){
		return OrderDAO.get();
	}
	public void setDBStatusWithDAO(String newStatus, int orderID) {
		OrderDAO.updateStatus(newStatus, orderID);
	}
	
	public Order getSpecificOrder(int orderID) {        //Iterate through both tables and if orderID match is found then iterate through books
		List<Book> ordersBooks = new ArrayList<Book>(); //and match order id with book order id returning a list of books to the order constructor
		Order targetResult = new Order();    			//recreating the order object from the DB.
		for(Order order: OrderDAO.get()) {
			if(order.getOrderID() == orderID) {
				targetResult.setOrderID(order.getOrderID());
				targetResult.setSqlDate(order.getSqlDate());
				targetResult.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
				targetResult.setStatus(order.getStatus());
				for(Book bookInOrder: BookDAO.get()) {
					if(bookInOrder.getOrderID() == orderID) {
						ordersBooks.add(bookInOrder);
					}
					else {
						System.out.println("ORDER FOUND BUT NO BOOKS MATCH ORDER ID.");
						return targetResult;
					}
				}
			}
			else {
				System.out.println("NO ORDER VALUE FOUND IN DATABASE.");
				return null;
			}
		}
		targetResult.setBook(ordersBooks);
		return targetResult;
	}
	
	public boolean checkShippingStatus(int orderID) throws ParseException {
		Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.format(currentDate);
		boolean isShipped = false;
		for(Order order: OrderDAO.get()) {
			if(order.getOrderID() == orderID && currentDate.after(format.parse(order.getSqlExpectedShippingDate()))) {
				isShipped = true;
				shipOrder(order);
			}
		}
		return isShipped;
	}
	private void shipOrder(Order order) {
		int orderID = order.getOrderID();
		OrderDAO.updateStatus("SHIPPED", orderID);
		order.setShipped(true);
		order.setStatus("SHIPPED");	
	}
	
	public String getOrderStatus(int orderID) throws ParseException {
		for(Order order: OrderDAO.get()) {
			if(order.getOrderID() == orderID) {
				checkShippingStatus(order.getOrderID());
				return order.getStatus();
			}
		}
		return "NO MATCHING ORDER FOUND.";
	}
/*	
	public Order postOrder(int orderID, List<Book> orderProducts) {
		Order order = new Order(orderID, orderProducts);
		OrderDAO.post(order);
		
		return order;
	}
	*/
	public Order postOrder(List<Book> orderProducts) {
		Order order = new Order(orderProducts);
		/*for(Book bookInList: orderProducts) { //ALREADY IN ORDER DAO
			BookDAO.post(bookInList);
		}*/
		OrderDAO.post(order);
		
		return order;
	}
	public Order updateOrder(int orderID, List<Book> orderProducts) {
		Order order = new Order(orderID, orderProducts);
		OrderDAO.put(order);
		
		return order;
	}
	public void deleteOrder(int orderID) {
		OrderDAO.deleteIDMatch(orderID);
	}
}
