package Objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class getTexture {
	
	public Texture gettexturepng(String path){
		
		return gettexture("PNG", path);
		
	}
	
	public Texture gettexture(String fileformat, String path){
		
		try {
			
			return TextureLoader.getTexture(fileformat, new FileInputStream(new File(path)));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}
	
}
