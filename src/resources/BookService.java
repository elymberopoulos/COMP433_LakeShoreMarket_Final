package resources;

import java.util.Set;

import javax.jws.WebService;

import representations.BookRepresentation;
import representations.BookRequest;

@WebService
public interface BookService {
	   
	public Set<BookRepresentation> getBooks();
	public BookRepresentation getBook(String bookId);
	public boolean createBook(BookRequest bookRequest);
   
    //public Response updateBook(BookRequest bookRequest);
    //public Response deleteBook(String bookId);
	
	

}