package activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import databaseConnector.BookManagerFacade;
import databaseConnector.OrderManagerFacade;
import errorHandling.DataNotFound;
import errorHandling.ErrorMessage;
import link.Link;
import representations.AbstractRepresentation;
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
		if(orders == null) {
			throw new DataNotFound("RESOURCE 404 " + orders + " not found");
		}
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
//		Link bookIdLink = new Link("List", "http://localhost:8081/book/bookId/");
		Link orderLink = new Link("Get_Order", "http://localhost:8081/order/" + orderRep.getOrderID());
		Link partnerRootLink = new Link("Get_Partners", "http://localhost:8081/partner/");
		Link customerRootLink = new Link("Get_Customers", "http://localhost:8081/customer/");


		
		orderRep.setLinks(orderLink, partnerRootLink, customerRootLink);
	}
	
	public OrderRepresentation getSpecificOrder(int id) throws ParseException {
		
        OrderRepresentation orderRepresentation = new OrderRepresentation();
        //BookRepresentation bookRep = new BookRepresentation();
		Order order = manager.getSpecificOrder(id);
		if(order == null) {
			throw new DataNotFound("RESOURCE 404 " + order + " not found");
		}
		orderRepresentation.setOrderID(order.getOrderID());
		orderRepresentation.setSqlDate(order.getSqlDate());
		orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
		orderRepresentation.setShipped(order.isShipped());
		orderRepresentation.setStatus(manager.getOrderStatus(order.getOrderID()));

		return orderRepresentation;
	}


	public OrderRepresentation createOrder(OrderRequest orderProducts, String customerID) throws ErrorMessage {
		
		//Order order = manager.postOrder(orderID, sqlDate, sqlExpectedShippingDate, isShipped);
		List<BookRequest> orderRequests = orderProducts.getOrderProducts();
		List<Book> books = new ArrayList<Book>();
		List<Book> convertedBooks = convertBookRequestToBooks(books,orderRequests,customerID);

		Order order = manager.postOrder(convertedBooks);
		if(order == null) {
			throw new ErrorMessage();
		}
		OrderRepresentation orderRepresentation = new OrderRepresentation();
		orderRepresentation.setOrderID(order.getOrderID());
		orderRepresentation.setSqlDate(order.getSqlDate());
		orderRepresentation.setSqlExpectedShippingDate(order.getSqlExpectedShippingDate());
		orderRepresentation.setShipped(order.isShipped());
        orderRepresentation.setStatus("ORDER CREATED");

		return orderRepresentation;
	}
	
	private List<Book> convertBookRequestToBooks(List<Book> books, List<BookRequest> orderRequests,String customerID) {
	
		books = new ArrayList<Book>();
		for(BookRequest br: orderRequests) {
			br.setProductOwner(customerID);
			Book book = new Book(br.getProductName(), br.getProductPrice(), br.getProductReview(), customerID, br.getProductID(), br.getIsbn(), br.getAuthor(), br.getCategory());
			books.add(book);
		}
		return books;
	
	}
	
	public String cancelOrder(int orderID) {
		manager.setDBStatusWithDAO("CANCELLED", orderID);
		return "ORDER IS CANCELLED";
	}

	public String deleteOrder(int id) {

		manager.deleteOrder(id);
		
		return "OK";
	}

}
