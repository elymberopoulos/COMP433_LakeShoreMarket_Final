package order;

public final class OrderNumberGenerator {
	
	public static int id=0;
	
	public  static int getNewID() {
		return ++id;
	}

}
