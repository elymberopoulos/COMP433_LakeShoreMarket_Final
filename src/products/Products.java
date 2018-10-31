package products;

import java.util.List;

public class Products {
	private String productName;
	private double productPrice;
	private String productReview;
	private String productOwner;
	private int productID;
	
	public Products(String productName, double productPrice, String productReview, String productOwner, int productID){
		this.productName = productName;
		this.productPrice = productPrice;
		this.productReview = productReview;
		this.productOwner = productOwner;
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductReview() {
		return productReview;
	}

	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}

	public String getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(String productOwner) {
		this.productOwner = productOwner;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
}
