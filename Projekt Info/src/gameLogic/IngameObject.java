package gameLogic;

import java.util.ArrayList;

public interface IngameObject {
	public void draw();
	public void tick();
	public void collision(ArrayList<Integer> effects);
}
