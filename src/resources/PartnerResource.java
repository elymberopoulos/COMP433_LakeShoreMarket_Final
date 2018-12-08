package resources;


import representations.PartnerRepresentation;
import representations.PartnerRequest;
import activity.PartnerActivity;
import errorHandling.ErrorMessage;

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
public class PartnerResource implements PartnerService{
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
	@LocalPreflight
	//This retrieves all the partners in the database
	public Set<PartnerRepresentation> getPartners() {
		System.out.println("GET METHOD Request for all Partners .............");
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartner();	
	}
	
	@OPTIONS
	@LocalPreflight
	@Path("/{partnerId}")
	public Response idOptions(@PathParam("customerId") String customerID	) {
		
		return Response.ok()
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "POST, PUT, GET")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS,"true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN,"*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,"Content-Type")
				.build();	
	}
	
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{partnerId}")
	//get partner by a specific partnerID
	public PartnerRepresentation getPartner(@PathParam("partnerId") String id) {
		System.out.println("GET METHOD Request from Client with PartnerRequest String ............." + id);
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartner(id);
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/")
	//This method creates a new partner at the root URI
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest) throws ErrorMessage {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName()
				 + "  " +  partnerRequest.getUserID()  + "  " + partnerRequest.getCompanyName()  + "  " + partnerRequest.getAddress()  + "  " + partnerRequest.getPhoneNumber()
				 + "  " + partnerRequest.getEmail()  + "  " + partnerRequest.getPartnerPassword() + "  " + partnerRequest.getBankAccountNumber());
		PartnerActivity PartnerActivity = new PartnerActivity();
		return PartnerActivity.createPartner(partnerRequest.getFirstName(), partnerRequest.getLastName(), partnerRequest.getCompanyName(), 
				partnerRequest.getAddress(), partnerRequest.getPhoneNumber(), partnerRequest.getEmail(), partnerRequest.getPartnerPassword(), 
				partnerRequest.getUserID(), partnerRequest.getBankAccountNumber());
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{partnerId}")
	//This method is for update a partner in the database. The entire entry is replaced with new values. similar to creating a customer or partner.
	//This method though replaces values in the database where this path parameter matches in the database.
	public PartnerRepresentation updatePartner(PartnerRequest  partnerRequest, @PathParam("partnerId") String id) throws ErrorMessage {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName()
				 + "  " +  partnerRequest.getUserID()  + "  " + partnerRequest.getCompanyName()  + "  " + partnerRequest.getAddress()  + "  " + partnerRequest.getPhoneNumber()
				 + "  " + partnerRequest.getEmail()  + "  " + partnerRequest.getPartnerPassword() + "  " + partnerRequest.getBankAccountNumber());
		PartnerActivity PartnerActivity = new PartnerActivity();
		return PartnerActivity.updatePartner(partnerRequest.getFirstName(), partnerRequest.getLastName(), partnerRequest.getCompanyName(), 
				partnerRequest.getAddress(), partnerRequest.getPhoneNumber(), partnerRequest.getEmail(), partnerRequest.getPartnerPassword(), 
				partnerRequest.getUserID(), partnerRequest.getBankAccountNumber());
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerId}")
	//delete partner from database at specific partnerID.
	public Response deletePartner(@PathParam("partnerId") String id){
		System.out.println("Delete METHOD Request from Client with PartnerRequest String ............." + id);
		PartnerActivity partnerActivity = new PartnerActivity();
		String res = partnerActivity.deletePartner(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
}
