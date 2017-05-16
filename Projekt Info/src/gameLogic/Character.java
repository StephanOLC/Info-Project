package gameLogic;

import java.util.ArrayList;

public abstract class Character {
	protected World world;
	protected Vektor position;
	protected Vektor previousPosition;
	protected int healthPoints;
	protected int status;
	protected char team;
	abstract void collision(ArrayList<Integer> effects);
	
}
