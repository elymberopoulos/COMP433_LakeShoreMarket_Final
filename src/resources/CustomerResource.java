package resources;


import representations.CustomerRepresentation;
import representations.CustomerRequest;
import activity.CustomerActivity;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@CrossOriginResourceSharing(
		allowAllOrigins = true,
		allowCredentials = true,
		allowHeaders = {
				"'Accept': 'application/json'",
				"'Content-Type': 'application/json'"
		})

@Path("/")
public class CustomerResource implements CustomerService{
	
	@Context
	private HttpHeaders header;
	
	@OPTIONS
	@LocalPreflight
	@Path("/")
	public Response options() {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	//Retrieves all customers
	public Set<CustomerRepresentation> getCustomers() {
		System.out.println("GET METHOD Request for all customers .............");
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomers();	
	}
	
	
	@OPTIONS
	@LocalPreflight
	@Path("/{customerId}")
	public Response idOptions(@PathParam("customerId") String customerID	) {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{customerId}")
	//gets a customer by their specific userID
	public CustomerRepresentation getCustomer(@PathParam("customerId") String customerID) {
		System.out.println("GET METHOD Request from Client with customerRequest String ............." + customerID);
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.getCustomer(customerID);
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/")
	//Creates a new customer at the root URI
	public CustomerRepresentation createCustomer(CustomerRequest  customerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName()
				 + "  " +  customerRequest.getUserID()  + "  " + customerRequest.getCompanyName()  + "  " + customerRequest.getAddress()  + "  " + customerRequest.getPhoneNumber()
				 + "  " + customerRequest.getEmail()  + "  " + customerRequest.getNumberOfOrders() + "  " + customerRequest.getCreditCardNumber());
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.createCustomer(customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getUserID(), customerRequest.getCompanyName(), 
				customerRequest.getAddress(), customerRequest.getPhoneNumber(), customerRequest.getEmail(), customerRequest.getNumberOfOrders(), customerRequest.getCreditCardNumber(),
				customerRequest.getPassword());
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{customerId}")
	//This method takes a customer request like createCustomer method but this one will update a user in the DB with a specific customerID
	public CustomerRepresentation updateCustomer(CustomerRequest  customerRequest, @PathParam("customerId") String customerID) {
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName()
				 + "  " +  customerRequest.getUserID()  + "  " + customerRequest.getCompanyName()  + "  " + customerRequest.getAddress()  + "  " + customerRequest.getPhoneNumber()
				 + "  " + customerRequest.getEmail()  + "  " + customerRequest.getNumberOfOrders() + "  " + customerRequest.getCreditCardNumber());
		CustomerActivity customerActivity = new CustomerActivity();
		return customerActivity.updateCustomer(customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getUserID(), customerRequest.getCompanyName(), 
				customerRequest.getAddress(), customerRequest.getPhoneNumber(), customerRequest.getEmail(), customerRequest.getNumberOfOrders(), customerRequest.getCreditCardNumber(),
				customerRequest.getPassword());
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{customerId}")
	//Deletes a customer by customerID
	public Response deleteCustomer(@PathParam("customerId") String id){
		System.out.println("Delete METHOD Request from Client with customerRequest String ............." + id);
		CustomerActivity customerActivity = new CustomerActivity();
		String res = customerActivity.deleteCustomer(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
}
