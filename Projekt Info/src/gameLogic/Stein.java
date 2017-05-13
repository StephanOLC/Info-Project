package gameLogic;

public class Stein implements IngameObject {
	private World world;
	private Vektor position;
	
	public Stein(Vektor position, World world){
		this.world = world;
		this.position = position;
	}

	public void draw() {
		// TODO Auto-generated method stub

	}
	
	public void tick(){
		world.detectCollissionType(position);
		//stuff that object should do in a tick
	}

}
