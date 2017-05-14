package Objects;

import org.newdawn.slick.opengl.Texture;

import Interfaces.object;
import Main.Interface;
import static org.lwjgl.opengl.GL11.*;

public class TextureObject implements object{
	
	int x,y,width,height;
	String path, fileformat,name;
	Texture texture;

	public TextureObject(int x, int y ,String path,String fileformat, String name,Interface inter ){
		
		this.x = x;
		this.y = y;
		this.path = path;
		this.fileformat = fileformat;
		this.name = name;
		texture = new getTexture().gettexture(fileformat, path);
		width = texture.getImageWidth();
		height = texture.getImageHeight();
		
		inter.addobject(this);
		
	}
	
	public TextureObject(int x, int y ,int width,int height,String path,String fileformat,String name, Interface inter ){
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.path = path;
		this.fileformat = fileformat;
		this.name = name;
		texture = new getTexture().gettexture(fileformat, path);
		
		inter.addobject(this);
		
	}
	
	@Override
	public void draw() {
		
		texture.bind();
		glColor3f(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(x, y);
			glTexCoord2f(1,0);
			glVertex2i(x + width, y);
			glTexCoord2f(1,1);
			glVertex2i(x + width, y + height);
			glTexCoord2f(0,1);
			glVertex2i(x, y + height);
		glEnd();	
		
	}

	@Override
	public int gety() {
		
		return y;
		
	}

	@Override
	public void run() {
		
	}

	@Override
	public String getName() {
		
		return name;
		
	}

}
