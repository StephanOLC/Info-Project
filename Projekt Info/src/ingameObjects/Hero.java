package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;

public class Hero  extends Character implements IngameObject {
	
	public Hero(Vektor position, World world){
		super(position.getX(), position.getY(), "Graphics/Trollface.png", "Hero" ,world.getInterface());
		this.world = world;
		this.position = position;
		healthPoints = 1000;
		team = 'h';
		/*
		animationList.add(loadtexture("Graphics/Protagonist/Stance/Stance_forward.png","png")); // 0
		animationList.add(loadtexture("Graphics/Protagonist/Stance/Stance_backward.png","png")); // 1
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Forward/Forward1.png","png")); // 2
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Forward/Forward2.png","png")); // 3
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Forward/Forward3.png","png")); // 4
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Forward/Forward4.png","png")); // 5
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Backward/Backward1","png")); // 6
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Backward/Backward2","png")); // 7
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Backward/Backward3","png")); // 8
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Backward/Backward4","png")); // 9
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Left/Left1","png")); // 10
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Left/Left2","png")); // 11
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Left/Left3","png")); // 12
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Right/Rechts1","png")); // 13
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Right/Rechts2","png")); // 14
		animationList.add(loadtexture("Graphics/Protagonist/Walking/Right/Rechts3","png")); // 15
		*/
	}

	public Vektor getPosition(){
		return position;
	}

	public void collision(ArrayList<Integer> collisions){
		for(int effect : collisions){
			switch (effect){
				case 0: position = previousPosition; //resets character to its old position in case it runs into a wall
				break;
				
				default: healthPoints = healthPoints - effect; if(healthPoints <= 0) status = 0;
				break;
			}
		}
	}

	@Override
	public void tick() {
		collision(world.detectCollissionType(position));
		updateGraphic();
		//stuff that hero should do in a tick - implement controller communication here here

	}

	@Override
	public char getTeam() {
		return team;
	}

	@Override
	public void updateGraphic() {
		int graphicNR = -1;
		
		switch(direction){
			
		case 'n': graphicNR = 1;
			break;
		case 'o': graphicNR = 2;
			break;
		case 's': graphicNR = 3;
			break;
		case 'w': graphicNR = 4;
			break;
		}
		graphicNR = graphicNR + 10*status;
		
		switch(graphicNR){
			case 11: setTexture(animationList.get(0));
				break;
			case 13: setTexture(animationList.get(1));
				break;
			case 21: setTexture(animationList.get(6));
					break;
			case 22: setTexture(animationList.get(13));
					break;
			case 23: setTexture(animationList.get(2));
					break;
			case 14: setTexture(animationList.get(10));
					break;
			
		}
		setPosition(position.getX(), position.getY());
		
	}

}
