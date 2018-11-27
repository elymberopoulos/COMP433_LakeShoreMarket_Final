package activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import databaseConnector.BookManagerFacade;
import databaseConnector.OrderManagerFacade;
import link.Link;
import representations.BookRepresentation;
import representations.BookRequest;
import representations.OrderRepresentation;
import representations.OrderRequest;
import order.Order;
import products.Book;

public class OrderActivity {
private static OrderManagerFacade manager = new OrderManagerFacade();
	
	public Set<OrderRepresentation> getOrders() throws ParseException {
		
		List<Order> orders = new ArrayList<Order>();
		Set<OrderRepresentation> orderRepresentations = new HashSet<OrderRepresentation>();
		orders = manager.getOrders();
		
		Iterator<Order> it = orders.iterator();
		while(it.hasNext()) {
          Order targetOrder = (Order)it.next();
          OrderRepresentation orderRepresentation = new OrderRepresentation();
          orderRepresentation.setOrderID(targetOrder.getOrderID());
          orderRepresentation.setSqlDate(targetOrder.getSqlDate());
          orderRepresentation.setSqlExpectedShippingDate(targetOrder.getSqlExpectedShippingDate());
          orderRepresentation.setShipped(targetOrder.isShipped());
          orderRepresentation.setStatus(manager.getOrderStatus(targetOrder.getOrderID()));
          //now add this representation in the list
          setLinksGetAllOrders(orderRepresentation);
          orderRepresentations.add(orderRepresentation);
        }
		return orderRepresentations;
	}
	private void setLinksGetAllOrders(OrderRepresentation orderRep) {
		// Set up the activities that can be performed on orders
		Link bookIdLink = new Link("List", "http://localhost:8081/book/bookId/");
		Link customerRootLink = new Link("List", "http://localhost:8081/customer/");
		Link orderRootLink = new Link("List", "http://localhost:8081/order/" + orderRep.getOrderID());
		Link partnerRootLink = new Link("List", "http://localhost:8081/partner/");


		
		orderRep.setLinks(bookIdLink, customerRootLink, orderRootLink, partnerRootLink);
	}
	
	public OrderRepresentation getSpecificOrder(int id) throws ParseException {
		
        OrderRepresentation orderRepresentation = new OrderRepresentation();

		Order order = manager.getSpecificOrder(id);

		orderRepresentation.setOrderID(order.getOrderID());
		orderRepresentation.setSqlDate(order.getSqlDate());
		orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
		orderRepresentation.setShipped(order.isShipped());
		orderRepresentation.setStatus(manager.getOrderStatus(order.getOrderID()));
		orderRepresentation.getBook();
		
		return orderRepresentation;
	}
	/*
	public OrderRepresentation createOrder(int orderID, List<Book> orderProducts) {
		
		//Order order = manager.postOrder(orderID, sqlDate, sqlExpectedShippingDate, isShipped);
		Order order = manager.postOrder(orderID, orderProducts);

		
		OrderRepresentation orderRepresentation = new OrderRepresentation();
		orderRepresentation.setOrderID(order.getOrderID());
		orderRepresentation.setSqlDate(order.getSqlDate());
		orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
		orderRepresentation.setShipped(order.isShipped());

		return orderRepresentation;
	}
	*/
	
	/*public OrderRepresentation createOrder(List<Book> orderProducts) {
		
		//Order order = manager.postOrder(orderID, sqlDate, sqlExpectedShippingDate, isShipped);
		Order order = manager.postOrder(orderProducts);

		
		OrderRepresentation orderRepresentation = new OrderRepresentation();
		orderRepresentation.setOrderID(order.getOrderID());
		orderRepresentation.setSqlDate(order.getSqlDate());
		orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
		orderRepresentation.setShipped(order.isShipped());

		return orderRepresentation;
	}
	*/
	public OrderRepresentation createOrder(OrderRequest orderProducts) {
		
		//Order order = manager.postOrder(orderID, sqlDate, sqlExpectedShippingDate, isShipped);
		List<BookRequest> orderRequests = orderProducts.getOrderProducts();
		List<Book> books = new ArrayList<Book>();
		convertBookRequestToBooks(books,orderRequests);

		Order order = manager.postOrder(books);

		OrderRepresentation orderRepresentation = new OrderRepresentation();
		orderRepresentation.setOrderID(order.getOrderID());
		orderRepresentation.setSqlDate(order.getSqlDate());
		orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
		orderRepresentation.setShipped(order.isShipped());
        orderRepresentation.setStatus("ORDER CREATED");

		return orderRepresentation;
	}
	
	private void convertBookRequestToBooks(List<Book> books, List<BookRequest> orderRequests) {
	
		books = new ArrayList<Book>();
		for(BookRequest br: orderRequests) {
			Book book = new Book(br.getProductName(), br.getProductPrice(), "", br.getProductOwner(), br.getProductID(), br.getIsbn(), br.getAuthor(), br.getCategory());
			books.add(book);
		}
	
	}
	
	public String requestRefund(int orderID) throws ParseException {
		if(manager.checkShippingStatus(orderID)) {
			return "ORDER HAS BEEN SHIPPED. RETURN VIA MAIL ONCE IT ARRIVES.";
		}
		else {
			manager.setDBStatusWithDAO("REFUNDED", orderID);
			return "ORDER CANCELLED AND REFUND HAS BEEN PROCESSED.";
		}
	}
	public String cancelOrder(int orderID) {
		manager.setDBStatusWithDAO("CANCELLED", orderID);
		return "ORDER IS CANCELLED";
	}

	public String deleteOrder(int id) {

		manager.deleteOrder(id);
		
		return "OK";
	}
/* DUPLICATE METHOD
	public OrderRepresentation getOrder(int id) {
		OrderRepresentation orderRepresentation = new OrderRepresentation();
		Order order = manager.getSpecificOrder(id);
			if(order == null){
				return null;
			}else {
				orderRepresentation.setOrderID(order.getOrderID());
				orderRepresentation.setSqlDate(order.getSqlDate());
				orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
				orderRepresentation.setShipped(order.isShipped());
		}
		return orderRepresentation;
	}*/
}
