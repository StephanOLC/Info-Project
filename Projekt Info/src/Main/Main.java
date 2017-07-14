package Main;

public class Main {

	public static void main(String[] args) {
		
		SoundInterface soundinter = new SoundInterface();
		Interface inter = new Interface(soundinter);
		new GameController(inter,soundinter);
		inter.run();

	}

}
