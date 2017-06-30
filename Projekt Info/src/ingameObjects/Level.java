package ingameObjects;

import gameLogic.Vektor;
import gameLogic.World;
import graphicObjects.TextureObject;

public class Level extends TextureObject implements IngameObject {
	
	int level;
	
	public Level(int level, World world){
		super(0, 0, "Graphics/Icon.png", "Level" ,world.getInterface());
		this.level = level;
		switch(level){
		case 0: //spawns hero @start-position,sets up hitboxes for demo-level boundaries (effectNr = 0 , lifetime = -1) and level specific creep spawns
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

	@Override
	public void updateGraphic() {
		// TODO Auto-generated method stub
		
	}

}
