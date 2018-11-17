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
		//employees = dao.getAllEmployees();
		customers = manager.getCustomer();
		
		Iterator<Customer> it = customers.iterator();
		while(it.hasNext()) {
          Customer targetCustomer = (Customer)it.next();
          CustomerRepresentation customerRepresentation = new CustomerRepresentation();
          customerRepresentation.setUserID(targetCustomer.getUserID());
          customerRepresentation.setFirstName(targetCustomer.getFirstName());
          customerRepresentation.setLastName(targetCustomer.getLastName());
          setLinksGetAllCustomers(customerRepresentation, customerRepresentation.getUserID());
          //now add this representation in the list
          customerRepresentations.add(customerRepresentation);
        }
		return customerRepresentations;
	}
	private void setLinksGetAllCustomers(CustomerRepresentation customerRep, String customerUserID) {
		// Set up the activities that can be performed on orders
		Link entryPoint = new Link("List", "http://localhost:8081/book/");
		Link customerIdLink = new Link("List", "http://localhost:8081/customer/" + customerUserID);


		
		customerRep.setLinks(entryPoint, customerIdLink);
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
			setLinksGetCustomer(customerRepresentation);
			return customerRepresentation;
	}
	private void setLinksGetCustomer(CustomerRepresentation customerRep) {
		// Set up the activities that can be performed on orders
		Link entryPoint = new Link("List", "http://localhost:8081/book/");
		Link customerRoot = new Link("List", "http://localhost:8081/customer/");

		customerRep.setLinks(entryPoint, customerRoot);
	}
	/*
	public CustomerRepresentation getSpecificCustomer(String id) {
        CustomerRepresentation customerRepresentation = new CustomerRepresentation();

		//Employee emp = dao.getEmployee(id);
		List<Customer> customer = manager.getCustomer();
		for(Customer targetCustomer: customer){
			if(id == targetCustomer.getUserID()){
		          customerRepresentation.setUserID(targetCustomer.getUserID());
		          customerRepresentation.setFirstName(targetCustomer.getFirstName());
		          customerRepresentation.setLastName(targetCustomer.getLastName());
								
			}
		}
		return customerRepresentation;
		
	}
	*/
	
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
		// Set up the activities that can be performed on orders
		Link entryPoint = new Link("List", "http://localhost:8081/book/"); // return to entry point to search for products now.
		Link customerRoot = new Link("List", "http://localhost:8081/customer/");

		customerRep.setLinks(entryPoint, customerRoot);
	}
	
	public String deleteCustomer(String id) {
		
		manager.deleteCustomer(id);
		
		return "OK";
	}


}
