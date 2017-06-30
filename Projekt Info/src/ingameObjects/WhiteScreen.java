package ingameObjects;

import Main.Interface;
import graphicObjects.TextureObject;

public class WhiteScreen extends TextureObject {

	public WhiteScreen(int x, int y, int width, int height, String Name, Interface inter){
		
		super(x, y, width, height, 0f, "Graphics/Buttons/White.png", Name, inter);
		
	}
	
}
