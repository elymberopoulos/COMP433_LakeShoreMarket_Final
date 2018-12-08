package activity;

import representations.BookRepresentation;
import representations.PartnerRepresentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import serviceUsers.Partner;
import databaseConnector.BookManagerFacade;
import databaseConnector.PartnerManagerFacade;
import link.Link;


public class PartnerActivity {
	private static PartnerManagerFacade manager = new PartnerManagerFacade();
	
	public Set<PartnerRepresentation> getPartner() {
		
		List<Partner> partners = new ArrayList<Partner>();
		Set<PartnerRepresentation> partnerRepresentations = new HashSet<PartnerRepresentation>();
		partners = manager.getPartner();
		
		Iterator<Partner> it = partners.iterator();
		while(it.hasNext()) {
          Partner partner = (Partner)it.next();
          PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
          	partnerRepresentation.setFirstName(partner.getFirstName());
	  		partnerRepresentation.setLastName(partner.getLastName());
	  		partnerRepresentation.setUserID(partner.getUserID());
	  		partnerRepresentation.setCompanyName(partner.getCompanyName());
	  		partnerRepresentation.setAddress(partner.getAddress());
	  		partnerRepresentation.setPhoneNumber(partner.getPhoneNumber());
	  		partnerRepresentation.setEmail(partner.getEmail());
	  		partnerRepresentation.setPartnerPassword(partner.getPartnerPassword());
	  		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());
          setLinksGetAllPartners(partnerRepresentation);
          partnerRepresentations.add(partnerRepresentation);
        }
		return partnerRepresentations;
	}
	
	private void setLinksGetAllPartners(PartnerRepresentation partnerRep) {
		Link partnerIdLink = new Link("Get_Partner", "http://localhost:8081/partner/" + partnerRep.getUserID()); //GET specific partner
//		Link orderRootLink = new Link("List", "http://localhost:8081/order/");
		
		partnerRep.setLinks(partnerIdLink);
	}
	
	public PartnerRepresentation getPartner(String id) {
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		Partner partner = manager.getSpecificPartner(id);
		
		partnerRepresentation.setFirstName(partner.getFirstName());
		partnerRepresentation.setLastName(partner.getLastName());
		partnerRepresentation.setUserID(partner.getUserID());
		partnerRepresentation.setCompanyName(partner.getCompanyName());
		partnerRepresentation.setAddress(partner.getAddress());
		partnerRepresentation.setPhoneNumber(partner.getPhoneNumber());
		partnerRepresentation.setEmail(partner.getEmail());
		partnerRepresentation.setPartnerPassword(partner.getPartnerPassword());
		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());
		setLinksGetPartner(partnerRepresentation);
		return partnerRepresentation;
	}
	private void setLinksGetPartner(PartnerRepresentation partnerRep) {
		Link partnerIdLink = new Link("partnerDirectory", "http://localhost:8081/partner/"); //GET partners
		Link bookRootLink = new Link("List_Book", "http://localhost:8081/book?partnerUserName="+ partnerRep.getUserID()); //POST new book with owner ID
		
		partnerRep.setLinks(partnerIdLink, bookRootLink);
	}
	
	
	public PartnerRepresentation createPartner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, String partnerPassword, String userID, int bankAccountNumber) {
		
		Partner partner = manager.postPartner(firstName, lastName, companyName, address, phoneNumber, email, partnerPassword, userID, bankAccountNumber);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setFirstName(partner.getFirstName());
		partnerRepresentation.setLastName(partner.getLastName());
		partnerRepresentation.setUserID(partner.getUserID());
		partnerRepresentation.setCompanyName(partner.getCompanyName());
		partnerRepresentation.setAddress(partner.getAddress());
		partnerRepresentation.setPhoneNumber(partner.getPhoneNumber());
		partnerRepresentation.setEmail(partner.getEmail());
		partnerRepresentation.setPartnerPassword(partner.getPartnerPassword());
		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());
		setLinksCreatePartner(partnerRepresentation);
		
		
		return partnerRepresentation;
	}
	private void setLinksCreatePartner(PartnerRepresentation partnerRep) {
		Link partnerIdLink = new Link("Get_Partner", "http://localhost:8081/partner/" + partnerRep.getUserID()); //GET created partner profile
		
		partnerRep.setLinks(partnerIdLink);
	}
	
	public PartnerRepresentation updatePartner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, String partnerPassword, String userID, int bankAccountNumber) {
		
		Partner partner = manager.updatePartner(firstName, lastName, companyName, address, phoneNumber, email, partnerPassword, userID, bankAccountNumber);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setFirstName(partner.getFirstName());
		partnerRepresentation.setLastName(partner.getLastName());
		partnerRepresentation.setUserID(partner.getUserID());
		partnerRepresentation.setCompanyName(partner.getCompanyName());
		partnerRepresentation.setAddress(partner.getAddress());
		partnerRepresentation.setPhoneNumber(partner.getPhoneNumber());
		partnerRepresentation.setEmail(partner.getEmail());
		partnerRepresentation.setPartnerPassword(partner.getPartnerPassword());
		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());

		setLinksUpdatePartner(partnerRepresentation);
		
		return partnerRepresentation;
	}
	private void setLinksUpdatePartner(PartnerRepresentation partnerRep) {
		Link partnerIdLink = new Link("Updated_Profile", "http://localhost:8081/partner/" + partnerRep.getUserID()); //GET updated partner profile
		
		partnerRep.setLinks(partnerIdLink);
	}
	
	public String deletePartner(String id) {
		
		manager.deletePartner(id);
		
		return "OK";
	}


}

