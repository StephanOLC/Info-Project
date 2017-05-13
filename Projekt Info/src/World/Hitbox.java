package World;

public class Hitbox {
	
	private int radius;
	private Vektor position;
	private int effectNr;
	private int lifetime;
	
	public Hitbox(Vektor position, int radius,  int effectNr, int lifetime){
		this.position = position;
		this.radius = radius;
		this.effectNr = effectNr;
		this.lifetime = lifetime;
	}
	
	public int collisionEffect(Vektor target){
		if(target.connectingTo(position).length() < radius){
			return effectNr;
		}
		else{
			return -1;
		}
	}
	
	public int getAndProgLifetime(){
		if(lifetime == -1){
			return 1;
		}
		else{
			return lifetime --;
		}
	}
	
}
