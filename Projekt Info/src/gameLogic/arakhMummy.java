package gameLogic;

import java.util.ArrayList;

public class arakhMummy implements IngameObject {
	
	Vektor position;
	World world;
	
	public arakhMummy(Vektor position, World world){
		this.position = position;
		this.world = world;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void collision(ArrayList<Integer> effects) {
		collision(world.detectCollissionType(position));

	}

}
