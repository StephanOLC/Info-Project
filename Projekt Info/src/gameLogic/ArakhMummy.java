package gameLogic;

import java.util.ArrayList;

public class ArakhMummy extends Character implements IngameObject {
	
	public ArakhMummy(Vektor position, World world){
		this.position = position;
		this.world = world;
		healthPoints = 500;
		speed = 5;
		status = 1; //1:Idle , 2&3:running , 4:jumping , 5:attacking
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
		System.out.println("arakhMummy - position: [" + position.getX() + ", " + position.getY() + "] HP: " + healthPoints + "Status: " + status);
		//collision detection first, movement second, attacking last
		if(timer >= 5 && status == 0) world.deathNote(this);
		collision(world.detectCollissionType(position));
		alreadyMoved = false;
		timer++;
		
		//now actions ->
		if(world.getClosest('n', position) != null && status != 0) jumpAttack(world.getClosest('n', position));
		
	}

	public void collision(ArrayList<Integer> collisions) {
		for(int effect : collisions){
			switch (effect){
				case 0: position = previousPosition;
				break;
				
				default: healthPoints = healthPoints - effect; 
						if(healthPoints <= 0){
							status = 0;
							timer = 0;
						}
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
		if(position.connectingTo(target).length() < 1.5*speed){
			world.addHitbox(new CircleHitbox(position.plus(position.connectingTo(target)), speed - 1, 10000, 1));
			status = 5;
			System.out.println("Hitbox created at x: " + position.plus(position.connectingTo(target)).getX() + " y: " + position.plus(position.connectingTo(target)).getY() + " Radius: " + (speed-1));
		}
		else if(position.connectingTo(target).length() < 6*speed){
			System.out.println("jumping...");
			movement(jumpTo(target));
			status = 4;
		}
		else{
			movement(goTo(target));
			if(status != 2)status = 2;
			else status = 3;
		}
	}
}
