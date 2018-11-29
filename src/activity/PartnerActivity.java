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
		//employees = dao.getAllEmployees();
		partners = manager.getPartner();
		
		Iterator<Partner> it = partners.iterator();
		while(it.hasNext()) {
          Partner targetPartner = (Partner)it.next();
          PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
          partnerRepresentation.setUserID(targetPartner.getUserID());
          partnerRepresentation.setFirstName(targetPartner.getFirstName());
          partnerRepresentation.setLastName(targetPartner.getLastName());
          partnerRepresentation.setPhoneNumber(targetPartner.getPhoneNumber());
          partnerRepresentation.setBankAccountNumber(targetPartner.getBankAccountNumber());
          setLinksGetAllPartners(partnerRepresentation);
          //now add this representation in the list
          partnerRepresentations.add(partnerRepresentation);
        }
		return partnerRepresentations;
	}
	
	private void setLinksGetAllPartners(PartnerRepresentation partnerRep) {
		// Set up the activities that can be performed on orders
		Link partnerIdLink = new Link("List", "http://localhost:8081/partner/" + partnerRep.getUserID());
		Link partnerRootLink = new Link("List", "http://localhost:8081/partner/");

//		Link orderRootLink = new Link("List", "http://localhost:8081/order/");


		
		partnerRep.setLinks(partnerIdLink, partnerRootLink);
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
		partnerRepresentation.setNumberOfOrders(partner.getNumberOfOrders());
		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());
		return partnerRepresentation;
	}
	
	
	public PartnerRepresentation createPartner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, int numberOfOrders, String userID, int bankAccountNumber) {
		
		Partner partner = manager.postPartner(firstName, lastName, companyName, address, phoneNumber, email, numberOfOrders, userID, bankAccountNumber);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setFirstName(partner.getFirstName());
		partnerRepresentation.setLastName(partner.getLastName());
		partnerRepresentation.setUserID(partner.getUserID());
		partnerRepresentation.setCompanyName(partner.getCompanyName());
		partnerRepresentation.setAddress(partner.getAddress());
		partnerRepresentation.setPhoneNumber(partner.getPhoneNumber());
		partnerRepresentation.setEmail(partner.getEmail());
		partnerRepresentation.setNumberOfOrders(partner.getNumberOfOrders());
		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());

		
		
		return partnerRepresentation;
	}
	
	public PartnerRepresentation updatePartner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, int numberOfOrders, String userID, int bankAccountNumber) {
		
		Partner partner = manager.updatePartner(firstName, lastName, companyName, address, phoneNumber, email, numberOfOrders, userID, bankAccountNumber);
		
		PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
		partnerRepresentation.setFirstName(partner.getFirstName());
		partnerRepresentation.setLastName(partner.getLastName());
		partnerRepresentation.setUserID(partner.getUserID());
		partnerRepresentation.setCompanyName(partner.getCompanyName());
		partnerRepresentation.setAddress(partner.getAddress());
		partnerRepresentation.setPhoneNumber(partner.getPhoneNumber());
		partnerRepresentation.setEmail(partner.getEmail());
		partnerRepresentation.setNumberOfOrders(partner.getNumberOfOrders());
		partnerRepresentation.setBankAccountNumber(partner.getBankAccountNumber());

		
		
		return partnerRepresentation;
	}
	
	public String deletePartner(String id) {
		
		//dao.deleteEmployee(id);
		manager.deletePartner(id);
		
		return "OK";
	}


}

