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
	
	//detectCollisionType() gives back the effect of the first hitbox found
	public int detectCollissionType(Vektor position){ 
		for(Hitbox hitbox : hitboxRegister){
			int result = hitbox.collisionEffect(position);
			if(result != -1){
				return result;
			}	
		}
		return  -1;
	}
	
	public void updateHitboxRegister(){
		for(Hitbox hitbox : hitboxRegister){
			if(hitbox.getAndProgLifetime() <= 0){
				hitboxRegister.remove(hitbox);
			}
		}
	}
	
	public void addHitbox(CircleHitbox hitbox){
		hitboxRegister.add(hitbox);
	}
	
	public void deathNote(IngameObject corpse){
		objectList.remove(corpse);
	}
	
}
