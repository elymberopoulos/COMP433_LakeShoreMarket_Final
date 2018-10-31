package resources;

import java.text.ParseException;
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

import representations.OrderRepresentation;
import representations.OrderRequest;
import activity.OrderActivity;

@Path("/")
public class OrderResource implements OrderService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<OrderRepresentation> getAllOrders() throws ParseException {
		System.out.println("GET METHOD Request for all Orders .............");
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.getOrders();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderId}")
	public OrderRepresentation getOrder(@PathParam("orderId") int id) throws ParseException {
		System.out.println("GET METHOD Request from Client with OrderRequest String ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		return orderActivity.getSpecificOrder(id);
		}

//	@GET
//	@Produces({"application/xml" , "application/json"})
//	@Path("/{orderId}")
//	public String refundOrder(@PathParam("orderId") int id) throws ParseException {
//		System.out.println("REFUND ORDER REQUEST." + id);
//		OrderActivity orderActivity = new OrderActivity();
//		return orderActivity.requestRefund(id);
//		}
	
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	public OrderRepresentation createOrder (OrderRequest  orderRequest) {
		System.out.println("POST METHOD Request from Client with ............." + orderRequest.getOrderID() + "  " + orderRequest.getSqlDate()
		+ " " + orderRequest.getSqlExpectedShippingDate() + " " + orderRequest.isShipped());		
		OrderActivity orderActivity = new OrderActivity(); 
		return orderActivity.createOrder(orderRequest);
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{orderId}")
	public Response deleteOrder(@PathParam("orderId") int id) {
		System.out.println("Delete METHOD Request from Client with OrderRequest String ............." + id);
		OrderActivity orderActivity = new OrderActivity();
		String res = orderActivity.deleteOrder(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
}

/////