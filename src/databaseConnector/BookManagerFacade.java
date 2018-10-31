package databaseConnector;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import order.Order;
import products.Book;
import serviceUsers.Customer;
import serviceUsers.Partner;

public class BookManagerFacade {

	public BookManagerFacade(){}
	
/////////////////////////////// BOOK //////////////////////////////
	public List<Book> getBooks(){
		return BookDAO.get();
	}
	public List<Book> getSpecificBook(String name){
		List<Book> resultList = new ArrayList<Book>();
		if(checkProductAvailability(name)) {
		for(Book targetBook: BookDAO.get()) {
			if(targetBook.getProductName() == name) {
				resultList.add(targetBook);
			}
		}
		}
		return resultList;
	}
	public Book getOneBook(String bookName) {
		if(checkProductAvailability(bookName)) {
		for(Book book: BookDAO.get()) {
			if(book.getProductName() == bookName) {
				return book;
			}
		}
		}
		return null;
	}
	
	public boolean checkProductAvailability(String productName) {
		boolean result = false;
		for(Book targetBook: BookDAO.get()) {
			if(targetBook.getProductName() == productName) {
				result = true;
			}
		}
		return result;
	}
	
	public Book postBook(String productName, double productPrice, String productReview, 
			String productOwner, int productID, int isbn, String author, String category) {
		Book book = new Book(productName, productPrice, productReview, 
			productOwner, productID, isbn, author, category);
		BookDAO.post(book);
		
		return book;
	}
	public Book updateBook(String productName, double productPrice, String productReview, 
			String productOwner, int productID, int isbn, String author, String category) {
		Book book = new Book(productName, productPrice, productReview, 
			productOwner, productID, isbn, author, category);
		BookDAO.put(book);
		
		return book;
	}
	public void deleteBook(int productID) {
		BookDAO.deleteIDMatch(productID);
	}
}
