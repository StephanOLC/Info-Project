package Main;

public class Main {

	public static void main(String[] args) {
		
		Interface inter = new Interface();
		new GameController(inter);
		inter.run();

	}

}
