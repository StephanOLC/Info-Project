package gameLogic;

import java.util.ArrayList;

public class arakhMummy extends Character implements IngameObject {
	
	public arakhMummy(Vektor position, World world){
		this.position = position;
		this.world = world;
		healthPoints = 500;
	}

	@Override
	public void draw() {
		switch(status){
			
		}

	}

	@Override
	public void tick() {
		collision(world.detectCollissionType(position));
		
	}

	public void collision(ArrayList<Integer> collisions) {
		for(int effect : collisions){
			switch (effect){
				case 0: position = previousPosition;
				break;
				
				default: healthPoints = healthPoints - effect; if(healthPoints <= 0) status = 0;
				break;
			}
		}
	}

	@Override
	public Vektor getPosition() {
		return position;
	}
	
	public char getTeam(){
		return 'e';
	}

}
