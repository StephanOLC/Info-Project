package gameLogic;

public interface IngameObject {
	public char getTeam();
	public Vektor getPosition();
	public void draw();
	public void tick();
}
