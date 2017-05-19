package Interfaces;

import gameLogic.Vektor;

public interface IngameObject {
	public char getTeam();
	public Vektor getPosition();
	public void draw();
	public void tick();
}
