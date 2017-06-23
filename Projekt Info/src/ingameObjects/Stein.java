package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;
import graphicObjects.TextureObject;

public class Stein extends TextureObject implements IngameObject {
	private World world;
	private Vektor position;
	private int healthPoints;
	
	public Stein(Vektor position, World world){
		super(position.getX(), position.getY(), "Graphics/icon.png", "png", "Stein" ,world.getInterface());
		this.position = position;
		this.world = world;
		this.position = position;
		healthPoints = 100;
	}
	
	public void collision(ArrayList<Integer> collisions){
		for(int effect : collisions){
			switch (effect){
				case 0: world.deathNote(this);
				break;
			
				default: healthPoints = healthPoints - effect; if(healthPoints <= 0) world.deathNote(this);
				break;
			}
		}
	}
	
	public void tick(){
		System.out.println("stein - position: [" + position.getX() + ", " + position.getY() + "] HP: " + healthPoints);
		collision(world.detectCollissionType(position));
		//stuff that object should do in a tick
		
	}

	@Override
	public char getTeam() {
		return 'n';
	}

	@Override
	public Vektor getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public void updateGraphic() {
		setPosition(position.getX(), position.getY());
		
	}

}
