package databaseConnector;

import java.util.ArrayList;
import java.util.List;

import products.Book;
import serviceUsers.Partner;

public class PartnerManagerFacade {

	public PartnerManagerFacade() {}
	
	public List<Partner> getPartner(){
		return PartnerDAO.get();
	}
	
	public Partner getSpecificPartner(String id) {
		return PartnerDAO.getMatchingPartner(id);
//		for(Partner partner: PartnerDAO.get()){
//			if(partner.getUserID() == id) {
//				return partner;
//			}
//		}
//		return null;
	}
	public Partner postPartner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, String partnerPassword, String userID, int bankAccountNumber) {
		Partner partner = new Partner(firstName, lastName, companyName, address,
			 phoneNumber, email, partnerPassword, userID, bankAccountNumber);
		PartnerDAO.post(partner);
		
		return partner;
	}
	public List<Book> getPartnerProducts(String partnerUserID){ //THIS LINKS PARTNERS WITH THEIR PRODUCTS
		List<Book> returnResult = new ArrayList<Book>();
		for(Partner targetPartner: PartnerDAO.get()) {
			if(targetPartner.getUserID() == partnerUserID) {
				for(Book targetBook: BookDAO.get()) {
					if(targetBook.getProductOwner() == targetPartner.getUserID()) {
						returnResult.add(targetBook);
					}
				}
				return returnResult;
			}
		}
		return null;
	}
	public Partner updatePartner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, String partnerPassword, String userID, int bankAccountNumber) {
		Partner partner = new Partner(firstName, lastName, companyName, address,
			 phoneNumber, email, partnerPassword, userID, bankAccountNumber);
		PartnerDAO.put(partner);
		
		return partner;
	}
	public void deletePartner(String userID) {
		PartnerDAO.deleteIDMatch(userID);
	}
}
