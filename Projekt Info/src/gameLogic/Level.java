package gameLogic;

public class Level implements IngameObject {
	
	int level;
	
	public Level(int level){
		this.level = level;
		switch(level){
		case 0: //hitboxes for demolevel boundaries (effectNr = 0 , lifetime = -1) and level specific creep spawns
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
		// leave empty or level events, like paths getting blocked after a while or terrain getting covered in fire(damage hitboxes)

	}

}
