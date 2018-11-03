package databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import order.Order;
import products.Book;
import representations.BookRequest;
import representations.CustomerRequest;
import representations.OrderRepresentation;
import representations.OrderRequest;
import representations.PartnerRequest;
import serviceUsers.Customer;
import serviceUsers.Partner;


public class Main {

	public static void main(String[] args) {
		
		
	 	Book bookTest1 = new Book("Harry Potter", 15.75, "good", "Alex", 2, 12345678, "JK rowling", "Fantasy");
		Book bookTest2 = new Book("Modified", 15.75, "good", "Alex", 2, 12345678, "JK rowling", "Fantasy");
		Customer customer1 = new Customer
			("Eric", "Steele", "Lymbo2", "Loyola", "26 East Pearson", 81555555, "luc.edu", 1, 222333432, "password12");
		Customer customer2 = new Customer
				("Steve", "Wright", "Lymbo2", "Intel", "1337 Silicon Valley", 8675309, "silicon@intel", 2, 333345544, "password123");
		Partner partner1 = new Partner
			("Max", "Hill", "Tech.inc", "27 North Street", 7758094, "max@mail.com", 2, "tony25", 8754909);
		Partner partner2 = new Partner
				("Sam", "Johnson", "Mech.inc", "27 South Street", 8675309, "sam@mail.com", 2, "tony25", 8755709);
		ArrayList<Book> bookList1 = new ArrayList<Book>();
		bookList1.add(bookTest1);
		bookList1.add(bookTest2);
		//Order order1 = new Order(1, bookList1);
		//Order order2 = new Order(1, bookList2);
		System.out.println("Testing new order constructor");
		Order orderTest1 = new Order(bookList1);
  	 List<Object> providers = new ArrayList<Object>();
     JacksonJsonProvider provider = new JacksonJsonProvider();
     provider.addUntouchable(Response.class);
     providers.add(provider);
     
     /*****************************************************************************************
      * GET METHOD invoke
      *****************************************************************************************/
     //GET METHOD BOOK
     System.out.println("GET METHOD BOOK.........................................................");
     WebClient getClient = WebClient.create("http://localhost:8080", providers);
     
     //Configuring the CXF logging interceptor for the outgoing message
     WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
     //Configuring the CXF logging interceptor for the incoming response
     WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());
     
     // change application/xml  to get in xml format
     getClient = getClient.accept("application/json").type("application/json").path("/bookservice/book/XY1111");
     
