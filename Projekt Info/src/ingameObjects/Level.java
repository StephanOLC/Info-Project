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
			textureList.add(newTexture("Graphics/Hero/Stance_forward.png")); // 0
			textureList.add(newTexture("Graphics/Hero/Backward.png")); // 1
			textureList.add(newTexture("Graphics/Hero/Forward1.png")); // 2
			textureList.add(newTexture("Graphics/Hero/Forward2.png")); // 3
			textureList.add(newTexture("Graphics/Hero/Forward3.png")); // 4
			textureList.add(newTexture("Graphics/Hero/Forward4.png")); // 5
			textureList.add(newTexture("Graphics/Hero/Backward1.png")); // 6
			textureList.add(newTexture("Graphics/Hero/Backward2.png")); // 7
			textureList.add(newTexture("Graphics/Hero/Backward3.png")); // 8
			textureList.add(newTexture("Graphics/Hero/Backward4.png")); // 9
			textureList.add(newTexture("Graphics/Hero/Left1.png")); // 10
			textureList.add(newTexture("Graphics/Hero/Left2.png")); // 11
			textureList.add(newTexture("Graphics/Hero/Left3.png")); // 12
			textureList.add(newTexture("Graphics/Hero/Rechts1.png")); // 13
			textureList.add(newTexture("Graphics/Hero/Rechts2.png")); // 14
			textureList.add(newTexture("Graphics/Hero/Rechts3.png")); // 15
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
