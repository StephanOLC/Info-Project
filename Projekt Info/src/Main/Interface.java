package Main;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import Objects.object;

public class Interface {
	
	int height = 480;
	int width = 640;
	float movex,movey;
	List<object> objects = new ArrayList<object>();
	List<object> insert = new ArrayList<object>();
	object back;
	public boolean run;
	boolean closerequested;
	
	public Interface(){
		
		run = true;
		closerequested = false;
		init();
		
	}
	
	public void init(){
		
		try {
			
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Project: Osiris");
			
			try {
				
				Display.setIcon(new ByteBuffer[] {
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Graphics/icon.png")), false, false, null),
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Graphics/icon.png")), false, false, null)
				        });
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
			Display.create();
			
		} catch (LWJGLException e) {
		
			e.printStackTrace();
			
		}
		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		new GameController(this);
		
		
	}
	
	public void run() {
		
		while(!Display.isCloseRequested() && !closerequested){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			getmovement();
			moveCamera();
			draw();
			
			Display.update();
			Display.sync(60);
		}
		
		close();
	}
	
	private void close(){
		
		run = false;
		Display.destroy();
		System.exit(1);
		
	}
	
	public void requestclose(){
		
		closerequested = true;
		
	}
	
	public int getwidth(){
		
		return width;
		
	}
	
	public int getheight(){
		
		return height;
		
	}
	
	
	public void moveCamera(){
		
		glTranslatef(movex, movey, 0);
		
		
	}
	
	public void getmovement(){
		
		if(Mouse.isButtonDown(0)){
			
			movex = Mouse.getDX();
			movey = -Mouse.getDY();
			
		}else{
			
			movex *= 0.25f;
			movey *= 0.35f;
			
			if(movex < 0.7){
				
				movex = 0;
				
			}
			
			if(movey < 0.7){
				
				movey = 0;
				
			}
			
		}
		
	}
	
	
	public void setmovement(float x ,float y){
		
		movex = x;
		movey = y;
		
	}
	
	public void draw(){
		
		if(back != null){
			
			back.draw();
		}
		
		objects.sort(new Sorter());
		
		for(object obj : objects){
			
			obj.draw();
			
		}
		for(object obj : insert){
			
			objects.add(obj);
			
		}
		
		insert = new ArrayList<object>();
		
	}

	public void addobject(object obj){
		
		insert.add(obj);
		
	}

	public void setbackground(object obj){
		
		back = obj;
		
	}

	public static class Sorter implements Comparator<object>{
	
		@Override
		public int compare(object obj1, object obj2) {
			
			return obj1.gety()- obj2.gety();
			
		}
		
	}
}
