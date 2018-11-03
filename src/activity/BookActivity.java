package activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import products.Book;

import databaseConnector.BookManagerFacade;

import representations.BookRepresentation;

/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 * using the BookDOA object.  
 *
 */
public class BookActivity {

	private static BookManagerFacade managerfacade = new BookManagerFacade();
	
	public Set<BookRepresentation> getAllBooks() {//GETS ALL BOOKS IN STORE
		
		List<Book> books = new ArrayList<Book>();
		Set<BookRepresentation> bookRepresentations = new HashSet<BookRepresentation>();
		//books = dao.getAllBooks();
		books = managerfacade.getBooks();
		
		Iterator<Book> it = books.iterator();
		while(it.hasNext()) {
          Book book = (Book)it.next();
          BookRepresentation bookRepresentation = new BookRepresentation();
          bookRepresentation.setProductName(book.getProductName());
          bookRepresentation.setProductPrice(book.getProductPrice());
          bookRepresentation.setProductReview(book.getProductReview());
          bookRepresentation.setProductOwner(book.getProductOwner());
          bookRepresentation.setProductID(book.getProductID());
          bookRepresentation.setIsbn(book.getIsbn());                 
          bookRepresentation.setAuthor(book.getAuthor());
          bookRepresentation.setCategory(book.getCategory());
          bookRepresentation.setOrderID(book.getOrderID());
          //now add this representation in the list
          bookRepresentations.add(bookRepresentation);
        }
		return bookRepresentations;
	}
	
	public List<BookRepresentation> getBooks(String bookName) {//GETS ALL BOOKS WITH MATCHING NAMES
		
		List<Book> matchingBooks = managerfacade.getSpecificBook(bookName);
		List<BookRepresentation> returnedRepresentations = new ArrayList<BookRepresentation>();
		
		for(Book book: matchingBooks) {
		BookRepresentation bookRepresentation = new BookRepresentation();
			bookRepresentation.setProductName(book.getProductName());
	        bookRepresentation.setProductPrice(book.getProductPrice());
	        bookRepresentation.setProductReview(book.getProductReview());
	        bookRepresentation.setProductOwner(book.getProductOwner());
	        bookRepresentation.setProductID(book.getProductID());
	        bookRepresentation.setIsbn(book.getIsbn());                 
	        bookRepresentation.setAuthor(book.getAuthor());
	        bookRepresentation.setCategory(book.getCategory());
	        //bookRepresentation.setOrderID(book.getOrderID());
	        returnedRepresentations.add(bookRepresentation);
		}
		return returnedRepresentations;
	}
	public BookRepresentation getOneBook(String name) {//GETS ONE BOOK WITH MATCHING NAME
		
		Book book = managerfacade.getOneBook(name);
		BookRepresentation bookRepresentation = new BookRepresentation();
		
		bookRepresentation.setProductName(book.getProductName());
        bookRepresentation.setProductPrice(book.getProductPrice());
        bookRepresentation.setProductReview(book.getProductReview());
        bookRepresentation.setProductOwner(book.getProductOwner());
        bookRepresentation.setProductID(book.getProductID());
        bookRepresentation.setIsbn(book.getIsbn());                 
        bookRepresentation.setAuthor(book.getAuthor());
        bookRepresentation.setCategory(book.getCategory());
        
        return bookRepresentation;
	}
	
	public BookRepresentation checkBookAvailability(String id) {
		BookRepresentation bookRepresentation = new BookRepresentation();
		if(managerfacade.checkProductAvailability(id)) {
			Book book = managerfacade.getOneBook(id);
			bookRepresentation.setProductName(book.getProductName() + " (IS AVAILABLE)");
	        bookRepresentation.setProductPrice(book.getProductPrice());
	        bookRepresentation.setProductReview(book.getProductReview());
	        bookRepresentation.setProductOwner(book.getProductOwner());
	        bookRepresentation.setProductID(book.getProductID());
	        bookRepresentation.setIsbn(book.getIsbn());                 
	        bookRepresentation.setAuthor(book.getAuthor());
	        bookRepresentation.setCategory(book.getCategory());
		}
		else {
			bookRepresentation.setProductName(id + " (IS NOT AVAILABLE)");
		}
        return bookRepresentation;
		
	}
	
	public BookRepresentation createBook (String productName, double productPrice, String productReview, 
			String productOwner, int productID, int isbn, String author, String category){		
	    Book book = managerfacade.postBook(productName, productPrice, productReview, productOwner, productID, isbn, author, category);
		
		BookRepresentation bookRepresentation = new BookRepresentation();
		bookRepresentation.setProductName(book.getProductName());
        bookRepresentation.setProductPrice(book.getProductPrice());
        bookRepresentation.setProductReview(book.getProductReview());
        bookRepresentation.setProductOwner(book.getProductOwner());
        bookRepresentation.setProductID(book.getProductID());
        bookRepresentation.setIsbn(book.getIsbn());                 
        bookRepresentation.setAuthor(book.getAuthor());
        bookRepresentation.setCategory(book.getCategory());
        //bookRepresentation.setOrderID(book.getOrderID());
		return bookRepresentation;
	}
	
	public String deleteBook(int id) {
		
		//dao.deleteBook(id);
		managerfacade.deleteBook(id);
		
		return "OK";
	}
	
}
