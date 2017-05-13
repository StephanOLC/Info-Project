package gameLogic;

public class Stein implements IngameObjekt {
	private World world;
	private Vektor position;
	
	public Stein(World world){
		this.world = world;
	}

	public void draw() {
		// TODO Auto-generated method stub

	}
	
	public void tick(){
		world.detectCollissionType(position);
	}

}
