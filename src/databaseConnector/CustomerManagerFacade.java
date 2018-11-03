package databaseConnector;

import java.util.List;

import serviceUsers.Customer;

public class CustomerManagerFacade {

	public CustomerManagerFacade() {}
	
	public List<Customer> getCustomer(){
		return CustomerDAO.get();
	}
	public Customer postCustomer(String firstName, String lastName, String userID, String companyName, String address,
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber, String password) {
		Customer customer = new Customer(firstName, lastName, userID, companyName, address,
			 phoneNumber, email, numberOfOrders, creditCardNumber, password);
		CustomerDAO.post(customer);
		
		return customer;
	}
	public Customer updateCustomer(String firstName, String lastName, String userID, String companyName, String address,
			int phoneNumber, String email, int numberOfOrders, int creditCardNumber, String password) {
		Customer customer = new Customer(firstName, lastName, userID, companyName, address,
				 phoneNumber, email, numberOfOrders, creditCardNumber, password);
		CustomerDAO.put(customer);
		
		return customer;
	}
	public void deleteCustomer(String userID) {
		CustomerDAO.deleteIDMatch(userID);
	}
}
