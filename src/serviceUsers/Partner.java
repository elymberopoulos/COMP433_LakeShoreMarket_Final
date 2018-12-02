package serviceUsers;

public class Partner {
	private String firstName;
	private String lastName;
	private String userID;
	private String companyName; //TODO causing issue in customer constructor.
	private String address;
	private int phoneNumber;
	private String email;
	private int numberOfOrders;
	private int bankAccountNumber;
	private String partnerPassword;
	
	public Partner(String firstName, String lastName, String companyName, String address,
			int phoneNumber, String email, String partnerPassword, String userID, int bankAccountNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.companyName = companyName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		//this.numberOfOrders = numberOfOrders;
		this.bankAccountNumber = bankAccountNumber;
		this.partnerPassword = partnerPassword;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public int getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(int bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getPartnerPassword() {
		return partnerPassword;
	}

	public void setPartnerPassword(String partnerPassword) {
		this.partnerPassword = partnerPassword;
	}

}
