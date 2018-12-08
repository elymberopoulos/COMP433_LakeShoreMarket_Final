package resources;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import errorHandling.ErrorMessage;
import representations.BookRepresentation;
import representations.BookRequest;

@WebService
public interface BookService {
	   
	public Set<BookRepresentation> getAllBooks();
	public BookRepresentation getBookMatchingName(String bookId, String customerID);
	public List<BookRepresentation> getBookByOrderID(int orderID);
	public List<BookRepresentation> getBookByOwnerID(String ownerID);
	public Response reviewBook(String bookName, String bookReview);
	public BookRepresentation createBook(BookRequest bookRequest, String partnerUserName) throws ErrorMessage;
	public Response deleteBook(int id);
   
    //public Response updateBook(BookRequest bookRequest);
    //public Response deleteBook(String bookId);
	
	

}