package activity;

import representations.CustomerRepresentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import serviceUsers.Customer;
import databaseConnector.BookManagerFacade;
import databaseConnector.CustomerManagerFacade;


public class CustomerActivity {
	private static CustomerManagerFacade manager = new CustomerManagerFacade();
	
	public Set<CustomerRepresentation> getCustomer() {
		
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
          
          //now add this representation in the list
          customerRepresentations.add(customerRepresentation);
        }
		return customerRepresentations;
	}
	
	public CustomerRepresentation getCustomer(String id) {
		CustomerRepresentation customerRepresentation = new CustomerRepresentation();
		for(Customer customer: manager.getCustomer()){
			if(customer.getUserID() == id){
				customerRepresentation.setFirstName(customer.getFirstName());
				customerRepresentation.setLastName(customer.getLastName());
				customerRepresentation.setUserID(customer.getUserID());
				customerRepresentation.setCompanyName(customer.getCompanyName());
				customerRepresentation.setAddress(customer.getAddress());
				customerRepresentation.setPhoneNumber(customer.getPhoneNumber());
				customerRepresentation.setEmail(customer.getEmail());
				customerRepresentation.setNumberOfOrders(customer.getNumberOfOrders());
				customerRepresentation.setCreditCardNumber(customer.getCreditCardNumber());
				return customerRepresentation;

			}
		}
		return null;
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
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber) {
		
		Customer customer = manager.postCustomer(firstName, lastName, userID, companyName, address, phoneNumber, email, numberOfOrders, creditCardNumber);
		
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

		
		
		return customerRepresentation;
	}
	
	public String deleteCustomer(String id) {
		
		manager.deleteCustomer(id);
		
		return "OK";
	}


}
