package ingameObjects;

import gameLogic.Vektor;

public class Level implements IngameObject {
	
	int level;
	
	public Level(int level){
		this.level = level;
		switch(level){
		case 0: //spawns hero @start-position,sets up hitboxes for demo-level boundaries (effectNr = 0 , lifetime = -1) and level specific creep spawns
			break;
			
		}
	}

	@Override
	public void draw() {
		switch(level){
		case 0: //demo level graphics
			break;
			
		}

	}

	@Override
	public void tick() {
		switch(level){
		case 0: // leave empty or level events, like paths getting blocked after a while or terrain getting covered in fire(damage hitboxes)
			break;
			
		}

	}

	@Override
	public char getTeam() {
		return 'w';
	}

	@Override
	public Vektor getPosition() {
		return new Vektor(0, 0);
	}

}