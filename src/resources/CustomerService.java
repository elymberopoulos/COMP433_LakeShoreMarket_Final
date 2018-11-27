package resources;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import representations.CustomerRepresentation;
import representations.CustomerRequest;

@WebService
public interface CustomerService {
	public Set<CustomerRepresentation> getCustomers();
	public CustomerRepresentation getCustomer(String id);
	public CustomerRepresentation createCustomer(CustomerRequest  customerRequest);
	public CustomerRepresentation updateCustomer(CustomerRequest  customerRequest, String id);
	public Response deleteCustomer(String id);
}
