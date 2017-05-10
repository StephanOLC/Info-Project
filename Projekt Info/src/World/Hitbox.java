package World;

public class Hitbox {
	
	private int radius;
	private Vektor position;
	private int effectNr;
	
	public Hitbox(Vektor position, int radius,  int effectNr){
		this.position = position;
		this.radius = radius;
		this.effectNr = effectNr;
	}
	
	public int collisionEffect(Vektor target){
		if(target.connectingTo(position).length() < radius){
			return effectNr;
		}
		else{
			return -1;
		}
	}
	
}
