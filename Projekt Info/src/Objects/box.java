package Objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.util.Random;

import Main.Interface;


public class box implements Runnable, object {
	
	public int x,y,height,width;
	public float colorRed, colorBlue,colorGreen;
	
	public box(int x,int y, Interface inter){
		this(x, y, 50, 50,inter);
	}

	public box(int x,int y, int height, int width, Interface inter){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		randomize();
		inter.addobject((object) this);
	}
	
	public void draw(){
		glColor3f(colorRed,colorBlue,colorGreen);
		
		glBegin(GL_QUADS);
			glVertex2i(x,y);
			glVertex2i(x + width, y);
			glVertex2i(x + width, y + height);
			glVertex2i(x,y+height);
		glEnd();	
	}
		
	public int gety(){
		return y;
	}

	@Override
	public void run() {
		
		
	}
	
	public void randomize(){
		Random random = new Random();
		colorRed = random.nextFloat();
		colorBlue = random.nextFloat();
		colorGreen = random.nextFloat();
	}

}
