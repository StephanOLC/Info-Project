package Inputs;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import Interfaces.KeyboardListener;
import Main.GameController;
import Main.Interface;

public class KeyboardController implements Runnable{
	
	Interface inter;
	GameController gamecontroller;
	List<KeyboardListener> keyboardlistener = new ArrayList<KeyboardListener>(); 
	
	public KeyboardController(Interface inter, GameController gamecontroller){
		
		this.inter = inter;
		this.gamecontroller = gamecontroller;

	}
	
	public void run(){
		
		while(inter.run){
			
			keyboard();
			
		}
	}
	
	
	public void addkeyboardlistener(KeyboardListener Listener){
		
		keyboardlistener.add(Listener);
		
	}
	
	public void direction(){
		
		boolean[] pressed = new boolean[5]; //1:w; 2:a; 3:s; 4:d; 5:e;
		
		pressed[0] = Keyboard.isKeyDown(Keyboard.KEY_W);
		pressed[1] = Keyboard.isKeyDown(Keyboard.KEY_A);
		pressed[2] = Keyboard.isKeyDown(Keyboard.KEY_S);
		pressed[3] = Keyboard.isKeyDown(Keyboard.KEY_D);
		pressed[4] = Keyboard.isKeyDown(Keyboard.KEY_E);
		
		gamecontroller.notifyWorldKeyboard(pressed);
		
	}
	
	public void keyboard(){
		
		while(Keyboard.next()){
			
			if(Keyboard.getEventKeyState()){
			
				for(KeyboardListener listener : keyboardlistener){
					
					listener.action(Keyboard.getEventKey());
				
				}
			}
		}
	}
	
}
