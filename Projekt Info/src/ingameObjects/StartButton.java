package ingameObjects;

import Main.GameController;
import Main.Interface;
import graphicObjects.ClickableObject;

public class StartButton extends ClickableObject {

	public StartButton(String name, Interface inter, GameController controller){
		
		super(0,0,"Graphics/Buttons/Start-Knopf.png","Png",name,inter,controller);
		setheight(620);
		setwidth(1050);
	}
	
}
