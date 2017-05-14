package unusedtestclasses;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import Interfaces.object;
import Main.Interface;


public class box implements object {
	
	public int x,y,height,width;
	public float colorRed, colorBlue,colorGreen;
	String name;
	
	public box(int x,int y,String name, Interface inter){
		
		this(x, y, 50, 50,name,inter);
		
	}

	public box(int x,int y, int height, int width,String name, Interface inter){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		randomize();
		inter.addobject((object) this);
		
	}
	
	public void draw(){
		
		glDisable(GL_TEXTURE_2D);
		
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

	@Override
	public String getName() {
		
		return name;
	}

}
