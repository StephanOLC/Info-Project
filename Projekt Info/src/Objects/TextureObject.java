package Objects;

import org.newdawn.slick.opengl.Texture;

import Interfaces.Drawableobject;
import Main.Interface;
import static org.lwjgl.opengl.GL11.*;

public class TextureObject implements Drawableobject{
	
	int x,y,width,height;
	float rotation;
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
		rotation = 0;
		
		inter.addDrawableobject(this);
		
	}
	
	public TextureObject(int x, int y ,int width,int height,float rotation,String path, String fileformat, String name, Interface inter ){
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.rotation = rotation;
		this.path = path;
		this.fileformat = fileformat;
		this.name = name;
		texture = new getTexture().gettexture(fileformat, path);
		
		inter.addDrawableobject(this);
		
	}
	
	@Override
	public void draw() {
		
		texture.bind();
		glColor3f(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
		glRotatef(rotation, 0, 0, 1);
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(x - 0.5f*width, y-0.5f*height);
			glTexCoord2f(1,0);
			glVertex2f(x + 0.5f*width, y- 0.5f*height);
			glTexCoord2f(1,1);
			glVertex2f(x + 0.5f*width, y + 0.5f*height);
			glTexCoord2f(0,1);
			glVertex2f(x - 0.5f*width, y + 0.5f*height);
		glEnd();	
		glRotatef(-rotation,0,0,1);
		
	}
	
	public void setPosition(int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	
	public void setOutbounds(int width, int height){
		
		this.width = width;
		this.height = height;
		
	}
	
	public void setrotation(float rotation){
		
		this.rotation = rotation;
		
	}
	
	public void newTexture(String path, String fileformat){
		
		texture = new getTexture().gettexture(fileformat, path);
		
	}

	@Override
	public int gety() {
		
		return y;
		
	}
	
	public int getx(){
		
		return x;
		
	}
	
	public int getwidth(){
		
		return width;
		
	}
	
	public int getheight(){
		
		return height;
		
	}
	
	public float getrotation(){
		
		return rotation;
		
	}
	
	public Texture gettexture(){
		
		return texture;
		
	}

	@Override
	public String getName() {
		
		return name;
		
	}

	@Override
	public void run() {
		
	}

}
