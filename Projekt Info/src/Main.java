
import gameLogic.Vektor;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testausgabe:");
		Vektor a = new Vektor(-2,3);
		Vektor b = new Vektor(2,2);
		
		System.out.println("Vektor a plus Vektor b is: x = " + a.plus(b).getX() + " y = " + a.plus(b).getY() + " length = " + a.plus(b).length());
		
		
	}

}