     //The following lines are to show how to log messages without the CXF interceptors
     String getRequestURI = getClient.getCurrentURI().toString();
     System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
     String getRequestHeaders = getClient.getHeaders().toString();
     System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);
     
     //to see as raw XML/json
     String response = getClient.get(String.class);
     System.out.println("GET BOOK METHOD Response: ...." + response);
     
      
     
    //to get the response as object of Employee class
    //Employee employee = client.get(Employee.class);
    //System.out.println("Name:" + employee.getFirstName());
    //System.out.println("privileges:" + employee.getPrivileges());
    
    /*****************************************************************************************
     * POST METHOD invoke
    *****************************************************************************************/
    //POST METHOD BOOK
    System.out.println("POST METHOD BOOK.........................................................");
    WebClient postClient = WebClient.create("http://localhost:8080", providers);
    WebClient.getConfig(postClient).getOutInterceptors().add(new LoggingOutInterceptor());
    WebClient.getConfig(postClient).getInInterceptors().add(new LoggingInInterceptor());
             
    // change application/xml  to application/json get in json format
    postClient = postClient.accept("application/xml").type("application/json").path("/bookservice/book");
 	
    String postRequestURI = postClient.getCurrentURI().toString();
    System.out.println("Client POST METHOD Request URI:  " + postRequestURI);
    String postRequestHeaders = postClient.getHeaders().toString();
    System.out.println("Client POST METHOD Request Headers:  " + postRequestHeaders);
    BookRequest bookRequest = new BookRequest();
    bookRequest.setProductName(bookTest1.getProductName());
    bookRequest.setProductPrice(bookTest1.getProductPrice());
    bookRequest.setProductReview(bookTest1.getProductReview());
    bookRequest.setProductOwner(bookTest1.getProductOwner());
    bookRequest.setProductID(bookTest1.getProductID());
    bookRequest.setIsbn(bookTest1.getIsbn());
    bookRequest.setAuthor(bookTest1.getAuthor());
    bookRequest.setCategory(bookTest1.getCategory());

    
 	String responsePost =  postClient.post(bookRequest, String.class);
    System.out.println("POST BOOK METHOD Response ........." + responsePost);
    
      /*****************************************************************************************
     * GET METHOD invoke for all books, orders, customers, partners
     *****************************************************************************************/
    //GET METHOD ALL BOOKS
    System.out.println("GET METHOD for all book..........................................");
    WebClient getAllClient = WebClient.create("http://localhost:8080", providers);
    WebClient.getConfig(getAllClient).getOutInterceptors().add(new LoggingOutInterceptor());
    WebClient.getConfig(getAllClient).getInInterceptors().add(new LoggingInInterceptor());
    
    // change application/xml  to get in xml format
    getAllClient = getAllClient.accept("application/json").type("application/json").path("/bookservice/book");
             
    String getAllRequestURI = getAllClient.getCurrentURI().toString();
    System.out.println("Client GET METHOD Request URI:  " + getAllRequestURI);
    String getAllRequestHeaders = getAllClient.getHeaders().toString();
    System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeaders);
    
    //to see as raw XML/json
    String getAllResponse = getAllClient.get(String.class);
    System.out.println("GET All METHOD Response: ...." + getAllResponse);
    
       
    /*****************************************************************************************
     * DELETE METHOD invoke
    *****************************************************************************************/
    
    //DELETE METHOD BOOK
    System.out.println("DELETE METHOD .........................................................");
    WebClient deleteClient = WebClient.create("http://localhost:8080", providers);
    WebClient.getConfig(deleteClient).getOutInterceptors().add(new LoggingOutInterceptor());
    WebClient.getConfig(deleteClient).getInInterceptors().add(new LoggingInInterceptor());
    
    // change application/xml  to application/json get in json format
    deleteClient = deleteClient.accept("application/xml").type("application/json").path("/bookservice/book/XY1111");
 	
    String deleteRequestURI = deleteClient.getCurrentURI().toString();
    System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURI);
    String deleteRequestHeaders = deleteClient.getHeaders().toString();
    System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeaders);
    
    deleteClient.delete();
    System.out.println("DELETE MEDTHOD Response ......... OK");
     
    System.exit(0);
    
       
    
    /*****************************************************************************************
     * GET METHOD invoke
     *****************************************************************************************/
