package gameLogic;

import java.util.ArrayList;

import Main.Interface;
import ingameObjects.ArakhMummy;
import ingameObjects.IngameObject;
import ingameObjects.Level;
import ingameObjects.Lever;
import ingameObjects.Stein;

public class World implements Runnable{
	
	private ArrayList<Hitbox> hitboxRegister;
	private ArrayList<IngameObject> objectList;
	ArrayList<IngameObject> deathNote;
	Level level;
	Interface inter;
	boolean run = true;
	
	public World(Interface inter){
		this.inter = inter;
		hitboxRegister = new ArrayList<Hitbox>();
		objectList = new ArrayList<IngameObject>();
		deathNote = new ArrayList<IngameObject>();
		level = new Level(0, this);
	}
	
	public Interface getInterface(){
		
		return inter;
		
	}
	
	public void run(){
		
		while(run){
			
			tick();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void tick(){
		System.out.println("_______________tick__________________");
		updateHitboxRegister();
		for(IngameObject object : objectList){
			object.tick();
		}
		objectList.removeAll(deathNote);
		//Stuff that is supposed to happen in a tick @world
	}
	
	//detectCollisionType() gives back the effect of every hitbox that collides
	public ArrayList<Integer> detectCollissionType(Vektor position){
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(Hitbox hitbox : hitboxRegister){
			int result = hitbox.collisionEffect(position);
			if(result != -1){
				output.add(result);
			}	
		}
		return  output;
	}
	
	public void updateHitboxRegister(){
		ArrayList<Hitbox> removals = new ArrayList<Hitbox>();
		for(Hitbox hitbox : hitboxRegister){
			if(hitbox.getAndProgLifetime() <= 0){
				removals.add(hitbox);
			}
		}
		hitboxRegister.removeAll(removals);
	}
	
	public void addHitbox(Hitbox hitbox){
		hitboxRegister.add(hitbox);
	}
	
	public Vektor getClosest(char team, Vektor position){
		ArrayList<Vektor> targets = new ArrayList<Vektor>();
		Vektor closest = null;
		for(IngameObject object : objectList){
			if(object.getTeam() == team){
				targets.add(object.getPosition());
			}
		}
		if(position != null && !targets.isEmpty()){
			closest = targets.get(0);
			for(Vektor targetPosition : targets){
				if(position.connectingTo(targetPosition).length() < position.connectingTo(closest).length()){
					closest = targetPosition;
				}
			}
		}
			return closest;
	}
	
	public void spawn(String name, int x, int y){
		switch(name){
			case "ArakhMummy":
			case "Arakh": objectList.add(new ArakhMummy(new Vektor(x, y), this));
				break;
				
			case "Stein": objectList.add(new Stein(new Vektor(x, y), this));
				break;
				
			case "Lever": objectList.add(new Lever(new Vektor(x, y), this));
				break;
			
		}
	}
	
	public void deathNote(IngameObject corpse){
		deathNote.add(corpse);
	}
	
}
