package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;

public class Hero  extends Character implements IngameObject {
	
	public Hero(Vektor position, ArrayList <Integer> textureList, World world){
		super(position.getX(), position.getY(), "Graphics/Protagonist/Stance/Stance_forward.png", "Hero" ,world.getInterface());
		this.world = world;
		this.position = position;
		this.textureList = textureList;
		healthPoints = 1000;
		team = 'h';
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
		//stuff that hero should do in a tick - implement controller communication here

	}

	@Override
	public char getTeam() {
		return team;
	}

	@Override
	public void updateGraphic() {
		int graphicNR = -1;
		
		if(timer <= 3){
			graphicNR = timer;
		}
		else{
			timer = 0;
			graphicNR = timer;
		}
		
		switch(direction){
			
		case 'n': graphicNR = graphicNR + 10;
			break;
		case 'o': graphicNR = graphicNR + 20;
			break;
		case 's': graphicNR = graphicNR + 30;
			break;
		case 'w': graphicNR = graphicNR + 40;
			break;
		}
		graphicNR = graphicNR + 100*status;
		
		switch(graphicNR){
			case 110: setTexture(textureList.get(0));
				break;
			case 130: setTexture(textureList.get(1));
				break;
			case 210: setTexture(textureList.get(6));
				break;
			case 211: setTexture(textureList.get(7));
				break;
			case 212: setTexture(textureList.get(8));
				break;
			case 213: setTexture(textureList.get(9));
				break;
			case 220: setTexture(textureList.get(13));
				break;
			case 221: setTexture(textureList.get(14));
				break;
			case 222: setTexture(textureList.get(15));
				break;
			case 230: setTexture(textureList.get(2));
				break;
			case 231: setTexture(textureList.get(3));
				break;
			case 232: setTexture(textureList.get(4));
				break;
			case 233: setTexture(textureList.get(5));
				break;
			case 240: setTexture(textureList.get(10));
				break;
			case 241: setTexture(textureList.get(11));
				break;
			case 242: setTexture(textureList.get(12));
				break;
			
		}
		setPosition(position.getX(), position.getY());
		
	}

}
