package ingameObjects;

import java.util.ArrayList;

import Main.Interface;
import gameLogic.Vektor;
import gameLogic.World;
import graphicObjects.TextureObject;

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
	protected char direction;
	
	public Character(int x, int y, int width, int height, float rotation, String path, String name, Interface inter) {
		
		super(x, y, width, height, rotation, path, name, inter);
		
	}
	
	public Character(int x, int y ,String path, String name,Interface inter ){
		
		super(x, y, path, name, inter);
		
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
			updateDirection(direction);
		}
	}
	
	protected void updateDirection(Vektor direction){
		if(direction.getX() > 0 && direction.getY() > 0){
			if(direction.getX() > direction.getY()){
				this.direction = 'o';
			}
			else{
				this.direction = 's';
			}
		}
		else if(direction.getX() > 0 && direction.getY() < 0){
			if(direction.getX()*direction.getX() > direction.getY()*direction.getY()){
				this.direction = 'o';
			}
			else{
				this.direction = 'n';
			}
				}
		else if(direction.getX() < 0 && direction.getY() < 0){
			if(direction.getX()*direction.getX() > direction.getY()*direction.getY()){
				this.direction = 'w';
			}
			else{
				this.direction = 'n';
			}
		}
		else if(direction.getX() < 0 && direction.getY() > 0){
			if(direction.getX()*direction.getX() > direction.getY()*direction.getY()){
				this.direction = 'w';
			}
			else{
				this.direction = 's';
			}
		}
	}
	
}
