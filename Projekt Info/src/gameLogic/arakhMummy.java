package gameLogic;

import java.util.ArrayList;

public class arakhMummy extends Character implements IngameObject {
	
	public arakhMummy(Vektor position, World world){
		this.position = position;
		this.world = world;
		healthPoints = 500;
		speed = 5;
	}
	
	@Override
	public Vektor getPosition() {
		return position;
	}
	
	public char getTeam(){
		return 'e';
	}
	
	@Override
	public void draw() {
		switch(status){
			
		}

	}

	@Override
	public void tick() {
		//collision detection first, movement second, attacking last
		collision(world.detectCollissionType(position));
		alreadyMoved = false;
		
		//now actions ->
		
		jumpAttack(world.getClosest('n', position));
	}

	public void collision(ArrayList<Integer> collisions) {
		for(int effect : collisions){
			switch (effect){
				case 0: position = previousPosition;
				break;
				
				default: healthPoints = healthPoints - effect; if(healthPoints <= 0) status = 0;
				break;
			}
		}
	}
	
	private Vektor jumpTo(Vektor target){
		Vektor way = position.connectingTo(target);
		return way.scale(3*speed/(way.length()));
	}
	
	private Vektor rollAway(Vektor target){
		Vektor way = target.connectingTo(position);
		return way.scale(2*speed/(way.length()));
	}
	
	private void jumpAttack(Vektor target){
		if(position.connectingTo(target).length() < 6*speed){
			movement(jumpTo(target));
		}
		else if(position.connectingTo(target).length() < speed){
			world.addHitbox(new CircleHitbox(position.plus(position.connectingTo(target)), speed - 1, 10000, 1));
		}
		else{
			movement(goTo(target));
		}
	}
}
