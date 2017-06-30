package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;
import graphicObjects.TextureObject;

public class Level extends TextureObject implements IngameObject {
	
	int level;
	ArrayList <Integer> textureList;
	
	public Level(int level, World world){
		super(0, 0, "Graphics/Icon.png", "Level" ,world.getInterface());
		this.level = level;
		switch(level){
		case 0: //spawns hero @start-position,sets up hitboxes for demo-level boundaries (effectNr = 0 , lifetime = -1) and level specific creep spawns
			textureList = new ArrayList<Integer>();
			//Hero-textures first, stone second, then small to large enemies!
			textureList.add(newTexture("Graphics/Protagonist/Stance/Stance_forward.png")); // 0
			textureList.add(newTexture("Graphics/Protagonist/Stance/Stance_backward.png")); // 1
			textureList.add(newTexture("Graphics/Protagonist/Walking/Forward/Forward1.png")); // 2
			textureList.add(newTexture("Graphics/Protagonist/Walking/Forward/Forward2.png")); // 3
			textureList.add(newTexture("Graphics/Protagonist/Walking/Forward/Forward3.png")); // 4
			textureList.add(newTexture("Graphics/Protagonist/Walking/Forward/Forward4.png")); // 5
			textureList.add(newTexture("Graphics/Protagonist/Walking/Backward/Backward1")); // 6
			textureList.add(newTexture("Graphics/Protagonist/Walking/Backward/Backward2")); // 7
			textureList.add(newTexture("Graphics/Protagonist/Walking/Backward/Backward3")); // 8
			textureList.add(newTexture("Graphics/Protagonist/Walking/Backward/Backward4")); // 9
			textureList.add(newTexture("Graphics/Protagonist/Walking/Left/Left1")); // 10
			textureList.add(newTexture("Graphics/Protagonist/Walking/Left/Left2")); // 11
			textureList.add(newTexture("Graphics/Protagonist/Walking/Left/Left3")); // 12
			textureList.add(newTexture("Graphics/Protagonist/Walking/Right/Rechts1")); // 13
			textureList.add(newTexture("Graphics/Protagonist/Walking/Right/Rechts2")); // 14
			textureList.add(newTexture("Graphics/Protagonist/Walking/Right/Rechts3")); // 15
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
	
	public ArrayList<Integer> getTextureList(){
		return textureList;
	}

	@Override
	public void updateGraphic() {
		// TODO Auto-generated method stub
		
	}

}
