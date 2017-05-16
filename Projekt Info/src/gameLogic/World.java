package gameLogic;

import java.util.ArrayList;

public class World {
	
	private ArrayList<Hitbox> hitboxRegister;
	private ArrayList<IngameObject> objectList;
	Level level;
	
	public World(){
		hitboxRegister = new ArrayList<Hitbox>();
		objectList = new ArrayList<IngameObject>();
		level = new Level(0);
	}
	
	public void tick(){
		updateHitboxRegister();
		for(IngameObject object : objectList){
			object.tick();
		}
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
		Vektor closest = null;
		for(IngameObject object : objectList){
			if(object.getTeam() == team){
				closest = object.getPosition();
			}
		}
		for(IngameObject object : objectList){
			if(object.getTeam() == team && position.connectingTo(object.getPosition()).length() < position.connectingTo(closest).length()){
				closest = object.getPosition();
			}
		}
		return closest;
	}
	
	public void spawn(String name, int x, int y){
		switch(name){
			case "arakhMummy":
			case "arakh": objectList.add(new arakhMummy(new Vektor(x, y), this));
				break;
		}
	}
	
	public void deathNote(IngameObject corpse){
		objectList.remove(corpse);
	}
	
}
