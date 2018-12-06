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
import link.Link;


public class CustomerActivity {
	private static CustomerManagerFacade manager = new CustomerManagerFacade();
	
	public Set<CustomerRepresentation> getCustomers() {
		
		List<Customer> customers = new ArrayList<Customer>();
		Set<CustomerRepresentation> customerRepresentations = new HashSet<CustomerRepresentation>();
		customers = manager.getCustomer();
		
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
		Link customerIdLink = new Link("List", "http://localhost:8081/customer/" + customerRep.getUserID());


		
		customerRep.setLinks(customerIdLink);
	}
	
	public CustomerRepresentation getCustomer(String id) {
		CustomerRepresentation customerRepresentation = new CustomerRepresentation();
		Customer customer = manager.getMatchingCustomer(id);
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
		// Set up the activities that can be performed on orders
		Link customerRoot = new Link("List", "http://localhost:8081/customer/"); //GET root directory of users
		Link customerIdLink = new Link("List", "http://localhost:8081/customer/" + customerRep.getUserID()); //DELETE/UPDATE OPTION
		//Link orderIdLink = new Link("List", "http://localhost:8081/inventory/" + customerRep.getUserID()); //DELETE/UPDATE OPTION


		customerRep.setLinks(customerRoot, customerIdLink);
	}

	
	public CustomerRepresentation createCustomer(String firstName, String lastName, String userID, String companyName, String address, 
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber, String password) {
		
		Customer customer = manager.postCustomer(firstName, lastName, userID, companyName, address, phoneNumber, email, numberOfOrders, creditCardNumber, password);
		
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
		//Link entryPoint = new Link("List", "http://localhost:8081/book/"); // after creating user, link to the bookstore
		Link customerRoot = new Link("List", "http://localhost:8081/customer/" + customerRep.getUserID()); //GET view created profile

		customerRep.setLinks(customerRoot);
	}
	public CustomerRepresentation updateCustomer(String firstName, String lastName, String userID, String companyName, String address, 
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber, String password) {
		
		Customer customer = manager.updateCustomer(firstName, lastName, userID, companyName, address, phoneNumber, email, numberOfOrders, creditCardNumber, password);
		
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
		//Link entryPoint = new Link("List", "http://localhost:8081/book/"); // after creating user, link to the bookstore (NO. User not created yet).
		Link customerRoot = new Link("List", "http://localhost:8081/customer/" + customerRep.getUserID()); //GET updated profile

		customerRep.setLinks(customerRoot);
	}
	
	public String deleteCustomer(String id) {
		
		manager.deleteCustomer(id);
		
		return "OK";
	}


}
