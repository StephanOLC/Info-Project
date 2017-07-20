package graphicObjects;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;

import org.lwjgl.openal.AL10;

import Interfaces.Drawableobject;
import Main.Interface;

public class TextureObject implements Drawableobject {

	protected int x,y,width,height, textureID, soundSourceID;
	protected float rotation;
	protected String name;
	
	
	public TextureObject(int x, int y ,String path, String name,Interface inter ){
		
		this.x = x;
		this.y = y;
		this.name = name;
		BufferedImage image = TextureLoader.loadImage(path);
		width = image.getWidth();
		height = image.getHeight();
		textureID = TextureLoader.loadTexture(image);
		rotation = 0;
		initSoundsource();
		
		inter.addDrawableobject(this);
		
	}
	
	public TextureObject (int x, int y,int width, int height, int texture, String name, Interface inter){
		
		this.x = x;
		this.y = y;
		this.name = name;
		textureID = texture;
		rotation = 0;
		this.width = width;
		this.height = height;
		initSoundsource();
		
		inter.addDrawableobject(this);
		
	}
	
	public TextureObject(int x, int y ,int width,int height,float rotation,String path, String name, Interface inter ){
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.rotation = rotation;
		this.name = name;
		BufferedImage image = TextureLoader.loadImage(path);
		width = image.getWidth();
		height = image.getHeight();
		textureID = TextureLoader.loadTexture(image);
		initSoundsource();
		
		inter.addDrawableobject(this);
		
	}
	
	public void initSoundsource(){
		
		soundSourceID = AL10.alGenSources();
		AL10.alSourcef(soundSourceID, AL10.AL_GAIN, 1);
		AL10.alSourcef(soundSourceID, AL10.AL_PITCH, 1);
		AL10.alSource3f(soundSourceID, AL10.AL_POSITION, x, y, 0);
		
	}
	
	public void playSoundeffect(int sound){
		
		AL10.alSourcei(soundSourceID, AL10.AL_BUFFER, sound);
		AL10.alSourcePlay(soundSourceID);
		
	}
	
	public void deleteSoundsource(){
		
		AL10.alDeleteSources(soundSourceID);
		
	}
	
	@Override
	public void draw() {
		
		glBindTexture(GL_TEXTURE_2D, textureID);
		glColor4f(1, 1, 1, 1);
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
		glBindTexture(GL_TEXTURE_2D , 0);
		
	}

	public void setPosition(int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	
	public void setrotation(float rotation){
		
		this.rotation = rotation;
		
	}
	
	protected void setsize(int width, int height){
		
		this.width = width;
		this.height = height;
		
	}
	
	public int newTexture(String path){
		
		BufferedImage image = TextureLoader.loadImage(path);
		return TextureLoader.loadTexture(image);
		
	}
	
	public void setTexture(int texture){
		
		textureID = texture;
		
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
	
	@Override
	public String getName() {
		
		return name;
		
	}

	@Override
	public void run() {
		
	}

}
