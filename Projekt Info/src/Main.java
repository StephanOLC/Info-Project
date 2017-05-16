
import gameLogic.CircleHitbox;
import gameLogic.Vektor;
import gameLogic.World;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testausgabe:");
		Vektor a = new Vektor(-2,3);
		Vektor b = new Vektor(2,2);
		
		System.out.println("Connection between a and b is: x= " + a.connectingTo(b).getX() + " y= " + a.connectingTo(b).getY());
		
		World disneyland = new World();
		disneyland.spawn("Stein", 10, 10);
		disneyland.addHitbox(new CircleHitbox(new Vektor(10,10), 3, 100, 1));
		for(int i = 0; i < 10 ; i++){
			disneyland.tick();
		}
		
	}

}
