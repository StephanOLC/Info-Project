package ingameObjects;

import Main.GameController;
import Main.Interface;
import graphicObjects.ClickableObject;

public class StartButton extends ClickableObject {

	public StartButton(int x, int y,String name, Interface inter, GameController controller){
		
		super(x,y,"Graphics/Start-Knopf.png","Png",name,inter,controller);
		
	}
	
}
