package resources;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import representations.BookRepresentation;
import representations.BookRequest;

@WebService
public interface BookService {
	   
	public Set<BookRepresentation> getAllBooks();
	public BookRepresentation getBookMatchingName(String bookId);
	public List<BookRepresentation> getBookByOrderID(int orderID);
	public BookRepresentation createBook(BookRequest bookRequest);
	public Response deleteBook(int id);
   
    //public Response updateBook(BookRequest bookRequest);
    //public Response deleteBook(String bookId);
	
	

}