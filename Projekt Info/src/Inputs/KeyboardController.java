package Inputs;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import Interfaces.KeyboardListener;
import Main.Interface;

public class KeyboardController implements Runnable{
	
	Interface inter;
	List<KeyboardListener> keyboardlistener = new ArrayList<KeyboardListener>(); 
	
	public KeyboardController(Interface inter){
		
		this.inter = inter;

	}
	
	public void run(){
		
		while(inter.run){
			
			keyboard();
			
		}
	}
	
	
	public void addkeyboardlistener(KeyboardListener Listener){
		
		keyboardlistener.add(Listener);
		
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
