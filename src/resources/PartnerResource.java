package resources;


import representations.PartnerRepresentation;
import representations.PartnerRequest;
import activity.PartnerActivity;

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
public class PartnerResource implements PartnerService{

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<PartnerRepresentation> getPartners() {
		System.out.println("GET METHOD Request for all Partners .............");
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartner();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/partnerId/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") String id) {
		System.out.println("GET METHOD Request from Client with PartnerRequest String ............." + id);
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartner(id);
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/")
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName()
				 + "  " +  partnerRequest.getUserID()  + "  " + partnerRequest.getCompanyName()  + "  " + partnerRequest.getAddress()  + "  " + partnerRequest.getPhoneNumber()
				 + "  " + partnerRequest.getEmail()  + "  " + partnerRequest.getNumberOfOrders() + "  " + partnerRequest.getBankAccountNumber());
		PartnerActivity PartnerActivity = new PartnerActivity();
		return PartnerActivity.createPartner(partnerRequest.getFirstName(), partnerRequest.getLastName(), partnerRequest.getCompanyName(), 
				partnerRequest.getAddress(), partnerRequest.getPhoneNumber(), partnerRequest.getEmail(), partnerRequest.getNumberOfOrders(), 
				partnerRequest.getUserID(), partnerRequest.getBankAccountNumber());
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{userId}")
	public Response deletePartner(@PathParam("userId") String id){
		System.out.println("Delete METHOD Request from Client with PartnerRequest String ............." + id);
		PartnerActivity partnerActivity = new PartnerActivity();
		String res = partnerActivity.deletePartner(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
}
