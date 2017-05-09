package Main;

import org.lwjgl.input.Keyboard;

public class Input implements Runnable{
	
	Interface inter;
	
	public Input(Interface inter){
		
		this.inter = inter;

	}
	
	public void run(){
		
		while(inter.run){
		
			while(Keyboard.next()){
			
				if(Keyboard.getEventKeyState()){
				
					System.out.println(Keyboard.getEventCharacter());
				
					switch(Keyboard.getEventKey()){
					
					case Keyboard.KEY_ESCAPE:
						break;
					
					}
				}
			}
		}
	}
}
