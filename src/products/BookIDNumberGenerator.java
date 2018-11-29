package products;

public class BookIDNumberGenerator {
	
	public static int id=0;
	
	public  static int getNewID() {
		return ++id;
	}
}
