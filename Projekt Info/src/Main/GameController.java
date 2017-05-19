package Main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import Inputs.KeyboardController;
import Inputs.MouseController;
import Interfaces.ClickListener;
import Interfaces.KeyboardListener;
import Interfaces.Drawableobject;
import Objects.ClickableObject;

public class GameController implements KeyboardListener{
	
	public Interface inter;
	public KeyboardController keyboardcontroller;
	public MouseController mousecontroller;
	public List<Drawableobject> Drawableobjects = new ArrayList<Drawableobject>();
	public List<ClickableObject> clickableObjects = new ArrayList<ClickableObject>();
	
	public GameController(Interface inter){
			
		init(inter);
		createObjects();
		startThreads();
		keyboardcontroller.addkeyboardlistener(this);
		
	}
	
	public void init(Interface inter){
		
		this.inter = inter;
		keyboardcontroller = new KeyboardController(inter);
		new Thread(keyboardcontroller).start();
		mousecontroller = new MouseController(inter);
		inter.setMouseController(mousecontroller);
		
	}
	
	public void createObjects(){
		
		
	}
	
	private void startThreads(){
		
		for(Drawableobject obj : Drawableobjects){
			
			new Thread(obj, obj.getName()).start();
			
		}
		
		for(ClickableObject clickableObject : clickableObjects){
			
			new Thread(clickableObject, clickableObject.getName()).start();
			
		}
		
	}
	
	public boolean addClickListener(ClickListener clickListener, String ClickableObject){
		
		for(ClickableObject clickableObject : clickableObjects){
			
			if(clickableObject.getName() == ClickableObject){
				
				return clickableObject.addListener(clickListener);
				
			}
			
		}
		
		return false;
		
	}
	
	public MouseController getMouseController(){
		
		return mousecontroller;
		
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
