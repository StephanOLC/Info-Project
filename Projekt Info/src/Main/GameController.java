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
import Objects.TextureObject;
import unusedtestclasses.box;

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
		
		//clickableObjects.add(new ClickableObject(20, 20, "Graphics/icon.png", "png", "button1", inter,this));
		//objects.add(new TextureTest(100, 100,150,150, "Graphics/Unicorn.jpg", "jpg","Texture1", inter));
		//objects.add(new box(300,300,"box2",inter));
		//objects.add(new box(55,60,"box3",inter));
		Drawableobjects.add(new TextureObject(0,0,200,200,75.5f, "Graphics/Icon.png", "png","Texture2",inter));
		//objects.add(new TextureObject(-150,100,200,130,90f, "Graphics/Trollface.png", "png","Texture2",inter));
		Drawableobjects.add(new box(1,1,4,4,"box1",inter));
		
		
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
