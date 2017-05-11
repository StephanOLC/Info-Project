package Main;

import org.lwjgl.input.Keyboard;

import Inputs.KeyboardController;
import Inputs.KeyboardListener;
import Objects.TextureTest;
import Objects.box;

public class GameController implements KeyboardListener{
	
	public Interface inter;
	public KeyboardController keyboardcontroller;
	
	public GameController(Interface inter){
			
		init(inter);
		createObjects();
		keyboardcontroller.addkeyboardlistener(this);
		
	}
	
	public void init(Interface inter){
		
		this.inter = inter;
		keyboardcontroller = new KeyboardController(inter);
		
	}
	
	public void createObjects(){
		
		new Thread(keyboardcontroller).start();
		new Thread(new box(50,50,inter)).start();
		new Thread(new box(300,300,inter)).start();
		new Thread(new box(55,60,inter)).start();
		new Thread(new TextureTest(100, 100,150,150, "Graphics/icon.png", "png", inter)).start();
		new Thread(new TextureTest(-150,100,200,130, "Graphics/Trollface.png", "png",inter)).start();
		
	}
	
	public KeyboardController getKeyboardController(){
		
		return keyboardcontroller;
		
	}

	@Override
	public void action(int EventKey) {
		
		if(EventKey == Keyboard.KEY_ESCAPE){
			
			inter.requestclose();
			
		}
		
	}

}
