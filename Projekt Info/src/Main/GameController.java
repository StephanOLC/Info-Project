package Main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import Inputs.KeyboardController;
import Inputs.MouseController;
import Interfaces.ClickListener;
import Interfaces.KeyboardListener;
import gameLogic.World;
import Interfaces.Drawableobject;
import graphicObjects.ClickableObject;
import graphicObjects.SoundSource;
import ingameObjects.StartButton;

public class GameController implements KeyboardListener,ClickListener{
	
	private Interface inter;
	private SoundInterface soundinter;
	private KeyboardController keyboardcontroller;
	private MouseController mousecontroller;
	private World world;
	private List<Drawableobject> Drawableobjects = new CopyOnWriteArrayList<Drawableobject>();
	private List<ClickableObject> clickableObjects = new CopyOnWriteArrayList<ClickableObject>();
	private String worldname;
	private boolean changeworld;
	
	public GameController(Interface inter){
			
		init(inter);
		startThreads();
		keyboardcontroller.addkeyboardlistener(this);
		
	}
	
	public void init(Interface inter){
		
		this.inter = inter;
		inter.setGamecontroller(this);
		keyboardcontroller = new KeyboardController(inter, this);
		new Thread(keyboardcontroller).start();
		mousecontroller = new MouseController(inter);
		inter.setMouseController(mousecontroller);
		SoundInterface.init();
		SoundInterface.setListenerData();
		
		changeWorld("startscreen");
		
	}
	
	public void changeWorld(String Worldname){
		
		worldname = Worldname;
		changeworld = true;
		
		System.out.println("changed world to" + Worldname);
		
	}
	
	public void updateWorld(){
		
		if(changeworld){
			
			switch(worldname){
			
			case"startscreen":
				startscreen();
				break;
				
			case"firstworld":
				firstWorld();
				break;
			
			}
			
			changeworld = false;
		}
		
	}
	
	private void startscreen(){
		
		
		inter.clear();
		inter.resetCamera();
		inter.setCameramoveable(false);
		clearClickableObjects();
		
		SoundSource sound = new SoundSource(0, 0);
		sound.playSoundeffect(SoundInterface.loadSound("Audio/test.wav"));
		
		StartButton startButton = new StartButton("startbutton", inter, this); 
		new Thread(startButton, "startbutton").start();
		clickableObjects.add(startButton);
		addClickListener(this, "startbutton");
		
		
	}
	
	private void firstWorld(){
		
		
		inter.clear();
		inter.resetCamera();
		inter.setCameramoveable(true);
		clearClickableObjects();
		inter.setCameraLimits(500f, -500f, 500f, -500f);

		World  world = new World(0, inter); 
		world.spawn("Arakh", 0, 0);
		world.spawn("Stein", 500, 500);
		new Thread(world, "world").start();
		
	}
	
	private List<ClickableObject> clearClickableObjects(){
		
		List<ClickableObject> old = clickableObjects;
		
		for(ClickableObject clickableObject : clickableObjects){
			
			clickableObject.stop();
			
		}
		
		return old;
		
		
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
	
	public SoundInterface getSoundInterface(){
		
		return soundinter;
		
	}
	
	public MouseController getMouseController(){
		
		return mousecontroller;
		
	}
	
	public KeyboardController getKeyboardController(){
		
		return keyboardcontroller;
		
	}
	
	public void notifyWorldKeyboard(boolean[] keys){
		
		if(world != null){
			
			world.setInput(keys);
			
		}
		
	}

	@Override
	public void action(int EventKey) {
		
		if(EventKey == Keyboard.KEY_ESCAPE){
			
			inter.requestclose();
			
		}
		
	}

	@Override
	public void onpress(String ButtonName) {
		
		switch(ButtonName){
			
		case "startbutton":
			
			changeWorld("firstworld");
			
			break;
		
		}
		
	}

}