//      
//    //GET METHOD ORDER
//    System.out.println("GET METHOD ORDER.........................................................");
//    WebClient getClientOrder = WebClient.create("http://localhost:8080", providers);
//    
//    //Configuring the CXF logging interceptor for the outgoing message
//    WebClient.getConfig(getClientOrder).getOutInterceptors().add(new LoggingOutInterceptor());
//    //Configuring the CXF logging interceptor for the incoming response
//    WebClient.getConfig(getClientOrder).getInInterceptors().add(new LoggingInInterceptor());
//    
//    // change application/xml  to get in xml format
//    getClientOrder = getClientOrder.accept("application/json").type("application/json").path("/orderservice/order/XY1111");
//    
//    //The following lines are to show how to log messages without the CXF interceptors
//    String getRequestURIOrder = getClientOrder.getCurrentURI().toString();
//    System.out.println("Client GET METHOD Request URI:  " + getRequestURIOrder);
//    String getRequestHeadersOrder = getClientOrder.getHeaders().toString();
//    System.out.println("Client GET METHOD Request Headers:  " + getRequestHeadersOrder);
//    
//    //to see as raw XML/json
//    String responseOrder = getClientOrder.get(String.class);
//    System.out.println("GET ORDER METHOD Response: ...." + responseOrder);
//    
//      
//    
//   //to get the response as object of Employee class
//   //Employee employee = client.get(Employee.class);
//   //System.out.println("Name:" + employee.getFirstName());
//   //System.out.println("privileges:" + employee.getPrivileges());
//   
//   /*****************************************************************************************
//    * POST METHOD invoke
//   *****************************************************************************************/
//   
//   //POST METHOD ORDER
//   System.out.println("POST METHOD ORDER.........................................................");
//   WebClient postClientOrder = WebClient.create("http://localhost:8080", providers);
//   WebClient.getConfig(postClientOrder).getOutInterceptors().add(new LoggingOutInterceptor());
//   WebClient.getConfig(postClientOrder).getInInterceptors().add(new LoggingInInterceptor());
//            
//   // change application/xml  to application/json get in json format
//   postClientOrder = postClientOrder.accept("application/xml").type("application/json").path("/orderservice/order");
//	
//   String postRequestURIOrder = postClientOrder.getCurrentURI().toString();
//   System.out.println("Client POST METHOD Request URI:  " + postRequestURIOrder);
//   String postRequestHeadersOrder = postClientOrder.getHeaders().toString();
//   System.out.println("Client POST METHOD Request Headers:  " + postRequestHeadersOrder);
//   OrderRequest orderRequest = new OrderRequest();
//   orderRequest.setOrderID(orderTest1.getOrderID());
//   orderRequest.setSqlDate(orderTest1.getSqlDate());
//   orderRequest.setSqlExpectedShippingDate(orderTest1.getSqlExpectedShippingDate());
//   orderRequest.setShipped(orderTest1.isShipped());
//   //orderRequest.setStatus(manager.getOrderStatus(orderTest1.getOrderID()));
//
//
//   String responsePostOrder =  postClientOrder.post(orderRequest, String.class);
//   System.out.println("POST ORDER METHOD Response ........." + responsePostOrder);
//   
//   
//     
//   /*****************************************************************************************
//    * GET METHOD invoke for all  orders
//    *****************************************************************************************/
//      
//   //GET METHOD ALL ORDERS
//   System.out.println("GET METHOD for all order..........................................");
//   WebClient getAllClientOrder = WebClient.create("http://localhost:8080", providers);
//   WebClient.getConfig(getAllClientOrder).getOutInterceptors().add(new LoggingOutInterceptor());
//   WebClient.getConfig(getAllClientOrder).getInInterceptors().add(new LoggingInInterceptor());
//   
//   // change application/xml  to get in xml format
//   getAllClientOrder = getAllClientOrder.accept("application/json").type("application/json").path("/orderservice/order");
//            
//   String getAllRequestURIOrder = getAllClientOrder.getCurrentURI().toString();
//   System.out.println("Client GET METHOD Request URI:  " + getAllRequestURIOrder);
//   String getAllRequestHeadersOrder = getAllClientOrder.getHeaders().toString();
//   System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeadersOrder);
//   
//   //to see as raw XML/json
//   String getAllResponseOrder = getAllClientOrder.get(String.class);
//   System.out.println("GET All METHOD Response: ...." + getAllResponseOrder);
//  
//   
//     
//   /*****************************************************************************************
//    * DELETE METHOD invoke
//   *****************************************************************************************/
//   
//   
//   //DELETE METHOD ORDER
//   System.out.println("DELETE METHOD .........................................................");
//   WebClient deleteClientOrder = WebClient.create("http://localhost:8080", providers);
//   WebClient.getConfig(deleteClientOrder).getOutInterceptors().add(new LoggingOutInterceptor());
//   WebClient.getConfig(deleteClientOrder).getInInterceptors().add(new LoggingInInterceptor());
//   
//   // change application/xml  to application/json get in json format
//   deleteClientOrder = deleteClientOrder.accept("application/xml").type("application/json").path("/orderservice/order/XY1111");
//	
//   String deleteRequestURIOrder = deleteClientOrder.getCurrentURI().toString();
//   System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURIOrder);
//   String deleteRequestHeadersOrder = deleteClientOrder.getHeaders().toString();
//   System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeadersOrder);
//   
//   deleteClient.delete();
//   System.out.println("DELETE MEDTHOD Response ......... OK");
//    
//   System.exit(0);
//   
//     
//   
//   /*****************************************************************************************
//    * GET METHOD invoke
//    *****************************************************************************************/
//    
//   //GET METHOD CUSTOMER
//   System.out.println("GET METHOD CUSTOMER.........................................................");
//   WebClient getClientCustomer = WebClient.create("http://localhost:8080", providers);
//   
//   //Configuring the CXF logging interceptor for the outgoing message
//   WebClient.getConfig(getClientCustomer).getOutInterceptors().add(new LoggingOutInterceptor());
//   //Configuring the CXF logging interceptor for the incoming response
//   WebClient.getConfig(getClientCustomer).getInInterceptors().add(new LoggingInInterceptor());
//   
//   // change application/xml  to get in xml format
//   getClientCustomer = getClientCustomer.accept("application/json").type("application/json").path("/customerservice/customer/XY1111");
//   
//   //The following lines are to show how to log messages without the CXF interceptors
//   String getRequestURICustomer = getClientCustomer.getCurrentURI().toString();
//   System.out.println("Client GET METHOD Request URI:  " + getRequestURICustomer);
//   String getRequestHeadersCustomer = getClientCustomer.getHeaders().toString();
//   System.out.println("Client GET METHOD Request Headers:  " + getRequestHeadersCustomer);
//   
//   //to see as raw XML/json
//   String responseCustomer = getClientCustomer.get(String.class);
//   System.out.println("GET CUSTOMER METHOD Response: ...." + responseCustomer);
//   
//    
//   
//  //to get the response as object of Employee class
//  //Employee employee = client.get(Employee.class);
//  //System.out.println("Name:" + employee.getFirstName());
//  //System.out.println("privileges:" + employee.getPrivileges());
//  
//  /*****************************************************************************************
//   * POST METHOD invoke
//  *****************************************************************************************/
// 
//    //POST METHOD CUSTOMER
//  System.out.println("POST METHOD CUSTOMER.........................................................");
//  WebClient postClientCustomer = WebClient.create("http://localhost:8080", providers);
//  WebClient.getConfig(postClientCustomer).getOutInterceptors().add(new LoggingOutInterceptor());
//  WebClient.getConfig(postClientCustomer).getInInterceptors().add(new LoggingInInterceptor());
//           
//  // change application/xml  to application/json get in json format
//  postClientCustomer = postClientCustomer.accept("application/xml").type("application/json").path("/customerservice/customer");
//	
//  String postRequestURICustomer = postClientCustomer.getCurrentURI().toString();
//  System.out.println("Client POST METHOD Request URI:  " + postRequestURICustomer);
//  String postRequestHeadersCustomer = postClient.getHeaders().toString();
//  System.out.println("Client POST METHOD Request Headers:  " + postRequestHeadersCustomer);
//  CustomerRequest customerRequest = new CustomerRequest();
//  customerRequest.setFirstName(customer1.getFirstName());
//	customerRequest.setLastName(customer1.getLastName());
//	customerRequest.setUserID(customer1.getUserID());
//	customerRequest.setCompanyName(customer1.getCompanyName());
//	customerRequest.setAddress(customer1.getAddress());
//	customerRequest.setPhoneNumber(customer1.getPhoneNumber());
//	customerRequest.setEmail(customer1.getEmail());
//	customerRequest.setNumberOfOrders(customer1.getNumberOfOrders());
//	customerRequest.setCreditCardNumber(customer1.getCreditCardNumber());
//
//
//  String responsePostCustomer =  postClientCustomer.post(customerRequest, String.class);
//  System.out.println("POST CUSTOMER METHOD Response ........." + responsePostCustomer);
//  
//  
//  /*****************************************************************************************
//   * GET METHOD invoke for all customers
//   *****************************************************************************************/
// 
//  //GET METHOD ALL CUSTOMERS
//  System.out.println("GET METHOD for all customer..........................................");
//  WebClient getAllClientCustomer = WebClient.create("http://localhost:8080", providers);
//  WebClient.getConfig(getAllClientCustomer).getOutInterceptors().add(new LoggingOutInterceptor());
//  WebClient.getConfig(getAllClientCustomer).getInInterceptors().add(new LoggingInInterceptor());
//  
//  // change application/xml  to get in xml format
//  getAllClientCustomer = getAllClientCustomer.accept("application/json").type("application/json").path("/customerservice/customer");
//           
//  String getAllRequestURICustomer = getAllClientCustomer.getCurrentURI().toString();
//  System.out.println("Client GET METHOD Request URI:  " + getAllRequestURICustomer);
//  String getAllRequestHeadersCustomer = getAllClient.getHeaders().toString();
//  System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeadersCustomer);
//  
//  //to see as raw XML/json
//  String getAllResponseCustomer = getAllClientCustomer.get(String.class);
//  System.out.println("GET All METHOD Response: ...." + getAllResponseCustomer);
//  
//   
//  /*****************************************************************************************
//   * DELETE METHOD invoke
//  *****************************************************************************************/
//  
//    
//  //DELETE METHOD CUSTOMER
//  System.out.println("DELETE METHOD .........................................................");
//  WebClient deleteClientCustomer = WebClient.create("http://localhost:8080", providers);
//  WebClient.getConfig(deleteClientCustomer).getOutInterceptors().add(new LoggingOutInterceptor());
//  WebClient.getConfig(deleteClientCustomer).getInInterceptors().add(new LoggingInInterceptor());
//  
//  // change application/xml  to application/json get in json format
//  deleteClientCustomer = deleteClientCustomer.accept("application/xml").type("application/json").path("/customerservice/customer/XY1111");
//	
//  String deleteRequestURICustomer = deleteClientCustomer.getCurrentURI().toString();
//  System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURICustomer);
//  String deleteRequestHeadersCustomer = deleteClientCustomer.getHeaders().toString();
//  System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeadersCustomer);
//  
//  deleteClientCustomer.delete();
//  System.out.println("DELETE MEDTHOD Response ......... OK");
//   
//  System.exit(0);
//
//  
//  
//  
//  
//  /*****************************************************************************************
//   * GET METHOD invoke
//   *****************************************************************************************/
//    
//  //GET METHOD PARTNER
//  System.out.println("GET METHOD PARTNER.........................................................");
//  WebClient getClientPartner = WebClient.create("http://localhost:8080", providers);
//  
//  //Configuring the CXF logging interceptor for the outgoing message
//  WebClient.getConfig(getClientPartner).getOutInterceptors().add(new LoggingOutInterceptor());
//  //Configuring the CXF logging interceptor for the incoming response
//  WebClient.getConfig(getClientPartner).getInInterceptors().add(new LoggingInInterceptor());
//  
//  // change application/xml  to get in xml format
//  getClientPartner = getClientPartner.accept("application/json").type("application/json").path("/partnerservice/partner/XY1111");
//  
//  //The following lines are to show how to log messages without the CXF interceptors
//  String getRequestURIPartner = getClientPartner.getCurrentURI().toString();
//  System.out.println("Client GET METHOD Request URI:  " + getRequestURIPartner);
//  String getRequestHeadersPartner = getClient.getHeaders().toString();
//  System.out.println("Client GET METHOD Request Headers:  " + getRequestHeadersPartner);
//  
//  //to see as raw XML/json
//  String responsePartner = getClientPartner.get(String.class);
//  System.out.println("GET PARTNER METHOD Response: ...." + responsePartner);
//  
//  
//  
// //to get the response as object of Employee class
// //Employee employee = client.get(Employee.class);
// //System.out.println("Name:" + employee.getFirstName());
// //System.out.println("privileges:" + employee.getPrivileges());
// 
// /*****************************************************************************************
//  * POST METHOD invoke
// *****************************************************************************************/
//  
// //POST METHOD PARTNER
// System.out.println("POST METHOD PARTNER.........................................................");
// WebClient postClientPartner = WebClient.create("http://localhost:8080", providers);
// WebClient.getConfig(postClientPartner).getOutInterceptors().add(new LoggingOutInterceptor());
// WebClient.getConfig(postClientPartner).getInInterceptors().add(new LoggingInInterceptor());
//          
// // change application/xml  to application/json get in json format
// postClientPartner = postClientPartner.accept("application/xml").type("application/json").path("/partnerservice/book");
//	
// String postRequestURIPartner = postClientPartner.getCurrentURI().toString();
// System.out.println("Client POST METHOD Request URI:  " + postRequestURIPartner);
// String postRequestHeadersPartner = postClientPartner.getHeaders().toString();
// System.out.println("Client POST METHOD Request Headers:  " + postRequestHeadersPartner);
// PartnerRequest partnerRequest = new PartnerRequest();
// partnerRequest.setFirstName(partner1.getFirstName());
//	partnerRequest.setLastName(partner1.getLastName());
//	partnerRequest.setUserID(partner1.getUserID());
//	partnerRequest.setCompanyName(partner1.getCompanyName());
//	partnerRequest.setAddress(partner1.getAddress());
//	partnerRequest.setPhoneNumber(partner1.getPhoneNumber());
//	partnerRequest.setEmail(partner1.getEmail());
//	partnerRequest.setNumberOfOrders(partner1.getNumberOfOrders());
//	partnerRequest.setBankAccountNumber(partner1.getBankAccountNumber());
//
// 
//	String responsePostPartner =  postClientPartner.post(partnerRequest, String.class);
// System.out.println("POST PARTNER METHOD Response ........." + responsePostPartner);
// 
// /*****************************************************************************************
//  * GET METHOD invoke for all partners
//  *****************************************************************************************/
// 
// //GET METHOD ALL PARTNERS
// System.out.println("GET METHOD for all partner..........................................");
// WebClient getAllClientPartner = WebClient.create("http://localhost:8080", providers);
// WebClient.getConfig(getAllClientPartner).getOutInterceptors().add(new LoggingOutInterceptor());
// WebClient.getConfig(getAllClientPartner).getInInterceptors().add(new LoggingInInterceptor());
// 
// // change application/xml  to get in xml format
// getAllClientPartner = getAllClientPartner.accept("application/json").type("application/json").path("/partnerservice/partner");
//          
// String getAllRequestURIPartner = getAllClientPartner.getCurrentURI().toString();
// System.out.println("Client GET METHOD Request URI:  " + getAllRequestURIPartner);
// String getAllRequestHeadersPartner = getAllClientPartner.getHeaders().toString();
// System.out.println("Client GET METHOD Request Headers:  " + getAllRequestHeadersPartner);
// 
// //to see as raw XML/json
// String getAllResponsePartner = getAllClient.get(String.class);
// System.out.println("GET All METHOD Response: ...." + getAllResponsePartner);
// 
// /*****************************************************************************************
//  * DELETE METHOD invoke
// *****************************************************************************************/
//  
// //DELETE METHOD PARTNER
// System.out.println("DELETE METHOD .........................................................");
// WebClient deleteClientPartner = WebClient.create("http://localhost:8080", providers);
// WebClient.getConfig(deleteClientPartner).getOutInterceptors().add(new LoggingOutInterceptor());
// WebClient.getConfig(deleteClientPartner).getInInterceptors().add(new LoggingInInterceptor());
// 
// // change application/xml  to application/json get in json format
// deleteClientPartner = deleteClientPartner.accept("application/xml").type("application/json").path("/partnerservice/partner/XY1111");
//	
// String deleteRequestURIPartner = deleteClientPartner.getCurrentURI().toString();
// System.out.println("Client DELETE METHOD Request URI:  " + deleteRequestURIPartner);
// String deleteRequestHeadersPartner = deleteClientPartner.getHeaders().toString();
// System.out.println("Client DELETE METHOD Request Headers:  " + deleteRequestHeadersPartner);
// 
// deleteClient.delete();
// System.out.println("DELETE MEDTHOD Response ......... OK");
//  
// System.exit(0);
    
           
    
    
    
	}
}
