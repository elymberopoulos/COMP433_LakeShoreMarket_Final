package serviceUsers;

public class User {
	private String firstName;
	private String lastName;
	private String userID;
	private String companyName; //TODO causing issue in customer constructor.
	private String address;
	private int phoneNumber;
	private String email;
	private int numberOfOrders;
	
	public User(String firstName, String lastName, String comapanyName, String address,
			int phoneNumber, String email, int numberOfOrders, String userID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.numberOfOrders = numberOfOrders;
		this.userID = userID;
	}

}
