package resources;

import java.text.ParseException;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import errorHandling.ErrorMessage;
import representations.OrderRepresentation;
import representations.OrderRequest;

@WebService
public interface OrderService {
	public Set<OrderRepresentation> getAllOrders() throws ParseException;
	public OrderRepresentation getOrder(int id) throws ParseException;
	public OrderRepresentation createOrder (OrderRequest  orderRequest) throws ErrorMessage;
	public Response deleteOrder(int id);
}
