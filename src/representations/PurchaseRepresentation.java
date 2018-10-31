package representations;

public class PurchaseRepresentation {

	private String purchaserID;
	private int purchaserCreditCardNumber;
	private String purchasedBook;
	
	public PurchaseRepresentation() {}

	public String getPurchaserID() {
		return purchaserID;
	}

	public void setPurchaserID(String purchaserID) {
		this.purchaserID = purchaserID;
	}

	public int getPurchaserCreditCardNumber() {
		return purchaserCreditCardNumber;
	}

	public void setPurchaserCreditCardNumber(int purchaserCreditCardNumber) {
		this.purchaserCreditCardNumber = purchaserCreditCardNumber;
	}

	public String getPurchasedBook() {
		return purchasedBook;
	}

	public void setPurchasedBook(String purchasedBook) {
		this.purchasedBook = purchasedBook;
	}
	public String toString() {
		String printValue = getPurchasedBook() + "Purchased by: " + getPurchaserID() + "With credit card number: "
			+ getPurchaserCreditCardNumber();
		return printValue;	
	}
}
