package resources;


import representations.PartnerRepresentation;
import representations.PartnerRequest;
import activity.PartnerActivity;

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

	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	@LocalPreflight
	//@Cacheable(cc="public, maxAge=3600") example for caching
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
	@LocalPreflight
	public PartnerRepresentation getPartner(@PathParam("partnerId") String id) {
		System.out.println("GET METHOD Request from Client with PartnerRequest String ............." + id);
		PartnerActivity partnerActivity = new PartnerActivity();
		return partnerActivity.getPartner(id);
	}
	@Override
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/")
	@LocalPreflight
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName()
				 + "  " +  partnerRequest.getUserID()  + "  " + partnerRequest.getCompanyName()  + "  " + partnerRequest.getAddress()  + "  " + partnerRequest.getPhoneNumber()
				 + "  " + partnerRequest.getEmail()  + "  " + partnerRequest.getPartnerPassword() + "  " + partnerRequest.getBankAccountNumber());
		PartnerActivity PartnerActivity = new PartnerActivity();
		return PartnerActivity.createPartner(partnerRequest.getFirstName(), partnerRequest.getLastName(), partnerRequest.getCompanyName(), 
				partnerRequest.getAddress(), partnerRequest.getPhoneNumber(), partnerRequest.getEmail(), partnerRequest.getPartnerPassword(), 
				partnerRequest.getUserID(), partnerRequest.getBankAccountNumber());
	}
	@Override
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/{partnerId}")
	@LocalPreflight
	public PartnerRepresentation updatePartner(PartnerRequest  partnerRequest, @PathParam("partnerId") String id) {
		System.out.println("POST METHOD Request from Client with ............." + partnerRequest.getFirstName() + "  " + partnerRequest.getLastName()
				 + "  " +  partnerRequest.getUserID()  + "  " + partnerRequest.getCompanyName()  + "  " + partnerRequest.getAddress()  + "  " + partnerRequest.getPhoneNumber()
				 + "  " + partnerRequest.getEmail()  + "  " + partnerRequest.getPartnerPassword() + "  " + partnerRequest.getBankAccountNumber());
		PartnerActivity PartnerActivity = new PartnerActivity();
		return PartnerActivity.updatePartner(partnerRequest.getFirstName(), partnerRequest.getLastName(), partnerRequest.getCompanyName(), 
				partnerRequest.getAddress(), partnerRequest.getPhoneNumber(), partnerRequest.getEmail(), partnerRequest.getPartnerPassword(), 
				partnerRequest.getUserID(), partnerRequest.getBankAccountNumber());
	}
	@Override
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/{partnerId}")
	@LocalPreflight
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
