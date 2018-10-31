package resources;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import representations.PartnerRepresentation;
import representations.PartnerRequest;

@WebService
public interface PartnerService {
	public Set<PartnerRepresentation> getPartners();
	public PartnerRepresentation getPartner(String id);
	public PartnerRepresentation createPartner(PartnerRequest  partnerRequest);
	public Response deletePartner(String id);
}
