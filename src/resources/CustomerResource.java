package resources;


import representations.CustomerRepresentation;
import representations.CustomerRequest;
import activity.CustomerActivity;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.CacheControl;


@Path("/")
public class CustomerResource implements CustomerService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<CustomerRepresentation> getCustomers() {
		System.out.println("GET METHOD Request for all customers .............");
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomer();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{userId}")
	public CustomerRepresentation getCustomer(@PathParam("customerID") String id) {
		System.out.println("GET METHOD Request from Client with customerRequest String ............." + id);
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomer(id);
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	public CustomerRepresentation createCustomer(CustomerRequest  customerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName()
				 + "  " +  customerRequest.getUserID()  + "  " + customerRequest.getCompanyName()  + "  " + customerRequest.getAddress()  + "  " + customerRequest.getPhoneNumber()
				 + "  " + customerRequest.getEmail()  + "  " + customerRequest.getNumberOfOrders() + "  " + customerRequest.getCreditCardNumber());
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.createCustomer(customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getUserID(), customerRequest.getCompanyName(), 
				customerRequest.getAddress(), customerRequest.getPhoneNumber(), customerRequest.getEmail(), customerRequest.getNumberOfOrders(), customerRequest.getCreditCardNumber(),
				customerRequest.getPassword());
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{userId}")
	public Response deleteCustomer(@PathParam("userId") String id){
		System.out.println("Delete METHOD Request from Client with customerRequest String ............." + id);
		CustomerActivity customerActivity = new CustomerActivity();
		String res = customerActivity.deleteCustomer(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
}
