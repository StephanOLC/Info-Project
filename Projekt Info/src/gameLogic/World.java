package gameLogic;

import java.util.ArrayList;

public class World {
	
	private ArrayList<Hitbox> hitboxRegister;
	private ArrayList<IngameObject> objectList;
	ArrayList<IngameObject> deathNote;
	Level level;
	
	public World(){
		hitboxRegister = new ArrayList<Hitbox>();
		objectList = new ArrayList<IngameObject>();
		deathNote = new ArrayList<IngameObject>();
		level = new Level(0);
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
			
		}
	}
	
	public void deathNote(IngameObject corpse){
		deathNote.add(corpse);
	}
	
}
