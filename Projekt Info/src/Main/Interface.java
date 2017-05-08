package Main;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import Objects.object;

public class Interface {
	int height = 480;//Höhe des Fensters
	int width = 640;//Weite des Fensters
	List<object> objects = new ArrayList<object>();
	List<object> insert = new ArrayList<object>();
	object back;
	
	public Interface(){
		init();
	}
	
	public void init(){
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Sandbox Test des Todes");
			try {
				Display.setIcon(new ByteBuffer[] {
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Graphics/icon.png")), false, false, null),
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Graphics/icon.png")), false, false, null)
				        });
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		new GameController(this);
		
		
	}
	
	public void run() {
		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT);
			
			draw();
			
			Display.update();
			Display.sync(60);
		}
		
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
