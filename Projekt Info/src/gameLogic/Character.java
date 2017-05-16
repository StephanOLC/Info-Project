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
	
	abstract void collision(ArrayList<Integer> effects);
	
	protected Vektor to(Vektor target){
		Vektor way = position.connectingTo(target);
		return way.scale(speed/(way.length()));
	}
	
}
