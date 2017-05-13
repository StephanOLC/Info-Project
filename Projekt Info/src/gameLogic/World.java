package gameLogic;

import java.util.ArrayList;

public class World {
	
	private ArrayList<Hitbox> hitboxRegister;
	private ArrayList<IngameObject> objectList;
	
	public World(){
		hitboxRegister = new ArrayList<Hitbox>();
	}
	
	public void tick(){
		updateHitboxRegister();
		for(int i = 0; i < objectList.size(); i++){
			objectList.get(i).tick();
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
		for(int i = 0; i < hitboxRegister.size(); i++){
			if(hitboxRegister.get(i).getAndProgLifetime() <= 0){
				hitboxRegister.remove(i);
			}
		}
	}
	
	public void addHitbox(Hitbox hitbox){
		hitboxRegister.add(hitbox);
	}
	
}
