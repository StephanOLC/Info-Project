package gameLogic;

import java.util.ArrayList;

public class Hero implements IngameObject {
	
	private World world;
	private Vektor position;
	private Vektor previousPosition;
	private int healthPoints;
	private int status;
	
	public Hero(Vektor position, World world){
		this.world = world;
		this.position = position;
		healthPoints = 1000;
	}

	@Override
	public void draw() {
		switch(status){
			case 0: //draw dead hero
				break;
				
			default: //draw idle hero
				break;
			
		}

	}
	
	public void collision(ArrayList<Integer> collisions){
		for(int effect : collisions){
			switch (effect){
			
				case -1: break;
			
				case 0: position = previousPosition; //resets character (referred to as "char") to its old position in case it runs into a wall
				break;
				
				default: healthPoints = healthPoints - effect; if(healthPoints <= 0) status = 0;
				break;
			}
		}
	}

	@Override
	public void tick() {
		collision(world.detectCollissionType(position));
		
		//stuff that hero should do in a tick

	}

}
