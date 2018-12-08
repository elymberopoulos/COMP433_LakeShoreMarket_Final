package activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import products.Book;

import databaseConnector.BookManagerFacade;
import errorHandling.DataNotFound;
import errorHandling.ErrorMessage;
import link.Link;
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
		if(books == null) {
			throw new DataNotFound("RESOURCE 404 " + books + " not found");
		}
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
          
          setLinksGetAllBooks(bookRepresentation);
          
          
        }
		return bookRepresentations;
	}
	private void setLinksGetAllBooks(BookRepresentation bookRep) {
		Link bookIdLink = new Link("Get_Book", "http://localhost:8081/book/" + bookRep.getProductName()); //link to this book
		Link registerCustomerLink = new Link("Register_Customer", "http://localhost:8081/customer/"); //customer register link // must register to purchase
		Link registerPartnerLink = new Link("Register_Partner", "http://localhost:8081/partner/"); //partner register link // must register to post books to be purchased


		
		bookRep.setLinks(bookIdLink, registerCustomerLink, registerPartnerLink);
	}

	
	public BookRepresentation getOneBook(String name, String customerID) { //Checks availability and returns representation if available
		BookRepresentation bookRepresentation = new BookRepresentation();
			Book book = managerfacade.getOneBook(name);
			if(book == null) {
				throw new DataNotFound("RESOURCE 404 " + book + " not found");
			}
			bookRepresentation.setProductName(book.getProductName());
	        bookRepresentation.setProductPrice(book.getProductPrice());
	        bookRepresentation.setProductReview(book.getProductReview());
	        bookRepresentation.setProductOwner(book.getProductOwner());
	        bookRepresentation.setProductID(book.getProductID());
	        bookRepresentation.setIsbn(book.getIsbn());                 
	        bookRepresentation.setAuthor(book.getAuthor());
	        bookRepresentation.setCategory(book.getCategory());
	        setLinksGetOneBook(bookRepresentation, customerID);
		

        return bookRepresentation;	
	}
	private void setLinksGetOneBook(BookRepresentation bookRep, String customerID) {
		Link orderRootLink = new Link("Purchase", "http://localhost:8081/order?customerId=" + customerID); //POST //QUERY change product owner to customer	(PURCHASE)																						//this books owner is set to current customerID from session(BUYING)
		Link bookReviewLink = new Link("Review_Book", "http://localhost:8081/book/submit_review/review/" + bookRep.getProductName() + "?review=");//POST a review to book with specific name
		//Link bookStore = new Link("List", "http://localhost:8081/book/"); //GET all books
		bookRep.setLinks(orderRootLink, bookReviewLink);
	}
	
	public List<BookRepresentation> getAllBooksByOrderID(int orderID) {//GETS ALL BOOKS WITH SPECIFIC ORDER_ID
	
		List<Book> books = new ArrayList<Book>();
		List<BookRepresentation> bookRepresentations = new ArrayList<BookRepresentation>();
		books = managerfacade.getBooksByOrderID(orderID);
		if(books == null) {
			throw new DataNotFound("RESOURCE 404 " + books + " not found");
		}
		
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
          
          setLinksGetAllBooksByOrderID(bookRepresentation);
          
          
        }
		return bookRepresentations;
	}
	private void setLinksGetAllBooksByOrderID(BookRepresentation bookRep) {
		Link bookIdLink = new Link("Get_Book", "http://localhost:8081/book/" + bookRep.getProductName()); //links to book in returned order
		//Link customerRootLink = new Link("List", "http://localhost:8081/customer/" + customerID); //links back to customer profile after checking order
		//Link partnerRootLink = new Link("List", "http://localhost:8081/partner/"); NOT NECESSARY

		bookRep.setLinks(bookIdLink);
	}
	
	public List<BookRepresentation> getAllBooksByOwnerID(String ownerID) {//GETS ALL BOOKS WITH SPECIFIC OWNER_ID
		
		List<Book> books = new ArrayList<Book>();
		List<BookRepresentation> bookRepresentations = new ArrayList<BookRepresentation>();
		books = managerfacade.getBooksByOwnerID(ownerID);
		if(books == null) {
			throw new DataNotFound("RESOURCE 404 " + books + " not found");
		}
		
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
          setLinksGetAllBooksByOwnerID(bookRepresentation, ownerID);
          
        }
		return bookRepresentations;
	}
	private void setLinksGetAllBooksByOwnerID(BookRepresentation bookRep, String customerID) {
		Link bookIdLink = new Link("Get_Book", "http://localhost:8081/book/" + bookRep.getProductName()); //links to book in returned order
		Link ownerProfileLink = new Link("Owner_Profile", "http://localhost:8081/customer/" + customerID); //Not necessary? links back to customer profile after checking order
		//Link partnerRootLink = new Link("List", "http://localhost:8081/partner/"); NOT NECESSARY

		bookRep.setLinks(bookIdLink, ownerProfileLink);
	}
	
	
	public String updateBookReview(String bookName, String bookReview) {
		BookManagerFacade.updateBookReview(bookName, bookReview);
		return "Updated";
	}
	
	public BookRepresentation createBook (String productName, double productPrice, String productReview, 
			String productOwner, int productID, int isbn, String author, String category) throws ErrorMessage{		
	    Book book = managerfacade.postBook(productName, productPrice, productReview, productOwner, productID, isbn, author, category);
		if(book == null) {
			throw new ErrorMessage();
		}
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
        
        setLinksCreateBook(bookRepresentation);
		return bookRepresentation;
	}
	//STATE MACHINE DEAD END. BACK TO PROFILE PAGE
	private void setLinksCreateBook(BookRepresentation bookRep) { 
		Link partnerIdLink = new Link("Create_Book", "http://localhost:8081/partner/" + bookRep.getProductOwner());//back to user profile
		//Link bookStore = new Link("List", "http://localhost:8081/book/"); //GET all books
		bookRep.setLinks(partnerIdLink);
	}
	
	public String deleteBook(int id) {
		
		managerfacade.deleteBook(id);

		return "OK";
	}

}
