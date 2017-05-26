package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;

public class Hero  extends Character implements IngameObject {
	
	public Hero(Vektor position, World world){
		super(position.getX(), position.getY(), "Graphics/Trollface.png", "png", "Hero" ,world.getInterface());
		this.world = world;
		this.position = position;
		healthPoints = 1000;
		team = 'h';
	}

	public Vektor getPosition(){
		return position;
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
				case 0: position = previousPosition; //resets character to its old position in case it runs into a wall
				break;
				
				default: healthPoints = healthPoints - effect; if(healthPoints <= 0) status = 0;
				break;
			}
		}
	}

	@Override
	public void tick() {
		collision(world.detectCollissionType(position));
		
		//stuff that hero should do in a tick - implement controller communication here here

	}

	@Override
	public char getTeam() {
		return team;
	}

}
