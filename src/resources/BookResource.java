package resources;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import representations.BookRepresentation;
import representations.BookRequest;
import representations.CustomerRepresentation;
import representations.PurchaseRepresentation;
import serviceUsers.Customer;
import activity.BookActivity;
import activity.CustomerActivity;

@CrossOriginResourceSharing(
		allowAllOrigins = true,
		allowCredentials = true,
		allowHeaders = {
				"'Accept': 'application/json'",
				"'Content-Type': 'application/json'"
		})

@Path("/")
public class BookResource implements BookService{
	@Context
	private HttpHeaders header;
	
	@OPTIONS
	@LocalPreflight
	@Path("/")
	public Response options() {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"http://localhost:63342")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}

	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<BookRepresentation> getAllBooks() {
		System.out.println("GET METHOD Request for all books .............");
		BookActivity bookActivity = new BookActivity();
		return bookActivity.getAllBooks();	
	}
	
	
	@OPTIONS
	@LocalPreflight
	@Path("/{bookId}")
	public Response idOptions(@PathParam("bookId") String bookID	) {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"http://localhost:63342")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	//SEARCH FOR SPECIFIC PRODUCT (BUSINESS MODEL)
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{bookId}")
	public BookRepresentation getBookMatchingName(@PathParam("bookId") String bookID, @QueryParam("customerId")String customerID) {
		System.out.println("GET METHOD Request from Client with bookRequest String ............." + bookID);
		BookActivity bookActivity = new BookActivity();
		
		return bookActivity.getOneBook(bookID, customerID);
	}
	
	
	@OPTIONS
	@LocalPreflight
	@Path("/order_id")
	public Response orderIdOptions(@QueryParam("order_id") int orderID	) {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"http://localhost:63342")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/order_id")
	@LocalPreflight
	public List<BookRepresentation> getBookByOrderID(@QueryParam("order_id") int orderID) {
		System.out.println("GET METHOD Request from Client with bookRequest ORDER_ID ............." + orderID);
		BookActivity bookActivity = new BookActivity();
//		for(BookRepresentation representation: bookActivity.getBooks(id)) {
//			System.out.println(representation.getProductName() + "Author: " + representation.getAuthor() + "PRICE: " + representation.getProductPrice());
//			System.out.println();
//		}
		
		return bookActivity.getAllBooksByOrderID(orderID);
	}
	
	@OPTIONS
	@LocalPreflight
	@Path("/inventory/owner_id")
	public Response ownerInventoryOptions(@QueryParam("owner_id") int ownerID) {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"http://localhost:63342")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/inventory/owner_id")
	@LocalPreflight
	public List<BookRepresentation> getBookByOwnerID(@QueryParam("owner_id") String ownerID) {
		System.out.println("GET METHOD Request from Client with bookRequest ORDER_ID ............." + ownerID);
		BookActivity bookActivity = new BookActivity();
//		for(BookRepresentation representation: bookActivity.getBooks(id)) {
//			System.out.println(representation.getProductName() + "Author: " + representation.getAuthor() + "PRICE: " + representation.getProductPrice());
//			System.out.println();
//		}
		
		return bookActivity.getAllBooksByOwnerID(ownerID);
	}
	
	@Override
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/")
	@LocalPreflight
	//, @QueryParam("partnerUserName") String partnerUserName
	public BookRepresentation createBook(BookRequest bookRequest) {
		System.out.println("POST METHOD Request from Client with ............." + bookRequest.getProductName() +" " + bookRequest.getProductPrice() + " " + bookRequest.getProductReview() + " " + bookRequest.getProductOwner() + " " + bookRequest.getProductID() + " " + bookRequest.getIsbn()
		 + " " + bookRequest.getAuthor() + " " + bookRequest.getCategory());
		BookActivity bookActivity = new BookActivity();
		return bookActivity.createBook(bookRequest.getProductName(), bookRequest.getProductPrice(), bookRequest.getProductReview(), 
				bookRequest.getProductOwner(), bookRequest.getProductID(), bookRequest.getIsbn(), bookRequest.getAuthor(), bookRequest.getCategory());
	}
	
	@OPTIONS
	@LocalPreflight
	@Path("/submit_review/review/{bookname}")
	public Response reviewSetOptions(@PathParam("bookName") String bookName, @QueryParam("review") String bookReview) {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"http://localhost:63342")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/submit_review/review/{bookName}")
	@LocalPreflight
	public Response reviewBook(@PathParam("bookName") String bookName, @QueryParam("review") String bookReview) {
		System.out.println("UPDATE review method");
		BookActivity bookActivity = new BookActivity();
		String res = bookActivity.updateBookReview(bookName, bookReview);
		if (res.equals("Updated")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@Override
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{bookId}")
	@LocalPreflight
	public Response deleteBook(@PathParam("bookId") int id) {
		System.out.println("Delete METHOD Request from Client with bookRequest String ............." + id);
		BookActivity bookActivity = new BookActivity();
		String res = bookActivity.deleteBook(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}


}