package resources;

import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.CacheControl;

import representations.BookRepresentation;
import representations.BookRequest;
import representations.CustomerRepresentation;
import representations.PurchaseRepresentation;
import serviceUsers.Customer;
import activity.BookActivity;
import activity.CustomerActivity;

@Path("/book")
public class BookResource implements BookService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<BookRepresentation> getBooks() {
		System.out.println("GET METHOD Request for all books .............");
		BookActivity bookActivity = new BookActivity();
		return bookActivity.getBooks();	
	}
	//SEARCH FOR SPECIFIC PRODUCT (BUSINESS MODEL)
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{bookId}")
	public BookRepresentation getBook(@PathParam("bookId") String id) {
		System.out.println("GET METHOD Request from Client with bookRequest String ............." + id);
		BookActivity bookActivity = new BookActivity();
		for(BookRepresentation representation: bookActivity.getBooks(id)) {
			System.out.println(representation.getProductName() + "Author: " + representation.getAuthor() + "PRICE: " + representation.getProductPrice());
			System.out.println();
		}
		
		return new BookRepresentation();
	}
	
//	@GET
//	@Produces({"application/xml" , "application/json"})
//	@Path("/book/{bookId}")
//	public PurchaseRepresentation purchaseBook(@PathParam("bookId") String bookID, @PathParam("userID") String customerID) {
//		System.out.println("PURCHASE BOOK METHOD WITH REPRESENTATION OF PURCHASE.....");
//		BookActivity bookActivity = new BookActivity();
//		BookRepresentation book = bookActivity.getOneBook(bookID);
//		CustomerActivity customerActivity = new CustomerActivity();
//		CustomerRepresentation customer = customerActivity.getCustomer(customerID);
//		PurchaseRepresentation purchaseRepresentation = new PurchaseRepresentation();
//		purchaseRepresentation.setPurchasedBook(book.getProductName());
//		purchaseRepresentation.setPurchaserID(customer.getUserID());
//		purchaseRepresentation.setPurchaserCreditCardNumber(customer.getCreditCardNumber());
//		System.out.println(purchaseRepresentation.toString());
//		return purchaseRepresentation;
//
//	}
//	
//	//CHECK FOR PRODUCT AVAILABILITY
//	@GET
//	@Produces({"application/xml" , "application/json"})
//	@Path("/book/{bookId}")
//	public BookRepresentation checkBookAvailability(@PathParam("bookId") String id) {
//		System.out.println("GET METHOD Request from Client CHECK PRODUCT AVAILABILITY." + id);
//		BookActivity bookActivity = new BookActivity();
//		return bookActivity.checkBookAvailability(id);
//	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	public boolean createBook(BookRequest  bookRequest) {
		System.out.println("POST METHOD Request from Client with ............." + bookRequest.getProductName() +" " + bookRequest.getProductPrice() + " " + bookRequest.getProductReview() + " " + bookRequest.getProductOwner() + " " + bookRequest.getProductID() + " " + bookRequest.getIsbn()
		 + " " + bookRequest.getAuthor() + " " + bookRequest.getCategory());
		BookActivity bookActivity = new BookActivity();
		return true; //JUST for testing
	}
	
//	@DELETE
//	@Produces({"application/xml" , "application/json"})
//	@Path("/book/{bookId}")
//	public Response deleteBook(@PathParam("bookId") int id) {
//		System.out.println("Delete METHOD Request from Client with bookRequest String ............." + id);
//		BookActivity bookActivity = new BookActivity();
//		String res = bookActivity.deleteBook(id);
//		if (res.equals("OK")) {
//			return Response.status(Status.OK).build();
//		}
//		return null;
//	}
	
}