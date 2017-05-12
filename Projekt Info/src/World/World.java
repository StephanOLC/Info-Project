package World;

import java.util.ArrayList;

public class World {
	
	ArrayList<Hitbox> hitboxRegister;
	
	public World(){
		hitboxRegister = new ArrayList<Hitbox>();
	}
	
	public int detectCollissionType(Vektor position){
		for(int i = 0; i < hitboxRegister.size(); i++){
			int result = hitboxRegister.get(i).collisionEffect(position);
			if(result != -1){
				return result;
			}	
		}
		return  -1;
	}
	
}
