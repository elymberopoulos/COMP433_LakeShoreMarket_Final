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
	
	public Order getSpecificOrder(int orderID) {
		
		return OrderDAO.getOrderByNumber(orderID);
	}
	
	public String checkShippingStatus(int orderID) throws ParseException {
		Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.format(currentDate);
		Order targetOrder = OrderDAO.getOrderByNumber(orderID);
		if(currentDate.after(format.parse(targetOrder.getSqlExpectedShippingDate()))) {
			shipOrder(orderID);
			//return "Shipped";
		}
//		for(Order order: OrderDAO.get()) {
//			if(order.getOrderID() == orderID && currentDate.after(format.parse(order.getSqlExpectedShippingDate()))) {
//				isShipped = true;
//				shipOrder(order);
//			}
//		}
		return "Not Shipped";
	}
	private void shipOrder(int orderID) {
		OrderDAO.updateStatus("Shipped", orderID);
//		order.setShipped(true);
//		order.setStatus("SHIPPED");	
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
