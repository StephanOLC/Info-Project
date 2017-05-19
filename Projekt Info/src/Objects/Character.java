package Objects;

import java.util.ArrayList;

import Main.Interface;
import Main.World;
import gameLogic.Vektor;

public abstract class Character extends TextureObject {
	
	protected World world;
	protected Vektor position;
	protected Vektor previousPosition;
	protected int speed;
	protected int healthPoints;
	protected int status;
	protected int timer;
	protected char team;
	protected boolean alreadyMoved;
	
	public Character(int x, int y, int width, int height, float rotation, String path, String fileformat, String name, Interface inter) {
		
		super(x, y, width, height, rotation, path, fileformat, name, inter);
		
	}
	
	public Character(int x, int y ,String path,String fileformat, String name,Interface inter ){
		
		super(x, y, path, fileformat, name, inter);
		
	}

	abstract void collision(ArrayList<Integer> effects);
	
	protected Vektor goTo(Vektor target){
		Vektor way = position.connectingTo(target);
		return way.scale(speed/(way.length()));
	}
	
	protected Vektor goAwayFrom(Vektor target){
		Vektor way = target.connectingTo(position);
		return way.scale(speed/(way.length()));
	}
	
	protected void movement(Vektor direction){
		if(!alreadyMoved){
			position = position.plus(direction);
		}
	}
	
}
