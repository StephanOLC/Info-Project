package Main;

import Objects.TextureTest;
import Objects.box;

public class GameController {
	
	public Interface inter;
	
	public GameController(Interface inter){
		
		this.inter = inter;
		new Thread(new box(50,50,inter)).start();
		new Thread(new box(60,70,inter)).start();
		new Thread(new box(55,60,inter)).start();
		new Thread(new TextureTest(100, 100,150,150, "Graphics/icon.png", "png", inter)).start();
		
	}
}
