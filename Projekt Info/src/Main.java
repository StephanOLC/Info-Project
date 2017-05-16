
import gameLogic.CircleHitbox;
import gameLogic.Vektor;
import gameLogic.World;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testausgabe:");
		
		World disneyland = new World();
		disneyland.spawn("Stein", 10, 10);
		disneyland.spawn("ArakhMummy", 50, 50);
		for(int i = 0; i < 10 ; i++){
			disneyland.tick();
		}
		
	}

}
