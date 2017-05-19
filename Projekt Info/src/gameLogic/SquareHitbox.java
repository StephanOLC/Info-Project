package gameLogic;

public class SquareHitbox implements Hitbox {
	
	private Vektor position;
	private int height;
	private int width;
	private int effectNr;
	private int lifetime;
	
	public SquareHitbox(Vektor position, int width, int height, int effectNr, int lifetime){
		this.position = position;
		this.width = width;
		this.height = height;
		this.effectNr = effectNr;
		this.lifetime = lifetime;
	}

	public int collisionEffect(Vektor target) {
		if(position.getX() < target.getX() && target.getX() < (position.getX()+width) && position.getY() < target.getY() && target.getY() < (position.getY()+height)){
			return effectNr;
		}
		else{
			return -1;
		}
	}

	public int getAndProgLifetime() {
		if(lifetime == -1){
			return 1;
		}
		else{
			return lifetime --;
		}
	}

}
