package activity;

import representations.BookRepresentation;
import representations.CustomerRepresentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import serviceUsers.Customer;
import databaseConnector.BookManagerFacade;
import databaseConnector.CustomerManagerFacade;
import errorHandling.DataNotFound;
import errorHandling.ErrorMessage;
import link.Link;


public class CustomerActivity {
	private static CustomerManagerFacade manager = new CustomerManagerFacade();
	
	public Set<CustomerRepresentation> getCustomers() {
		
		List<Customer> customers = new ArrayList<Customer>();
		Set<CustomerRepresentation> customerRepresentations = new HashSet<CustomerRepresentation>();
		customers = manager.getCustomer();
		if(customers == null) {
			throw new DataNotFound("RESOURCE 404 " + customers + " not found");
		}
		
		Iterator<Customer> it = customers.iterator();
		while(it.hasNext()) {
          Customer customer = (Customer)it.next();
          CustomerRepresentation customerRepresentation = new CustomerRepresentation();
			customerRepresentation.setFirstName(customer.getFirstName());
			customerRepresentation.setLastName(customer.getLastName());
			customerRepresentation.setUserID(customer.getUserID());
			customerRepresentation.setCompanyName(customer.getCompanyName());
			customerRepresentation.setAddress(customer.getAddress());
			customerRepresentation.setPhoneNumber(customer.getPhoneNumber());
			customerRepresentation.setEmail(customer.getEmail());
			customerRepresentation.setNumberOfOrders(customer.getNumberOfOrders());
			customerRepresentation.setCreditCardNumber(customer.getCreditCardNumber());
			customerRepresentation.setPassword(customer.getPassword());
			setLinksGetAllCustomers(customerRepresentation);
          customerRepresentations.add(customerRepresentation);
        }
		return customerRepresentations;
	}
	private void setLinksGetAllCustomers(CustomerRepresentation customerRep) {
		Link customerIdLink = new Link("Get_Customer", "http://localhost:8081/customer/" + customerRep.getUserID());


		
		customerRep.setLinks(customerIdLink);
	}
	
	public CustomerRepresentation getCustomer(String id) {
		CustomerRepresentation customerRepresentation = new CustomerRepresentation();
		Customer customer = manager.getMatchingCustomer(id);
		if(customer == null) {
			throw new DataNotFound("RESOURCE 404 " + customer + " not found");
		}
			customerRepresentation.setFirstName(customer.getFirstName());
			customerRepresentation.setLastName(customer.getLastName());
			customerRepresentation.setUserID(customer.getUserID());
			customerRepresentation.setCompanyName(customer.getCompanyName());
			customerRepresentation.setAddress(customer.getAddress());
			customerRepresentation.setPhoneNumber(customer.getPhoneNumber());
			customerRepresentation.setEmail(customer.getEmail());
			customerRepresentation.setNumberOfOrders(customer.getNumberOfOrders());
			customerRepresentation.setCreditCardNumber(customer.getCreditCardNumber());
			customerRepresentation.setPassword(customer.getPassword());

			setLinksGetCustomer(customerRepresentation);
			return customerRepresentation;
	}
	private void setLinksGetCustomer(CustomerRepresentation customerRep) {
		Link customerRoot = new Link("Customer_Directory", "http://localhost:8081/customer/"); //GET root directory of users
		Link customerIdLink = new Link("Update-Delete", "http://localhost:8081/customer/" + customerRep.getUserID()); //DELETE/UPDATE OPTION


		customerRep.setLinks(customerRoot, customerIdLink);
	}

	
	public CustomerRepresentation createCustomer(String firstName, String lastName, String userID, String companyName, String address, 
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber, String password) {
		
		Customer customer = manager.postCustomer(firstName, lastName, userID, companyName, address, phoneNumber, email, numberOfOrders, creditCardNumber, password);
		if(customer == null) {
			throw new DataNotFound("RESOURCE 404 " + customer + " not found");
		}
		CustomerRepresentation customerRepresentation = new CustomerRepresentation();
		customerRepresentation.setFirstName(customer.getFirstName());
		customerRepresentation.setLastName(customer.getLastName());
		customerRepresentation.setUserID(customer.getUserID());
		customerRepresentation.setCompanyName(customer.getCompanyName());
		customerRepresentation.setAddress(customer.getAddress());
		customerRepresentation.setPhoneNumber(customer.getPhoneNumber());
		customerRepresentation.setEmail(customer.getEmail());
		customerRepresentation.setNumberOfOrders(customer.getNumberOfOrders());
		customerRepresentation.setCreditCardNumber(customer.getCreditCardNumber());
		customerRepresentation.setPassword(customer.getPassword());
		setLinksCreateCustomer(customerRepresentation);
		
		
		return customerRepresentation;
	}
	private void setLinksCreateCustomer(CustomerRepresentation customerRep) {
		Link bookStoreLink = new Link("Book_Store", "http://localhost:8081/book/"); //GET bookStore after account creation
		Link customerID = new Link("Created_Profile", "http://localhost:8081/customer/" + customerRep.getUserID()); //GET view created profile

		customerRep.setLinks(bookStoreLink, customerID);
	}
	public CustomerRepresentation updateCustomer(String firstName, String lastName, String userID, String companyName, String address, 
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber, String password) throws ErrorMessage {
		
		Customer customer = manager.updateCustomer(firstName, lastName, userID, companyName, address, phoneNumber, email, numberOfOrders, creditCardNumber, password);
		if(customer == null) {
			throw new ErrorMessage();
		}
		CustomerRepresentation customerRepresentation = new CustomerRepresentation();
		customerRepresentation.setFirstName(customer.getFirstName());
		customerRepresentation.setLastName(customer.getLastName());
		customerRepresentation.setUserID(customer.getUserID());
		customerRepresentation.setCompanyName(customer.getCompanyName());
		customerRepresentation.setAddress(customer.getAddress());
		customerRepresentation.setPhoneNumber(customer.getPhoneNumber());
		customerRepresentation.setEmail(customer.getEmail());
		customerRepresentation.setNumberOfOrders(customer.getNumberOfOrders());
		customerRepresentation.setCreditCardNumber(customer.getCreditCardNumber());
		customerRepresentation.setPassword(customer.getPassword());
		setLinksUpdateCustomer(customerRepresentation);
		
		
		return customerRepresentation;
	}
	
	private void setLinksUpdateCustomer(CustomerRepresentation customerRep) {
		Link bookStoreLink = new Link("Book_Store", "http://localhost:8081/book/"); //GET bookStore after account creation
		Link customerRoot = new Link("Updated_Profile", "http://localhost:8081/customer/" + customerRep.getUserID()); //GET updated profile

		customerRep.setLinks(bookStoreLink, customerRoot);
	}
	
	public String deleteCustomer(String id) {
		
		manager.deleteCustomer(id);
		
		return "OK";
	}


}
