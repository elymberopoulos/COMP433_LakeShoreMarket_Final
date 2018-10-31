package products;

public class Book extends Products {
	private int isbn;
	private String author;
	private String category = "Book";
	private int orderID;
	
	public Book(String productName, double productPrice, String productReview, 
			String productOwner, int productID, int isbn, String author, String category){
		
		super(productName, productPrice, productReview, productOwner, productID);
		this.isbn = isbn;
		this.author = author;
		this.category = "Book";
		this.orderID = 0;
	}

	public String getProductName() {
		return super.getProductName();
	}

	public void setProductName(String productName) {
		super.setProductName(productName);
	}

	public double getProductPrice() {
		return super.getProductPrice();
	}

	public void setProductPrice(double productPrice) {
		super.setProductPrice(productPrice);
	}

	public String getProductReview() {
		return super.getProductReview();
	}

	public void setProductReview(String productReview) {
		super.setProductReview(productReview);
	}

	public String getProductOwner() {
		return super.getProductOwner();
	}

	public void setProductOwner(String productOwner) {
		super.setProductOwner(productOwner);
	}

	public int getProductID() {
		return super.getProductID();
	}

	public void setProductID(int productID) {
		super.setProductID(productID);
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

}
