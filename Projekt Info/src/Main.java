
import gameLogic.CircleHitbox;
import gameLogic.SquareHitbox;
import gameLogic.Vektor;
import gameLogic.World;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testausgabe:");
		Vektor a = new Vektor(-2,3);
		Vektor b = new Vektor(2,2);
		
		System.out.println("Vektor a plus Vektor b is: x = " + a.plus(b).getX() + " y = " + a.plus(b).getY() + " length = " + a.plus(b).length());
		
		World disneyland = new World();
		disneyland.addHitbox(new SquareHitbox(new Vektor(1,1), 2, 2, 100, 1));
		disneyland.addHitbox(new CircleHitbox(new Vektor(1,1), 2, 100, 1));
		disneyland.updateHitboxRegister();
		System.out.println("Collision type for 'a' with CircleHitbox is: " + disneyland.detectCollissionType(a));
		System.out.println("Collision type for 'b' with CircleHitbox is: " +disneyland.detectCollissionType(b));
		System.out.println("Number means: Collision, -1 means: none \nNew tick is beginning - hitboxRegister is being updated...");
		disneyland.updateHitboxRegister();
		System.out.println("Collision type for 'a' with CircleHitbox is: " + disneyland.detectCollissionType(a));
		System.out.println("Collision type for 'b' with CircleHitbox is: " +disneyland.detectCollissionType(b));

	}

}
