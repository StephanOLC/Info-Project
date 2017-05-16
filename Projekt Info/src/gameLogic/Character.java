package gameLogic;

import java.util.ArrayList;

public abstract class Character {
	protected World world;
	protected Vektor position;
	protected Vektor previousPosition;
	protected int speed;
	protected int healthPoints;
	protected int status;
	protected int timer;
	protected char team;
	protected boolean alreadyMoved;
	
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
