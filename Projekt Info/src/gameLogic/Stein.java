package gameLogic;

public class Stein implements IngameObject {
	private World world;
	private Vektor position;
	private int healthPoints;
	
	public Stein(Vektor position, World world){
		this.world = world;
		this.position = position;
		healthPoints = 100;
	}

	public void draw() {
		// TODO Auto-generated method stub

	}
	
	public void tick(){
		int effect = world.detectCollissionType(position);
		switch (effect){
		case 0: world.deathNote(this);
		break;
		default: healthPoints = healthPoints - effect;
		break;
		}
		//stuff that object should do in a tick
	}

}
